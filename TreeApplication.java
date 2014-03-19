//CS201 Assignment 3 Part 2
//Nicole Fella

import javax.swing.JFrame;


/**
* This is the command line application which will initiate a Frame to put the TreePanel.
**/
public class TreeApplication
{
	public static void main(String[] args)
	{
		//create JFrame
		JFrame treeFrame = new JFrame("Painting Trees is Fun!");
		
		//set size
		treeFrame.setSize(500,700);
		
		//add TreePanel to Frame
		treeFrame.add(new TreePanel());
		
		//exit on close
		treeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//show frame
		treeFrame.setVisible(true);
		
	}
}