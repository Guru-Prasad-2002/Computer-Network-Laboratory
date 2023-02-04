import java.io.*;
import java.net.*;
public class TCP_Server {
	public static void main(String[] args) throws Exception{
		String curdir=null;
		ServerSocket server=null;
		Socket client=null;
		BufferedReader br=null;
		OutputStreamWriter out=null;
		PrintWriter pw=null;
		BufferedReader fr=null;
		curdir=System.getProperty("user.dir");
		System.out.println(curdir);
		try {
			server=new ServerSocket(6000);
			System.out.println("Server is Running");
			client=server.accept();
			System.out.println("Client Connected Successfully");
		}catch(IOException e){
			e.printStackTrace();
		}
		while(true) {
			try {
				br=new BufferedReader(new InputStreamReader(client.getInputStream()));
				String filename=br.readLine();
				if(filename.equalsIgnoreCase("Exit")) {
					System.out.println("Terminated");
					System.exit(0);
				}
				System.out.println("Filename ="+filename);
				out=new OutputStreamWriter(client.getOutputStream());
				pw=new PrintWriter(out,true);
				fr=new BufferedReader(new FileReader(curdir+"/"+filename));
				String temp;
				while((temp=fr.readLine())!=null) {
					pw.println(temp);
					System.out.println(temp);
				}
				System.out.println("File Contents Sent Successfully");
				break;
			}catch(FileNotFoundException e) {
				System.out.println("No Such File Exists");
				pw.println("No File");
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
