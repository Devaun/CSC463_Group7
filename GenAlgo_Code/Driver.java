
public class Driver 
{
	public static void main(String[] args) 
	{
		AlgoBuilder j = new AlgoBuilder();
		
		j.testPrint(0);
		
		System.out.println("Max Depth = " + j.findDepth(0));
		
		System.out.println("\n\nREPLACEING PARTS\n");
		
		j.replace();
		j.testPrint(0);
	}
}