//CS 201 Assignment 3 Part 2
//Nicole Fella



import java.util.concurrent.TimeUnit;


//awt
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.Graphics;
import java.awt.Color;

//swing
import javax.swing.JComponent;


/**
* TreePainter is the subclass of JComponent and implements the MouseListener interface.
* This class is responsible for creating the tree. 
**/
public class TreePainter extends JComponent implements MouseListener
{	
	/** Number of generations to create branches. Play with this for coarser/finer detail. **/
	public static final int NUM_GENERATIONS = 8;
 
	/** Number of children for each branch. Play with this for sparser/fluffier trees. **/
	public static final int NUM_CHILDREN = 5; 
 
	/** Golden ratio makes the child branches aesthetically appealing **/
	public static final double GOLDEN_RATIO = 1.618;
 
	/** Maximum branching angle of children from a parent stick **/
	public static final double MAX_BRANCHING_ANGLE = -.85*Math.PI;
 
	/** Diameter of the blossoms. **/
	public static final int BLOSSOM_DIAM = 4;
	

	/** create new instance with Double values **/
   	private Point2D startP;
   	private Point2D endP;
	
   	//the following three methods are blank because they are not being used
   	//but are part of the interface MouseListener
	public void mouseExited(java.awt.event.MouseEvent e){}
	public void mouseEntered(java.awt.event.MouseEvent e){}
	public void mouseClicked(java.awt.event.MouseEvent e){}
	
	/**
	* Constructor, which adds mouse listener 
	**/
	public TreePainter()
	{
		//call super constructor
		super();
		//add mouse listener to component 
		addMouseListener(this);
	}
	
	/**
	* Method to know when user presses mouse
	**/
	public void mousePressed(MouseEvent e)
	{
		startP = new Point2D.Double(e.getX(), e.getY());
	}
	
	/**
	* Method to know when user releases mouse
	**/
	public void mouseReleased(MouseEvent e)
	{
		endP = new Point2D.Double(e.getX(), e.getY());

		//updates graphics so that the trunk is painted
		repaint();
	}

	/**
	* Method to paint tree. 
	* Start off by drawing trunk of tree. 
	* Then compute information to start drawing branches.
	**/
	public void paint(Graphics g)
	{
		//starts off by drawing the trunk of the tree with user-input recieved 
		drawBranch(startP, endP, g);

		//compute information necessary to start recursively drawing the branches
		Double[] init = compute(startP.getX(), startP.getY(), endP.getX(), endP.getY());
		paintBranch(endP, init[0], init[1], NUM_GENERATIONS, g);

	}

	/**
	* Helper method to compute length and angle between starting and end points.
	* Used API for Point2D and Math to find methods I wanted to use.
	**/
	protected Double[] compute(Double x1, Double y1, Double x2, Double y2)
	{
		Double[] result = {Point2D.distance(x1, y1, x2, y2),Math.atan2(y2-y1, x2-x1)};
		return result;
	}
	
	/**
	* Recursive method to paint branch of tree.
	* If the generation is 0, draw a blossom.
	* Otherwise, draw branches.
	**/
	protected void paintBranch(Point2D point, Double length, Double angle, int generation, Graphics g)
	{
		//base case: draw blossom
		if (generation == 0)
		{
			drawBlossom((int)point.getX(), (int)point.getY(), g);
		}

		else if (generation == NUM_GENERATIONS)
		{
			for (int i=0; i<NUM_CHILDREN; i++)
				paintBranch(point, length/GOLDEN_RATIO, Math.random()*MAX_BRANCHING_ANGLE, generation-1, g);
		}
		//recursive case: draw branches for number of children
		else 
		{
			for (int i=0; i<NUM_CHILDREN; i++)
			{
				//draw stick using length and angle
				Point2D newP = computeEndpoint(point,length,angle);
				drawBranch(point, newP, g);
				//recursively call paintBranch to start painting at end point
				paintBranch(newP, length/GOLDEN_RATIO, Math.random()*MAX_BRANCHING_ANGLE, generation-1, g);
			}
		}
		
	}
    
	/**
	 * Method used to draw the blossoms on the trees, for the base case.
	 * Code for drawing blossom based off of Lab2 ice cream cone
	 **/
	protected void drawBlossom( int x, int y, Graphics g )
	{
		g.setColor(Color.MAGENTA);
		g.fillOval(x, y,BLOSSOM_DIAM, BLOSSOM_DIAM);
	}

	/**
	 * Method used for drawing branches for the tree.
	 * Endpoint must be computed in order to draw the branch.
	 **/
	protected void drawBranch(Point2D point1, Point2D point2, Graphics g) 
	{
		//control color of branch
		g.setColor(Color.DARK_GRAY);

		//draw branch
		g.drawLine((int)point1.getX(), (int)point1.getY(), (int)point2.getX(), (int)point2.getY());

	}
	
    /** 
	 * Compute the point that is length away from p at the angle.
	 * Uses cosine to get the new x coordinate, sine to get the new y coordinate.
	 **/
	protected Point2D computeEndpoint( Point2D p, double length, double angle )
	{
		return new Point2D.Double( 	p.getX() + length*Math.cos(angle), // x is cos
                				p.getY() + length*Math.sin(angle));	// y is sin
	}
	
}