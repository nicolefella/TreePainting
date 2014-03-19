//CS 201 Assignment 3 Part 2
//Nicole Fella

//awt
import java.awt.BorderLayout;
//swing
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
* This class will create a panel which will display instructions 
* and initiate a TreePainter.
**/
public class TreePanel extends JPanel
{
	/**
	 * Constructor
	 */
	public TreePanel()
	{
		super();
		
		initGUI();
	}
	
	/**
	* Method which will create the GUI components.
	* Layout taken from Lab2
	**/
	public void initGUI()
	{
		//use and set borderlayout
		setLayout( new BorderLayout() );
		
		//create textPanel in the North
		add (createTextArea(), BorderLayout.NORTH);
		//create TreePainter component to add to the center
		add(new TreePainter(),BorderLayout.CENTER);
	}
	
	/**
	 * Method to create text area
	 */
	public JTextArea createTextArea()
	{
		//create new Text area
		JTextArea text = new JTextArea("Click and drag to start a tree painting. "
				+ "Painting can take a bit, so be patient. :)");
		//Prevents user from updating text
		text.setEditable(false);
		//Prevents Text from running off of the area
		text.setLineWrap(true);
		//return text area
		return text;
	}

	
}