package com.thejacer87;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SortingPanel extends JPanel implements ActionListener {
    /**
     * Don't know why it has to be serialized??
     */
    private static final long serialVersionUID = 1L;
    private JRadioButton bubbleSortButton;
    private JRadioButton quickSortButton;
    private JRadioButton insertionSortButton;
    private JRadioButton selectionSortButton;
    private JRadioButton slowButton;
    private JRadioButton mediumButton;
    private JRadioButton fastButton;
    protected JButton sortButton;
    protected JButton resetButton;
    private SortingCanvas sortingCanvas;
    private JTextField textfield;
    private int bars;

    public SortingPanel() {
	// Sets layout and background color of main panel.
	Color background = new Color(0xDBADF7);
	setLayout(new BorderLayout());
	this.setBackground(background);

	// Creates southPanel components
	JPanel southPanel = new JPanel();
	JLabel barLabel = new JLabel(
		"Enter the amount of bars to sort and hit enter: ");
	bars = 15;
	textfield = new JTextField("" + bars, 5);
	sortButton = new JButton(" Sort ");
	resetButton = new JButton("Reset");
	southPanel.setBorder(BorderFactory.createRaisedBevelBorder());
	southPanel.setBackground(background);
	southPanel.add(barLabel);
	southPanel.add(textfield);
	southPanel.add(sortButton);
	southPanel.add(resetButton);

	// Creates centerPanel components
	JPanel centerPanel = new JPanel();
	sortingCanvas = new SortingCanvas(500, 310, 80, this);
	centerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
	centerPanel.setBackground(background);
	centerPanel.add(sortingCanvas);

	// Creates westPanel components
	JPanel westPanel = new JPanel(new GridLayout(2, 1));
	JPanel sortTypePanel = new JPanel(new GridLayout(5, 1));
	JPanel speedPanel = new JPanel(new GridLayout(4, 1));
	JLabel typeLabel = new JLabel("SORT TYPES:", JLabel.CENTER);
	JLabel speedLabel = new JLabel("SORTING SPEEDS:", JLabel.CENTER);
	ButtonGroup sortButtonGroup = new ButtonGroup();
	ButtonGroup speedButtonGroup = new ButtonGroup();
	bubbleSortButton = new JRadioButton("Bubble Sort");
	quickSortButton = new JRadioButton("Quick Sort", true);
	insertionSortButton = new JRadioButton("Insertion Sort");
	selectionSortButton = new JRadioButton("Selection Sort");
	slowButton = new JRadioButton("Slow");
	mediumButton = new JRadioButton("Medium");
	fastButton = new JRadioButton("Fast", true);

	// Creates buttonGroups
	sortButtonGroup.add(bubbleSortButton);
	sortButtonGroup.add(quickSortButton);
	sortButtonGroup.add(insertionSortButton);
	sortButtonGroup.add(selectionSortButton);
	speedButtonGroup.add(slowButton);
	speedButtonGroup.add(mediumButton);
	speedButtonGroup.add(fastButton);

	// Adds buttons to appropriate panels
	speedPanel.add(speedLabel);
	speedPanel.add(slowButton);
	speedPanel.add(mediumButton);
	speedPanel.add(fastButton);
	sortTypePanel.add(typeLabel);
	sortTypePanel.add(bubbleSortButton);
	sortTypePanel.add(quickSortButton);
	sortTypePanel.add(insertionSortButton);
	sortTypePanel.add(selectionSortButton);

	// Adds components to westPanel
	westPanel.setBorder(BorderFactory.createRaisedBevelBorder());
	westPanel.add(sortTypePanel);
	westPanel.add(speedPanel);

	// Register listeners and add border layout panels to main panel
	textfield.addActionListener(this);
	sortButton.addActionListener(this);
	resetButton.addActionListener(this);
	add(westPanel, BorderLayout.WEST);
	add(centerPanel, BorderLayout.CENTER);
	add(southPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == sortButton) {
	    setBars();
	    sortingCanvas.sort(getSortType(), getDelaySpeed());
	    sortButton.setEnabled(false);
	    resetButton.setEnabled(false);
	}
	if (e.getSource() == textfield) {
	    setBars();
	    sortingCanvas.reset();
	}
	if (e.getSource() == resetButton) {
	    sortingCanvas.reset();
	}
    }

    // Main method
    public static void main(String[] args) {
	JFrame myApp = new JFrame("Sorting GUI");
	SortingPanel myPanel = new SortingPanel();
	myApp.add(myPanel);
	myApp.pack();
	myApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	myApp.setSize(635, 385);
	myApp.setVisible(true);
	myApp.setResizable(false);
	myApp.setLocationRelativeTo(null);
    } // end Main

    private void setBars() {
	try {
	    bars = Integer.parseInt(textfield.getText());
	} catch (NumberFormatException nfe) {
	    JOptionPane.showMessageDialog(SortingPanel.this,
		    "Please enter a number between 1 and 125",
		    "Number Format Error", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	if (bars < 2 || bars > 125) {
	    JOptionPane.showMessageDialog(SortingPanel.this,
		    "Please enter a number between 1 and 125",
		    "Number Format Error", JOptionPane.ERROR_MESSAGE);
	    return;
	}
	sortingCanvas.setBars(bars);
    }

    private int getDelaySpeed() {
	if (slowButton.isSelected()) {
	    return 300;
	}
	if (mediumButton.isSelected()) {
	    return 150;
	}
	if (fastButton.isSelected()) {
	    return 75;
	}
	return -1;
    }

    private int getSortType() {
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