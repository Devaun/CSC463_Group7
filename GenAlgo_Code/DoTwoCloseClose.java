import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DoTwoCloseClose extends AbFunctions
{
	int sensorNums[];
	
	public DoTwoCloseClose(int depth)
	{
		numSensors = 2;
		
		sensorNums = new int[numSensors];
		
		sensorNums[0] = (int)(Math.random() * 4);
		sensorNums[1] = (int)(Math.random() * 4);
		
		leftChild = build(depth + 1);
		rightChild = build(depth + 1);
	}
	
	public DoTwoCloseClose(int sensorOne, int sensorTwo)
	{
		numSensors = 2;
		sensorNums = new int[numSensors];	
		
		sensorNums[0] = sensorOne;
		sensorNums[1] = sensorTwo;
	}

	@Override
	public void testPrint(int num)
	{
		testTabs(num);
		System.out.println("Do Two Close Close");
		
		if(leftChild != null)
			leftChild.testPrint(num + 1);
		if(rightChild != null)
			rightChild.testPrint(num + 1);
	}

	@Override
	protected int findDepth(int current)	
	{
		int leftDepth = leftChild.findDepth(current + 1);
		int rightDepth = rightChild.findDepth(current + 1);
		
		if(leftDepth > rightDepth)
			return leftDepth;
		else
			return rightDepth;
	}

	@Override
	protected boolean replaceNode(int maxDepth, int currentDepth, int nodeToReplace)
	{
		int val;
		
		if((currentDepth + 1) == nodeToReplace)
		{
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
			if(leftChild.replaceNode(maxDepth, currentDepth + 1, nodeToReplace) == false)
			{
				return rightChild.replaceNode(maxDepth, currentDepth + 1, nodeToReplace);
			}
		}
		
		return true;
	}
	
	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		out.printf("%d, %d, %d\n", 3, sensorNums[0], sensorNums[1]);
		
		leftChild.save(out);
		rightChild.save(out);
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
				
				leftChild = LoadHelper(fun[0], fun[1], fun[2]);
				
				leftChild.load(file);
			}
			
			if( (line = file.readLine()) != null)
			{			
				s = new StringTokenizer(line);
				
				fun[0] = Integer.parseInt(s.nextToken(",").trim());
				fun[1] = Integer.parseInt(s.nextToken(",").trim());
				fun[2] = Integer.parseInt(s.nextToken(",").trim());
				
				rightChild = LoadHelper(fun[0], fun[1], fun[2]);
				
				rightChild.load(file);
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public AbFunctions getTreeNode(int desiredDepth, int currentDepth)
	{
		AbFunctions temp;
		if((currentDepth + 1) == desiredDepth)
			return this;
		else
		{
			temp = leftChild.getTreeNode(desiredDepth, currentDepth + 1);
			
			if(temp == null)
				return rightChild.getTreeNode(desiredDepth, currentDepth + 1);
			else
				return temp;
		}
	}

	@Override
	protected void swap(AbFunctions node)
	{
		if(((int)Math.random() * 2) == 0)
			leftChild = node;
		else
			rightChild = node;
	}
	
	@Override
	protected void print(int depth, int motorRight, int motorLeft)
	{
		tabs(depth);
		System.out.printf("while(analog(%s) < THRESHOLD)\n", getSensorName(sensorNums[0]));
		tabs(depth);
		System.out.printf("{\n");
		leftChild.print(depth + 1, motorRight, motorLeft);
		tabs(depth);
		System.out.printf("}\n");
		
		tabs(depth);
		System.out.printf("while(analog(%s) < THRESHOLD)\n", getSensorName(sensorNums[1]));
		tabs(depth);
		System.out.printf("{\n");
		rightChild.print(depth + 1, motorRight, motorLeft);
		tabs(depth);
		System.out.printf("}\n");
	}
}