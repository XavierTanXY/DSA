//FILE:				Network.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A container class that stores and manages network and sorting
import java.util.StringTokenizer;
import java.io.*;

public class Network
{
	//Contants Fields
	public static final int VISITED = 1;
	public static final int UNVISITED = 0;

	//Class Fields
	private DSALinkedList<Vertex> vertexList;
	private DSAQueue<Edge> edgeQueue;

	//Default Constructor
	public Network()
	{
		edgeQueue = new DSAQueue<Edge>();
		vertexList = new DSALinkedList<Vertex>();
	}

	//Alternate Constructor
	public Network( DSAQueue<Edge> inEdgeQueue, DSALinkedList<Vertex> inVertexList )
	{
		setEdgeQueue( inEdgeQueue );
		setVertexList( inVertexList );
	}

	//Accessor - get Edge Queue
	public DSAQueue<Edge> getEdgeQueue()
	{
		return edgeQueue;
	}

	//Accessor - get Vertex list
	public DSALinkedList<Vertex> getVertexList()
	{
		return vertexList;
	}

	//Accessor - get the length of Edge Queue
	public int getTotalEdge()
	{
		return edgeQueue.getCount();
	}

	//Accessor - get the length of Vertex list
	public int getTotalVertex()
	{
		//return vertexArray.length;
		return vertexList.getCount();
	}
	
	//Mutator - set Edge Queue
	public void setEdgeQueue( DSAQueue<Edge> inEdgeQueue )
	{
		if( inEdgeQueue == null  )
		{
			throw new IllegalArgumentException( "Edge queue is null" );
		}
		else
		{
			edgeQueue = inEdgeQueue;
		}
	}

	//Mutator - set Vertex List
	public void setVertexList( DSALinkedList<Vertex> inVertexList )
	{
		if( inVertexList == null  )
		{
			throw new IllegalArgumentException( "Vertex list is null" );
		}
		else
		{
			vertexList = inVertexList;
		}
	}

	//Mutator - set Network using Edge Queue and Vertex List
	public void setNetwork( DSAQueue<Edge> inEdgeQueue, DSALinkedList<Vertex> inVertexList  )
	{	
		setEdgeQueue( inEdgeQueue );
		setVertexList( inVertexList );
	}

	//PURPOSE:	Returns the next edge stored in the network
	//IMPORT:	None
	//EXPORT:	Edge object
	public Edge nextEdge()
	{
		Edge edge = null;

		try
		{
			edge = edgeQueue.dequeue();
		}
		catch( IllegalArgumentException e )
		{

			System.out.println("Error: " + e.getMessage() );
		}
		return edge ;
	}

	//PURPOSE:	Accepts file name and reads the contents of the network from a network file
	//IMPORT:	A filename
	//EXPORT:	An Network object
	public Network( String filename )
	{
		Network nt;
		String extension;

		extension = filename.substring( filename.lastIndexOf(".") + 1, filename.length() ); //Getting the filename suffix

		try{

				nt = FileIO.reading( filename ); //Reading file from IO Class and returns Network object
			
				setNetwork( nt.getEdgeQueue(), nt.getVertexList() ); //Set the returned Network into this class
				
				if( extension.equals("nt") ) //If file is unsorted
				{
					sort(); //Sorts the Network
					FileIO.writing( this, filename ); //Writing sorted Network to a file for later use
				}
				else if( extension.equals("srt") ) //If file is sorted
				{
					System.out.println( "This file is sorted!" );
				}
			
		}
		catch( IllegalArgumentException e )
		{
			System.out.println( "Error: " + e.getMessage() );
		}
		catch( Exception e2 )
		{
			System.out.println( "Error: " + e2.getMessage() );
		}
	}

	//PURPOSE:	Write the network to the screen in a sensible fashion
	//IMPORT:	None
	//EXPORT:	None
	public void display()
	{
		Vertex fromVertex, toVertex;
			
		//Display line by line
		for( Edge edge: edgeQueue )
		{
			fromVertex = edge.getFrom();
			toVertex = edge.getTo();
			System.out.println( fromVertex.getName() + " ----- " + edge.getName() + " ----- " + toVertex.getName() );
		}
	}

	//PURPOSE:	Sort the network using several methods
	//IMPORT:	None
	//EXPORT:	None
	private void sort()
	{
		DSALinkedList<Vertex> sortingVertexList = new DSALinkedList<Vertex>();
		DSAQueue<Vertex> queue;
		DSALinkedList<Vertex> sortedVertexList;
					
		queue = calcSource();
		sortedVertexList = sortVertexRecurse(queue, sortingVertexList);
		sortEdge(sortedVertexList);
	}


	//PURPOSE:	Find SOURCE and enqueue to the queue for vertex sorting
	//IMPORT:	None
	//EXPORT:	Queue that has vertices which are 1 path away from the SOURCE
	private DSAQueue<Vertex> calcSource()
	{
		DSAQueue<Vertex> queue = new DSAQueue<Vertex>();
		int path;
		Vertex fVertex, tVertex, nestedFVertex, nestedTVertex;

		for( Edge edge: edgeQueue ) //Looping through the edges that has been read from an unsorted file
		{
			fVertex = edge.getFrom();
			tVertex = edge.getTo();

			if( fVertex.isSource() && fVertex.getVisited() == UNVISITED ) //Looking for unvisited SOURCE in the edge queue
			{
				//Once SOURCE is found, set the state into VISITED and Path = 0
				//fVertex.setPath(0); 
				fVertex.setVisited(VISITED);

				queue.enqueue(fVertex); //Enqueue the SOURCE vertex to the queue
	
				for( Edge nestedEdge: edgeQueue ) //Again, looping through the edges to change the states of the vertices whose state or path has just changed
				{
					nestedFVertex = nestedEdge.getFrom();
					nestedTVertex = nestedEdge.getTo();

					//If one of the vertices from this Edge is equal to the SOURCE, change its states to be SOURCE's states
					if( ( nestedFVertex.getName() ).equals( fVertex.getName() ) ) 
					{
						nestedFVertex.setVisited( VISITED );
					}

					//If one of the vertices from this Edge is equal to the SOURCE, change its states to be SOURCE's states
					if( ( nestedTVertex.getName() ).equals( fVertex.getName() ) )
					{
						nestedTVertex.setVisited( VISITED );
					}

				}
			}
		}

		return queue;
	}

	/*PURPOSE:	Calculate the path for vertices by using recusive call 
				to dequeue each vertex to find the other vertex which is 
				connected from the vertice which has been just dequeue.
				The idea of this comes from Breath First Search, but not exactly the same as BFS*/
	//IMPORT:	Queue that contain SOURCE, List to store sorted vertices
	//EXPORT:	Sorted vertex list
	private DSALinkedList<Vertex> sortVertexRecurse( DSAQueue<Vertex> queue, DSALinkedList<Vertex> sortingVertexList )
	{
		Vertex v, fromV, toV;

		if( queue.isEmpty() ) //Base case hits when the queue is empty
		{
			setVertexList( sortingVertexList ); //Set to this class
		}
		else
		{
			v = queue.dequeue(); //Dequeue first vertex
			sortingVertexList.insertLast(v); //Insert to the list 
			
			for( Edge e: edgeQueue ) //Looping through the edges to find vertex which is connected from the vertex that has just been dequeue
			{
				fromV = e.getFrom();
				toV = e.getTo();

				if( ( fromV.getName() ).equals( v.getName() ) )
				{
					if( toV.getVisited() == 0 ) //If found and is not visited
					{
							toV.setVisited(VISITED);
			
							queue.enqueue( toV ); //Enqueue to the queue in order to find vertex which is connected from the vertex that has just been enqueue

							iterateEdge( fromV, toV );//Looping through the edges to change the states of the vertices whose state has just changed
					}

				}

			}

			sortVertexRecurse( queue, sortingVertexList ); //Recursive call
		}

		return sortingVertexList;
	}

	//PURPOSE:	A method that is used to change the states of the vertices in the edges 
	//IMPORT:	fromVertex(Left), toVertex(Right), path
	//EXPORT:	None
	private void iterateEdge( Vertex fromVertex, Vertex toVertex ) 
	{
		Vertex fVertex;
		Vertex tVertex;
		for( Edge edge: edgeQueue )
		{
		
			fVertex = edge.getFrom();
			tVertex = edge.getTo();

			if( ( fVertex.getName() ).equals( fromVertex.getName() ) )
			{
				fVertex.setVisited(VISITED);
			}

			if( ( fVertex.getName() ).equals( toVertex.getName() ) )
			{
				fVertex.setVisited(VISITED);
			}

			if( ( tVertex.getName() ).equals( toVertex.getName() ) )
			{
				tVertex.setVisited(VISITED);
			}	

			if( ( tVertex.getName() ).equals( fromVertex.getName() ) )
			{
				tVertex.setVisited(VISITED);
			}
		}
	}

	//PURPOSE:	Sort edge by using the sorted vertex list
	//IMPORT:	sorted vertex list
	//EXPORT:	None
	private void sortEdge( DSALinkedList<Vertex> sortedVertexList )
	{
		DSAQueue<Edge> sortedEdgeQueue = new DSAQueue<Edge>();
		String fromVertexName, toVertexName;
		Vertex fVertex, tVertex;
		
	
		for( Vertex fromVertex: sortedVertexList ) //Using the first vertex in the list to match the other vertices
		{
			fromVertexName = fromVertex.getName();

			for( Vertex toVertex: sortedVertexList )
			{
				toVertexName = toVertex.getName();

				for( Edge edge: edgeQueue )
				{
					fVertex = edge.getFrom();
					tVertex = edge.getTo();

					//If found, enqueue the edge into the sortedEdge queue
					if( ( fVertex.getName() ).equals( fromVertexName ) && ( ( tVertex.getName() ).equals( toVertexName ) ) )
					{
						sortedEdgeQueue.enqueue(edge);
							
					}
				}

			}
		
			

		}
		
		
		setEdgeQueue(sortedEdgeQueue); //Set to this class
		
		

	}
}

