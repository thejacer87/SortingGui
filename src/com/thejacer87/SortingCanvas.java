package com.thejacer87;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.thejacer87.Algorithms.Algorithms;

public class SortingCanvas extends Canvas implements Runnable {
    /**
     * Your guess is as good as mine...
     */
    private static final long serialVersionUID = 1L;
    private final Color BAR_COLOR = Color.DARK_GRAY;
    private int bars = 15;
    private SortingPanel sortingPanel;
    private Thread thread;
    private Graphics graphics;
    private int[] barsArray;
    private int sortType;
    private int delay;

    public SortingCanvas(int width, int height, int delay,
	    SortingPanel sortingPanel) {
	setPreferredSize(new Dimension(width, height));
	setBackground(Color.YELLOW);
	createBarArray();
	this.sortingPanel = sortingPanel;
    }

    public void reset() {
	createBarArray();
	repaint();
    }

    public void createBarArray() {
	barsArray = new int[getBars()];
	for (int i = 0; i < barsArray.length; i++) {
	    barsArray[i] = (int) (Math.random() * 20) + 1;
	}
    }

    public synchronized void draw(int index1, int index2, int index3) {
	BufferStrategy buffer = getBufferStrategy();
	if (buffer == null) {
	    createBufferStrategy(3);
	    return;
	}
	graphics = buffer.getDrawGraphics();
	graphics.setColor(Color.YELLOW);
	graphics.fillRect(0, 0, getWidth(), getHeight());
	graphics.setColor(BAR_COLOR);
	for (int i = 0, k = 0; i < barsArray.length; i++, k += getWidth()
		/ barsArray.length) {
	    if (index1 == i) {
		graphics.setColor(Color.RED);
	    }
	    if (index2 == i) {
		graphics.setColor(Color.GREEN);
	    }
	    if (index3 == i) {
		graphics.setColor(Color.BLUE);
	    }
	    graphics.fillRect(k + 5, getHeight() - barsArray[i] * 15,
		    getWidth() / barsArray.length - 1, getHeight() * 2);
	    graphics.setColor(BAR_COLOR);
	}
	graphics.dispose();
	buffer.show();
    }

    @Override
    public void paint(Graphics g) {
	super.paint(g);
	g.setColor(BAR_COLOR);
	for (int i = 0, k = 0; i < barsArray.length; i++, k += getWidth()
		/ barsArray.length) {
	    g.fillRect(k + 5, getHeight() - barsArray[i] * 15, getWidth()
		    / barsArray.length - 1, getHeight() * 2);
	}
    }

    public void sort(int sortType, int delay) {
	this.sortType = sortType;
	this.delay = delay;
	thread = new Thread(this);
	thread.start();
    }

    @Override
    public void run() {
	try {
	    switch (sortType) {
	    case 0:
		(new Algorithms()).bubbleSort(barsArray, delay, this);
		break;
	    case 1:
		(new Algorithms()).quickSort(barsArray, 0,
			barsArray.length - 1, delay, this);
		break;
	    case 2:
		(new Algorithms()).insertionSort(barsArray, delay, this);
		break;
	    case 3:
		(new Algorithms()).selectionSort(barsArray, delay, this);
		break;
	    }
	} catch (InterruptedException ie) {
	    ie.printStackTrace();
	}
	sortingPanel.sortButton.setEnabled(true);
	sortingPanel.resetButton.setEnabled(true);
    }

    public void setBars(int bars) {
	this.bars = bars;
    }

    private int getBars() {
	return bars;
    }
}
