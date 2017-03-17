//FILE:             UnitTestQn.java
//AUTHOR:           Xhien Yi Tan ( Xavier )
//ID:               18249833
//UNIT:             Data Structures and Algorithms 120
//PURPOSE:          Unit testing for Qn class

public class UnitTestQn
{
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        Qn qn;
        int ii;
        int numNodes;
        boolean bIsEmpty;
        HNode node;

        iNumPassed = 0;
        iNumTests = 0;
        node = new HNode();
        DSAQueue<HNode> q;
    
        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Methods");
        System.out.println("===================================");

        iNumTests++;
		System.out.print("Testing creation of Qn: ");
		qn = new Qn();
		iNumPassed++;
		System.out.println("passed");

        iNumTests++;
        System.out.print("Testing getNextQueue(): ");
        q = qn.getNextQueue();
        iNumPassed++;
        System.out.println("passed");

        try {
                iNumTests++;
                System.out.println("Testing insert(): ");
                for (ii = 1; ii <= 10; ii++)
                {
                    // Create queue of node.
                    qn.insert(new HNode());
                    System.out.println("Front of queue: " + ii);
                }
                iNumPassed++;
                System.out.println("Testing insert(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Testing numNodes(): ");
                numNodes = qn.numNodes();
                if (numNodes!=10)
                {
                    throw new IllegalStateException("Number of node is incorrect.");
                }
                iNumPassed++;
                System.out.println("passed");
            } catch(Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Testing front(): ");
                node = qn.front();
                FloatingPoint fp = node.getRel();
                double rel = fp.getFloatingPoint();
                if (  Math.abs( rel - 1.0 ) > 0.00001 )
                {
                    throw new IllegalStateException("Not looking at first node.");
                }
                iNumPassed++;
                System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.println("Testing front() dequeue everything:");
                for (ii = 1; ii <= 9; ii++)
                {
                    node = qn.front();
                    FloatingPoint fp = node.getRel();
                	double rel = fp.getFloatingPoint();

                    if ( Math.abs( rel - 1.0 ) > 0.00001 )
                    {
                        throw new IllegalStateException("Queue testing failed at index " + ii);
                    }

                    System.out.println("Front of queue: " + ii);
                }
                iNumPassed++;
                System.out.println("Testing front(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Testing isEmpty(): ");
                bIsEmpty = qn.isEmpty();
                if (bIsEmpty==false)
                {
                    throw new IllegalStateException("Queue is empty.");
                }
                iNumPassed++;
                System.out.println("passed");
         } catch(Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Testing swap(): ");
                Qc qc = new Qc( new HNode() ); //Create a qc with 1 node in it
                qn.insert( new HNode() ); //qn is empty, so insert a node for testing
                qn.swap( qc ); //moving node from qn to qc
                int num = qc.numNodes(); //qc should have 2 nodes in it
                if (num!=2)
                {
                    throw new IllegalStateException("Swap is not working");
                }
                iNumPassed++;
                System.out.println("passed");
         } catch(Exception e) { System.out.println("FAILED"); }

		

        System.out.println("\n");
        System.out.println("Testing Error Conditions - Methods");
        System.out.println("==================================");

            try {
                iNumTests++;
                System.out.print("Testing front() for empty queue: ");
                if (qn.front() != null)
                {
                    System.out.println("FAILED");
                }
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
        
        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}