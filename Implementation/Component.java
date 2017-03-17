//FILE:				Component.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A container class that stores and manages component
//RELATIONSHIP:		Parent's class of Vertex and Edge

public class Component
{
	//Contants Fields for both Vertex and Edge type
	public static final String DEVICETYPE_RV325 = "RV325";
	public static final String DEVICETYPE_2921K9 = "2921/k9";
	public static final String DEVICETYPE_1841 = "1841";
	public static final String DEVICETYPE_CAT4 = "CAT4";
	public static final String DEVICETYPE_CAT5 = "CAT5";
	public static final String DEVICETYPE_WIRELESS = "Wireless";
	public static final String DEVICETYPE_SATELITE = "Satelite";

	//Class Fields
	private String name;
	private String deviceType;
	private FloatingPoint reliability;

	//Default Constructor
	public Component()
	{
		name = "No Name"; //Assume as defualt for name
		deviceType = "No Type"; //Assume as defualt for type
		reliability = new FloatingPoint();
	}

	//Alternate Constructor
	public Component( String inName, String inDeviceType, double inReliability )
	{
		setName( inName );
		setDeviceType(inDeviceType);
		reliability = new FloatingPoint( inReliability );	
	}

	//Copy Constructor
	public Component( Component inComponent )
	{
		if( inComponent == null )
		{
			throw new IllegalArgumentException( "Component is null" );
		}
		else
		{
			name = inComponent.getName();
			deviceType = inComponent.getType();
			reliability = inComponent.getRel();
		}
	}

	//Accessor - get name
	public String getName()
	{
		return name;
	}

	//Accessor - get type
	public String getType()
	{
		return deviceType;
	}

	//Accessor - get reliability
	public FloatingPoint getRel()
	{
		return reliability;
	}

	//Mutator - set name
	public void setName( String inName )
	{
		if( inName == null )
		{
			throw new IllegalArgumentException( "Name is invalid" );
		}
		else
		{
			name = inName ;
		}
	}

	//Mutator - set device type
	public void setDeviceType( String inDeviceType )
	{
		if( ( inDeviceType == null )  || !( inDeviceType.equals( DEVICETYPE_CAT4 ) ) && !( inDeviceType.equals( DEVICETYPE_CAT5 ) ) && !( inDeviceType.equals( DEVICETYPE_WIRELESS ) ) && !( inDeviceType.equals( DEVICETYPE_SATELITE) ) && !( inDeviceType.equals( DEVICETYPE_RV325 )  )  && !( inDeviceType.equals( DEVICETYPE_2921K9 )  ) && !( inDeviceType.equals( DEVICETYPE_1841 )  ) )
		{
			throw new IllegalArgumentException( "Invalid Device Type" );
			
		}
		else 
		{
			deviceType = inDeviceType;
			
		}
	}

	//Mutator - set realiability
	public void setReliability( double inReliability )
	{
		reliability.setFloatingPoint( inReliability );
	}

	//PURPOSE:	Returns the component stored in the class as a String
	//IMPORT:	None
	//EXPORT:	A string contains details of component
	public String toString()
	{
		return new String( "Name: " + name + ", Type: " + deviceType + ", Reliability: " + reliability );
	}

}