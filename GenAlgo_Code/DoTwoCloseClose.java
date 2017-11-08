import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

	@Override
	public void testPrint(int num)
	{
		tabs(num);
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
		out.printf("%d, %d, %d", 3, sensorNums[0], sensorNums[1]);		
	}
}