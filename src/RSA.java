import java.util.Scanner;
public class RSA {
	public static int gcd(int m,int n) {
		int r;
		while(n!=0) {
			r=m%n;
			m=n;
			n=r;
		}
		return m;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		String message;
		int p,q,n,phi,e=2,d=2;
		int[] nums=new int[100];
		int[] encrypted=new int[100];
		int[] decrypted=new int[100];
		System.out.println("Enter first prime no ");
		p=sc.nextInt();
		System.out.println("Enter second prime no ");
		q=sc.nextInt();
		System.out.println("Enter the message to be encrypted");
		message=sc.next();
		n=p*q;
		phi=(p-1)*(q-1);
		for(int i=2;i<phi;i++) {
			if(gcd(phi,i)==1) {
				e=i;
				break;
			}
		}
		for(int i=2;i<phi;i++) {
			if((e*i)%phi==1) {
				d=i;
				break;
			}
		}		
		for(int i=0;i<message.length();i++) {
			nums[i]=(int)(message.charAt(i))-70;
		}
		for(int i=0;i<message.length();i++) {
			encrypted[i]=1;//Subtract or add any Random value
			for(int j=0;j<e;j++) {
				encrypted[i]=(encrypted[i]*nums[i])%n;
			}
		}
		System.out.println("Encrypted Message is as follows");
		for(int i=0;i<message.length();i++) {
			System.out.print(encrypted[i]+" ");
		}
		System.out.println();
		System.out.println("Encrypted Message in String Format is as follows");
		for(int i=0;i<message.length();i++) {
			System.out.print((char)(encrypted[i]+70));
		}
		for(int i=0;i<message.length();i++) {
			decrypted[i]=1;//Subtract or add any Random value
			for(int j=0;j<d;j++) {
				decrypted[i]=(decrypted[i]*encrypted[i])%n;
			}
		}
		System.out.println();
		System.out.println("Decrypted Message is as follows");
		for(int i=0;i<message.length();i++) {
			System.out.print((char)(decrypted[i]+70));
		}
		System.out.println();
		sc.close();
	}

}
