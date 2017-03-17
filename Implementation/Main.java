//FILE:				Main.java
//AUTHOR:			Xhien Yi Tan ( Xavier )
//ID:				18249833
//UNIT:				Data Structures and Algorithms 120
//PURPOSE:			Main that runs the program

import java.util.Scanner;
public class Main
{
	public static void main( String args[] )
	{
		Scanner input;
		String filename;
		OBDDH o;
		

		try
		{
				System.out.println( "Please enter the file name" );
				input = new Scanner( System.in );
				filename = input.nextLine();

				o = new OBDDH( filename );
	
		}
		catch ( IllegalArgumentException e )
		{
			System.out.println( "Error : " + e.getMessage() );
		}
		catch ( NullPointerException e2 )
		{
			System.out.println( "Error :" + e2.getMessage() );
		}
		catch ( Exception e3 )
		{
			System.out.println( "Error :" + e3.getMessage() );
		}
		
	}

}
