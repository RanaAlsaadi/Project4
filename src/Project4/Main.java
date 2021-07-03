package Project4;

public class Main{

    public static void main(String []args){
    	
    	// same array sizes and divisions will be preserved for new code version.
    	// tasks
    	// - improve time complexity if possible
    	// - add delay tolerance
    	// - clarify naming of functions
    	// - minimize # inputs
    	// - generate data output
 
       //int a[] = generateArray(5);
    	//int a[] = {1,2,3,4,5};
    	int a[] = {5,9,3,2,8,4,6,11};
    	//int a[] = {5,9,3,2,8};
    	//partitionAndEvaluate(a,2);
    	partitionAndEvaluate(a,3);
       //a = generateArray(50);
       //partitionAndEvaluate(a,15);
//       a = generateArray(100);
//       partitionAndEvaluate(a,30);
//       a = generateArray(500);
//       partitionAndEvaluate(a,2);
//       a = generateArray(500);
//       partitionAndEvaluate(a,100);
    }
    public static void partitionAndEvaluate(int arr[],int k){
        LinearPartition LP_object = new LinearPartition();
        int num_executions = LP_object.evaluateLinearPartition(k,arr);
        System.out.println("Number of num_executions for "+ arr.length +" elements with "+k+" partitions : "+ num_executions);
        System.out.println(num_executions);
    }
    public static int[] generateArray(int len){
        int arr[] = new int[len];
        for(int i = 0 ; i<len ; i++){
            arr[i] = (int)(Math.random()*len+1);
        }
        return arr;
    }
}


