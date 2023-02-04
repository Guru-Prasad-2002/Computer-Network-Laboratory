import java.io.*;
import java.net.*;
public class UDP_Server {
	public static void main(String[] args) throws Exception {
		int sport=3000,dport=9000;
		InetAddress ia=InetAddress.getLocalHost();
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket ds=new DatagramSocket(sport);
		byte[] b=new byte[1024];
		DatagramPacket dp=new DatagramPacket(b,b.length);
		Thread th=new Thread("BackgroundThread") {
			public void run() {
				super.run();
				while(true) {
					try {
						ds.receive(dp);
					}catch(IOException e) {
						System.out.println("IOException");
						e.printStackTrace();
						System.exit(0);
					}
					String str=new String(dp.getData(),0,dp.getLength());
					System.out.println("Client Said:"+str);
					if(str.equalsIgnoreCase("Exit")) {
						System.out.println("Terminating.......");
						ds.close();
						System.exit(0);
					}
				}
			}
		};
		System.out.println("Server is Running...");
		th.start();
		while(true) {
			System.out.println();
			System.out.print("Enter a message for client:");
			String st=br.readLine();
			byte[] buff=st.getBytes();
			ds.send(new DatagramPacket(buff,buff.length,ia,dport));
			if(st.equalsIgnoreCase("Exit")) {
				System.out.println("Terminating.......");
				ds.close();
				System.exit(0);
			}
		}
	}
}
