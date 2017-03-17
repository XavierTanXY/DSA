// Class file for the nodes used by the OBDDH diagram.
 
// Currently mostly stubs to allow testing of team member's code.
// * Will not find any nodes isomorphic (ie won't reduce the diagram).
// * Will find nodes successful or failed once the max level is reached. You can reset
//   the maximum level through the testing method setMaxLevel below.
// * Is missing the state change information that will actually allow it to work properly.


class HNode
{
  private FloatingPoint reliability;
  private boolean success;
  private boolean failure;
  private int level;                                  // For initial testing only
  private final static int DEFAULT_MAX_LEVEL = 12;    // For initial testing only
  private static int max_level;                       // For initial testing only

  // The default constructor creates the root node of the OBDDH diagram
  public HNode() 
  {
    reliability = new FloatingPoint( 1.0 );
    success = false;
    failure = false;
    level = 0;
    max_level = DEFAULT_MAX_LEVEL;
  }

  // Testing function to set max_level to something different. For testing, set max_level
  // equal to the number of edges in the network.
  public static void setMaxLevel( int val )
  {
    max_level = val;
  }

  // Returns true if the given node is equal (isomorphic) to the current node
  public boolean isEqual( HNode node )
  {
    return false; // Currently will never detect equality - this means no isomorphism will occur
  }

  // Merges the current node into the provided node. The current node should not be used further after this.
  public void mergeInto( HNode node )
  {
    node.reliability.add( reliability );        // Increment the reliability of the other node
  }

  // Creates a new node that is the positive child of the current one
  public HNode makePos( Edge e ) 
  {
    HNode pos = new HNode();                    // Create a HNode with initial reliability of 1
    pos.reliability.times( e.getRel() );        // Multiply by the change that the edge is available
    pos.reliability.times( reliability );       // Multiply by this node's reliability to get the child reliability
    level++;                                    // Increase the level of the node
    if ( level == max_level )
    {
      success = true;
    }
    return pos;
  }

  // Turns the current node into its own negative child. This is done as an optimization since the node would
  // otherwise be discarded after the negative child was created.
  public void makeNeg( Edge e ) 
  {
    FloatingPoint factor = new FloatingPoint( e.getRel() );     // Get the reliability of the edge
    factor.invert();                                            // Invert it for the chance of failure
    reliability.times( factor );                                // Calculate the probability of the negative child 
    level++;                                                    // Increase the level of the node
    if ( level == max_level )
    {
      failure = true;
    }
  }

//***********//
// Accessors //
//***********//

  // Returns true only if the node is successful. A node is considered successful if it represents a state where the source
  // is connected to the target
  public boolean isSuccess()
  {
    return success;
  } 

  // Returns true only if the node is failed. A node is considered failed if it represents a state of the network where the
  // source is unable to connect to the target
  public boolean isFailed() 
  {
    return failure;
  }

  // Returns the reliability of the node
  public FloatingPoint getRel() 
  {
    return reliability;
  }

// Note that there are no direct set mutators since there is no legitimate need for them.
} // End of the class HNode
