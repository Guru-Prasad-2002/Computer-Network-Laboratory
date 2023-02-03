//Single source shortes path algorithm 
//works for only directed graph
//Undirected graph can be converted to directed graph by adding two edges between vertices with same weight
//will help us detect negative cycles
//Relax all the edges N-1 times sequentially,edges can be processed in any order but must maintain same order
// for all iterations, Here N is the no of Nodes
import java.util.*;
public class Bellman_Ford {

	public static void main(String[] args) {
		int n;
		int[][] g;
		int src;
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the no of edges");
		n=sc.nextInt();
		g=new int[n+1][n+1];
		System.out.println("Enter the adjacency matrix:(Enter 999 if no direct edges exists between 2 vertice");
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				g[i][j]=sc.nextInt();
			}
		}
		System.out.println("Enter the source vertex ("+ 0 + "," + (n-1)+ ")");
		src=sc.nextInt();
		calc_dist(n,g,src);
		sc.close();
	}
	public static void calc_dist(int n,int[][] g,int src) {
		int[] d =new int[n];
		for(int i=0;i<n;i++) {
			d[i]=999;
		}
		d[src]=0;
		for(int i=0;i<n-1;i++) {
			for(int j=0;j<n;j++) {
				for(int k=0;k<n;k++) {
					if(d[k]>d[j]+g[j][k]) {
						d[k]=d[j]+g[j][k];
					}
				}
			}
		}
		for(int j=0;j<n;j++) {
			for(int k=0;k<n;k++) {
				if(g[j][k]!=999 && d[k]>d[j]+g[j][k]) {
					System.out.println("Negative Edge Exists!Cant Apply Bellman Ford Algorithm");
					return;
				}
			}
		}
		System.out.println("The distace between Source vertex and every other vertices is as follows");
		for(int i=0;i<n;i++) {
			System.out.println(src+"->"+i+"="+d[i]);
		}
		return;
	}
}
