package com.example.src.ForTest;

import java.util.ArrayList;
import java.util.List;


public class LIS {//That's "longest increasing subsequence"
	 
	 static int bSearch(List<Integer> b, int Key)
	 {
	     int low=-1, high=b.size()-1;
	     int guess;

	     while (high - low > 1) {
	            guess = ((high - low)>>1)  + low;

	            if(Key > b.get(guess))
				{
	                low = guess;
	            }
	            else
	            {
	                high = guess;
	            }
	        }

	     return high;
	 }

	 public static void main(String[] args) {
		  int p[]=new int[]{1,7,3,5,8,9,7,10,5,9,18,6,56,34,40,41,42,43,44,45,78,11,23,4,6,28,19,
				  29,32,30,15,32,33,19,35,36,37,38,39,100};
		  List<Integer> b = new ArrayList<>();//This list save the least value of LIS
		 	
		  List<ArrayList<Integer>> LIS = new ArrayList<>();
		 
		  
		 	b.add(p[0]);
		 	LIS.add(new ArrayList<>());
		 	LIS.get(0).add(p[0]);

		 	for(int i=0; i<p.length; i++)
		 	{
				if(p[i] > b.get(b.size()-1))//p[i] is the present LIS
				{
					b.add(p[i]);
					LIS.add(new ArrayList<>());
					LIS.get(LIS.size()-1).add(p[i]);
				}
				else
				{
					int position = bSearch(b, p[i]);

					b.set(position, p[i]);

					if(position == b.size()-1){//cover it
						LIS.get(position).set(0, p[i]);
					}
					else if(p[i] != LIS.get(position).get(LIS.get(position).size()-1)){//put the value to Alternative LIS queue
						LIS.get(position).add(p[i]);
					}
				}
			}

		 for(int i =LIS.size()-1; i>=0; i--){
			 if(LIS.get(i).size() > 1){//list LIS has a lot of sub list, Need choose the best one
				 int k=0;
				 while(LIS.get(i).get(k) >= LIS.get(i+1).get(0)){//select the best one
					 k++;
				 }

				 LIS.get(i).set(0, LIS.get(i).get(k));

				 while(LIS.get(i).size()>1){//clean invalid value
					 LIS.get(i).remove(LIS.get(i).size()-1);
				 }
			 }
		 }
		 System.out.println(b.size());	
		 System.out.println(LIS);
	 }
}
