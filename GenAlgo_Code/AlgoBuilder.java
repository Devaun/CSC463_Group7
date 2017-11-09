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
		tabs(0);
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
}