import CITS2200.Sort;

/**
* A class to give a comparative view of common sorting algorithms.
* The maintains a private static variable that counts the number 
* of array assignments that are performed (as an approximate measure
* of the complexity of the algorithm.
* @author Tim French. 
**/

public class Sorter implements Sort {
   private int count;
   
   /**
	* Returns the number of array assignment operations 
	* performed by this class since the count variable was rest.
	* @return the number of assignments
	**/
	public int getCount(){
		return count;
	}	
		
	/**
	*Resets the counter variable to 0
	**/
	public void reset(){
		count = 0;
	}
	
	/**
	* Executes the insertion sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted
	**/
	public void insertionSort(long[] a) {
        reset();
    	for (int j = 1; j < a.length; j++){
            long key = a[j];
            int i = j - 1;
             while(i >= 0 && a[i] > key){
                 a[i + 1] = a[i];
                i--;
                count++;
             }
            a[i + 1] = key;
            count++;
        }
	}

	
	/**
	* Executes the quick sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted.
	**/
	public void quickSort(long[] a){
		reset();
		quickSort(a, 0, a.length - 1);
	}

	/**
	 * A private method to partition the elements of an array for quick sort.
	 * @param a the array to be sorted.
	 * @param p the lower index of the array.
	 * @param r the upper index of the array.
	 * @return the index of the pivot element.
	 */
	private int partition(long[] a, int p, int r){
		long x = a[r]; // Rightmost element is pivot.
		int i = p - 1; // Leftmost element.

		for (int j = p; j < r; j++){
			// Check if element is smaller than pivot.
			if (a[j] <= x) {
				i++;
				// Swap elements i and j.
				long a2 = a[i];
				a[i] = a[j];
				a[j] = a2;
				count++;
			}
		}
		// Move pivot to its correct position.
		long a2 = a[i + 1];
		a[i + 1] = a[r];
		a[r] = a2;
		count++;

		return i + 1;
	}

	/**
	 * A private method to recursively sort the elements of an array using quick sort.
	 * @param a the array to be sorted.
	 * @param p the lower index of the array.
	 * @param r the upper index of the array.
	 */
	private void quickSort(long[] a, int p, int r){
		if (p < r) {
			int q = partition(a, p, r);
			quickSort(a, p, q - 1);
			quickSort(a, q + 1, r);
			}
	}
	
	/**
	* Executes the merge sort algorithm sorting the argument array.
	* There is no return as the parameter is to be mutated.
	* @param a the array of long integers to be sorted
	**/
	public void mergeSort(long[] a){
		reset();
		mergeSort(a, 0, a.length - 1);
	}
	
    /**
	*A private method to merge the elements in the array between p and r.
	*the array a, between the indices p and r, inclusive.
	*Given the array is sorted between p and q and q+1 and r
	*sorts the array between p and r.
	*@param a the array to be sorted, which is mutated by the method
	*@param p the lower index of the range to be partitioned
	*@param q the midpoint of the two sorted sections.
	*@param r the upper index of the range to be partitioned
	*@return the index of the point of partition
	**/
	private void merge(long[] a, int p, int q, int r) {
		
		// Create new array to hold the sorted numbers.
		long[] a2 = new long[r - p + 1];
		// i and j are the indices of the sub arrays, k is the index of the merged array
		int i = p, j = q + 1, k = 0;

		// Merge the two separately sorted halves of the array.
		while (i <= q && j <= r){
			if (a[i] < a[j]) a2[k++] = a[i++];
			else a2[k++] = a[j++];
			count++;
		}
		// Copy elements from the sub arrays.
		while (i <= q) {
			a2[k++] = a[i++];
			count++;
		}
		while (j <= r) {
			a2[k++] = a[j++];
			count++;
		}
		// Copy the sorted array back into the original array.
		for (i = 0; i < k; i++) {
			a[p + i] = a2[i];
			count++;
		}
	}
	
   /**
   *Overloads the mergeSort method with parameters to set the 
   *range to be sorted.
   **/ 
	private void mergeSort(long[] a, int p, int r)
	{
		// Get the midpoint of the array.
		int q = (p + r) / 2;
		if (p < r) {
			// Sort both halves of the array.
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			// Merge the two halves back together.
			merge(a, p, q, r);
		}
	}
  }