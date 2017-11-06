public abstract class AbFunctions
{
	int numSensors;
	
	AbFunctions leftChild;
	AbFunctions rightChild;

	public AbFunctions build()
	{
		int val = (int) ((Math.random() * 12));
		
		switch(val)
		{
			case 0:
				return (new CloseToWall());
			case 1:
				return (new FarFromWall());
			case 2:
				return (new DoTwoFarFar());
			case 3:
				return (new DoTwoCloseClose());
			case 4:
				return (new DoTwoFarClose());
			case 5:
				return (new DoTwoCloseFar());
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
		
		return null;
	}
}