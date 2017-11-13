import java.util.Scanner;

public class Driver 
{
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		boolean cont = true;
		AlgoBuilder tree = new AlgoBuilder();

		int answer = 0;
		int runNum = 1;
		
		tree.buildTree();
		tree.saveTree("TreeSaving.txt");
		
		while(cont)
		{
			System.out.println("Make a Choice:");
			System.out.print("1: Build a Tree\n"
							+"2: Save a tree\n"
							+"3: Load a tree\n"
							+"4: Replace a node\n"
							+"5: Merge\n"
							+"6: Full print\n"
							+"7: End code- ");
			try
			{
				answer = scan.nextInt();
			}
			catch(Exception e)
			{
				
			}
			
			switch(answer)
			{
				case 1:
					tree.buildTree();
					
					System.out.println("Tree #: " + runNum);
					tree.simplePrint();
					
					System.out.println();
					runNum++;
					
					break;
				case 2:
					tree.saveTree("TreeSaving.txt");
					break;
				case 3:
					tree.loadTree("TreeSaving.txt");
					
					System.out.println("Tree #: " + runNum);
					tree.simplePrint();
					
					System.out.println();
					runNum++;
					
					break;
				case 4:
					
					System.out.println("Before Tree:");
					tree.simplePrint();
					System.out.println();
					
					tree.replaceRandomNode();
					
					System.out.println("After Tree");
					tree.simplePrint();
					System.out.println();
					runNum++;
					
					break;
				case 5:
					//Function chooses a random node in the current Tree and a random node from the saved file and changes the current tree
					tree.swapSingleNode("TreeSaving.txt");
					
					System.out.println("Tree #: " + runNum);
					tree.simplePrint();
					
					System.out.println();
					runNum++;
					
					break;
				case 6:
					tree.fullPrint("Output2.txt", 50, 50);
					break;
				case 7:
					cont = false;
					break;
				default:
					break;
			}
		}
		
		scan.close();
	}
}