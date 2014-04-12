package com.thejacer87.Algorithms;

public class Algorithms {

    public static void insertionSort(int[] array) {
	int pickup;
	for (int i = 1; i < array.length - 1; i++) {
	    pickup = array[i];
	    int k = i;
	    while (k > 0 && pickup < array[k - 1]) {
		array[k] = array[k - 1];
		k--;
	    }
	    array[k] = pickup;
	}
    }

    public static void selectionSort(int[] array) {
	for (int i = 0; i < array.length - 1; i++) {
	    int lowValueIndex = i;
	    for (int k = i + 1; k < array.length; k++) {
		if (array[lowValueIndex] > array[k])
		    lowValueIndex = k;
	    }
	    swap(array, i, lowValueIndex);
	}
    }

    public static void quickSort(int[] a, int start, int end) {
	if (start < end) {
	    int split = partition(a, start, end);
	    quickSort(a, start, split - 1);
	    quickSort(a, split + 1, end);
	} // if
    }

    public static int partition(int[] a, int start, int end) {
	int right = end;
	int pivot = a[start];
	int left = start + 1;
	while (left <= right) {
	    while (left <= end && a[left] <= pivot)
		left++;
	    while (right > start && a[right] > pivot)
		right--;
	    if (left < right)
		swap(a, left, right);
	} // while
	swap(a, right, start);
	return right;
    } // partition

    public static void bubbleSort(int[] array) {
	for (int i = 0; i < array.length - 1; i++) {
	    for (int h = array.length - 2; h >= i; h--) {
		if (array[h] > array[h + 1])
		    swap(array, h, h + 1);
	    }
	}
    }

    public static void swap(int[] array, int i_1, int i_2) {
	int temp = array[i_1];
	array[i_1] = array[i_2];
	array[i_2] = temp;
    }
}
