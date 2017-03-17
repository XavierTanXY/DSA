//FILE:             UnitTestDSAQueueGenerics.java
//AUTHOR:           Xhien Yi Tan ( Xavier )
//ID:               18249833
//UNIT:             Data Structures and Algorithms 120
//PURPOSE:          Unit testing for DSAQueue class

public class UnitTestDSAQueueGenerics
{	
	public static void main(String args[])
	{
        int iNumPassed;
        int iNumTests;
        DSAQueue<Vertex> q;
        int ii;
        int count;
        boolean bIsEmpty;
        Vertex vertex;

        iNumPassed = 0;
        iNumTests = 0;
        vertex = new Vertex();
        q = new DSAQueue<Vertex>();

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Constructor");
        System.out.println("=======================================");

        try {
                iNumTests++;
                System.out.print("Testing dequeue(): ");
                if (q.dequeue() != null)
                System.out.println("FAILED");
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        System.out.println("\n");
        System.out.println("Testing Normal Conditions - Methods");
        System.out.println("===================================");

        try {
                iNumTests++;
                System.out.println("Testing enqueue(): ");
                for (ii = 1; ii <= 10; ii++)
                {
                    // Create queue of vertex.
                    q.enqueue(new Vertex( "RV325", "VERTEX", 0.9, ii, "SOURCE"));
                    System.out.println("Front of queue: " + ii);
                }
                iNumPassed++;
                System.out.println("Testing enqueue(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Testing getCount(): ");
                count = q.getCount();
                if (count != 10)
                {
                    throw new IllegalStateException("Not getting the right count.");
                }
                iNumPassed++;
                System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Testing peek(): ");
                vertex = (Vertex)q.peek();
                if (vertex.getCost() != 1)
                {
                    throw new IllegalStateException("Not looking at first vertex.");
                }
                iNumPassed++;
                System.out.println("passed");
        } catch (Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.println("Testing dequeue():");
                for (ii = 1; ii <= 10; ii++)
                {
                    vertex = q.dequeue();

                    if (vertex.getCost() != ii)
                    {
                        throw new IllegalStateException("Queue testing failed at index " + ii);
                    }

                    System.out.println("Front of queue: " + ii);
                }
                iNumPassed++;
                System.out.println("Testing dequeue(): passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        try {
                iNumTests++;
                System.out.print("Queue isEmpty(): ");
                bIsEmpty = q.isEmpty();
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
                System.out.print("Testing dequeue() for empty queue: ");
                if (q.dequeue() != null)
                {
                    System.out.println("FAILED");
                }
        } catch(Exception e) { iNumPassed++; System.out.println("passed"); }

        try {
                iNumTests++;
                System.out.print("Testing peek() for empty stack: ");
                vertex = q.peek();
                System.out.println("FAILED");
        } catch (Exception e) { iNumPassed++; System.out.println("passed"); }
        
        System.out.println("\n");
        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests + " (" + (int)(100.0*(double)iNumPassed/(double)iNumTests) + "%)");
    }
}