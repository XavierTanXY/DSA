// Class file for the the OBDDH diagram.
//
// The idea is that a diagram is created (which loads in the network and then sets up the structures). Then compute
// is called, which gets the class to actually generate all of the nodes and also computes the reliability. Finally,
// the reliability is displayed to the screen.
 
import java.util.*;

// Add code where appropriate but do not change existing code without written permission
//
// This code isn't entirely complete but is usable

class OBDDH
{
  private FloatingPoint reliability;
  private Qc current;
  private Qn next;
  private Network net;

  // The default constructor creates the root node of the OBDDH diagram
  public OBDDH( String filename ) 
  {
    // First load in the network
    net = new Network( filename );

    // Then set up the rest of the structures
    reliability = new FloatingPoint( 0.0 );  // Will be adding to this number
    next = new Qn();                         // Initialize the next queue to be empty
    HNode root = new HNode();                      // Set up the root node of the diagram
    current = new Qc( root );                // Initialize the current queue to contain the root node

    // Start the computation
    compute();
  }

  // Computes the reliability of the network, which must have been initialized before this is called
  private void compute() 
  {
    HNode node, child;
    Edge e;

    while ( !current.isEmpty() ) // Keep going through levels until all nodes are processed
    {
									// HHH - todo - add a try/catch statement here
      e = net.nextEdge(); // Get the edge to be processed for this level
      while ( !current.isEmpty() ) // Work on the current level until all nodes are processed
      {
        node = current.front(); // Remove the first node from the current level
        child = node.makePos( e );
        add( child );
        node.makeNeg( e );      // Transform node into its own negative child
        add( node );
      } // End of loop over current queue

      // We now move on to the next level of the diagram. This means putting the nodes from Qn onto Qc.
      // Qn becomes empty.
      next.swap( current );
      // After the swap, the only way for Qc to be empty is if both Qc and Qn are empty. When this happens we're done.
    }
  }

  // Checks to see if the node is terminal. If not, attempts to add the node to Qn. If terminal and successful, record the
  // reliability.
  private void add( HNode n )
  {
    if ( n.isSuccess() )
    {
      reliability.add( n.getRel() ); // Record the reliability of the successful node. It gets discarded.
    }
    else if ( !n.isFailed() ) // Failure nodes are ignored and discarded.
    {
      if ( next.add( n ) ) 
      {
	// codes counting nodes per level will go here
      }
    }
  }

  // Displays the reliability of the network to the screen
  // Note that the marking scripts expect exactly this format. Students should not write anything to output -
  // the only output should be through this method unless an exception occurs.
  public void displayRel() 
  {
    System.out.println("The reliability of the network is "+reliability.toString());
  }

  /**
   *Initializes the network by calling the constructor to load information from a file
   *I assume that the imported filename is the name with suffix .nt - which is an unsorted file
   *I will change the filename suffix to .srt - which is a sorted file, 
   *and reuse the reading() method to load sorted data from the sorted file
   **/
  private void loadNetwork( String filename ) 
  {
    
    String[] sArray;
    String loadFileName, loadFileSuffix = ".srt"; //File format for sorted
  
    if( filename == null )
    {
      throw new IllegalArgumentException( "File name is null" );
    }
    else
    {
      sArray = filename.split("\\."); //Change the filename's that has been read from .nt to .srt 
      loadFileName = sArray[0];
      net = FileIO.reading( loadFileName + loadFileSuffix );
    }
  }

  // After a new child node has been created this method attempts to add it to next
  private void addToQn( HNode node ) 
  {
    if( node == null )
    {
      throw new IllegalArgumentException( "Node is null" );
    }
    else
    {
      next.insert( node );
    }
  }


} // end of class OBDDH
