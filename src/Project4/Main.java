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
    	
    	int is_verbose = 0;
 
    	//sample simple minimum 
    	//int a[] = {5,9,3,2,8,4,6,11};
    	//partitionAndEvaluate(a,3, is_verbose);
       
    	int min_array_size = 100;
    	int max_array_size = 1000;
    	int array_size_increment = 100;
    	
    	int min_num_partitions = 100;
    	int max_num_partitions = 1000;
    	int num_partitions_increment = 100;
    	
    	for(int current_array_size = min_array_size; current_array_size < max_array_size ; current_array_size += array_size_increment)
    	{
    		for(int current_num_partitions = min_num_partitions; current_num_partitions < max_num_partitions ; current_num_partitions += num_partitions_increment)
        	{
    			if(is_verbose != 0)
                {
    				System.out.println("current_array_size: "+ current_array_size + " current_num_partitions: " + current_num_partitions);
                }
    			
    			int a[] = generateArray(current_array_size);
    			partitionAndEvaluate(a,current_num_partitions, 0);
    			
        	}
    	}
    	

    }
    public static void partitionAndEvaluate(int arr[],int k, int is_verbose){
        //LinearPartition LP_object = new LinearPartition();
        //int optimal_weight = LP_object.evaluateLinearPartition(k,arr, is_verbose);
        LinearPartitionResult current_result = LinearPartition.evaluateLinearPartition(k,arr, is_verbose);
        int optimal_weight = current_result.optimal_weight;
        
        if(is_verbose != 0)
        {
        	System.out.println("Optimal weight for "+ arr.length +" elements with "+k+" partitions : "+ optimal_weight);
        }
        else
        {
        	System.out.println(arr.length + ","  + k + "," + optimal_weight);
        }
        
        
    }
    public static int[] generateArray(int len){
        int arr[] = new int[len];
        for(int i = 0 ; i<len ; i++){
            arr[i] = (int)(Math.random()*len+1);
        }
        return arr;
    }
}


