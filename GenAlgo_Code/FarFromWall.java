import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FarFromWall extends AbFunctions
{
	int sensorNums[];
	
	/**
	 * basic constructor
	 * @param depth
	 */
	public FarFromWall(int depth)
	{
		//This AbFunction only has one sensor value
		numSensors = 1;
		
		//allocate space and assign a value
		sensorNums = new int[numSensors];		
		sensorNums[0] = (int)(Math.random() * 4);
		
		//Keep building the tree
		leftChild = build(depth + 1);
		rightChild = null;
	}
	
	/**
	 * Constructor used for loading a tree from a file
	 * @param sensorOne
	 * @param sensorTwo
	 */
	public FarFromWall(int sensorOne, int sensorTwo)
	{
		//This AbFunction only has one sensor value
		numSensors = 1;
		sensorNums = new int[numSensors];	
		
		//load the sensor value
		sensorNums[0] = sensorOne;
	}

	
	/**
	 * Does a simplified printing of the FarFromWall AbFunctions
	 * @param num The current depth
	 */
	@Override
	protected void testPrint(int num)
	{
		testTabs(num);
		System.out.println("Far From Wall");
		
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
		return leftChild.findDepth(current + 1);
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
		//If the next node is to be replaced
		if((currentDepth + 1) == nodeToReplace)
		{
			//replace it
			System.out.println("Replaced");
			leftChild = build(currentDepth + 1);
			
			//successfully replaced a node
			return true;
		}
		else
			//check the next node
			return leftChild.replaceNode(maxDepth, currentDepth + 1, nodeToReplace);
	}
	
	
	/**
	 * Saves a FarFromWall function to the file
	 * FarFromWall has a code of 1
	 * @param out The print stream
	 * @throws FileNotFoundException
	 */
	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		//save this AbFunction
		out.printf("%d, %d, %d\n", 1, sensorNums[0], -1);
		
		//save the next node
		leftChild.save(out);
	}
	
	
	/**
	 * Loads the next node into the tree
	 * @param file the file to be read
	 */
	@Override
	protected void load(BufferedReader file) 
	{
		int fun[] = new int[3];
		StringTokenizer s;
		String line;
		
		try 
		{
			if( (line = file.readLine()) != null)
			{			
				s = new StringTokenizer(line);
				
				//Parse the file into the different components
				fun[0] = Integer.parseInt(s.nextToken(",").trim());
				fun[1] = Integer.parseInt(s.nextToken(",").trim());
				fun[2] = Integer.parseInt(s.nextToken(",").trim());
				
				//load the next node into the tree
				leftChild = LoadHelper(fun[0], fun[1], fun[2]);
				
				//move to the next node
				leftChild.load(file);
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		//If the next set of nodes is to be replaced return this object
		if((currentDepth + 1) == desiredDepth)
			return this;
		else
			return leftChild.getTreeNode(desiredDepth, currentDepth + 1);
	}

	
	/**
	 * Performs a swap on the next node
	 * @param node the node to be swapped in
	 */
	@Override
	protected void swap(AbFunctions node)
	{
		leftChild = node;
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
		out.printf("while(analog(%s) > THRESHOLD)\n", getSensorName(sensorNums[0]));
		tabs(out, depth);
		out.printf("{\n");
		leftChild.print(out, depth + 1, motorRight, motorLeft);
		tabs(out, depth);
		out.printf("}\n");
	}
}