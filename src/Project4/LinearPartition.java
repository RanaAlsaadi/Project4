package Project4;

import java.util.Arrays;

public class LinearPartition{
	   public int evaluateLinearPartition(int k,int ar[]){

		   int indices_skipped = 0;
		   int executions = 0;
		   int n = ar.length;
		   
		   System.out.println("source_array: " );
	       System.out.println(Arrays.toString(ar) );
		   
	       // k is number of partitions
	       
	       // ar is the input array that will be partitioned
	       
	       //optimal_weights array:
	       // 1st dimension is ( # partitions considered - 1 )
	       // 2nd dimension is ( array length considered - 1 )
	       int  optimal_weights[][] = new int[k][n];
	       
	       //sum_from_last array:
	       // sum_from_last[i] is the sum from ar[i] to ar[ar.length]
	       int[] sum_from_last = new int[n];
	       
	       //calculate the weights of all single partitions
	       optimal_weights[0][0] = ar[0];
	       for(int array_length_index = 1 ; array_length_index < n ; array_length_index++)
	       {
	    	   optimal_weights[0][array_length_index] = ar[array_length_index] + optimal_weights[0][array_length_index-1];
	       }
	       
	       System.out.println("optimal_weights after single partitions filled: " );
	       System.out.println(Arrays.deepToString(optimal_weights));
	       
	       //save sum of weights from each index to the last index in ar in sum_from_last
	       sum_from_last[n - 1] = ar[n-1];
	       for(int current_index = n - 2 ; current_index >= 0  ; current_index--)
	       {
	    	   sum_from_last[current_index] = ar[current_index] + sum_from_last[current_index + 1];
	       }
	       
	       System.out.println("sum_from_last: " );
	       System.out.println(Arrays.toString(sum_from_last));
	       
	       //from partitions 2 to k, fill the rest of the optimal_weights array
	       for(int num_partition_index = 1 ; num_partition_index < k ; num_partition_index++)
	       {
	    	   int left_window_start_index_bound_reduction = -1;
	    	   for(int array_length_index = num_partition_index ; array_length_index < n ; array_length_index ++)
	    	   {
	    		   int current_min = Integer.MAX_VALUE;
	    		   
	    		   System.out.println("num_partition_index: " + num_partition_index + " array_length_index: " + array_length_index + " sfl[array_length_index]: " + sum_from_last[array_length_index] + " ow[num_partition_index-1][array_length_index-1]: " + optimal_weights[num_partition_index-1][array_length_index-1]);
	    		   
	               for(int right_window_start_index = array_length_index ; right_window_start_index >= num_partition_index ; right_window_start_index--)
	               {
	            	   
	            	   int left_window_index = right_window_start_index -1;
	            	   
	            	   
	            	   if(left_window_start_index_bound_reduction > left_window_index)
	            	   {
	            		   System.out.println("xxxx   xxxxx   xxxxx  skipping index where left window ends at: " + left_window_index + " xxxx xxxx  xxx");
	            		   indices_skipped++;
	            	   }
	            	   else
	            	   {
	            		   executions++;
	            		   
	            		   int right_window_end_index_offset = array_length_index +1;
		            	   int right_window_offset = 0;
		            	   if(right_window_end_index_offset < n)
		            	   {
		            		   right_window_offset = sum_from_last[ right_window_end_index_offset ];
		            	   }

		            	   int left_window_value = optimal_weights[num_partition_index-1][left_window_index];
		            	   int right_window_value = sum_from_last[ right_window_start_index ] - right_window_offset;
		            	   
		            	   int current_max = Math.max(left_window_value, right_window_value);
		            	   
		            	   if(current_min >= current_max)
		            	   {
		            		   current_min = current_max;

		            		   if(left_window_value < right_window_value)
		            		   {
		            			   left_window_start_index_bound_reduction = left_window_index;
			            		   System.out.println("new min is " + current_min +  "." + " now skipping indices less than " + left_window_start_index_bound_reduction );
		            		   }
		            		   else
		            		   {
		            			   System.out.println("new min is " + current_min +  ".");
		            		   }
		            		   
		            	   }
		            	   System.out.println("left window 0 to " + left_window_index  + " right window " + right_window_start_index + " to " + array_length_index + " comparison: " + left_window_value + " vs " + right_window_value);
		            	   
		            	   //now assign min to the appropriate location
		            	   optimal_weights[num_partition_index][array_length_index] = current_min;
	            	   }
                   }
	    	   }
	       }
	       
	       System.out.println("executions: " + executions + " indices_skipped: " + indices_skipped );
	       
	       System.out.println("final optimal_weights" );
	       System.out.println(Arrays.deepToString(optimal_weights));
	       
	       return optimal_weights[k-1][n-1];
	    }
	}
