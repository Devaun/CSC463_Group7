public class Driver 
{
	public static void main(String[] args)
	{
		AlgoBuilder j = new AlgoBuilder();
		AlgoBuilder loadTest = new AlgoBuilder();
		
		j.buildTree();
		
		System.out.println("ORIGINAL TREE");
		j.testPrint(0);
		System.out.println("Max Depth: " + j.findTreeDepth());
		
		System.out.println("\nLOADING:");
		
		loadTest.loadTree("TreeSaving.txt");
		loadTest.testPrint(0);
		System.out.println("Max Depth: " + loadTest.findTreeDepth());
		
		System.out.println("\nSwapping a single node:");
		
		j.swapSingleNode("TreeSaving.txt");
		
		System.out.println("\nNEW TREE");
		
		j.testPrint(0);
	}
}