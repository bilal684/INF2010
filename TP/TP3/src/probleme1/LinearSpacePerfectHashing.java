package probleme1;

import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array) throws IllegalArgumentException
	{
		if(array.size() >= p)
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
			data = new QuadraticSpacePerfectHashing[1];
			data[0] = new QuadraticSpacePerfectHashing<AnyType>();
			data[0] = (QuadraticSpacePerfectHashing<AnyType>) array.get(0);
			return;
		}

		// A completer
		a = generator.nextInt(p - 1) + 1;
		b = generator.nextInt(p);
		data = new QuadraticSpacePerfectHashing[array.size()];
		ArrayList<AnyType> [] tableau = (ArrayList<AnyType>[]) new ArrayList[array.size()];
		
		for(int i = 0; i < array.size(); i++)
		{
			int pos = ((a * array.get(i).hashCode() + b) % p) % data.length;
			if(tableau[pos] == null)
				tableau[pos] = new ArrayList<AnyType>();
			tableau[pos].add(array.get(i));
		}
		
		for(int i = 0; i < data.length; i++)
		{
			if(tableau[i] != null)
				data[i] = new QuadraticSpacePerfectHashing<AnyType>(tableau[i]);
		}
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean contains(AnyType x )
	{
		// A completer
		
		if(data != null)
		{
			int pos = ((a * x.hashCode() + b) % p) % data.length;

			if(data[pos] != null)
				return data[pos].contains(x);
		}
		return false;		
	}
	
	public AnyType getItem (AnyType x) {
		// A completer
		int pos = ((a * x.hashCode() + b) % p) % data.length;
		if (contains(x))
		{
			return data[pos].getItem(x);
		}
		return null;
	}
	
	public void remove (AnyType x) {
		// A completer
		int pos = ((a * x.hashCode() + b) % p) % data.length;
		if(contains(x))
		{
			data[pos].remove(x);
		}
	}
}
