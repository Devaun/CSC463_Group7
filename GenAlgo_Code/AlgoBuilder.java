import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AlgoBuilder extends AbFunctions
{
	AbFunctions treeHead;
	Scanner scan;
	
	public AlgoBuilder()
	{
		treeHead = build(0);
	}

	@Override
	public void testPrint(int num)
	{
		num = 0;
		tabs(0);
		System.out.println("Main");
		
		treeHead.testPrint(num + 1);
	}
	
	public void replace()
	{
		replaceNode(0,0,0);
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
	public int findDepth(int current)
	{
		current = 0;
		return treeHead.findDepth(current);
	}

	@Override
	public void save(PrintWriter out) throws FileNotFoundException
	{
		out = new PrintWriter("TreeSaving.txt");
		
		treeHead.save(out);
	}
}