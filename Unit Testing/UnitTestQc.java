//FILE:             UnitTestQc.java
//AUTHOR:           Xhien Yi Tan ( Xavier )
//ID:               18249833
//UNIT:             Data Structures and Algorithms 120
//PURPOSE:          Unit testing for Qc class

public class UnitTestQc
{
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        Qc qc;
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
        System.out.print("Testing creation of Qc: ");
        qc = new Qc( node ); //one node is insert when creating a Qc
        iNumPassed++;
        System.out.println("passed");

        iNumTests++;
        System.out.print("Testing getNextQueue(): ");
        q = qc.getCurrQueue();
        iNumPassed++;
        System.out.println("passed");


        try {
                iNumTests++;
                System.out.println("Testing insert(): ");
                for (ii = 1; ii <= 10; ii++) //insert ten nodes
                {
                    // Create queue of node.
                    qc.insert(new HNode());
                    System.out.println("Front of queue: " + ii);
                }
                iNumPassed++;
                System.out.println("Testing insert(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Testing numNodes(): ");
                numNodes = qc.numNodes(); //should have 11 nodes
                if (numNodes!=11)
                {
                    throw new IllegalStateException("Number of node is incorrect.");
                }
                iNumPassed++;
                System.out.println("passed");
            } catch(Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Testing front(): ");
                node = qc.front(); //one nodes is removed
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
                for (ii = 1; ii <= 10; ii++) //should have ten nodes
                {
                    node = qc.front();
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
                bIsEmpty = qc.isEmpty();
                if (bIsEmpty==false)
                {
                    throw new IllegalStateException("Queue is empty.");
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
                if (qc.front() != null)
                {
                    System.out.println("FAILED");
                }
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }
        
        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}