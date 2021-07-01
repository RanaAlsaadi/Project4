package Project4;

public class Main{

    public static void main(String []args){
    	
    	// same array sizes and divisions will be preserved for new code version.
    	// tasks
    	// - improve time complexity if possible
    	// - add delay tolerance
       int a[]=getArray(10);
       printSolution(a,10,3);
       a=getArray(50);
       printSolution(a,50,15);
       a=getArray(100);
       printSolution(a,100,30);
       a=getArray(500);
       printSolution(a,500,60);
       a=getArray(500);
       printSolution(a,500,100);
    }
    public static void printSolution(int arr[],int n,int k){
        LinearPartition obj=new LinearPartition();
        long start = System.nanoTime();
        int ans=obj.minimumPartitionSum(n,k,arr);
        long end = System.nanoTime();
        long duration=end-start;
        System.out.println("Time taken for "+n+" elements with "+k+" partitions : "+duration+"ns");
        System.out.println(ans);
    }
    public static int[] getArray(int len){
        int arr[]=new int[len];
        for(int i=0;i<len;i++){
            arr[i]=(int)(Math.random()*len);
        }
        return arr;
    }
}


