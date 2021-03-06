import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TurnRight extends AbFunctions
{
	/**
	 * basic constructor
	 */
	public TurnRight()
	{
		numSensors = 0;
		
		//leaves have no children
		leftChild = null;
		rightChild = null;
	}
	
	
	/**
	 * Does a simplified printing of the backup AbFunctions
	 * @param num The current depth
	 */
	@Override
	protected void testPrint(int num)
	{
		testTabs(num);
		System.out.println("Turn right");
		
		if(leftChild != null)
			leftChild.testPrint(num + 1);
		if(rightChild != null)
			rightChild.testPrint(num + 1);
	}
	
	
	/**
	 * Finds the maximum depth of the tree
	 * @param current The current depth
	 * @return The maximum depth
	 */
	@Override
	protected int findDepth(int current)	
	{
		return (current + 1);
	}
	
	
	/**
	 * Replaces a node
	 * @param maxDepth How far down the tree goes
	 * @param currentDepth How far we have currently gone
	 * @param nodeToReplace The depth of the node to replace
	 * @return true if we found a node to replace
	 */
	@Override
	protected boolean replaceNode(int maxDepth, int currentDepth, int nodeToReplace)
	{
		//Cant replace a node for leaves
		return false;	
	}
	
	
	/**
	 * Saves a TurnRight function to the file
	 * TurnRight has a code of 7
	 * @param out The print stream
	 * @throws FileNotFoundException
	 */
	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		out.printf("%d, %d, %d\n", 7, -1, -1);		
	}
	
	
	/**
	 * Loads a tree from a text file
	 * @param file the file to be read
	 */
	@Override
	protected void load(BufferedReader file) 
	{
		//Does Nothing
	}
	
	
	/**
	 * Returns the parent of a node to be replaced
	 * @param desiredDepth How far down we want to replace
	 * @param currentDepth How far we have currently gone
	 * @return The AbFunction that is the parent of the desired node
	 */
	@Override
	protected AbFunctions getTreeNode(int desiredDepth, int currentDepth)
	{
		//Does nothing
		return null;
	}

	
	/**
	 * Performs a swap on a non-leaf node
	 * @param node the node to be swapped in
	 */
	@Override
	protected void swap(AbFunctions node)
	{
		//Does nothing
	}
	
	
	/**
	 * Full prints the code
	 * @param depth Used for correct spacing of the code
	 * @param motorRight The rightMotor value
	 * @param motorLeft The left motor value
	 */
	@Override
	protected void print(PrintWriter out, int depth, int motorRight, int motorLeft)
	{
		tabs(out, depth);
		out.printf("moveMotors(-%d, %d);\n", motorRight, motorLeft);
	}
}