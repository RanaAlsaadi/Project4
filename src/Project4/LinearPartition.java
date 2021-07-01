package Project4;

public class LinearPartition{
	   public int minimumPartitionSum(int n,int k,int ar[]){
	        //Table to store minimum partition sum
	       int  dp[][]=new int[n][k];
	       dp[0][0]=ar[0];

	       //Calculating presum
	       for(int i=1;i<n;i++)
	           dp[i][0]+=dp[i-1][0]+ar[i];
	       //for all k from k=2 
	       for(int i=1;i<k;i++){
	           //check for all j=1 to n
	           for(int j=i;j<n;j++){
	               int min=Integer.MAX_VALUE;
	               int sum=0;
	               //checking for all precalculated sum and finding minimum
	               for(int t=j;t>=i;t--){
	                   sum+=ar[t];
	                   int max=Math.max(sum,dp[t-1][i-1]);
	                   min=Math.min(min,max);
	               }
	               //storing the minimum sum of partitions
	               dp[j][i]=min;
	           }
	       }

	       return dp[n-1][k-1];
	    }
	}
