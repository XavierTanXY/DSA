//FILE:				Qc.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A class using queue to store HNodes at the current level of the OBDDH
import java.util.Iterator;

public class Qc
{
	//Class Field - current queue
	private DSAQueue<HNode> currQueue;

	//PURPOSE:	Initializes the structure to be empty apart from the given root node
	//IMPORT:	The root node
	//EXPORT:	Qc object
	public Qc( HNode root )
	{
		if( root == null  )
		{
			throw new IllegalArgumentException( "Root is null" );
		}
		else
		{
			currQueue = new DSAQueue<HNode>();
			insert( root );
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
			throw new IllegalStateException( "Current queue is empty" );
		}
		else
		{
				frontNode = currQueue.dequeue();
		}

		return frontNode;
	}

	//PURPOSE:	Returns true if no nodes remain stored, or false otherwise
	//IMPORT:	None
	//EXPORT:	isEmpty
	public boolean isEmpty()
	{
		boolean isEmpty;

		try
		{ 
			HNode node = currQueue.peek();
			isEmpty = false;
		}
		catch( IllegalStateException e )
		{
			isEmpty = true;
		}

		return isEmpty;
	}

	//PURPOSE:	Inserts the given node to the back of the structure
	//IMPORT:	HNode object
	//EXPORT:	None
	public void insert( HNode node )
	{
		if( node == null  )
		{
			throw new IllegalArgumentException( "Node is null" );
		}
		else
		{
			currQueue.enqueue( node );
		}
	}

	//PURPOSE:	Returns the number of nodes currently stored in this structure
	//IMPORT:	None
	//EXPORT:	number of nodes
	public int numNodes()
	{
		return currQueue.getCount();
	}

	//Accessor - get current queue
	public DSAQueue<HNode> getCurrQueue()
	{
		return currQueue;
	}
	
	
}