import socket
import select

def main():

    PORT = 60009
    BUFFER_SIZE = 1024
    GPIO = 17
    FILE = "codes.json"

    sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
  
    try:
      sock.sendto("1".encode('utf-8'),('192.168.10.2',PORT))
    except Exception as e:
        print(e)
    
    sock.close()

if __name__== "__main__":
    main()