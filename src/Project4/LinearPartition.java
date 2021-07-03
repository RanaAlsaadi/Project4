package Project4;

import java.util.Arrays;
import java.util.Calendar;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;

public class LinearPartition{
		public static int[] generateArray(int len){
	        int arr[] = new int[len];
	        for(int i = 0 ; i<len ; i++){
	            arr[i] = (int)(Math.random()*len+1);
	        }
	        return arr;
	    }
	
		public static void partitionAndEvaluate(int arr[],int k, int is_verbose, int csv_output_enabled){

	        LinearPartitionResult current_result = LinearPartition.evaluateLinearPartition(k,arr, is_verbose);

	        if(is_verbose != 0)
	        {
	        	System.out.println(arr.length + ","  + current_result.number_of_partitions + "," + current_result.optimal_weight + "," + current_result.executions + "," + current_result.indices_skipped);
	        }
	        
	        if(csv_output_enabled != 0)
	        {
	        	Date today = Calendar.getInstance().getTime();
	        	DateFormat date_string_format = new SimpleDateFormat("yyyy-MM-dd");
	        	
	        	String output_filename = "output_" + date_string_format.format(today) + ".csv";
	        	
	        	if(is_verbose != 0)
		        {
	        		System.out.println(output_filename);
		        }
	        	
	        	try (PrintWriter fw = new PrintWriter(new FileWriter(output_filename,true)))
	        	{
	        		try (BufferedWriter bw = new BufferedWriter(fw))
		        	{
	        		
	        		bw.write(arr.length + ","  + current_result.number_of_partitions + "," + current_result.optimal_weight + "," + current_result.executions + "," + current_result.indices_skipped);
	        		bw.newLine();
	        		bw.close();
		        	}
	        		catch(IOException e)
		        	{
		        		System.out.println(e.getMessage());
		        	}
	        	} catch(FileNotFoundException e)
	        	{
	        		System.out.println(e.getMessage());
	        	}catch(IOException e)
	        	{
	        		System.out.println(e.getMessage());
	        	}
	        }
	        
	    }
	    
	   public static LinearPartitionResult evaluateLinearPartition(int k,int ar[], int is_verbose){

		   int indices_skipped = 0;
		   int executions = 0;
		   int n = ar.length;
		   
		   if(is_verbose !=0)
		   {
			   System.out.println("source_array: " );
		       System.out.println(Arrays.toString(ar) );
		   }
		   
		   if(k > ar.length)
		   {
			   if(is_verbose !=0)
			   {
				   System.out.println("Input error." );
			   }
			   return new LinearPartitionResult();
		   }
		   
		   
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
	       
	       if(is_verbose !=0)
		   {
	    	   System.out.println("optimal_weights after single partitions filled: " );
		       System.out.println(Arrays.deepToString(optimal_weights));
		   }
	       
	       //save sum of weights from each index to the last index in ar in sum_from_last
	       sum_from_last[n - 1] = ar[n-1];
	       for(int current_index = n - 2 ; current_index >= 0  ; current_index--)
	       {
	    	   sum_from_last[current_index] = ar[current_index] + sum_from_last[current_index + 1];
	       }
	       
	       if(is_verbose !=0)
		   {
	    	   System.out.println("sum_from_last: " );
		       System.out.println(Arrays.toString(sum_from_last));
		   }
	       
	       
	       //from partitions 2 to k, fill the rest of the optimal_weights array
	       for(int num_partition_index = 1 ; num_partition_index < k ; num_partition_index++)
	       {
	    	   // initialize the index lower bound, which will constrain the main inner loop
	    	   int left_window_start_index_bound_reduction = -1;
	    	   for(int array_length_index = num_partition_index ; array_length_index < n ; array_length_index ++)
	    	   {
	    		   int current_min = Integer.MAX_VALUE;
	    		   
	    		   if(is_verbose !=0)
	    		   {
	    			   System.out.println("num_partition_index: " + num_partition_index + " array_length_index: " + array_length_index + " sfl[array_length_index]: " + sum_from_last[array_length_index] + " ow[num_partition_index-1][array_length_index-1]: " + optimal_weights[num_partition_index-1][array_length_index-1]);
		    		   
	    		   }
	    		   
	    		   //main inner loop
	    		   //for indices within range, calculate the min and increment executions. Otherwise, increment indices_skipped.
	               for(int right_window_start_index = array_length_index ; right_window_start_index >= num_partition_index ; right_window_start_index--)
	               {
	            	   
	            	   int left_window_index = right_window_start_index -1;
	            	   
	            	   
	            	   if(left_window_start_index_bound_reduction > left_window_index)
	            	   {
	            		   if(is_verbose !=0)
	            		   {
	            			   System.out.println("skipping index where left window ends at: " + left_window_index);
	            		   }
	            		   
	            		   indices_skipped++;
	            	   }
	            	   else
	            	   {
	            		   //index is within range!
	            		   
	            		   
	            		   executions++;
	            		   
	            		   // sums are pre-calculated, so they can be summarized as a subtraction from one field to another
	            		   
	            		   int right_window_end_index_offset = array_length_index +1; // right window offset index calculated
	            		   
	            		   //find offset from right window offset index
		            	   int right_window_offset = 0;
		            	   if(right_window_end_index_offset < n)
		            	   {
		            		   right_window_offset = sum_from_last[ right_window_end_index_offset ]; 
		            	   }

		            	   //find values that will be compared at the current boundary
		            	   int left_window_value = optimal_weights[num_partition_index-1][left_window_index]; // left value is stored in optimal_weights
		            	   int right_window_value = sum_from_last[ right_window_start_index ] - right_window_offset; // right value is calculated as a subtraction from one field to another.
		            	   
		            	   //calculate the max weight using this boundary
		            	   int current_max = Math.max(left_window_value, right_window_value);
		            	   
		            	   
		            	   if(current_min >= current_max)
		            	   {
		            		   current_min = current_max; //updating min value

		            		   if(left_window_value < right_window_value)
		            		   {
		            			   //if the weight is less than min and the left value is less than the right value, then it will not get used again using this number of partitions.
		            			   //the right value will always be greater with greater array lengths considered, since the right value grows and the left value stays the same.
		            			   
		            			   left_window_start_index_bound_reduction = left_window_index; //bound reduction is updated.
		            			   if(is_verbose !=0)
		            			   {
		            				   System.out.println("new min is " + current_min +  "." + " now skipping indices less than " + left_window_start_index_bound_reduction );
		            			   }
			            		   
		            		   }
		            		   else
		            		   {
		            			   //the min was the right value, so there is still a chance that the left value will be used. Bound reduction stays the same.
		            			   if(is_verbose !=0)
		            			   {
		            				   System.out.println("new min is " + current_min +  ".");
		            			   }
		            			   
		            		   }
		            		   
		            	   }
		            	   
		            	   if(is_verbose !=0)
		        		   {
		            		   System.out.println("left window 0 to " + left_window_index  + " right window " + right_window_start_index + " to " + array_length_index + " comparison: " + left_window_value + " vs " + right_window_value);
			            	   
		        		   }
		            	   
		            	   //now assign min to the appropriate location
		            	   optimal_weights[num_partition_index][array_length_index] = current_min;
	            	   }
                   }
	    	   }
	       }
	       
	       if(is_verbose !=0)
		   {
	    	   System.out.println("executions: " + executions + " indices_skipped: " + indices_skipped );
		       
		       System.out.println("final optimal_weights" );
		       System.out.println(Arrays.deepToString(optimal_weights));
		   }
	       
	       //optimal weight is at optimal_weights[k-1][n-1]
	       LinearPartitionResult output = new LinearPartitionResult();
	       output.source_array = ar;
	       output.optimal_weights = optimal_weights;
	       output.optimal_weight = optimal_weights[k-1][n-1];
	       output.number_of_partitions = k;
	       output.indices_skipped = indices_skipped;
	       output.executions = executions;
	       return output;
	    }
	}
