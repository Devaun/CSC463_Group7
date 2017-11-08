import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Backup extends AbFunctions
{
	public Backup()
	{
		numSensors = 0;
		
		leftChild = null;
		rightChild = null;
	}

	@Override
	public void testPrint(int num)
	{
		tabs(num);
		System.out.println("Backup");
		
		if(leftChild != null)
			leftChild.testPrint(num + 1);
		if(rightChild != null)
			rightChild.testPrint(num + 1);
	}

	@Override
	protected int findDepth(int current)	
	{
		return (current + 1);
	}

	@Override
	public boolean replaceNode(int maxDepth, int currentDepth, int nodeToReplace)
	{
		return false;	
	}

	@Override
	protected void save(PrintWriter out) throws FileNotFoundException
	{
		out.printf("%d, %d, %d", 9, -1, -1);		
	}
}
