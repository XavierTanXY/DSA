//FILE:				UnitTestComponent.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			Unit testing for Edge class

public class UnitTestEdge
{	
	public static void main(String args[])
	{
		int iNumPassed = 0;
		int iNumTests = 0;

		Edge edge;
		Vertex fromVertex;
		Vertex toVertex;
		String sEName;
		double dECost;
		double dEReliability;
		String sEDeviceType;
		FloatingPoint eRel;

		// Test with normal conditions (shouldn't throw exceptions)
		System.out.println("\n");
		System.out.println("Testing Normal Conditions - Constructor");
		System.out.println("=======================================");
		
		try
		{   
			iNumTests++;
			System.out.print("Testing creation of Edge: ");

			fromVertex = new Vertex("RV325", "Vertex1", 0.5, 12, "SOURCE" );
			toVertex = new Vertex("1841", "Vertex2", 0.7, 13, "TARGET" );
			sEName = new String("Test Edge");
			dECost = 150.0;
			sEDeviceType = new String("CAT4");	//Simply change the device type if wish to test each possible device type
			dEReliability = 0.75;

			edge = new Edge( fromVertex, toVertex, sEName, dECost, sEDeviceType, dEReliability );
			iNumPassed++;
			System.out.println("passed");

			iNumTests++;
			System.out.print("Testing getFrom(): ");
			Vertex v1 = edge.getFrom();
			if ( !( v1.getName() ).equals(fromVertex.getName()) ) 
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing getTo(): ");
			Vertex v2 = edge.getTo();
			if ( !( v2.getName() ).equals(toVertex.getName()) ) 
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing getCost(): ");
			dECost = edge.getCost();
			if ( Math.abs( dECost - 150.0) > 0.00001) 
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.println("Testing toString(): ");
			System.out.println( edge.toString() );
			iNumPassed++;
			System.out.println("passed");

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
		fromVertex = new Vertex("RV325", "Vertex1", 0.5, 12, "SOURCE" );
		toVertex = new Vertex("1841", "Vertex2", 0.7, 13, "TARGET" );
		sEName = new String("Test Edge");
		dECost = 150.0;
		sEDeviceType = new String("CAT4");	//Simply change the device type if wish totest each possible device type
		dEReliability = 0.75;

		try
		{
			iNumTests++;
			System.out.print("Testing null fromVertex: ");
			fromVertex = null;  
			edge = new Edge( fromVertex, toVertex, sEName, dECost, sEDeviceType, dEReliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		fromVertex = new Vertex("RV325", "Vertex1", 0.5, 12, "SOURCE" );

		try
		{
			iNumTests++;
			System.out.print("Testing null toVertex: ");
			toVertex = null;    
			edge = new Edge( fromVertex, toVertex, sEName, dECost, sEDeviceType, dEReliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		toVertex = new Vertex("1841", "Vertex2", 0.7, 13, "TARGET" );

		try
		{
			iNumTests++;
			System.out.print("Testing null name: ");
			sEName = null;		
			edge = new Edge( fromVertex, toVertex, sEName, dECost, sEDeviceType, dEReliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		sEName = new String("Test Edge");

		try
		{
			iNumTests++;
			System.out.print("Testing negative cost: ");
			dECost = -1.0;			// Negatives aren't allowed
			edge = new Edge( fromVertex, toVertex, sEName, dECost, sEDeviceType, dEReliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }

		try
		{
			iNumTests++;
			sEDeviceType = new String("CAT6");	// Testing a random string that we haven't used
			System.out.print("Testing bad type: ");
			edge = new Edge( fromVertex, toVertex, sEName, dECost, sEDeviceType, dEReliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		sEDeviceType = new String("CAT4");

		
		try
		{
			iNumTests++;
			System.out.print("Testing high reliability: ");
			dEReliability = 1.2;	// Too high
			edge = new Edge( fromVertex, toVertex, sEName, dECost, sEDeviceType, dEReliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		
		try
		{
			iNumTests++;
			System.out.print("Testing negative reliability: ");
			dEReliability = -0.1;	// Too low 
			edge = new Edge( fromVertex, toVertex, sEName, dECost, sEDeviceType, dEReliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		dEReliability = 0.75;

		System.out.println("\n");
		System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");

	} // End of main
} // End of Class