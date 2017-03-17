//FILE:				Qn.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A class using queue to store HNodes at the next level of the OBDDH	

public class Qn
{
	//Class Fields
	private DSAQueue<HNode> nextQueue;

	//PURPOSE:	Initializes the structure to be empty
	//IMPORT:	None
	//EXPORT:	Qn object
	public Qn()
	{
		nextQueue = new DSAQueue<HNode>();
	}

	//PURPOSE:	Returns true if no nodes remain stored, or false otherwise
	//IMPORT:	None
	//EXPORT:	isEmpty
	public boolean isEmpty()
	{
		boolean isEmpty;
		
		try
		{ 
			HNode node = nextQueue.peek();
			isEmpty = false;
		}
		catch( IllegalStateException e )
		{
			isEmpty = true;
		}

		return isEmpty;
	}

	//PURPOSE:	Returns the number of nodes currently stored in this structure
	//IMPORT:	None
	//EXPORT:	number of nodes
	public int numNodes()
	{
		return nextQueue.getCount();
	}

	//PURPOSE:	Checks to see if a node equal to the provided node is already stored in the structure
	//IMPORT:	HNode object
	//EXPORT:	true if equal node is found
	public boolean add( HNode node )
	{
		boolean exist = false;

		for( HNode nextNode: nextQueue )
		{
			if( node.isEqual( nextNode ) )
			{
				exist = true;
			}
		}

		return exist;
	}

	//PURPOSE:	Moves all of the nodes stored in this structure and adds them to current, one at a time
	//IMPORT:	Qc object- current level 
	//EXPORT:	None
	public void swap( Qc current )
	{
		
		for( HNode nextNode: nextQueue )
		{
			nextNode = nextQueue.dequeue();
			current.insert( nextNode );
		}

	}

	//PURPOSE:	Inserts the given node to the back of the structure
	//IMPORT:	HNode object
	//EXPORT:	None
	public void insert( HNode node )
	{
		if( node.equals( null ) )
		{
			throw new IllegalArgumentException( "Node is null" );
		}
		else
		{
			nextQueue.enqueue( node );
		}
	}

	//PURPOSE:	Removes the first node in the structure and returns it
	//IMPORT:	None
	//EXPORT:	HNode object
	public HNode front()
	{
		HNode frontNode = null;

		if( isEmpty() )
		{
			throw new IllegalStateException( "Next queue is empty" );
		}
		else
		{
				frontNode = nextQueue.dequeue();
		}

		return frontNode;
	}

	//Accessor - get next queue
	public DSAQueue<HNode> getNextQueue()
	{
		return nextQueue;
	}

}