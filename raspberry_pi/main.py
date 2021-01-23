import socket
import select
import send_ir

def main():

    PORT = 60009
    BUFFER_SIZE = 1024
    GPIO = 17
    FILE = "codes.json"

    sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
    sock.bind(("0.0.0.0",PORT))
    sock.setblocking(False)

    print("Now accepting signals...")
    try:
        while True:
            try:
                read,_,_ = select.select([sock.fileno()],[],[],0.1)
                if len(read) <= 0 : continue
                data,_ = sock.recvfrom(BUFFER_SIZE)
                decoded = data.decode('utf-8')
                send_ir.send_ir(GPIO,FILE,id=[decoded])
            except Exception as e:
                print(e)
    except KeyboardInterrupt as e:
        sock.close()
        print(e)



if __name__== "__main__":
    main()
