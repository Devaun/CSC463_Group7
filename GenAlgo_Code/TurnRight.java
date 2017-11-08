public class TurnRight extends AbFunctions
{
	public TurnRight()
	{
		numSensors = 0;
		
		leftChild = null;
		rightChild = null;
	}
	
	@Override
	public void testPrint(int num)
	{
		tabs(num);
		System.out.println("Turn right");
		
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
		//System.err.println("TURNRIGHT: WE SHOULDNT GET HERE");
		return false;	
	}
}