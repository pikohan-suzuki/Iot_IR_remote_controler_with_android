#!/usr/bin/env python

import time
import json
import os
import argparse

import pigpio # http://abyz.co.uk/rpi/pigpio/python.html

def carrier(gpio, frequency, micros):
   """
   Generate carrier square wave.
   """
   wf = []
   cycle = 1000.0 / frequency
   cycles = int(round(micros/cycle))
   on = int(round(cycle / 2.0))
   sofar = 0
   for c in range(cycles):
      target = int(round((c+1)*cycle))
      sofar += on
      off = target - sofar
      sofar += off
      wf.append(pigpio.pulse(1<<gpio, 0, on))
      wf.append(pigpio.pulse(0, 1<<gpio, off))
   return wf


def send_ir(gpio,file,id,freq=38.0,gap=100,glitch=100,post=15,pre=200,short=10,tolerance=15,verbose=True,):

    GPIO       = gpio
    GLITCH     = glitch
    FILE       = file
    PRE_MS     = pre
    POST_MS    = post
    FREQ       = freq
    VERBOSE    = verbose
    SHORT      = short
    GAP_MS     = gap
    TOLERANCE  = tolerance

    POST_US    = POST_MS * 1000
    PRE_US     = PRE_MS  * 1000
    GAP_S      = GAP_MS  / 1000.0
    TOLER_MIN =  (100 - TOLERANCE) / 100.0
    TOLER_MAX =  (100 + TOLERANCE) / 100.0

    last_tick = 0
    in_code = False
    code = []
    fetching_code = False

    pi = pigpio.pi() # Connect to Pi.

    if not pi.connected:
        exit(0)

    try:
        f = open(FILE, "r")
    except:
        print("Can't open: {}".format(FILE))
        exit(0)

    records = json.load(f)

    f.close()

    pi.set_mode(GPIO, pigpio.OUTPUT) # IR TX connected to this GPIO.

    pi.wave_add_new()

    emit_time = time.time()

    if VERBOSE:
        print("Playing")

    for arg in id:
        if arg in records:

            code = records[arg]

            # Create wave

            marks_wid = {}
            spaces_wid = {}

            wave = [0]*len(code)

            for i in range(0, len(code)):
                ci = code[i]
                if i & 1: # Space
                    if ci not in spaces_wid:
                        pi.wave_add_generic([pigpio.pulse(0, 0, ci)])
                        spaces_wid[ci] = pi.wave_create()
                    wave[i] = spaces_wid[ci]
                else: # Mark
                    if ci not in marks_wid:
                        wf = carrier(GPIO, FREQ, ci)
                        pi.wave_add_generic(wf)
                        marks_wid[ci] = pi.wave_create()
                    wave[i] = marks_wid[ci]

            delay = emit_time - time.time()

            if delay > 0.0:
                time.sleep(delay)

            pi.wave_chain(wave)

            if VERBOSE:
                print("key " + arg)

            while pi.wave_tx_busy():
                time.sleep(0.002)

            emit_time = time.time() + GAP_S

            for i in marks_wid:
                pi.wave_delete(marks_wid[i])

            marks_wid = {}

            for i in spaces_wid:
                pi.wave_delete(spaces_wid[i])

            spaces_wid = {}
        else:
            print("Id {} not found".format(arg))

    pi.stop() # Disconnect from Pi.

