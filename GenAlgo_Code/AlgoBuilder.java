import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AlgoBuilder extends AbFunctions
{
	private AbFunctions treeHead;
	
	public AlgoBuilder()
	{
		
	}
	
	/**
	 * Build a tree
	 */
	public void buildTree()
	{
		treeHead = build(0);
	}
	
	
	/**
	 * A simplified print of the tree
	 */
	public void simplePrint()
	{
		testPrint(0);
	}
	
	
	/**
	 * Preforms a full print of the tree
	 * @param motorRight The right motor speed
	 * @param motorLeft The left motor speed
	 */
	public void fullPrint(int motorRight, int motorLeft)
	{
		print(0, motorRight, motorLeft);
	}

	
	/**
	 * Replaced a random node of the tree
	 */
	public void replaceRandomNode()
	{
		replaceNode(0,0,0);
	}
	
	
	/**
	 * Loads a tree from a text file
	 * @param path The path where the fill will be loaded from
	 */
	public void loadTree(String path)
	{
		try 
		{
			load(new BufferedReader(new FileReader(path)));
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Finds the maximum depth of the tree
	 * @return The maximum depth of the tree
	 */
	public int findTreeDepth()
	{
		return findDepth(0);
	}

	
	/**
	 * Method used to save a tree
	 * @param path The file path where the tree will be saved
	 */
	public void saveTree(String path)
	{
		try 
		{
			save(new PrintWriter(path));
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Replaced a random node in the current with a random node from the loaded tree
	 * @param loadPath The file path where the second tree is to be loaded from
	 */
	public void swapSingleNode(String loadPath)
	{
		//variables
		AlgoBuilder secondaryTree = new AlgoBuilder();
		AbFunctions secondaryNode;
		AbFunctions originalNode;
		int originalDepth, originalRandomNum;
		int secondaryDepth, secondaryRandomNum;
		
		//load the secondary tree
		secondaryTree.loadTree(loadPath);
		
		//find the depths of both trees
		originalDepth = findTreeDepth();
		secondaryDepth = secondaryTree.findTreeDepth();
		
		//Find a random node
		originalRandomNum = (int)(Math.random() * originalDepth) + 1;
		originalNode = getTreeNode(originalRandomNum, 1);
		
		//if the node is a leaf node re-do
		while(originalNode == null)
		{
			originalRandomNum = (int)(Math.random() * originalDepth) + 1;
			originalNode = getTreeNode(originalRandomNum, 1);
		}
		
		//find a random node
		secondaryRandomNum = (int)(Math.random() * secondaryDepth) + 1;
		secondaryNode = secondaryTree.getTreeNode(secondaryRandomNum, 1);
		
		//if the node is a leaf node re-do
		while(secondaryNode == null)
		{
			secondaryRandomNum = (int)(Math.random() * secondaryDepth) + 1;
			secondaryNode = secondaryTree.getTreeNode(secondaryRandomNum, 1);
		}
		
		System.out.printf("\tSwaping Node: %d\n", originalRandomNum);
		originalNode.testPrint(0);
		System.out.printf("\tWith: %d\n", secondaryRandomNum);
		secondaryNode.testPrint(0);
		
		//If we are replacing the first node set the tree head to the new node
		if(originalDepth == 1)
			treeHead = secondaryNode;
		else
			//go to the random nodes parent and swap
			originalNode.swap(secondaryNode);		
	}
	
	
	/**
	 * Does a simplified printing of the code
	 * @param num The current depth
	 */
	@Override
	protected void testPrint(int num)
	{
		num = 0;
		testTabs(0);
		System.out.println("Main");
		
		treeHead.testPrint(num + 1);
	}
	
	
	/**
	 * Finds the maximum depth of the tree
	 * @param current The current depth
	 * @return The maximum depth
	 */
	@Override
	protected int findDepth(int current)
	{
		current = 0;
		return treeHead.findDepth(current);
	}
	
	
	/**
	 * Replaces a node
	 * @param maxDepth How far down the tree goes
	 * @param currentDepth How far we have currently gone
	 * @param nodeToReplace The depth of the node to replace
	 * @return true if we found a node to replace
	 */
	protected boolean replaceNode(int maxDepth, int currentDepth, int nodeToReplace)
	{
		maxDepth = findDepth(0); //How deep the tree is
		currentDepth = 0;	//Current depth
		nodeToReplace = (int)(Math.random() * maxDepth) + 1; //Random node to be replaced
		
		System.out.println("Replace: "+ nodeToReplace);
		
		//if the next node is to be replaced, rebuild the tree
		if(nodeToReplace == (currentDepth + 1))
			treeHead = build(0);
		else
			//Find the node to be replaced
			treeHead.replaceNode(maxDepth, currentDepth, nodeToReplace);
		
		return true;
	}
	
	
	/**
	 * Saves a tree to a text file
	 * @param out The print stream
	 * @throws FileNotFoundException
	 */
	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		//Start saving the tree		
		treeHead.save(out);
		
		//close the file
		out.close();
	}
	
	
	/**
	 * Loads a tree from a text file
	 * @param file the file to be read
	 */
	@Override
	protected void load(BufferedReader file) 
	{
		//variables
		int fun[] = new int[3];
		StringTokenizer s;
		String line;
		
		try 
		{
			//Attempt to read the next line
			if( (line = file.readLine()) != null)
			{			
				s = new StringTokenizer(line);
				
				//parse the line
				fun[0] = Integer.parseInt(s.nextToken(",").trim());
				fun[1] = Integer.parseInt(s.nextToken(",").trim());
				fun[2] = Integer.parseInt(s.nextToken(",").trim());
				
				//load the AbFunction into the tree
				treeHead = LoadHelper(fun[0], fun[1], fun[2]);
				
				//Load the next entry
				treeHead.load(file);
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				//Close the file
				file.close();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		//This is before the top of the tree
		currentDepth = 0;
		
		//if we want to swap the entire tree, do it here
		if((currentDepth + 1) == desiredDepth)
			return treeHead;
		else
			//search for the desired node
			return treeHead.getTreeNode(desiredDepth, currentDepth + 1);
	}


	/**
	 * Performs a swap on a non-leaf node
	 * @param node the node to be swapped in
	 */
	@Override
	protected void swap(AbFunctions node)
	{
		//Does nothing at this level
	}
	
	
	/**
	 * Full prints the code
	 * @param depth Used for correct spacing of the code
	 * @param motorRight The rightMotor value
	 * @param motorLeft The left motor value
	 */
	@Override
	protected void print(int depth, int motorRight, int motorLeft)
	{
		depth = 0;
		
		//prints out the #defines used in the program
		System.out.println("//TODO Set the define values");
		System.out.println("#define THRESHOLD   ");
		System.out.println("#define LEFT_SEN    //Sensor position");
		System.out.println("#define RIGHT_SEN   //Sensor position");
		System.out.println("#define FRONT_LEFT  //Sensor position");
		System.out.println("#define FRONT_RIGHT //Sensor position");
		
		//Prints out a function to control the bots motors
		System.out.println("\nvoid move_motors(int rightSpeed, int leftSpeed)\r\n" + 
				"{\r\n" + 
				"     motor(0, rightSpeed);\r\n" + 
				"     motor(2, leftSpeed);\r\n" + 
				"}");
		
		//Print out the function called to have the bot turn 90 degrees
		System.out.println("\nvoid turnLeftNine(void)\r\n" + 
				"{\r\n" + 
				"     //TODO: Design the code to make the bot turn 90 degrees\r\n" + 
				"}");
		
		//Print out the function used to square up the bot
		System.out.println("\nvoid squareUp(void)\r\n" + 
				"{\r\n" + 
				"     int range = 5;\r\n" + 
				"     int value;\r\n" +
				"     int dir;\r\n" +
				"     if(analog(FRONT_LEFT) > analog(FRONT_RIGHT)\r\n" +
				"     {\r\n" +
				"          dir = 1; //Turn to the right\r\n" +
				"     }\r\n" +
				"     else\r\n" +
				"     {\r\n" + 
				"          dir = 0; //Turn to the left\r\n" +
				"     }\r\n" +
				"\r\n" + 
				"     switch(dir)\r\n" + 
				"     {\r\n" +
				"          case 0:\r\n" +
				"               value = analog(FRONT_LEFT);\r\n" + 
				"               move_motors(" +  motorRight + ", " + (-1 * motorLeft) + ");\r\n" + 
				"               while(analog(FRONT_RIGHT) >= (value + range));\r\n" +
				"               break;\r\n" + 
				"          case 1:\r\n" +
				"               value = analog(FRONT_RIGHT);\r\n" + 
				"               move_motors(" +  (-1 * motorRight) + ", " + motorLeft + ");\r\n" + 
				"               while(analog(FRONT_LEFT) >= (value + range));\r\n" +
				"               break;\r\n" + 
				"     }\r\n" +
				"}\r\n");
		
		//print out main
		System.out.println("int main(void)");
		System.out.println("{");
		
		//start the call to print the tree
		treeHead.print(depth + 1, motorRight, motorLeft);
		
		System.out.println("}");
	}
}