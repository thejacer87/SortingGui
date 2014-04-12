package com.thejacer87.Algorithms;

import com.thejacer87.SortingCanvas;

public class Algorithms {
    private int delay;

    public Algorithms(int delay) {
	this.delay = delay;
    }

    public synchronized void insertionSort(int[] array, SortingCanvas canvas)
	    throws InterruptedException {
	int pickup;
	for (int i = 1; i < array.length; i++) {
	    pickup = array[i];
	    int k = i;
	    while (k > 0 && pickup < array[k - 1]) {
		try {
		    array[k] = array[k - 1];
		    Thread.sleep(delay);
		    canvas.repaint();
		} catch (InterruptedException ie) {
		}
		k--;
	    }
	    try {
		array[k] = pickup;
		Thread.sleep(delay);
		canvas.repaint();
	    } catch (InterruptedException ie) {
	    }
	}
    }

    public synchronized void selectionSort(int[] array, SortingCanvas canvas)
	    throws InterruptedException {
	for (int i = 0; i < array.length - 1; i++) {
	    int lowValueIndex = i;
	    for (int k = i + 1; k < array.length; k++) {
		if (array[lowValueIndex] > array[k])
		    lowValueIndex = k;
	    }
	    try {
		swap(array, i, lowValueIndex);
		Thread.sleep(delay);
		canvas.repaint();
	    } catch (InterruptedException ie) {
	    }
	}
    }

    public synchronized void quickSort(int[] a, int start, int end,
	    SortingCanvas canvas) throws InterruptedException {
	if (start < end) {
	    int split = partition(a, start, end, canvas);
	    quickSort(a, start, split - 1, canvas);
	    quickSort(a, split + 1, end, canvas);
	} // if
    }

    public synchronized void bubbleSort(int[] array, SortingCanvas canvas)
	    throws InterruptedException {
	for (int i = 0; i < array.length - 1; i++) {
	    for (int h = array.length - 2; h >= i; h--) {
		try {
		    if (array[h] > array[h + 1])
			swap(array, h, h + 1);
		    Thread.sleep(delay);
		    canvas.repaint();
		} catch (InterruptedException ie) {
		}
	    }
	}
    }

    private int partition(int[] a, int start, int end, SortingCanvas canvas)
	    throws InterruptedException {
	int right = end;
	int pivot = a[start];
	int left = start + 1;
	while (left <= right) {
	    while (left <= end && a[left] <= pivot)
		left++;
	    while (right > start && a[right] > pivot)
		right--;
	    if (left < right)
		try {
		    swap(a, left, right);
		    Thread.sleep(delay);
		    canvas.repaint();
		} catch (InterruptedException ie) {
		}
	} // while
	swap(a, right, start);
	return right;
    } // partition

    private static void swap(int[] array, int index1, int index2) {
	int temp = array[index1];
	array[index1] = array[index2];
	array[index2] = temp;
    }
}
