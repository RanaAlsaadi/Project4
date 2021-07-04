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
    	int csv_output_enabled = 1;
 
    	//sample simple minimum 
    	//int a[] = {5,9,3,2,8,4,6,11};
    	//LinearPartition.partitionAndEvaluate(a,3, is_verbose, csv_output_enabled);
       
    	int min_array_size = 100;
    	int max_array_size = 1000;
    	int array_size_increment = 50;
    	
    	int min_num_partitions = 100;
    	int max_num_partitions = 1000;
    	int num_partitions_increment = 50;
    	
    	int random_number_repetitions = 3;
    	
    	int delay_tolerance_size = 2;
    	
    	for(int current_repetition = 0; current_repetition < random_number_repetitions ; current_repetition++)
    	{
	    	for(int current_array_size = min_array_size; current_array_size < max_array_size ; current_array_size += array_size_increment)
	    	{
	    		for(int current_num_partitions = min_num_partitions; current_num_partitions < max_num_partitions ; current_num_partitions += num_partitions_increment)
	        	{
	    			if(current_num_partitions <= current_array_size)
	    			{
	    				if(is_verbose != 0)
	                    {
	        				System.out.println("current_array_size: "+ current_array_size + " current_num_partitions: " + current_num_partitions);
	                    }
	        			
	        			int a[] = LinearPartition.generateArray(current_array_size);
	        			LinearPartition.partitionAndEvaluate(a,current_num_partitions, is_verbose, csv_output_enabled, delay_tolerance_size);
	    			}
	        	}
	    	}
    	}

    }
    
}


