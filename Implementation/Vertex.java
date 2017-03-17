//FILE:				Vertex.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A container class that stores and manages vertex
//RELATIONSHIP:		Inherit from Component class

public class Vertex extends Component
{	
	//Constants Fields
	public static final String COMREQUIREMENT_SOURCE = "SOURCE";
	public static final String COMREQUIREMENT_TARGET = "TARGET";
	public static final int VISITED = 1;
	public static final int UNVISITED = 0;


	//Class Fields
	private int cost;
	private String comRequirement;
	private int visited;

	//Default Constructor	
	public Vertex()
	{
		super(); //Default construtor in parent's class
		cost = 0; //Assume as default for cost
		comRequirement = ""; //Assume as default for comRequirement
		visited = UNVISITED;
	}
		
	//Alternate Constructor
	public Vertex( String inDeviceType, String inName, double inReliability, int inCost, String inComRequirement ) 
	{
			super( inName, inDeviceType, inReliability );
			setCost( inCost );
			setComRequirement( inComRequirement );
			visited = UNVISITED;
	}
	
	//Copy Constructor
	public Vertex( Vertex inVertex )
	{
		super( inVertex );
		cost = inVertex.getCost();
		comRequirement = inVertex.getComRequirement();
		
	}

	//Accessor - get cost
	public int getCost()
	{
		return cost;
	}
	
	//Accessor - get port type - SOURCE or TARGET
	public String getComRequirement()
	{
		return comRequirement;
	}

	//Accessor - get whether a vertex is marked visited or unvisited
	public int getVisited()
	{
		return visited;
	}
	
	//Mutator - set cost not to be less than zero or equal to zero
	public void setCost( int inCost )
	{
		if( inCost <= 0 )
		{
		 	throw new IllegalArgumentException( "Vertex's cost cant be negative or zero" );
		}
		else
		{
			cost = inCost;
		}
	}
	
	//Mutator - set Communication Requirement to be either SOURCE or TARGET	
	public void setComRequirement( String inComRequirement )
	{
		if( inComRequirement.equals("") || inComRequirement.equals( COMREQUIREMENT_SOURCE ) || inComRequirement.equals( COMREQUIREMENT_TARGET ) )
		{
			comRequirement = inComRequirement;
		}
		else
		{
			throw new IllegalArgumentException( "Communication Requirement is invalid" );
		}
	}

	//Mutator - set visited
	public void setVisited( int inVisited )
	{
		if( inVisited != VISITED && inVisited != UNVISITED )
		{
			throw new IllegalArgumentException( "Visited is invalid" );
		}
		else
		{
			visited = inVisited;
		}
	}

	//PURPOSE:	Returns the vertex stored in the class as a String
	//IMPORT:	None
	//EXPORT:	A string contains details of vertex
	public String toString()
	{
		return new String ( super.toString() + " , Cost: " + cost + ", Communication Requirement: " + comRequirement + ", Visited: " + visited );			
	}

	//PURPOSE:	Check whether a vertex is a SOURCE
	//IMPORT:	None
	//EXPORT:	isSource
	public boolean isSource()
	{
		boolean isSource = false;

		if( comRequirement.equals( COMREQUIREMENT_SOURCE ) )
		{
			isSource = true;
		}
	
		return isSource;
	}

	//PURPOSE:	Check whether a vertex is a TARGET
	//IMPORT:	None
	//EXPORT:	isTarget
	public boolean isTarget()
	{
		boolean isTarget = false;

		if( comRequirement.equals( COMREQUIREMENT_TARGET ) )
		{
			isTarget = true;
		}
	
		return isTarget;
	}


} 	
