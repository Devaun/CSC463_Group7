public class FarFromWall extends AbFunctions
{
	int sensorNums[];
	
	public FarFromWall()
	{
		numSensors = 1;
		
		sensorNums = new int[numSensors];		
		sensorNums[0] = (int)(Math.random() * 4);
		
		leftChild = build();
		rightChild = null;
	}
}