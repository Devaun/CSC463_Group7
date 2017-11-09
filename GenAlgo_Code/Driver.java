public class Driver 
{
	public static void main(String[] args)
	{
		AlgoBuilder j = new AlgoBuilder();
		AlgoBuilder loadTest = new AlgoBuilder();
		
		j.buildTree();
		j.testPrint(0);
		
		System.out.println("\nSAVING\n");
		
		j.saveTree("TreeSaving.txt");
		
		System.out.println("\nDONE");
		System.out.println("\nLOADING\n");
		
		loadTest.loadTree("TreeSaving.txt");
		
		loadTest.testPrint(0);
	}
}