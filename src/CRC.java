//CRC_CCIT 16 Bits
// X^16+ X^12 + X^5 +1 polynomial
 
import java.util.Scanner;
public class CRC {
	public static void div(int[] a,int k) {
		int[] polynomial= {1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};
		int count=0;
		for(int i=0;i<k;i++){
			if(a[i]==1) {
				for(int j=i;j<=16+i;j++) {
					a[j]=a[j]^polynomial[count];
					count=count+1;
				}
				count=0;
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int[] a=new int[100];
		int[] b=new int[100];
		int len,k,flag=1;
		System.out.println("Enter the size of Data Frame");
		len=sc.nextInt();
		System.out.println("Enter the data bits 1 by 1");
		for(int i=0;i<len;i++) {
			a[i]=sc.nextInt();
		}
		for(int i=len;i<len+16;i++) {
			a[i]=0;
		}
		k=len;
		len=len+16;
		System.arraycopy(a, 0, b, 0, len);
		div(a,k);
		for(int i=0;i<len;i++) {
			a[i]=a[i]^b[i];
		}
		System.out.println("This Must be the received data if it does not contain any errors");
		for(int i=0;i<len;i++) {
			System.out.print(a[i]+" ");
		}
		System.out.println("\nEnter the received data");
		for(int i=0;i<len;i++) {
			b[i]=sc.nextInt();
		}
		for(int i=0;i<len;i++){
			if(a[i]!=b[i]) {
				flag=0;
				break;
			}
		}
		if(flag==1) {
			System.out.println("Data is Consistent.No Error");
		}else {
			System.out.println("The Received Data contains Errors");
		}
		sc.close();
	}
}
