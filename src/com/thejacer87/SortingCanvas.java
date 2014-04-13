package com.thejacer87;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.thejacer87.Algorithms.Algorithms;

public class SortingCanvas extends Canvas implements Runnable {

    private int[] barsArray = new int[15];
    private Thread thread;
    private Algorithms algorithms;
    private int sortType;
    private Graphics graphics;
    private Color barColor;

    public SortingCanvas(int width, int height, int delay) {
	setPreferredSize(new Dimension(width, height));
	setBackground(Color.YELLOW);
	createBarArray();
	algorithms = new Algorithms(delay);
    }

    public void reset() {
	createBarArray();
	repaint();
    }

    public void createBarArray() {
	for (int i = 0; i < barsArray.length; i++) {
	    barsArray[i] = i + 1;
	}
	for (int i = 0; i < barsArray.length; i++) {
	    int random = (int) (Math.random() * (barsArray.length - 1));
	    int temp = barsArray[i];
	    barsArray[i] = barsArray[random];
	    barsArray[random] = temp;
	}
    }

    public synchronized void draw(int index1, int index2) {
	BufferStrategy buffer = getBufferStrategy();
	if (buffer == null) {
	    createBufferStrategy(3);
	    return;
	}
	graphics = buffer.getDrawGraphics();
	graphics.setColor(Color.YELLOW);
	graphics.fillRect(0, 0, getWidth(), getHeight());
	graphics.setColor(barColor);
	for (int i = 0, k = 0; i < barsArray.length; i++, k += getWidth()
		/ barsArray.length) {
	    if (index1 == i) {
		graphics.setColor(Color.RED);
	    }
	    if (index2 == i) {
		graphics.setColor(Color.GREEN);
	    }
	    graphics.fillRect(k + 5, getHeight() - barsArray[i] * 15,
		    getWidth() / barsArray.length - 1, getHeight() * 2);
	    graphics.setColor(barColor);
	}
	graphics.dispose();
	buffer.show();
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);
	barColor = g.getColor();
	for (int i = 0, k = 0; i < barsArray.length; i++, k += getWidth()
		/ barsArray.length) {
	    g.fillRect(k + 5, getHeight() - barsArray[i] * 15, getWidth()
		    / barsArray.length - 1, getHeight() * 2);
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
		algorithms.bubbleSort(barsArray, this);
		break;
	    case 1:
		algorithms.quickSort(barsArray, 0, barsArray.length - 1, this);
		break;
	    case 2:
		algorithms.insertionSort(barsArray, this);
		break;
	    case 3:
		algorithms.selectionSort(barsArray, this);
		break;
	    }
	} catch (InterruptedException ie) {
	    ie.printStackTrace();
	}
    }
}
