import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FarFromWall extends AbFunctions
{
	int sensorNums[];
	
	public FarFromWall(int depth)
	{
		numSensors = 1;
		
		sensorNums = new int[numSensors];		
		sensorNums[0] = (int)(Math.random() * 4);
		
		leftChild = build(depth + 1);
		rightChild = null;
	}
	
	public FarFromWall(int sensorOne, int sensorTwo)
	{
		numSensors = 1;
		sensorNums = new int[numSensors];	
		
		sensorNums[0] = sensorOne;
	}

	@Override
	public void testPrint(int num)
	{
		testTabs(num);
		System.out.println("Far From Wall");
		
		if(leftChild != null)
			leftChild.testPrint(num + 1);
		if(rightChild != null)
			rightChild.testPrint(num + 1);
	}
	
	@Override
	protected int findDepth(int current)	
	{
		return leftChild.findDepth(current + 1);
	}
	
	@Override
	protected boolean replaceNode(int maxDepth, int currentDepth, int nodeToReplace)
	{
		if((currentDepth + 1) == nodeToReplace)
		{
			leftChild = build(currentDepth + 1);
			
			return true;
		}
		else
			return leftChild.replaceNode(maxDepth, currentDepth + 1, nodeToReplace);
	}
	
	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		out.printf("%d, %d, %d\n", 1, sensorNums[0], -1);
		
		leftChild.save(out);
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
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public AbFunctions getTreeNode(int desiredDepth, int currentDepth)
	{
		if((currentDepth + 1) == desiredDepth)
			return this;
		else
			return leftChild.getTreeNode(desiredDepth, currentDepth + 1);
	}

	@Override
	protected void swap(AbFunctions node)
	{
		leftChild = node;
	}
	
	@Override
	protected void print(int depth, int motorRight, int motorLeft)
	{
		tabs(depth);
		System.out.printf("while(analog(%s) > THRESHOLD)\n", getSensorName(sensorNums[0]));
		tabs(depth);
		System.out.printf("{\n");
		tabs(depth + 1);
		leftChild.print(depth + 1, motorRight, motorLeft);
		tabs(depth);
		System.out.printf("}\n");
	}
}