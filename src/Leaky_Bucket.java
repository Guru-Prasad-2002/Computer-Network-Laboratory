import java.util.Scanner;
public class Leaky_Bucket {
	public static void main(String[] args) {
		// ****************ROC************************* 
		Scanner sc=new Scanner(System.in);
		int buffer,inp_rate,out_rate,overflow,cur_storage=0,req_storage,option=0;
		System.out.println("**LEAKY BUCKET ALGORITHM**");
		System.out.println("Enter the buffer size");
		buffer=sc.nextInt();
		System.out.println("Enter the constanct output rate");
		out_rate=sc.nextInt();
		do {
			System.out.println("Enter the variable input rate");
			inp_rate=sc.nextInt();
			if(buffer<cur_storage+inp_rate) {
				req_storage=cur_storage+inp_rate;
				overflow=req_storage-buffer;
				cur_storage=buffer-out_rate;
			}else {
				req_storage=cur_storage+inp_rate;
				overflow=0;
				cur_storage=cur_storage+inp_rate-out_rate;
			}
			System.out.println("INP_RATE="+inp_rate+"\nCONST_OUT_RATE="+out_rate+"\nBUFFER_SIZE="+buffer+
								"\nCUR_STORAGE="+cur_storage+"\nREQ_STORAGE="+req_storage+"\nOVERFLOW="
								+overflow);
			System.out.println("Do you want to continue?(1/0)");
			option=sc.nextInt();
		}while(option!=0);
		sc.close();
	}

}
