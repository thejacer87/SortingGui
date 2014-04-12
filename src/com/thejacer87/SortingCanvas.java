package com.thejacer87;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.thejacer87.Algorithms.Algorithms;

public class SortingCanvas extends Canvas implements Runnable {

    private int[] bars = new int[15];
    private Thread thread;

    public SortingCanvas(int width, int height) {
	setPreferredSize(new Dimension(width, height));
	setBackground(Color.yellow);
	createBarArray();
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

    public void sort(int SortType) {
	switch (SortType) {
	case 0:
	    Algorithms.bubbleSort(bars);
	    break;
	case 1:
	    Algorithms.quickSort(bars, 0, bars.length - 1);
	    break;
	case 2:
	    Algorithms.insertionSort(bars);
	    break;
	case 3:
	    Algorithms.selectionSort(bars);
	    break;
	}
	repaint();
    }

    @Override
    public void run() {
    }

}
