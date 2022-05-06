package udpdemo;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.io.IOException;

public class UDPServer {
	private DatagramSocket datagramSocket;
	private byte[] buffer  = new byte[1024];
	
	public UDPServer(DatagramSocket datagramSocket) {
		this.datagramSocket = datagramSocket;
	}
	
	//M�TODO PARA REENVIO DA MENSAGEM AP�S O SERVIDOR RECEBER
	
	public void receberEnviar() {
		while(true) {
			try {
				DatagramPacket datagramPacket = new DatagramPacket(buffer,buffer.length);
				datagramSocket.receive(datagramPacket);
				InetAddress inetAddress = datagramPacket.getAddress();
				int port = datagramPacket.getPort();
				String mensagemdoClient = new String(datagramPacket.getData(),0,datagramPacket.getLength());
				System.out.println("Mensagem do client:" + mensagemdoClient);
				datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
				datagramSocket.send(datagramPacket);
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}
	public static void main(String[] args) throws SocketException {
		DatagramSocket datagramSocket = new DatagramSocket(9876);
		UDPServer server = new UDPServer(datagramSocket);
		server.receberEnviar();
	}
}
