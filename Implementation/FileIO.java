//FILE:             FileIO.java
//AUTHOR:           Xhien Yi Tan ( Xavier )
//ID:               18249833
//UNIT:             Data Structures and Algorithms 120
//PURPOSE:          A class is responsible of reading from a file and writing to a file 
import java.io.*;
import java.lang.String;
import java.lang.Float;
import java.util.StringTokenizer;

public class FileIO
{
    //PURPOSE:	Reading the File and Porcess Edges and Vertices
	//IMPORT:	A file name
	//EXPORT:	Network that has been read from a file
	public static Network reading( String filename )
	{
		//Objects Fields
		Network network = new Network();
		DSAQueue<Edge> edgeQueue = new DSAQueue<Edge>(); //A queue that is used to store Edge
		DSALinkedList<Vertex> vertexList = new DSALinkedList<Vertex>(); //A list that is used to store Vertex
		DSAQueue<String> vertexLine = new DSAQueue<String>(); //Queues that store each vertex line
		DSAQueue<String> edgeLine = new DSAQueue<String>(); //Queues that store each edge line

		Vertex vertex;
		Edge edge;

		boolean hasSource = false, hasTarget = false; //Checking whether there is at least one SOURCE among the Vertices
		String strLine; //File line
		String[] names; //Storage for comparing duplicate vertex name
		int totalLine = 0; //To keep track of total lines in the file
		int edgeCount = 0, vertexCount = 0; //To keep track of total edges and vertices

		try{

			FileInputStream fstream = new FileInputStream(filename);  //Open the file
			DataInputStream in = new DataInputStream(fstream); //Create a reader to read the stream
			BufferedReader br = new BufferedReader(new InputStreamReader(in)); //To read the stream one line at a time
			
			//Read File Line By Line
			while ( ( strLine = br.readLine() ) != null ) 	
			{
				if( strLine.charAt(1) == 'V' ) //Counting vertex when file name starts with V
				{	
					vertexCount++;
					vertexLine.enqueue( strLine ); //Enqueue vertex line to queue for later use
				}
				else if( strLine.charAt(1) == 'E' ) //Counting Edge when file name starts with E
				{
					edgeCount++;
					edgeLine.enqueue( strLine ); //Enqueue edge line to queue for later use
				}
				totalLine++;	//Total lines in the file	
			}
			
			//Checking for total lines excluding the <NET> and </NET> tags	
			if( vertexCount + edgeCount + 2 != totalLine )
			{
				throw new IllegalArgumentException( "File Line invalid or File is empty" );
			}

			names = new String[vertexCount]; //Creates array using total vertex count
			
			for(int i = 0; i < vertexCount; i++) //Deals with Vertex
			{
				strLine = vertexLine.dequeue(); //Dequeue vertex line from queue
				vertex = processVertex( strLine ); //Process that vertex

				if( vertex.isSource() )  //Checking there is a SOURCE
				{
					hasSource = true; //true if there is a SOURCE
				}

				if( vertex.isTarget() )  //Checking there is a TARGET
				{
					hasTarget = true; //true if there is a TARGET
				}

				for( int k = 0; k < names.length; k++ ) //Checking for duplicate name for vertex
				{
					if( ( vertex.getName() ).equals( names[k] ) )
					{
						throw new IllegalArgumentException("Duplicate Vertex name is found: " + vertex.getName() );
					}
				}

				names[i] = vertex.getName(); //Places vertex name in the array for comparison
				vertexList.insertLast(vertex); //Placing vertex into final list		
			}

			if( hasSource == false ) //If there is no SOURCE
			{
				throw new IllegalArgumentException("No Source is detected");
			}

			if( hasTarget == false ) //If there is no TARGET
			{
				throw new IllegalArgumentException("No Target is detected");
			}

			for( int j = 0; j < edgeCount; j++ ) //Deals with Edge
			{
				strLine = edgeLine.dequeue(); //Dequeue edge line from queue
				edge = processEdge( strLine, vertexList ); //Process that edge along with the list of vertex
				edgeQueue.enqueue(edge); //Enqueue edge into the filnal queue
			}
				
			network = new Network( edgeQueue, vertexList ); //Set edge queue and vertex list into Network 
			System.out.println( "Finish reading!" );

			in.close(); //Close File
		
			
		}
		catch ( IOException e ) //Catch IO exception if any
		{
				System.out.println( "Error in file processing: " + e.getMessage() );
		}
	
		return network; //Return Network Object for later use
	}

	//PURPOSE:	Process Each Vertex that has been read
	//IMPORT:	String Line that has been read AND Line that has the details of Vertex
	//EXPORT:	Each Vertex
	private static Vertex processVertex( String strLine )
	{
		//Intialising String Tokenizer
		String thisToken = null;
		StringTokenizer strTok;

		//Class fields for Vertex 
		Vertex vertex;
		double reliability = -0.1;
		String deviceType = null, comRequirement = "", finalName = null, finalTypeName = null; //Initialise in this way so that it would throw exception if none of these is detected from file
		int cost = -1;

		//Class fieds for temporary storage
		String tempName, splitName, name;
		String[] nameArray;
		
		int tokenPosition = 1; //Keep tracking of the position of the token, which starts from 2 - the second token
		
		strTok = new StringTokenizer( strLine," " ); //Creates a StringTokenizer using string line

		while( strTok.hasMoreTokens() )		
		{
			tokenPosition++;
			thisToken = strTok.nextToken(); //Get the next token every time
			char token = thisToken.charAt(0); //Get the first character of the token
			
			switch(token)
			{
				//Deals with RELIABILITY
				case 'R':
					
					reliability = Double.parseDouble( thisToken.substring(4) );	
					break;

				//Deals with TYPE and TARGET
				case 'T':

					if( thisToken.charAt(1) == 'Y' ) //Deals with TYPE
					{
						tempName = thisToken.substring(6); //Get the 6th onwards String
						nameArray = tempName.split("\"",0); //Split the name with ""
						finalTypeName = nameArray[0]; //Get the final name for TYPE
					}
					else if( thisToken.charAt(1) == 'A'  ) //Deals with TARGET
					{
						comRequirement = Vertex.COMREQUIREMENT_TARGET;
					}
					break;
				
				//Deals with COST
				case 'C':
					cost = Integer.parseInt( thisToken.substring(5) );
					break;
				
				//Deals with Vertex Name
				case 'N':
					name = thisToken.substring(6); //Get the 6th onwards String
					nameArray = name.split("\"",0); //Splits the quotes from the name if any quotes are involved
					splitName = nameArray[0]; 

					/* 
						Creates a temporary token from the initial StringTokenizer 
						so that it does not alter the internal state of the initial Tokenizer
					*/
					StringTokenizer tempToken = new StringTokenizer(strLine," ");
				
					//Using the temporary token to get to the same position as initial Tokenizer
					String newToken = getNewTokenPosition( tempToken, tokenPosition);

					//Check whether next token is still a name
					finalName = checkName( newToken, splitName );	
					break;
				
				//Deals with SOURCE
				case 'S':
					comRequirement = Vertex.COMREQUIREMENT_SOURCE;
					break;
			}
		}

		vertex = new Vertex( finalTypeName, finalName, reliability, cost, comRequirement );

		return vertex; //Return vertex	

	}

	//PURPOSE:	Using temporary token to get the position of the initial Tokenizer
	//IMPORT:	TemporaryToken and initial Tokennizer's position
	//EXPORT:	New token
	private static String getNewTokenPosition( StringTokenizer tempToken, int tokenPosition )
	{
		String newToken = null;

		for(int j = 0; j < tokenPosition ; j++)
		{
			newToken = tempToken.nextToken();
		}	
		return newToken;
	}

	//PURPOSE:	Checks whether next token is still a name
	//IMPORT:	Current token and name
	//EXPORT:	The final name
	private static String checkName( String token, String name )
	{
		String finalName, tempName;
		String[] nameArray;

		if( token.charAt(0) != '/' && token.charAt(0) != 'T' && token.charAt(0) != 'C' && token.charAt(0) != 'S' && token.charAt(0) != 'R' )
		{
			tempName = name + " " + token.substring(0) ;
			nameArray = tempName.split("\"", 0) ; //Split any quotes if there is any in the name
			finalName = nameArray[0];
		
		}
		else
		{
			finalName = name;
		}


		return finalName;
	}

	//PURPOSE:	Process each Edge that has been read
	//IMPORT:	String line that has been read and the Vertex list
	//EXPORT:	Each Edge Object
	private static Edge processEdge( String strLine, DSALinkedList<Vertex> vertexList )
	{
		//Declaring StringTokenizer
		String thisToken = null;
		StringTokenizer strTok;

		//Declaring Edge object and its class fields for storing
		Edge edge;	
		double reliability = -0.1, cost = -0.1; //Initialise in this way so that it would throw exception if none of these is detected from file
		String deviceType = null, name = null;
		Vertex toVertex = null, fromVertex = null;

		//Variables for temporary storage
		String tempName, fromPoint, toPoint, finalTypeName, finalFromName, finalToName, finalName = null;
		String[] nameArray;
		char token;
		

		int tokenPosition = 1; //Keep tracking of the position of the token, which starts from 2 second token
		
		strTok = new StringTokenizer( strLine," " );
	
		while( strTok.hasMoreTokens() )		
		{
			tokenPosition++;
			thisToken = strTok.nextToken();
			token = thisToken.charAt(0);
	
			switch(token)
			{
				//Deals with RELIABILITY
				case 'R':
					reliability = Double.parseDouble( thisToken.substring(4) );	
					break;
				
				//Deals with TYPE and TO
				case 'T':
					if( thisToken.charAt(1) == 'Y' ) //Deals with TYPE
					{
						tempName = thisToken.substring(6);
						nameArray = tempName.split("\"",0);
						finalTypeName = nameArray[0];
						deviceType = finalTypeName;
					}
					else if( thisToken.charAt(1) == 'O'  ) //Deals with TO
					{
							toPoint = thisToken.substring(4);

							/* 
								Creates a temporary token from the initial StringTokenizer 
								so that it does not alter the internal state of the initial Tokenizer
							*/
							StringTokenizer tmpToken = new StringTokenizer(strLine," ");

							//Using the temporary token to get to the same position as initial Tokenizer
							String newToken = getNewTokenPosition( tmpToken, tokenPosition);
							
							if( newToken.charAt(0) != 'F' && newToken.charAt(0) != 'C' && newToken.charAt(0) != 'N' && newToken.charAt(0) != 'R')
							{
								tempName = toPoint + " " + newToken;
								nameArray = tempName.split("\"", 0) ;
							}
							else
							{
								nameArray = toPoint.split("\"", 0) ;
							}

							finalToName = nameArray[0];
						
							toVertex = nameMatch( finalToName, vertexList );

							if( toVertex == null )
							{
								throw new IllegalArgumentException( "No To Vertex is matched in the edge" );
							}
					}
					break;
				
				//Deals with COST
				case 'C':
					cost = Double.parseDouble( thisToken.substring(5) );
					break;
				
				//Deals with NAME
				case 'N':
					name = thisToken.substring(6);
		
					nameArray = name.split("\"", 0);
					finalName = nameArray[0];
					break;
				
				//Deals with FROM
				case 'F':
					fromPoint = thisToken.substring(6);

					/* 
						Creates a temporary token from the initial StringTokenizer 
						so that it does not alter the internal state of the initial Tokenizer
					*/
					StringTokenizer tmpToken = new StringTokenizer(strLine," ");
					String newToken = getNewTokenPosition( tmpToken, tokenPosition );

					if( newToken.charAt(0)  != 'T' )
					{
						tempName = fromPoint + " " + newToken;
						nameArray = tempName.split("\"", 0) ;
						
					}
					else
					{	
						nameArray = fromPoint.split("\"", 0) ;
					}

					finalFromName = nameArray[0];
					fromVertex = nameMatch( finalFromName, vertexList );

					if( fromVertex == null )
					{
						throw new IllegalArgumentException( "No From Vertex is matched in the edge" );
					}
					break;
			}
	
		}
		
	edge = new Edge( fromVertex, toVertex, finalName, cost, deviceType, reliability );
		
	return edge;

	}

	//PURPSOE:	Matching fromPoint or toPoint to Vertex
	//IMPORT:	nameToCompare, vertex list
	//EXPORT:	Vertex that has the same name
	private static Vertex nameMatch( String nameToCompare, DSALinkedList<Vertex> vertexList ) 
	{
		Vertex retVal = null;

		for( Vertex vertex: vertexList )
		{
			if( ( vertex.getName() ).equals( nameToCompare ) )
			{
				retVal = vertex;
			}
		}

		return retVal;
	}
	
	//PURPOSE:	Writing to a file from Ntework objects by changing the file format or suffix
	//IMPORT:	Network object, filename
	//EXPORT:	None
	public static void writing( Network network, String filename )
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw;
		String[] sArray;
		String saveFileName, saveFileSuffix = ".srt"; //File format to save 
		DSALinkedList<Vertex> vertexList ;
		DSAQueue<Edge> edgeQueue;

		try
		{	
			sArray = filename.split("\\."); //Change the filename's that has been read from .nt to .srt 
			saveFileName = sArray[0];

			fileStrm = new FileOutputStream( saveFileName + saveFileSuffix ); //Creates FileOutputStream
			pw = new PrintWriter( fileStrm ); //Creates a PrinterWriter for writing

			//Creates Data Structure from Network 
			vertexList = network.getVertexList();
			edgeQueue = network.getEdgeQueue();
			
			pw.println( "<NET>" ); //Prints the first TAG

			//Prints Vertex Line by Line
			for( Vertex vertex: vertexList )
			{
				pw.println( "<VERTEX " + "REL=" + vertex.getRel() + " TYPE=\"" + vertex.getType() + "\" COST=" + vertex.getCost() + " NAME=\"" + vertex.getName() + "\" " + vertex.getComRequirement() + " />" );
			}

			//Prints Edge Line by Line
			for( Edge edge: edgeQueue )
			{
				Vertex fromVertex = edge.getFrom();
				Vertex toVertex = edge.getTo();
				pw.println( "<EDGE " + "FROM=\"" + fromVertex.getName() + "\" TO=\"" + toVertex.getName() + "\" REL=" + edge.getRel() + " TYPE=\"" + edge.getType() +  "\" COST=" + edge.getCost() + " NAME=\"" + edge.getName() + "\" />" );
			}

			pw.println( "</NET>" ); //Prints the last TAG

			pw.close(); //Close the print writer
		}
		catch( IOException e ) //Catch IOException if there is any
		{
			System.out.println("Error in Writing : " + e.getMessage() );
		}
		catch( Exception e2 )
		{
			System.out.println("Error: " + e2.getMessage() );
		}
	}	

}
