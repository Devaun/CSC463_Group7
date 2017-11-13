import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class AbFunctions
{
	protected int numSensors;
	protected AbFunctions leftChild;
	protected AbFunctions rightChild;

	/**
	 * Allows for the tree to be built
	 * @param depth How many nodes deep we have gone
	 * @return An object of the next node being created
	 */
	protected AbFunctions build(int depth)
	{
		int val;
		
		if(depth < 15)
		{
			val = (int) ((Math.random() * 12));
			
			switch(val)
			{
				case 0:
					return (new CloseToWall(depth));
				case 1:
					return (new FarFromWall(depth));
				case 2:
					return (new DoTwoFarFar(depth));
				case 3:
					return (new DoTwoCloseClose(depth));
				case 4:
					return (new DoTwoFarClose(depth));
				case 5:
					return (new DoTwoCloseFar(depth));
				case 6:
					return (new GoForward());
				case 7:
					return (new TurnRight());
				case 8:
					return (new TurnLeft());
				case 9:
					return (new Backup());
				case 10:
					return (new TurnParallelToPosition());
				case 11:
					return (new TurnSquareWithWall());
			}
		}
		else
		{
			//If we have maxed out how deep we can go only make a leaf node
			val = (int) ((Math.random() * 6));
			
			switch(val)
			{
				case 0:
					return (new GoForward());
				case 1:
					return (new TurnRight());
				case 2:
					return (new TurnLeft());
				case 3:
					return (new Backup());
				case 4:
					return (new TurnParallelToPosition());
				case 5:
					return (new TurnSquareWithWall());
			}
		}
		
		return null;
	}
	
	
	/**
	 * Full prints the code
	 * @param depth Used for correct spacing of the code
	 * @param motorRight The rightMotor value
	 * @param motorLeft The left motor value
	 */
	protected abstract void print(PrintWriter out, int depth, int motorRight, int motorLeft);
	
	
	/**
	 * Does a simplified printing of the code
	 * @param num The current depth
	 */
	protected abstract void testPrint(int num);
	
	
	/**
	 * Returns the parent of a node to be replaced
	 * @param desiredDepth How far down we want to replace
	 * @param currentDepth How far we have currently gone
	 * @return The AbFunction that is the parent of the desired node
	 */
	protected abstract AbFunctions getTreeNode(int desiredDepth, int currentDepth);
	
	
	/**
	 * Finds the maximum depth of the tree
	 * @param current The current depth
	 * @return The maximum depth
	 */
	protected abstract int findDepth(int current);
	
	
	/**
	 * Replaces a node
	 * @param maxDepth How far down the tree goes
	 * @param currentDepth How far we have currently gone
	 * @param nodeToReplace The depth of the node to replace
	 * @return true if we found a node to replace
	 */
	protected abstract boolean replaceNode(int maxDepth, int currentDepth, int nodeToReplace);
	
	
	/**
	 * Saves a tree to a text file
	 * @param out The print stream
	 * @throws FileNotFoundException
	 */
	protected abstract void save(PrintWriter out) throws FileNotFoundException;
	
	
	/**
	 * Loads a tree from a text file
	 * @param file the file to be read
	 */
	protected abstract void load(BufferedReader file);
	
	
	/**
	 * Takes the parameters from a tree file and converts it into an object
	 * @param function The integer representation of the function
	 * @param sensorOne The integer representation of the first sensor location
	 * @param sensorTwo The integer representation of the second sensor location
	 * @return
	 */
	protected AbFunctions LoadHelper(int function, int sensorOne, int sensorTwo)
	{
		switch(function)
		{
			case 0:
				return (new CloseToWall(sensorOne, sensorTwo));
			case 1:
				return (new FarFromWall(sensorOne, sensorTwo));
			case 2:
				return (new DoTwoFarFar(sensorOne, sensorTwo));
			case 3:
				return (new DoTwoCloseClose(sensorOne, sensorTwo));
			case 4:
				return (new DoTwoFarClose(sensorOne, sensorTwo));
			case 5:
				return (new DoTwoCloseFar(sensorOne, sensorTwo));
			case 6:
				return (new GoForward());
			case 7:
				return (new TurnRight());
			case 8:
				return (new TurnLeft());
			case 9:
				return (new Backup());
			case 10:
				return (new TurnParallelToPosition());
			case 11:
				return (new TurnSquareWithWall());
			default:
				return null;
		}
	}
	
	
	/**
	 * Performs a swap on a non-leaf node
	 * @param node the node to be swapped in
	 */
	protected abstract void swap(AbFunctions node);
	
	
	/**
	 * Converts a sensor number into the corresponding #define
	 * @param sensorVal
	 * @return
	 */
	protected String getSensorName(int sensorVal)
	{
		switch(sensorVal)
		{
		case 0:
			return "LEFT_SEN";
		case 1:
			return "RIGHT_SEN";
		case 2:
			return "FRONT_LEFT";
		case 3:
			return "FRONT_RIGHT";
		default:
			return "";
		}
	}
	
	
	/**
	 * Places tabs and a label into the text output
	 * @param num the number of times 5 spaces should be printed
	 */
	protected void testTabs(int num)
	{
		System.out.print("Depth: " + num + " ");
		
		for(int i = 0; i < num; i++)
		{
			System.out.print("   ");
		}
	}
	
	
	/**
	 * Places tabs into the text output
	 * @param num the number of times 5 spaces should be printed
	 */
	protected void tabs(PrintWriter out, int num)
	{
		for(int i = 0; i < num; i++)
		{
			out.print("   ");
		}
	}
}