package com.thejacer87.Algorithms;

import com.thejacer87.SortingCanvas;

public class Algorithms {

    public Algorithms() {
    }

    public synchronized void insertionSort(int[] array, int delay,
	    SortingCanvas canvas) throws InterruptedException {
	int pickup;
	canvas.draw(-1, -1, -1);
	for (int i = 1; i < array.length; i++) {
	    pickup = array[i];
	    int k = i;
	    while (k > 0 && pickup < array[k - 1]) {
		Thread.sleep(delay);
		canvas.draw(k, -1, i + 1);
		array[k] = array[k - 1];
		k--;
	    }
	    Thread.sleep(delay);
	    canvas.draw(k, -1, i + 1);
	    array[k] = pickup;
	}
	canvas.draw(-1, -1, -1);
    }

    public synchronized void selectionSort(int[] array, int delay,
	    SortingCanvas canvas) throws InterruptedException {
	canvas.draw(-1, -1, -1);
	for (int i = 0; i < array.length - 1; i++) {
	    int lowValueIndex = i;
	    for (int k = i + 1; k < array.length; k++) {
		if (array[lowValueIndex] > array[k])
		    lowValueIndex = k;
		canvas.draw(lowValueIndex, k, i);
		Thread.sleep(delay);
	    }
	    swap(array, i, lowValueIndex);
	}
	canvas.draw(-1, -1, -1);
    }

    public synchronized void quickSort(int[] a, int start, int end, int delay,
	    SortingCanvas canvas) throws InterruptedException {
	if (start < end) {
	    int split = partition(a, start, end, delay, canvas);
	    quickSort(a, start, split - 1, delay, canvas);
	    quickSort(a, split + 1, end, delay, canvas);
	}
	canvas.draw(-1, -1, -1);
    }

    public synchronized void bubbleSort(int[] array, int delay,
	    SortingCanvas canvas) throws InterruptedException {
	canvas.draw(-1, -1, -1);
	for (int i = 0; i < array.length - 1; i++) {
	    for (int k = array.length - 2; k >= i; k--) {
		if (array[k] > array[k + 1])
		    swap(array, k, k + 1);
		Thread.sleep(delay);
		canvas.draw(k, k - 1, -1);
	    }
	}
	canvas.draw(-1, -1, -1);
    }

    private int partition(int[] a, int start, int end, int delay,
	    SortingCanvas canvas) throws InterruptedException {
	int right = end;
	int pivot = a[start];
	int left = start + 1;
	while (left <= right) {
	    while (left <= end && a[left] <= pivot)
		left++;
	    Thread.sleep(delay);
	    canvas.draw(-1, -1, pivot);
	    while (right > start && a[right] > pivot)
		right--;
	    Thread.sleep(delay);
	    canvas.draw(-1, -1, pivot);
	    if (left < right)
		swap(a, left, right);
	    Thread.sleep(delay);
	    canvas.draw(left, right, pivot);
	}
	swap(a, right, start);
	Thread.sleep(delay);
	canvas.draw(left, right, pivot);
	return right;
    }

    private static void swap(int[] array, int index1, int index2) {
	int temp = array[index1];
	array[index1] = array[index2];
	array[index2] = temp;
    }
}
