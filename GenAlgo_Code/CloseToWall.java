import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CloseToWall extends AbFunctions
{
	int sensorNums[];
	
	public CloseToWall(int depth)
	{
		numSensors = 1;
		
		sensorNums = new int[numSensors];		
		sensorNums[0] = (int)(Math.random() * 4);
		
		leftChild = build(depth + 1);
		rightChild = null;
	}
	
	public CloseToWall(int sensorOne, int sensorTwo)
	{
		numSensors = 1;
		sensorNums = new int[numSensors];	
		
		sensorNums[0] = sensorOne;
	}

	@Override
	public void testPrint(int num)
	{
		tabs(num);
		System.out.println("Close to wall");
		
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
			System.out.println("Replaced");
			leftChild = build(currentDepth + 1);
			
			return true;
		}
		else
			return leftChild.replaceNode(maxDepth, currentDepth + 1, nodeToReplace);
	}
	
	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		out.printf("%d, %d, %d\n", 0, sensorNums[0], -1);
		
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
}
