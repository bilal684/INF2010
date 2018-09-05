

public class ArrayQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0;		//Nombre d'elements dans la file.
	private int startindex = 0;	//Index du premier element de la file
	private AnyType[] table;
   
	@SuppressWarnings("unchecked")
	public ArrayQueue() 
	{
		//A completer
		table = (AnyType[]) new Object[10];		
	}
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexité asymptotique: O(1)
	public AnyType peek()
	{
		//A completer
		if (!this.empty())
			return (AnyType) table[startindex];
		
		return null;
	}
	
	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		if(this.empty())
			throw new EmptyQueueException();
		
		startindex++;
		size--;
	}
	
	//Ajoute un element a la fin de la file
	//Double la taille de la file si necessaire (utiliser la fonction resize definie plus bas)
	//complexité asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est necessaire )
	public void push(AnyType item)
	{
		//A completer
		if(size < table.length)
		{
			if(size + startindex < table.length)
			{
				table[size+startindex] = item;
			}
			else
				table[size + startindex - table.length] = item;
		}
		else 
		{
			this.resize(2*table.length);
			table[size] = item;
		}
		size++;
	}
   
	//Redimensionne la file. La capacite est multipliee par un facteur de resizeFactor.
	//Replace les elements de la file au debut du tableau
	//complexité asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor)
	{
		//A completer
		AnyType [] tmp = table;
		table = (AnyType[]) new Object[resizeFactor * table.length];
		
		for(int i = startindex; i < tmp.length; i++)
		{
			table[i - startindex] = tmp[i];
		}
		for(int i = 0; i < startindex; i++)
		{
			table[i + (tmp.length - startindex)] = tmp[i];
		}
		startindex = 0;
	}   
}

