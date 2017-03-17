//FILE:				Edge.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A container class that stores and manages edge
//RELATIONSHIP:		Inherit from Component class

public class Edge extends Component
{
	//Class Fields and Vertices object
	private Vertex toVertex;
	private Vertex fromVertex;
	private double cost;
	
	//Default Constructor
	public Edge()
	{
		super();
		toVertex = new Vertex();
		fromVertex = new Vertex();
		cost = 0.0; 
	} 
	
	//Alternate Constructor
	public Edge( Vertex inFromVertex, Vertex inToVertex, String inName, double inCost, String inDeviceType, double inReliability )
	{
		super( inName, inDeviceType, inReliability );
		setToVertex( inToVertex );
		setFromVertex( inFromVertex );
		setCost( inCost );
	}
	
	//Copy Constructor
	public Edge( Edge inEdge )
	{
		super( inEdge );
		cost = inEdge.getCost();
		toVertex = inEdge.getTo();
		fromVertex = inEdge.getFrom();
	}

	//Accessor - get To Vertx
	public Vertex getTo()
	{
		return toVertex;
	}

	//Accesor - get From Vertex
	public Vertex getFrom()
	{
		return fromVertex;
	}

	//Accessor - get cost
	public double getCost()
	{
		return cost;
	}
 
	//Mutator - set To Vertex (Right)
	public void setToVertex( Vertex inToVertex )
	{
		if( inToVertex == null  )
		{
			throw new IllegalArgumentException( "To Vertex (Right) is Null, Edge Can't be created" );
		}
		else
		{
			toVertex = new Vertex( inToVertex );
		}
	}
	
	//Mutator - set From Vertex (Left)
	public void setFromVertex( Vertex inFromVertex )
	{
		if( inFromVertex == null  )
		{
			throw new IllegalArgumentException( "From Vertex (Left) is Null, Edge Can't be created" );
		}
		else
		{
			fromVertex = new Vertex( inFromVertex );
		}
	}

	//Mutator - set cost
	public void setCost( double inCost )
	{
		if( inCost < 0.0 )
		{
			throw new IllegalArgumentException( "Edge's cost cant be negative" );
		}
		else
		{
			cost = inCost;
		}
	}

	//PURPOSE:	Returns the edge stored in the class as a String
	//IMPORT:	None
	//EXPORT:	a string contains details of edge
	public String toString()
	{
		return new String( super.toString() + ", FROM: " + fromVertex.getName() + ", TO: " + toVertex.getName() + ", Edge cost: " + cost  );

	}

	

}
		
