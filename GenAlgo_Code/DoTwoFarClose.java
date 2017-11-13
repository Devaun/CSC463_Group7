import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DoTwoFarClose extends AbFunctions
{
	int sensorNums[];
	
	/**
	 * basic constructor
	 * @param depth
	 */
	public DoTwoFarClose(int depth)
	{
		//This AbFunction has two sensor values
		numSensors = 2;
		
		//allocate space and assign values
		sensorNums = new int[numSensors];
		sensorNums[0] = (int)(Math.random() * 4);
		sensorNums[1] = (int)(Math.random() * 4);
		
		//Keep building the tree
		leftChild = build(depth + 1);
		rightChild = build(depth + 1);
	}
	
	
	/**
	 * Constructor used for loading a tree from a file
	 * @param sensorOne
	 * @param sensorTwo
	 */
	public DoTwoFarClose(int sensorOne, int sensorTwo)
	{
		numSensors = 2;
		sensorNums = new int[numSensors];	
		
		//load the sensor values
		sensorNums[0] = sensorOne;
		sensorNums[1] = sensorTwo;
	}

	
	/**
	 * Does a simplified printing of the DoTwoFarClose AbFunctions
	 * @param num The current depth
	 */
	@Override
	protected void testPrint(int num)
	{
		testTabs(num);
		System.out.println("Do Two Far Close");
		
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
		//Check the depth of both children
		int leftDepth = leftChild.findDepth(current + 1);
		int rightDepth = rightChild.findDepth(current + 1);
		
		//return the size of the larger one
		if(leftDepth > rightDepth)
			return leftDepth;
		else
			return rightDepth;
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
		int val;
		
		//Check if the next nodes are to be replaced
		if((currentDepth + 1) == nodeToReplace)
		{
			//if true select one of the nodes to replace
			val = (int)(Math.random() * 2);
			
			if(val == 0)
			{
				leftChild = build(currentDepth + 1);
			}
			else
			{
				rightChild = build(currentDepth + 1);
			}
		}
		else
		{
			//Check the left branch for a node to replace
			if(leftChild.replaceNode(maxDepth, currentDepth + 1, nodeToReplace) == false)
			{
				//if no node found check the right node
				return rightChild.replaceNode(maxDepth, currentDepth + 1, nodeToReplace);
			}
		}
		
		return true;
	}
	
	
	/**
	 * Saves a DoTwoFarClose function to the file
	 * DoTwoFarClose has a code of 4
	 * @param out The print stream
	 * @throws FileNotFoundException
	 */
	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		//save this AbFunction
		out.printf("%d, %d, %d\n", 4, sensorNums[0], sensorNums[1]);
		
		//save the left branch before the right branch
		leftChild.save(out);
		rightChild.save(out);
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
			//read the next line of the file
			if( (line = file.readLine()) != null)
			{			
				s = new StringTokenizer(line);
				
				//parse the input
				fun[0] = Integer.parseInt(s.nextToken(",").trim());
				fun[1] = Integer.parseInt(s.nextToken(",").trim());
				fun[2] = Integer.parseInt(s.nextToken(",").trim());
				
				//load the AbFunction into the tree
				leftChild = LoadHelper(fun[0], fun[1], fun[2]);
				
				//move to the next node for the left branch
				leftChild.load(file);
			}
			
			//read the next line of the file
			if( (line = file.readLine()) != null)
			{			
				s = new StringTokenizer(line);
				
				//parse the input
				fun[0] = Integer.parseInt(s.nextToken(",").trim());
				fun[1] = Integer.parseInt(s.nextToken(",").trim());
				fun[2] = Integer.parseInt(s.nextToken(",").trim());
				
				//load the AbFunction into the tree
				rightChild = LoadHelper(fun[0], fun[1], fun[2]);
				
				//move to the next node for the right branch
				rightChild.load(file);
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
		AbFunctions temp;
		
		//If the next node is to be replaced return this object
		if((currentDepth + 1) == desiredDepth)
			return this;
		else
		{
			//move down the left branch
			temp = leftChild.getTreeNode(desiredDepth, currentDepth + 1);
			
			//If the left branch doesn't contain the node to be replaced move down the right node
			if(temp == null)
				return rightChild.getTreeNode(desiredDepth, currentDepth + 1);
			else
				return temp;
		}
	}

	
	/**
	 * Performs a swap on one of the children
	 * @param node the node to be swapped in
	 */
	@Override
	protected void swap(AbFunctions node)
	{
		//randomly select one of the nodes to swap
		if(((int)Math.random() * 2) == 0)
			leftChild = node;
		else
			rightChild = node;
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
		
		tabs(out, depth);
		out.printf("while(analog(%s) < THRESHOLD)\n", getSensorName(sensorNums[1]));
		tabs(out, depth);
		out.printf("{\n");
		rightChild.print(out, depth + 1, motorRight, motorLeft);
		tabs(out, depth);
		out.printf("}\n");
	}
}
