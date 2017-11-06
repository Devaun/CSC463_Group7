
public class DoTwoFarClose extends AbFunctions
{
	int sensorNums[];
	
	public DoTwoFarClose()
	{
		numSensors = 2;
		
		sensorNums = new int[numSensors];
		
		sensorNums[0] = (int)(Math.random() * 4);
		sensorNums[1] = (int)(Math.random() * 4);
		
		leftChild = build();
		rightChild = build();
	}
}
