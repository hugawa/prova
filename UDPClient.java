package udpdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.util.Scanner;

public class UDPClient {
	
	private DatagramSocket datagramSocket;
	private InetAddress inetAddress;
	private byte[] buffer;
	
	public UDPClient(DatagramSocket datagramSocket, InetAddress inetAddress) {
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
	}
	
	
	//MÉTODO PARA  
	public void enviarReceber() {
		try (Scanner scanner = new Scanner(System.in)) {
			while(true) {
				try {
					String mensagemEnvio = scanner.nextLine();
					buffer = mensagemEnvio.getBytes();
					DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, 9876);
					datagramSocket.send(datagramPacket);
					datagramSocket.receive(datagramPacket);
				
					String mensagemdoServidor = new String(datagramPacket.getData(), 0,datagramPacket.getLength());
					System.out.println("O servidor retornou: " + mensagemdoServidor);
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
		
	
		}
	
	public static void main(String[] args) throws UnknownHostException, SocketException {
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress inetAddress = InetAddress.getByName("localhost");
		UDPClient UDPClient = new UDPClient(datagramSocket, inetAddress);
		System.out.println("Enviar mensagem para o servidor");
		UDPClient.enviarReceber();
	
	}
	
	
}
