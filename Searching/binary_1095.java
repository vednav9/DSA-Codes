/**
 * This interface is provided by the problem and is not given in the code. 
 * It represents the MountainArray object.
 * You can assume it has the following methods:
 * int get(int index) - Returns the element of the mountain array at index.
 * int length() - Returns the length of the mountain array.
 */


 class binary_1095 {
    /*public int findInMountainArray(int target, int mountainArr) {
        // Step 1: Find the peak index in the mountain array
        int peakIndex = findPeakIndex(mountainArr);
        
        // Step 2: Search for the target in the ascending part of the mountain
        int left = binarySearchAscending(mountainArr, target, 0, peakIndex);
        
        // If the target is found in the ascending part, return its index
        if (left != -1) {
            return left;
        }
        
        // Step 3: Search for the target in the descending part of the mountain
        int right = binarySearchDescending(mountainArr, target, peakIndex + 1, mountainArr.length() - 1);
        
        // Return the index where the target is found in the descending part
        return right;
    }
    
    // Helper function to find the peak index in the mountain array
    private int findPeakIndex(MountainArray mountainArr) {
        int left = 0;
        int right = mountainArr.length() - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // Compare the current element with the next element
            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                // If the current element is less than the next element, move to the right
                left = mid + 1;
            } else {
                // Otherwise, move to the left
                right = mid;
            }
        }
        
        // The left pointer will be at the peak element's index
        return left;
    }
    
    // Helper function for binary search on the ascending part of the mountain
    private int binarySearchAscending(MountainArray mountainArr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = mountainArr.get(mid);
            
            if (midValue == target) {
                // If the current element is the target, return its index
                return mid;
            } else if (midValue < target) {
                // If the current element is less than the target, move to the right
                left = mid + 1;
            } else {
                // If the current element is greater than the target, move to the left
                right = mid - 1;
            }
        }
        
        // If the target is not found in the ascending part, return -1
        return -1;
    }
    
    // Helper function for binary search on the descending part of the mountain
    private int binarySearchDescending(MountainArray mountainArr, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = mountainArr.get(mid);
            
            if (midValue == target) {
                // If the current element is the target, return its index
                return mid;
            } else if (midValue < target) {
                // If the current element is less than the target, move to the left
                right = mid - 1;
            } else {
                // If the current element is greater than the target, move to the right
                left = mid + 1;
            }
        }
        
        // If the target is not found in the descending part, return -1
        return -1;
    }*/
}