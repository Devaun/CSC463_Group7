import java.io.FileNotFoundException;
import java.io.PrintWriter;

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
		out.printf("%d, %d, %d", 0, sensorNums[0], -1);		
	}
}
