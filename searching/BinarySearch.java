package searching;

/*
* Binary Search can only be implemented when the array is sorted.
* Time Complexity - O(log n)
* */

public class BinarySearch {

    public static void main(String[] args)
    {
        int[] array = new int[]{1,2,3,4,5,6};
        int binarySearch = BinarySearch.binarySearch(array, 0, array.length - 1, 3);
        System.out.println(binarySearch);
    }

    private static int binarySearch(int[] array, int low, int high,int toSearch)
    {
        while(low <= high)
        {
            int mid = low + (high-low)/2;

            // If the element is found
            if(array[mid] == toSearch)
                return mid;

            // If the element is bigger than the mid element, then search right side of the mid element in the array.
            // If the element is lesser than the mid element, then search left side of the mid element in the array.
            if(array[mid] < toSearch)
                low = mid +1;
            else
                high = mid -1;
        }

        // If element not found in the array, then return -1;
        return -1;
    }
}
