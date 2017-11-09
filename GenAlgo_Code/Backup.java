import java.io.BufferedReader;
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
		out.printf("%d, %d, %d\n", 9, -1, -1);		
	}

	@Override
	protected void load(BufferedReader file) 
	{
		//Does Nothing
	}

	@Override
	public AbFunctions getTreeNode(int desiredDepth, int currentDepth)
	{
		//Does nothing
		return null;
	}

	@Override
	protected void swap(AbFunctions node)
	{
		//Does nothing
	}
}
