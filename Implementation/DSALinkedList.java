//FILE:				DSALinkedList.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			A class that is responsible for LINKED LIST
import java.util.Iterator;

public class DSALinkedList<E> implements Iterable<E>
{
	//Private Inner Class DSAListNode
	private class DSAListNode<E>
	{
		//Class Fields
		private E value;
		private DSAListNode<E> next;
		private DSAListNode<E> prev;
	
		//Alternate constructor for Node
		public DSAListNode( E inValue )	
		{
			value = inValue;
			next = null;
			prev = null;
		}
	
		//Accessor -  get value of Node
		public E getValue()
		{
			return value;
		}

		//Accessor - get next Node
		public DSAListNode<E> getNext()
		{
			return next;
		}

		//Accessor - get previous Node
		public DSAListNode<E> getPrev()
		{
			return prev;
		}
	
		//Mutators -  set next Node
		public void setNext( DSAListNode<E> newNext )
		{
			next = newNext;
		}

		//Mutators -  set previous Node
		public void setPrev( DSAListNode<E> newPrev )
		{
			prev = newPrev;
		}

		//Display Node method
		public void displayNode()
		{
			System.out.println( "" + value );
		}
	}
	
	//Private Inner Class DSALinkedListIterator		
	private class DSALinkedListIterator<E> implements Iterator<E>
	{
		private DSALinkedList<E>.DSAListNode<E> iterNext;
		
		public DSALinkedListIterator( DSALinkedList<E>.DSAListNode<E> head )
		{
			iterNext = head;
		}

		public boolean hasNext()
		{
			return ( iterNext != null );
		}

		public E next()
		{
			E value;
			
			if( iterNext == null )
			{	
				value = null;
			}
			else
			{
				value = iterNext.getValue();
				iterNext = iterNext.getNext();
			}
			
			return value;
		}
		
		public void remove()
		{
			throw new UnsupportedOperationException( "Not supported" );
		}
	}
	
	//Class Fields of DSALinkedList
	private DSALinkedList<E>.DSAListNode<E> head;
	private DSALinkedList<E>.DSAListNode<E> tail;
	private int count;

	
	//Default Constructor
 	public DSALinkedList()
	{
		head = null;
		tail = null;
		count = 0;
	}

	//Accessor - get whether the List is empty
	public boolean isEmpty()
	{
		boolean isEmpty = false;
		if( head == null && tail == null )
		{
			isEmpty = true;
		}
		return isEmpty;
	}

	//Accessor - get the first item in the list
	public E peekFirst()
	{
		E nodeValue = null;
	
		if( isEmpty() )
		{
			throw new IllegalStateException( "There is nothing in the list" );
		}
		else
		{
			nodeValue = head.getValue();
		}
		return nodeValue;
	}

	//Accessor - get the last item in the list
	public E peekLast()
	{
		E nodeValue;

		if( isEmpty() )
		{
			throw new IllegalStateException( " There is nothing in the list" );	
		}
		else
		{
			nodeValue = tail.getValue();
		}

		return nodeValue;
	}

	//Insert new item first into the list
	public void insertFirst( E inValue )
	{
		DSALinkedList<E>.DSAListNode<E> newNode = new DSAListNode<E>( inValue );

		if( isEmpty() )
		{
			head = newNode;
			tail = newNode;
			newNode.setNext( null );
			newNode.setPrev( null );
		}
		else
		{
			newNode.setPrev( null );
			newNode.setNext( head );
			head = newNode;
			tail.setPrev( newNode );
			
		}
		count++;
	}

	//Insert new item last into the list
	public void insertLast( E inValue )
	{
		DSALinkedList<E>.DSAListNode<E> newNode = new DSAListNode<E>( inValue );
		
		if( isEmpty() )
		{
			head = newNode;
			tail = newNode;
			newNode.setNext( null );
			newNode.setPrev( null );
		}
		else
		{
			newNode.setPrev( tail );
			newNode.setNext( null );
			tail.setNext( newNode );
			tail = newNode;
		}
		count++;
		
	}
	
	//Remove item from the front
	public E removeFirst()
	{
		E nodeValue = null;
		
		if( isEmpty() )
		{
			throw new IllegalStateException( "Can't remove the first element of an empty listt" );
		}
		else if( head.getNext() == null )
		{
			nodeValue = head.getValue();
			head = null;
			tail = null;
		}
		else
		{
			nodeValue = head.getValue();
			head = head.getNext();
			head.setPrev( null );
		}
		count--;
		return nodeValue;
	}

	//Remove item from the last or back
	public E removeLast()
	{
		E nodeValue = null;
	
		if( isEmpty() )
		{
			throw new IllegalStateException( "Can't remove the last element of an empty listy" );
		}
		else if( tail.getPrev() == null )
		{
			nodeValue = tail.getValue();
			head = null;
			tail = null;
		}
		else
		{
			nodeValue = tail.getValue();
			tail = tail.getPrev();
			tail.setNext( null );
		}
		count--;
		return nodeValue;
	}

	//Display List
	public void displayList()
	{
		DSALinkedList<E>.DSAListNode<E> currentNode = head;
		
		while( currentNode != null )
		{
			currentNode.displayNode();
			currentNode = currentNode.getNext();
		}
	}

	//Get total items in the list
	public int getCount()
	{
		return count;
	}
		
	//Iterator for this list
	public Iterator<E> iterator()
	{
		return new DSALinkedListIterator<E>( head );
	} 
	
}

