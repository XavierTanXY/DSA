//FILE:           UnitTestNetwork.java
//AUTHOR:         Xhien Yi Tan ( Xavier )
//ID:             18249833
//UNIT:           Data Structures and Algorithms 120
//PURPOSE:        Unit testing for Network class

public class UnitTestNetwork
{	
   public static void main(String args[])
   {
      int iNumPassed = 0;
      int iNumTests = 0;

      Network network;
      Edge e1, e2, e3, e4, e5;
      Vertex v1, v2, v3, v4;
      DSALinkedList<Vertex> vertexList;
      DSAQueue<Edge> edgeQueue;
      
      // Test with normal conditions (shouldn't throw exceptions)
      System.out.println("\n");
      System.out.println("Testing Normal Conditions - Constructor");
      System.out.println("=======================================");
      try
      {   
         iNumTests++;
         System.out.print("Testing creation of Network: ");

         v1 = new Vertex("RV325", "v1", 0.6, 123, "SOURCE");
         v2 = new Vertex("RV325", "v2", 0.6, 123, "SOURCE"); //For Testing only
         v3 = new Vertex("RV325", "v3", 0.6, 123, "SOURCE"); 
         v4 = new Vertex("RV325", "v4", 0.6, 123, "TARGET");
         vertexList = new DSALinkedList<Vertex>();
         vertexList.insertLast(v1);
         vertexList.insertLast(v2);
         vertexList.insertLast(v3);
         vertexList.insertLast(v4);
         e1 = new Edge( v1, v2, "edge1", 0.6, "CAT4", 0.6  ); //For Testing only
         e2 = new Edge( v1, v3, "edge2", 0.6, "CAT4", 0.6 );
         e3 = new Edge( v2, v3, "edge3", 0.6, "CAT4", 0.6  );
         e4 = new Edge( v2, v4, "edge4", 0.6, "CAT4", 0.6 );
         e5 = new Edge( v3, v4, "edge5", 0.6, "CAT4", 0.6 );
         edgeQueue = new DSAQueue<Edge>();
         edgeQueue.enqueue(e1);      
         edgeQueue.enqueue(e2);   
         edgeQueue.enqueue(e3);      
         edgeQueue.enqueue(e4); 
         edgeQueue.enqueue(e5);      
         network = new Network(edgeQueue,vertexList); 
         iNumPassed++;
         System.out.println("passed");

         iNumTests++;
         System.out.print("Testing getEdgeQueue(): ");
         edgeQueue = network.getEdgeQueue();
         if ( edgeQueue == null)  
         {
           throw new IllegalArgumentException("FAILED");
         }
         else
         {
            iNumPassed++;
            System.out.println("passed");
         }

         iNumTests++;
         System.out.print("Testing getVertexList(): ");
         vertexList = network.getVertexList();
         if ( vertexList == null )
         {
           throw new IllegalArgumentException("FAILED");
         }
         else
         {
            iNumPassed++;
            System.out.println("passed");
         }

         iNumTests++;
         System.out.print("Testing setNetwork(): ");
	      network.setNetwork( edgeQueue, vertexList );
         if( !( network.getEdgeQueue() ).equals(edgeQueue) && !( network.getVertexList() ).equals( vertexList ) )
         {
            throw new IllegalArgumentException("FAILED");
         }
         else
         {
            iNumPassed++;
            System.out.println("passed");
         }

         iNumTests++;
         System.out.print("Testing getTotalEdge() ");
         if (  network.getTotalEdge() != 5 )
         {
           throw new IllegalArgumentException("FAILED");
         }
         else
         {
            iNumPassed++;
            System.out.println("passed");
         }

         iNumTests++;
         System.out.print("Testing getTotalVertex(): ");
         if ( network.getTotalVertex() != 4 ) // Adjust for desired accuracy
         {
           throw new IllegalArgumentException("FAILED");
         }
         else
         {
            iNumPassed++;
            System.out.println("passed");
         }

         iNumTests++;
         System.out.println("Testing display(): ");
         network.display();
         iNumPassed++;
         System.out.println("passed");

         iNumTests++;
         System.out.print("Testing nextEdge(): ");
         if ( !( network.nextEdge() ).equals(e1) ) // Adjust for desired accuracy
         {
           throw new IllegalArgumentException("FAILED");
         }
         else
         {
            iNumPassed++;
            System.out.println("passed");
         }

      }
      catch(IllegalArgumentException e)
      {
         System.out.println("Illegal Argument:" + e.getMessage());
      }
      catch(Exception e)
      {
         System.out.println("FAILED");
      }

      // Tests with error conditions (SHOULD throw exceptions)
      // Testing constructor's parameters of ore type and units.
      System.out.println("\n");
      System.out.println("Testing Error Conditions - Constructor");
      System.out.println("======================================");

      // Testing Constructor - we don't have much else to test
      // This test would also be redundant if they choose to use enums instead of constants.
      v1 = new Vertex("RV325", "v1", 0.6, 123, "SOURCE");
      v2 = new Vertex("RV325", "v2", 0.6, 123, "SOURCE"); //For Testing only
      v3 = new Vertex("RV325", "v3", 0.6, 123, "SOURCE"); //For Testing only
      v4 = new Vertex("RV325", "v4", 0.6, 123, "TARGET");
      vertexList = new DSALinkedList<Vertex>();
      vertexList.insertLast(v1);
      vertexList.insertLast(v2);
      vertexList.insertLast(v3);
      vertexList.insertLast(v4);
      e1 = new Edge( v1, v2, "edge1", 0.6, "CAT4", 0.6  );
      e2 = new Edge( v1, v3, "edge2", 0.6, "CAT4", 0.6 );
      e3 = new Edge( v2, v3, "edge3", 0.6, "CAT4", 0.6  );
      e4 = new Edge( v2, v4, "edge4", 0.6, "CAT4", 0.6 );
      e5 = new Edge( v3, v4, "edge5", 0.6, "CAT4", 0.6 );
      edgeQueue = new DSAQueue<Edge>();
      edgeQueue.enqueue(e1);      
      edgeQueue.enqueue(e2);   
      edgeQueue.enqueue(e3);      
      edgeQueue.enqueue(e4); 
      edgeQueue.enqueue(e5);      
      network = new Network(edgeQueue,vertexList); 

      try
      {
            iNumTests++;
            vertexList = null;  
            System.out.print("Testing null vertex list: ");
            network = new Network( edgeQueue, vertexList );
            System.out.println("FAILED");
      }
      catch(Exception e) { iNumPassed++; System.out.println("passed"); }
      vertexList = new DSALinkedList<Vertex>();
      vertexList.insertLast(v1);
      vertexList.insertLast(v2);
      vertexList.insertLast(v3);
      vertexList.insertLast(v4);

      try
      {
            iNumTests++;
            System.out.print("Testing null edge queue: ");
            edgeQueue = null;     // All types other than this are legal. We are relying on garbage collection here
            network = new Network( edgeQueue, vertexList );            
            System.out.println("FAILED");
      }
      catch(Exception e) { iNumPassed++; System.out.println("passed"); }
      edgeQueue = new DSAQueue<Edge>();
      edgeQueue.enqueue(e1);      
      edgeQueue.enqueue(e2);   
      edgeQueue.enqueue(e3);      
      edgeQueue.enqueue(e4); 
      edgeQueue.enqueue(e5); 
     

      System.out.println("\n");
      System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");

      }
}