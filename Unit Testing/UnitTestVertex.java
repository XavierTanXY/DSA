//FILE:				UnitTestVertex.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			Unit testing for Vertex class

public class UnitTestVertex
{	
	public static final int VISITED = 1;
	public static final int UNVISITED = 0;

	public static void main(String args[])
	{ 
		int iNumPassed = 0;
		int iNumTests = 0;

		Vertex vertex;
		String sVertexDeviceType;
		String sVertexName;
		String comRequirement;
		double dVertexReliability;
		int iVertexCost;
		FloatingPoint vRel;
		int visited;

		// Test with normal conditions (shouldn't throw exceptions)
		System.out.println("\n");
		System.out.println("Testing Normal Conditions - Constructor");
		System.out.println("=======================================");

		try
		{   
			iNumTests++;
			System.out.print("Testing creation of Vertex: ");

			sVertexDeviceType = new String("RV325");	//Simply change the device type if wish to test each possible device type
			sVertexName = new String("Test device");
			comRequirement = new String("SOURCE");
			dVertexReliability = 0.9;
			iVertexCost = 150;
			vertex = new Vertex(sVertexDeviceType, sVertexName, dVertexReliability, iVertexCost, comRequirement );
			iNumPassed++;
			System.out.println("passed");

			iNumTests++;
			System.out.print("Testing getComRequirement(): ");
			comRequirement = vertex.getComRequirement();
			if ( !comRequirement.equals("SOURCE") )
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing toString(): ");
			System.out.println( vertex.toString() );
			iNumPassed++;
			System.out.println("passed");

			iNumTests++;
			System.out.print("Testing getCost(): ");
			iVertexCost = vertex.getCost();
			if (iVertexCost != 150 )
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing getVisited(): ");
			visited = vertex.getVisited();
			if (visited != UNVISITED )
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing isSource(): ");
			if ( vertex.isSource() == false )
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			vertex.setComRequirement( "TARGET" );

			iNumTests++;
			System.out.print("Testing isTarget(): ");
			if ( vertex.isTarget() == false )
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Illegal Argument:" + e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println("FAILED");
		}

		// Tests with error conditions (SHOULD throw exceptions)
		// Testing constructor's parameters
		System.out.println("\n");
		System.out.println("Testing Error Conditions - Constructor");
		System.out.println("======================================");

		// Testing Constructor
		// This test would also be redundant if they choose to use enums instead of constants.
		sVertexDeviceType = new String("RV325");
		sVertexName = new String("Test device");
		dVertexReliability = 0.9;
		iVertexCost = 150;
		comRequirement = new String("TARGET");

		try
		{
			iNumTests++;
			System.out.print("Testing negative cost: ");
			iVertexCost = -1;			// Negatives aren't allowed
			vertex = new Vertex(sVertexDeviceType, sVertexName, dVertexReliability, iVertexCost, comRequirement );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }

		try
		{
			iNumTests++;
			System.out.print("Testing zero cost: ");
			iVertexCost = 0;			// Also testing zero, just in case
			vertex = new Vertex(sVertexDeviceType, sVertexName, dVertexReliability, iVertexCost, comRequirement );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		iVertexCost = 150;

		try
		{
			iNumTests++;
			System.out.print("Testing invalid comRequirement: ");
			comRequirement = new String("AA");       
			vertex = new Vertex(sVertexDeviceType, sVertexName, dVertexReliability, iVertexCost, comRequirement );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		comRequirement = new String("TARGET");

		try
		{
			iNumTests++;
			System.out.print("Testing invalid visited: ");
			visited = 2;       
			vertex = new Vertex(sVertexDeviceType, sVertexName, dVertexReliability, iVertexCost, comRequirement );
			vertex.setVisited(2);
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		comRequirement = new String("TARGET");

		System.out.println("\n");
		System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");

	} // End of main
} // End of Class