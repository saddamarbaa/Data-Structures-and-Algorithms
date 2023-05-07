/*
268. Missing Number
Easy
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is
missing from the array.

Example 1:

Input: nums = [3,0,1]
Output: 2
Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the
range since it does not appear in nums.
Example 2:

Input: nums = [0,1]
Output: 2
Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the
range since it does not appear in nums.
Example 3:

Input: nums = [9,6,4,2,3,5,7,0,1]
Output: 8
Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the
range since it does not appear in nums.
 */


import java.util.Arrays;
import java.util.HashMap;

public class MissingNumber {
    public static void main(String[] args) {
        int[] input1 = {3,0,1};
        int expected1 = 2;
        int result1 = missingNumber(input1);
        System.out.println("Test Case 1 - Input: " + Arrays.toString(input1));
        System.out.println("Test Case 1 - Expected result: " + expected1);
        System.out.println("Test Case 1 - Actual result: " + result1);
        System.out.println("Test Case 1 - Result matches expected: " + (result1 == expected1));

        int[] input2 = {1, 2, 0};
        int expected2 = 3;
        int result2 = missingNumber(input2);
        System.out.println("Test Case 2 - Input: " + Arrays.toString(input2));
        System.out.println("Test Case 2 - Expected result: " + expected2);
        System.out.println("Test Case 2 - Actual result: " + result2);
        System.out.println("Test Case 2 - Result matches expected: " + (result2 == expected2));

        int[] input3 = {2, 1};
        int expected3 = 0;
        int result3 = missingNumber(input3);
        System.out.println("Test Case 3 - Input: " + Arrays.toString(input3));
        System.out.println("Test Case 3 - Expected result: " + expected3);
        System.out.println("Test Case 3 - Actual result: " + result3);
        System.out.println("Test Case 3 - Result matches expected: " + (result3 == expected3));
    }


    /**
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range
     * that is missing from the array.
     * Algorithm Steps:
     * Handle the edge case when the input array is null or empty. Return 1 in this case.
     * Set a variable n to the length of the input array.
     * First loop: change all non-positive numbers to n+1.
     * Second loop: mark present elements in the array by changing the sign of the element at index num-1 (where num
     * is the absolute value of the current element in the array), if the element at index num-1 is positive and num
     * is less than or equal to n.
     * Third loop: find the first missing positive element by iterating over the array. If the element at index i is
     * positive, then i+1 is the first missing positive element. Return i+1.
     * If we haven't found the first missing positive element in step 5, then the answer is n+1.
     * Time Complexity: O(n), where n is the size of the given array. We iterate over the array three times, which
     * has a time complexity of O(n).
     * Space Complexity: O(1), as we are not using any additional data structure to store the frequency or anything
     * else.
     */
    public static int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 1;
        }

        int n = nums.length;

        // First loop: change all non-positive numbers to n+1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }

        // Second loop: mark present elements in the array
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n && nums[num - 1] > -1) {
                nums[num - 1] = -1 * nums[num - 1];
            }
        }

        // Third loop: find the first missing positive element
        for (int i = 0; i < n; i++) {
            if (nums[i] > -1) {
                return i + 1;
            }
        }

        return 0;
    }

    /**
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range
     * that is missing from the array.
     * Algorithm Steps:
     * 1: Create a HashMap to store the frequency of positive integers in the given array.
     * 2. Iterate over the array and for each positive integer, update its frequency in the HashMap.
     * 3. Set a variable "smallestPositive" to 1, which represents the smallest positive integer we need to find.
     * 4. While the HashMap contains "smallest", increment "smallest" to the next smallest positive integer.
     * 5. Return "smallest", which represents the smallest missing positive integer in the array
     * Time Complexity:
     * Time Complexity: O(n), where n is the size of the given array. We only need to iterate over the array once to
     * update the frequency of positive integers in the HashMap and find the smallest missing positive integer.
     * Space Complexity:
     * the Space Complexity: O(n), where n is the size of the given array. We need to create a HashMap to store the
     * frequency of positive integers.
     */
    public static int missingNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;


        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        int smallestPositive = 0;

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > -1) { // Ignore negative numbers and zero
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1); // Update the frequency of the number
            }
        }

        while (frequencyMap.containsKey(smallestPositive)) { // Find the smallest missing positive number
            smallestPositive++;
        }

        return smallestPositive;
    }

    /**
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range
     * that is missing from the array..
     * Algorithm Steps:
     * 1: sort the array
     * 2. Iterate over the given array and move all non-positive integers to the left of the array.
     * 3. Set a variable "next" to 1, which represents the smallest positive integer we need to find.
     * 4. Iterate over the remaining positive integers in the array.
     * a. If the current number is equal to "next", increment "next" to the next smallest positive integer.
     * b. If the current number is greater than "next", we have found our answer. Return "next".
     * 5. If we have not found any missing positive integer in step 3, then the answer is "next".
     * 6. Return "next"
     * Time Complexity:
     * Time Complexity: O(n log n), where n is the size of the given array. This is because we need to sort the array
     * first, which has a time complexity of O(n log n), and then iterate over the array to find the smallest
     * positive integer.
     * Space Complexity:
     * the Space Complexity: O(1), as we are not using any additional data structure to store the frequency or
     * anything else.
     */
    public static int missingNumber3(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int smallest = 1;

        for (int i = 0; i < n; i++) {
            if (nums[i] == smallest) {
                smallest++;
            }
        }

        return smallest;
    }
}





