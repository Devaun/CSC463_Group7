import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class AlgoBuilder extends AbFunctions
{
	AbFunctions treeHead;
	
	public AlgoBuilder()
	{
		
	}
	
	public void buildTree()
	{
		treeHead = build(0);
	}

	@Override
	public void testPrint(int num)
	{
		num = 0;
		testTabs(0);
		System.out.println("Main");
		
		treeHead.testPrint(num + 1);
	}
	
	public void replace()
	{
		replaceNode(0,0,0);
	}
	
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
	
	public int findTreeDepth()
	{
		return findDepth(0);
	}

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
	
	public void swapSingleNode(String loadPath)
	{
		AlgoBuilder secondaryTree = new AlgoBuilder();
		AbFunctions secondaryNode;
		AbFunctions originalNode;
		int originalDepth, originalRandomNum;
		int secondaryDepth, secondaryRandomNum;
		
		secondaryTree.loadTree(loadPath);
		
		originalDepth = findTreeDepth();
		secondaryDepth = secondaryTree.findTreeDepth();
		
		originalRandomNum = (int)(Math.random() * originalDepth) + 1;
		originalNode = getTreeNode(originalRandomNum, 1);
		
		while(originalNode == null)
		{
			originalRandomNum = (int)(Math.random() * originalDepth) + 1;
			originalNode = getTreeNode(originalRandomNum, 1);
		}
		
		secondaryRandomNum = (int)(Math.random() * secondaryDepth) + 1;
		secondaryNode = secondaryTree.getTreeNode(secondaryRandomNum, 1);
		
		while(secondaryNode == null)
		{
			secondaryRandomNum = (int)(Math.random() * secondaryDepth) + 1;
			secondaryNode = secondaryTree.getTreeNode(secondaryRandomNum, 1);
		}
		
		System.out.printf("\tSwaping Node: %d\n", originalRandomNum);
		originalNode.testPrint(0);
		System.out.printf("\tWith: %d\n", secondaryRandomNum);
		secondaryNode.testPrint(0);
		
		if(originalDepth == 1)
			treeHead = secondaryNode;
		else
			originalNode.swap(secondaryNode);		
	}
	
	@Override
	protected int findDepth(int current)
	{
		current = 0;
		return treeHead.findDepth(current);
	}
	
	protected boolean replaceNode(int maxDepth, int currentDepth, int nodeToReplace)
	{
		maxDepth = findDepth(0);
		currentDepth = 0;
		nodeToReplace = (int)(Math.random() * maxDepth) + 1;
		
		System.out.println("Replace: "+ nodeToReplace);
		
		if(nodeToReplace == (currentDepth + 1))
			treeHead = build(0);
		else
			treeHead.replaceNode(maxDepth, currentDepth, nodeToReplace);
		
		return true;
	}
	
	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		out = new PrintWriter("TreeSaving.txt");
		
		treeHead.save(out);
		
		out.close();
	}
	
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
				
				fun[0] = Integer.parseInt(s.nextToken(",").trim());
				fun[1] = Integer.parseInt(s.nextToken(",").trim());
				fun[2] = Integer.parseInt(s.nextToken(",").trim());
				
				treeHead = LoadHelper(fun[0], fun[1], fun[2]);
				
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
				file.close();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	/***
	 * If the next depth is the desired depth return itself
	 */
	@Override
	public AbFunctions getTreeNode(int desiredDepth, int currentDepth)
	{
		currentDepth = 0;
		
		if((currentDepth + 1) == desiredDepth)
			return treeHead;
		else
			return treeHead.getTreeNode(desiredDepth, currentDepth + 1);
	}

	@Override
	protected void swap(AbFunctions node)
	{
		//Does nothing
	}
	
	@Override
	protected void print(int depth, int motorRight, int motorLeft)
	{
		depth = 0;
		System.out.println("//TODO Set the define values");
		System.out.println("#define THRESHOLD   \n");
		System.out.println("#define LEFT_SEN    //Sensor position\n");
		System.out.println("#define RIGHT_SEN   //Sensor position\n");
		System.out.println("#define FRONT_LEFT  //Sensor position\n");
		System.out.println("#define FRONT_RIGHT //Sensor position\n");
		System.out.println("void move_motors(int rightSpeed, int leftSpeed)\r\n" + 
				"{\r\n" + 
				"     motor(0, rightSpeed);\r\n" + 
				"     motor(2, leftSpeed);\r\n" + 
				"}");
		
		System.out.println("\nvoid turnLeftNine(void)\r\n" + 
				"{\r\n" + 
				"     //TODO: Design the code to make the bot turn 90 degrees\r\n" + 
				"}");
		
		System.out.println("\nvoid squareUp(void)\r\n" + 
				"{\r\n" + 
				"     int range = 5;\r\n" + 
				"     int value;\r\n" +
				"     int dir;" +
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
				"          case 0:" +
				"               value = analog(FRONT_LEFT);\r\n" + 
				"               move_motors(" +  motorRight + ", " + (-1 * motorLeft) + ");\r\n" + 
				"               while(analog(FRONT_RIGHT) >= (value + range));\r\n" +
				"               break;" + 
				"          case 1:" +
				"               value = analog(FRONT_RIGHT);\r\n" + 
				"               move_motors(" +  (-1 * motorRight) + ", " + motorLeft + ");\r\n" + 
				"               while(analog(FRONT_LEFT) >= (value + range));\r\n" +
				"               break;" + 
				"     }\r\n" +
				"}");
		
		System.out.println("int main(void)");
		System.out.println("{");
		treeHead.print(depth + 1, motorRight, motorLeft);
		System.out.println("}");
		
	}
}