import java.util.*;
import java.io.*;
import java.net.*;
public class TCP_Client {
	public static void main(String[] args) throws Exception{
		Scanner sc=new Scanner(System.in);
		BufferedReader br=null;
		PrintWriter pw=null;
		OutputStreamWriter op=null;
		FileWriter fw=null;
		Socket client=null;
		String address;
		String serverfilename=null;
		System.out.println("Enter the Server Address");
		address=sc.next();
		try {
			client=new Socket(address,6000);
			System.out.println("Client Successfully connected to Server");
		}catch(IOException e){
			e.printStackTrace();
		}
		while(true) {
			try {
				System.out.println("Enter the Filename");
				serverfilename=sc.next();
				op=new OutputStreamWriter(client.getOutputStream());
				pw=new PrintWriter(op,true);
				if(serverfilename.equalsIgnoreCase("Exit")) {
					System.out.println("Terminated");
					pw.println("Exit");
					System.exit(0);
				}
				pw.println(serverfilename);
				br=new BufferedReader(new InputStreamReader(client.getInputStream()));
				fw=new FileWriter("Client_"+serverfilename);
				fw.write("");
				String temp=br.readLine();
				if(temp.equalsIgnoreCase("No File")) {
					System.out.println("No Such File Exists");
					continue;
				}else {
					fw.append(temp+"\n");
					fw.flush();
				}
				while((temp=br.readLine())!=null) {
					if(temp=="\0") {
						break;
					}else {
						fw.append(temp+"\n");
						fw.flush();
					}
				}
				fw.close();
				System.out.println("Contents Written Successfully");
				break;
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		sc.close();
	}
}
