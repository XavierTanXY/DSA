//FILE:				UnitTestComponent.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			Unit testing for Component class

public class UnitTestComponent
{
	public static final String DEVICETYPE_RV325 = "RV325";
	public static final String DEVICETYPE_2921K9 = "2921/k9";
	public static final String DEVICETYPE_1841 = "1841";
	public static final String DEVICETYPE_CAT4 = "CAT4";
	public static final String DEVICETYPE_CAT5 = "CAT5";
	public static final String DEVICETYPE_WIRELESS = "Wireless";
	public static final String DEVICETYPE_SATELITE = "Satelite";


	public static void main(String args[])
	{
		int iNumPassed = 0;
		int iNumTests = 0;

		Component comp;
		String deviceType;
		String name;
		double reliability;
		FloatingPoint rel;

		// Test with normal conditions (shouldn't throw exceptions)
		System.out.println("\n");
		System.out.println("Testing Normal Conditions - Constructor");
		System.out.println("=======================================");

		try
		{   
			iNumTests++;
			System.out.print("Testing creation of Component: ");

			deviceType = new String("RV325");	//Simply change the device type if wish to test each possible device type
			name = new String("Test device");
			reliability = 0.9;
			comp = new Component( name, deviceType, reliability );
			iNumPassed++;
			System.out.println("passed");

			iNumTests++;
			System.out.print("Testing getType(): ");
			deviceType = comp.getType();
			if ( !deviceType.equals("RV325") ) 
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing getName(): ");
			name = comp.getName();
			if ( !name.equals("Test device") )
			{
				throw new IllegalArgumentException("FAILED");
			}
			else
			{
				iNumPassed++;
				System.out.println("passed");
			}

			iNumTests++;
			System.out.print("Testing getRel(): ");
			rel = comp.getRel();
			if ( Math.abs( rel.getFloatingPoint() - 0.9 ) > 0.00001) // Adjust for desired accuracy
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
			System.out.println( comp.toString() );
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
		deviceType = new String("RV325");
		name = new String("Test device");
		reliability = 0.9;

		try
		{
			iNumTests++;
			deviceType = new String("RV324");	// Testing a random string that we haven't used
			System.out.print("Testing bad type: ");
			comp = new Component( name, deviceType, reliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		deviceType = new String("RV325");

		try
		{
			iNumTests++;
			System.out.print("Testing null name: ");
			name = null;		
			comp = new Component( name, deviceType, reliability );	         
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		name = new String("Test device");

		
		try
		{
			iNumTests++;
			System.out.print("Testing high reliability: ");
			reliability = 1.2;	// Too high
			comp = new Component( name, deviceType, reliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }

		try
		{
			iNumTests++;
			System.out.print("Testing negative reliability: ");
			reliability = -0.1;	// Too low 
			comp = new Component( name, deviceType, reliability );
			System.out.println("FAILED");
		}
		catch(Exception e) { iNumPassed++; System.out.println("passed"); }
		reliability = 0.9;

		System.out.println("\n");
		System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
	}

}	