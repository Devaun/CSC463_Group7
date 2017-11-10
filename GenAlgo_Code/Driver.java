public class Driver 
{
	public static void main(String[] args)
	{
		AlgoBuilder j = new AlgoBuilder();
		AlgoBuilder loadTest = new AlgoBuilder();

		System.out.println("TREE 1:");
		j.buildTree();
		j.simplePrint();
		j.saveTree("TreeSaving.txt");
		System.out.println("Max Depth: " + j.findTreeDepth());
		
		System.out.println("\nFULL PRINT:");
		j.fullPrint(50, 50);
		
//		j.buildTree();
//		System.out.println("\nTREE 2:");
//		j.simplePrint();
//		
//		System.out.println("\nSWAPPING:");
//		j.swapSingleNode("TreeSaving.txt");
//		
//		System.out.println("\nNEW TREE:");
//		
//		j.simplePrint();
	}
}