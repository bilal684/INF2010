package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean contains(AnyType x )
	{
		// A completer
		if(items != null)
		{
			int pos = ((a*x.hashCode() + b) % p) % items.length;
			if(items[pos] != null && items[pos].equals(x))
				return true;
		}
		return false;
	}

	public AnyType getItem (AnyType x) {
		// A completer
		if(items != null)
		{
			int pos = ((a*x.hashCode() + b) % p) % items.length;
			return items[pos];
		}
		return null;
	}

	public void remove (AnyType x) {
		// A completer
		if(items != null)
		{
			int pos = ((a*x.hashCode() + b) % p) % items.length;
			if(contains(x))
				items[pos] = null;
		}

	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array) throws IllegalArgumentException
	{
		if(array.size() * array.size() >= p)
			throw new IllegalArgumentException();
		Random generator = new Random( System.nanoTime() );

		if(array == null || array.size() == 0)
		{
			// A completer
			
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

			// A completer	
			items = (AnyType []) new Object[1];
			items[0] = array.get(0);
			return;
		}

		do
		{
			items = null;

			// A completer
			a = generator.nextInt(p - 1) + 1;
			b = generator.nextInt(p);
			items = (AnyType []) new Object[array.size() * array.size()];
		}
		while( collisionExists( array ) );
	}

	@SuppressWarnings("unchecked")
	private boolean collisionExists(ArrayList<AnyType> array)
	{
		// A completer
		for(int i = 0; i < array.size(); i++)
		{
			int pos = ((a * array.get(i).hashCode() + b) % p) % items.length;
			if(items[pos] != null)
				return true;
			else
				items[pos] = array.get(i);
		}
		return false;
	}
}