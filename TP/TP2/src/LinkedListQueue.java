
public class LinkedListQueue<AnyType> implements Queue<AnyType>
{	
	// Un noeud de la file
	@SuppressWarnings("hiding")
	private class Node<AnyType> 
	{
		private AnyType data;
		private Node next;
		
		public Node(AnyType data, Node next) 
		{
			this.data = data;
			this.next = next;
		}

		public void setNext(Node next) 
		{
			this.next = next;
		}
		
		public Node<AnyType> getNext() 
		{
			return next;
		}
		
		public AnyType getData() 
		{
			return data;
		}
	}
   
	private int size = 0;		//Nombre d'elements dans la file.
	private Node<AnyType> last;	//Dernier element de la liste
	
	public LinkedListQueue() 
	{
		//A completer
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
	@SuppressWarnings("unchecked")
	public AnyType peek()
	{
		//A completer
		if(this.empty())
			return null;
		else if (size == 1)
			return (AnyType) last.getData();
		else 
			return (AnyType) last.getNext().getData();
	}

	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		//A completer
		
		if (this.empty())
			throw new EmptyQueueException();
		
		last.setNext(last.getNext().getNext());
		size--;
	}
	
	//Ajoute un element a la fin de la file
	//complexité asymptotique: O(1)
	public void push(AnyType item)
	{		
		//A completer
		if(size == 0)
			last = new Node<AnyType>(item, null);
		else if(size == 1)
		{
			Node<AnyType> noeud = last;
			last = new Node<AnyType>(item, noeud);
			noeud.setNext(last);
		}
		else
		{
			Node<AnyType> noeud = last;
			last = new Node<AnyType>(item, last.getNext());
			noeud.setNext(last);
		}
		size++;
	}  
}
