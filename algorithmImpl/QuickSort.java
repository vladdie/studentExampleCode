package algorithmImpl;


public class QuickSort {
	
	private int[] input = {4,7,2,1,6,8,8,3,2,5,9,0,7};
	//private int[] input = {26,1,22,44,28,49,44,43,13,19,12,50};
	                                                                                                                                                		                		
	public void sort_incr(int begin, int end){
		if(begin == end){
			return;
		}
		
		int pivot = input[begin];
		int swapIndex = begin + 1;
		for(int i = begin + 1; i < end; i++){
			//System.out.print(input[i]+" ");
			if(input[i] < pivot){
				swap(i, swapIndex);
				swapIndex++;
			}
		}
		swap(begin, swapIndex-1);
		
		
		int low = begin;
		int high = swapIndex-1;//pivot index
		
		sort_incr(low, high);
		//System.out.println("finished left partition of: "+pivot+" ");
		
		low = swapIndex;
		high = end;
		sort_incr(low, high);
		//System.out.println("finished right partition of: "+pivot+" ");
		return;
	}
	
	public void sort_decr(int begin, int end){
		if(begin == end){
			return;
		}
		int pivot = input[begin];
		int swapIndex = begin + 1;
		for(int i = begin + 1; i < end; i++){
			if(input[i] > pivot){
				swap(i, swapIndex);
				swapIndex++;
			}
		}
		swap(begin, swapIndex-1);
		
		int low = begin;
		int high = swapIndex-1;//pivot index
		
		sort_decr(low, high);
		
		low = swapIndex;
		high = end;
		sort_decr(low, high);
		return;
	}
	
	
	private void swap(int x, int y){
		int temp = input[x];
		input[x] = input[y];
		input[y] = temp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSort sort = new QuickSort();
		sort.sort_decr(0, sort.input.length);
		System.out.println("*************************************");
		for(int i = 0; i < sort.input.length; i++){
			System.out.print(sort.input[i]+" ");
		}
		System.out.println("\n"+"*************************************");
	}

}
