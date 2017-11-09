import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public abstract class AbFunctions
{
	int numSensors;
	
	AbFunctions leftChild;
	AbFunctions rightChild;

	public AbFunctions build(int depth)
	{
		int val;
		
		if(depth < 15)
		{
			val = (int) ((Math.random() * 12));
			
			switch(val)
			{
				case 0:
					return (new CloseToWall(depth));
				case 1:
					return (new FarFromWall(depth));
				case 2:
					return (new DoTwoFarFar(depth));
				case 3:
					return (new DoTwoCloseClose(depth));
				case 4:
					return (new DoTwoFarClose(depth));
				case 5:
					return (new DoTwoCloseFar(depth));
				case 6:
					return (new GoForward());
				case 7:
					return (new TurnRight());
				case 8:
					return (new TurnLeft());
				case 9:
					return (new Backup());
				case 10:
					return (new TurnParallelToPosition());
				case 11:
					return (new TurnSquareWithWall());
			}
		}
		else
		{
			val = (int) ((Math.random() * 6));
			
			switch(val)
			{
				case 0:
					return (new GoForward());
				case 1:
					return (new TurnRight());
				case 2:
					return (new TurnLeft());
				case 3:
					return (new Backup());
				case 4:
					return (new TurnParallelToPosition());
				case 5:
					return (new TurnSquareWithWall());
			}
		}
		
		return null;
	}
	
	protected AbFunctions LoadHelper(int function, int sensorOne, int sensorTwo)
	{
		switch(function)
		{
			case 0:
				return (new CloseToWall(sensorOne, sensorTwo));
			case 1:
				return (new FarFromWall(sensorOne, sensorTwo));
			case 2:
				return (new DoTwoFarFar(sensorOne, sensorTwo));
			case 3:
				return (new DoTwoCloseClose(sensorOne, sensorTwo));
			case 4:
				return (new DoTwoFarClose(sensorOne, sensorTwo));
			case 5:
				return (new DoTwoCloseFar(sensorOne, sensorTwo));
			case 6:
				return (new GoForward());
			case 7:
				return (new TurnRight());
			case 8:
				return (new TurnLeft());
			case 9:
				return (new Backup());
			case 10:
				return (new TurnParallelToPosition());
			case 11:
				return (new TurnSquareWithWall());
			default:
				return null;
		}
	}
	
	//public abstract void print();
	public abstract void testPrint(int num);
	
	public abstract AbFunctions getTreeNode(int desiredDepth, int currentDepth);
	
	protected abstract int findDepth(int current);
	
	protected abstract boolean replaceNode(int maxDepth, int currentDepth, int nodeToReplace);
	
	protected abstract void save(PrintWriter out) throws FileNotFoundException;
	
	protected abstract void load(BufferedReader file);
	
	protected abstract void swap(AbFunctions node);
	
	protected void tabs(int num)
	{
		System.out.print("Depth: " + num + " ");
		for(int i = 0; i < num; i++)
		{
			System.out.print("   ");
		}
	}
}