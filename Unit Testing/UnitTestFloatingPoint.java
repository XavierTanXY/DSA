//FILE:				UnitTestFloatingPoint.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			Unit testing for FloatingPoint class

public class UnitTestFloatingPoint
{   
	public static void main(String args[])
	{
		int iNumPassed = 0;
		int iNumTests = 0;
		FloatingPoint fp;
		double floatPoint;
		double precision = 0.00000001;

		// Test with normal conditions (shouldn't throw exceptions)
		System.out.println("\n");
		System.out.println("Testing Normal Conditions - Constructor");
		System.out.println("=======================================");

		try
		{   
			iNumTests++;
			System.out.print("Testing creation of FloatingPoint: ");
			floatPoint = 0.2;
			fp = new FloatingPoint(floatPoint);
			iNumPassed++;
			System.out.println("passed");

			iNumTests++;
			System.out.print("Testing setFloatingPoint(0.35): ");
			fp.setFloatingPoint(0.3);   
			iNumPassed++;
			System.out.println("passed");

			iNumTests++;
			System.out.print("Testing getFloatingPoint(): ");
			floatPoint = fp.getFloatingPoint();
			if ( Math.abs( floatPoint - 0.3 ) > 0.00001) 
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing setPrecision(0.0001): ");
			fp.setPrecision(0.0001);   
			iNumPassed++;
			System.out.println("passed");

			iNumTests++;
			System.out.print("Testing getPrecision(): ");
			precision = fp.getPrecision();
			if ( Math.abs( precision - 0.0001 ) > 0.00001) 
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing Copy Constructor: ");
			FloatingPoint fpToCopy = new FloatingPoint(fp);
			iNumPassed++;
			System.out.println("passed");

			iNumTests++;
			System.out.print("Testing isEqual(): ");
			if( !fpToCopy.isEqual(fp) )
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
			System.out.println( fp.toString() );
			iNumPassed++;
			System.out.println("passed");

			System.out.println("\n");
			System.out.println("Testing Other methods");
			System.out.println("===============================================");


			iNumTests++;
			System.out.print( "Testing add( FloatingPoint inNum ): ");
			FloatingPoint fp2 = new FloatingPoint(0.3);
			fp.add(fp2); // 0.3 + 0.3
			if( Math.abs( fp.getFloatingPoint() - 0.6 ) > 0.00001)  
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{ 
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print( "Testing add( int inNum ): ");
			fp.add(1); // 0.6 + 1
			if( Math.abs( fp.getFloatingPoint() - 1.6 ) > 0.00001)  
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{ 
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print( "Testing invert(): ");
			fp.setFloatingPoint(0.3);
			fp.invert(); // 1.0 - 0.3
			if( Math.abs( fp.getFloatingPoint() - 0.7 ) > 0.00001)  
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{ 
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print( "Testing times( FloatingPoint inNum ): ");
			FloatingPoint fp3 = new FloatingPoint(0.1);
			fp.times(fp3); // 0.7 * 0.1
			if( Math.abs( fp.getFloatingPoint() - 0.07 ) > 0.00001)  
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

		try 
		{
			iNumTests++;
			System.out.print("Testing constructor FloatPoint(1.3): ");
			fp = new FloatingPoint(1.3); //Testing upper bound
			System.out.println("FAILED");
		} 
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		fp = new FloatingPoint(0.3);

		try 
		{
			iNumTests++;
			System.out.print("Testing constructor FloatPoint(-0.3): ");
			fp = new FloatingPoint(-0.3); //Testing lower bound
			System.out.println("FAILED");
		} 
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }

		try 
		{
			iNumTests++;
			System.out.print("Testing constructor setPrecision(-0.3): ");
			fp.setPrecision(-0.3); //Testing negative precision
			System.out.println("FAILED");
		} 
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }


		System.out.println("\n");
		System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");







	}
}