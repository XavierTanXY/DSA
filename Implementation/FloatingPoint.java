//FILE:				FloatingPoint.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A container class that stores and manages floating point

public class FloatingPoint
{
	//Class Fields
	private double floatPoint;
	private static double precision =  0.00000001; 

	//Defaulty Constructor
	public FloatingPoint()
	{
		floatPoint = 0.0;
	}

	//Alternate Constructor
	public FloatingPoint( double inPoint )
	{
		setFloatingPoint( inPoint );
	}

	//Copy Consturctor
	public FloatingPoint( FloatingPoint inNum )
	{
		if( inNum == null )
		{
			throw new IllegalArgumentException( "FloatingPoint is null" );
		}
		else
		{
			floatPoint = inNum.getFloatingPoint();
		}
	}

	//Accessor - get floating point
	public double getFloatingPoint() 
	{
		return floatPoint;
	}

	//Accessor - get precision
	public double getPrecision()
	{
		return precision;
	}
	
	//Mutator - set floating point to be between 0.0 to 1.0
	public void setFloatingPoint( double inPoint )
	{
		if( inPoint < 0.0 || inPoint > 1.0)
		{
			throw new IllegalArgumentException( "Point can not be less than 0 or greater than 1" );
		}
		else
		{
			floatPoint = inPoint;
		}
	}

	//PURPOSE:	Checks to see whether the two numbers are within precision of each other
	//IMPORT:	FloatingPoint object
	//EXPORT:	isEqual
	public boolean isEqual( FloatingPoint inNum ) 
	{
		boolean isEqual = false;
		
		if( inNum == null  )
		{
			throw new NullPointerException( "FloatingPoint is null" );
		}
		else if( Math.abs( floatPoint - inNum.getFloatingPoint() ) < precision )
		{
			isEqual = true;
		}

		return isEqual;
	}

	//Mutator - set precision to the new value but not negative
	public static void setPrecision( double inPrecision )
	{
		if( inPrecision < 0.0 )
		{
			throw new IllegalArgumentException( "Precision can not be negative" );
		}
		else	
		{
			precision = inPrecision;
		}
	}
	
	//PURPOSE:	Adds the given FloatingPoint number to the current one
	//IMPORT:	FloatingPoint object
	//EXPORT:	None
	public void add( FloatingPoint inNum )
	{
		if( inNum == null  )
		{
			throw new NullPointerException( "FloatingPoint is null" );
		}
		else
		{
			floatPoint = floatPoint + inNum.getFloatingPoint();
		}
	}
	
	//PURPOSE:	Adds the given integer to to the current FloatingPoint one
	//IMPORT:	an integer
	//EXPORT:	None
	public void add( int inNum )
	{
		floatPoint = floatPoint + (double)inNum;
	}

	//PURPOSE:	Inverts the floating point number by subracting it from 1
	//IMPORT:	None
	//EXPORT:	None
	public void invert()
	{
		floatPoint = 1.0 - floatPoint;
	}

	//PURPOSE:	Multiplies the given FloatingPoint number into the current one
	//IMPORT:	FloatingPoint object
	//EXPORT:	None
	public void times( FloatingPoint inNum )
	{
		if( inNum.equals( null ) )
		{
			throw new NullPointerException( "FloatingPoint is null" );
		}
		else
		{
			floatPoint = floatPoint * inNum.getFloatingPoint();
		}
	}


	//PURPOSE:	Returns the floating point number stored in the class as a String
	//IMPORT:	None
	//EXPORT:	A string contains floating point number
	public String toString()
	{
		return new String( "" + floatPoint );
	}
	
}



		
