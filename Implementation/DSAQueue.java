//FILE:				DSAQueue.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A class that is responsible for QUEUE by using LinkedList
import java.util.Iterator;

public class DSAQueue<E> implements Iterable<E>
{
	private DSALinkedList<E> list;
	private int count;

	//Default Constructor
	public DSAQueue()
	{
		list = new DSALinkedList<E>();
		count = 0;
	}
	
	//Checking the queue is empty
	public boolean isEmpty()
	{
		boolean isEmpty;

		try
		{
			E value = list.peekFirst();
			isEmpty = false;
		}
		catch( IllegalStateException e )
		{
			isEmpty = true;
		}

		return isEmpty;
	}

	//Put thing into the queue
	public void enqueue( E value )
	{
		list.insertLast( value );
		count++;
	}

	//Removing thing from the queue
	public E dequeue()
	{
		E value;

		if( isEmpty() )
		{
			throw new IllegalStateException( "Queue is empty" );
		}
		else
		{
			value = list.removeFirst();
			--count;
		}
		
		return value;
	}

	//Looking the first item in the queue, not changing anything
	public E peek()
	{
		E frontVal;
		
		if( isEmpty() )
		{
			throw new IllegalStateException( "Queue is empty" );
		}
		else
		{
			frontVal = list.peekFirst();
		}

		return frontVal;
	}

	//Total items in the queue
	public int getCount()
	{
		return count;
	}

	//Iterator for this queue
	public Iterator<E> iterator()
	{
		return list.iterator();
	}
}
