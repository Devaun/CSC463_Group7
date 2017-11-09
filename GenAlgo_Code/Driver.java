public class Driver 
{
	public static void main(String[] args)
	{
		AlgoBuilder j = new AlgoBuilder();
		AlgoBuilder loadTest = new AlgoBuilder();
		
		System.out.println("TREE 1:");
		j.buildTree();
		j.testPrint(0);
		j.saveTree("TreeSaving.txt");
		System.out.println("Max Depth: " + j.findTreeDepth());
		
		
		j.buildTree();
		System.out.println("\nTREE 2:");
		j.testPrint(0);
		
		System.out.println("\nSWAPPING:");
		j.swapSingleNode("TreeSaving.txt");
		
		System.out.println("\nNEW TREE:");
		
		j.testPrint(0);
	}
}