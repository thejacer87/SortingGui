package com.thejacer87;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SortingPanel extends JPanel implements ActionListener {
    private JRadioButton bubbleSortButton;
    private JRadioButton quickSortButton;
    private JRadioButton insertionSortButton;
    private JRadioButton selectionSortButton;
    private JButton sortButton;
    private JButton resetButton;
    private SortingCanvas sortingCanvas;
    private int delay;

    public SortingPanel() {
	delay = 250;
	// Sets layout and background color of main panel.
	Color background = new Color(0xBBBBBB);
	setLayout(new BorderLayout());
	this.setBackground(background);
	JRadioButton radioButtonArray[] = { bubbleSortButton, quickSortButton,
		insertionSortButton, selectionSortButton };

	JPanel southPanel = new JPanel();
	sortButton = new JButton(" Sort ");
	resetButton = new JButton("Reset");
	southPanel.setBackground(background);
	southPanel.add(sortButton);
	southPanel.add(resetButton);

	JPanel centerPanel = new JPanel();
	sortingCanvas = new SortingCanvas(500, 300, delay);
	centerPanel.setBackground(background);
	centerPanel.add(sortingCanvas);

	JPanel westPanel = new JPanel();
	ButtonGroup buttonGroup = new ButtonGroup();
	bubbleSortButton = new JRadioButton("Bubble Sort", true);
	quickSortButton = new JRadioButton("Quick Sort");
	insertionSortButton = new JRadioButton("Insertion Sort");
	selectionSortButton = new JRadioButton("Selection Sort");
	westPanel.setLayout(new GridLayout(4, 1));
	// for (int i = 0; i < radioButtonArray.length; i++) {
	// radioButtonArray[i].setBackground(background);
	// buttonGroup.add(radioButtonArray[i]);
	// westPanel.add(radioButtonArray[i]);
	// }
	buttonGroup.add(bubbleSortButton);
	buttonGroup.add(quickSortButton);
	buttonGroup.add(insertionSortButton);
	buttonGroup.add(selectionSortButton);

	westPanel.add(bubbleSortButton);
	westPanel.add(quickSortButton);
	westPanel.add(insertionSortButton);
	westPanel.add(selectionSortButton);
	bubbleSortButton.setBackground(background);
	quickSortButton.setBackground(background);
	insertionSortButton.setBackground(background);
	selectionSortButton.setBackground(background);

	sortButton.addActionListener(this);
	resetButton.addActionListener(this);
	add(westPanel, BorderLayout.WEST);
	add(centerPanel, BorderLayout.CENTER);
	add(southPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
	JFrame myApp = new JFrame("Sorting GUI");
	SortingPanel myPanel = new SortingPanel();
	myApp.add(myPanel);
	myApp.pack();
	myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	myApp.setSize(635, 385);
	myApp.setVisible(true);
	myApp.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == sortButton) {
	    sortingCanvas.sort(getButtonValue());
	}
	if (e.getSource() == resetButton) {
	    sortingCanvas.reset();
	}
    }

    private int getButtonValue() {
	if (bubbleSortButton.isSelected())
	    return 0;
	if (quickSortButton.isSelected())
	    return 1;
	if (insertionSortButton.isSelected())
	    return 2;
	if (selectionSortButton.isSelected())
	    return 3;
	return -1;
    }
}