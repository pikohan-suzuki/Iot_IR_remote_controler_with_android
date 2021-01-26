package com.amebaownd.pikohan_niwatori.irremotecontroler.ui.main

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amebaownd.pikohan_niwatori.irremotecontroler.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.InetAddress
import com.amebaownd.pikohan_niwatori.irremotecontroler.data.Constants
import java.net.DatagramPacket
import java.net.DatagramSocket

class TvFragmentViewModel(application:Application,repository:Repository):AndroidViewModel(application) {

    private val ip = InetAddress.getByName(Constants.IP_ADDRESS)
    private lateinit var socket:DatagramSocket
    fun start() {
        socket = DatagramSocket(Constants.UDP_PORT)
    }

    fun onButtonClicked(view: View){
        val massage = when(view.id){
            R.id.power_button -> "power"
            R.id.program_info_button -> "program_info"
            R.id.screen_display_button -> "screen_display"
            R.id.digital_button -> "digital"
            R.id.bs_button -> "bs"
            R.id.cs_button -> "cs"
            R.id.channel1_button -> "1"
            R.id.channel2_button -> "2"
            R.id.channel3_button -> "3"
            R.id.channel4_button -> "4"
            R.id.channel5_button -> "5"
            R.id.channel6_button -> "6"
            R.id.channel7_button -> "7"
            R.id.channel8_button -> "8"
            R.id.channel9_button -> "9"
            R.id.channel10_button -> "10"
            R.id.channel11_button -> "11"
            R.id.channel12_button -> "12"
            R.id.change_sound_button -> "change_sound"
            R.id.silent_button -> "silent"
            R.id.volume_minus_button -> "sound_minus"
            R.id.volume_plus_button -> "sound_plus"
            R.id.channel_next_button -> "channel_next"
            R.id.channel_prev_button -> "channel_previous"
            R.id.data_button -> "data"
            R.id.change_input_button -> "change_input"
            R.id.program_table_button -> "tv_programs"
            R.id.record_list_button -> "record_list"
            R.id.home_button -> "home"
            R.id.end_button -> "end"
            R.id.tool_button -> "tools"
            R.id.previous_button -> "previous"
            R.id.up_button -> "up"
            R.id.down_button -> "down"
            R.id.left_button -> "left"
            R.id.right_button -> "right"
            R.id.confirm_button -> "enter"
            R.id.blue_button -> "blue"
            R.id.red_button -> "red"
            R.id.yellow_button -> "yellow"
            R.id.green_button -> "green"
            R.id.record_button -> "start_record"
            R.id.counter_program_button -> "counterprogram"
            R.id.familink_button -> "familink"
            R.id.fast_rewind_button -> "rewind"
            R.id.play_button -> "play"
            R.id.fast_forward_button -> "fast_forward"
            R.id.rewind_10sec_button -> "rewind_10sec"
            R.id.stop_once_button -> "stop_once"
            R.id.stop_button -> "stop"
            R.id.forward_30sec_button -> "forward_30sec"
            else->
                return
        }

        viewModelScope.launch(Dispatchers.Default){
            val buffer = massage.toByteArray()
            val packet = DatagramPacket(buffer,buffer.size,ip,Constants.UDP_PORT)
            socket.send(packet)
            Log.d("send msg", massage)
        }
    }

    fun onDestroy(){
        socket.close()
    }
}