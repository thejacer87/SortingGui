package com.thejacer87;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.thejacer87.Algorithms.Algorithms;

public class SortingCanvas extends Canvas implements Runnable {

    private int[] bars = new int[15];
    private Thread thread;
    private Algorithms algorithms;
    private int sortType;

    public SortingCanvas(int width, int height, int delay) {
	setPreferredSize(new Dimension(width, height));
	setBackground(Color.yellow);
	createBarArray();
	algorithms = new Algorithms(delay);
    }

    public void reset() {
	createBarArray();
	repaint();
    }

    public void createBarArray() {
	for (int i = 0; i < bars.length; i++) {
	    bars[i] = (int) (Math.random() * 20);
	}
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);
	for (int i = 0, k = 0; i < bars.length; i++, k += getWidth()
		/ bars.length) {
	    g.fillRect(k + 5, getHeight() - bars[i] * 15, getWidth()
		    / bars.length - 1, getHeight() * 2);
	}
    }

    public void sort(int sortType) {
	this.sortType = sortType;
	thread = new Thread(this);
	thread.start();
    }

    @Override
    public void run() {
	try {
	    switch (sortType) {
	    case 0:
		algorithms.bubbleSort(bars, this);
		break;
	    case 1:
		algorithms.quickSort(bars, 0, bars.length - 1, this);
		break;
	    case 2:
		algorithms.insertionSort(bars, this);
		break;
	    case 3:
		algorithms.selectionSort(bars, this);
		break;
	    }
	} catch (InterruptedException ie) {
	    ie.printStackTrace();
	}
    }
}
