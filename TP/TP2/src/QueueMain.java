


public class QueueMain 
{
	final static int COUNT = 10000;
	
	public static void main(String[] args) 
	{
		Queue<Integer> arrayQueue = new ArrayQueue<Integer>();
		Queue<Integer> listQueue = new LinkedListQueue<Integer>();
		
		for(int i = 0; i < COUNT; i++)
		{
			arrayQueue.push(i);
			listQueue.push(i);
		}
		
		if(arrayQueue.size() != COUNT || listQueue.size() != COUNT)
		{
			System.out.println("Erreur: La taille de la file n'est egale a " + COUNT + " apres avoir ajoute " + COUNT + " elements");
		}
		
		for(int i = 0; i < COUNT; i++)
		{
			if(arrayQueue.peek() != i || listQueue.peek() != i)
			{
				System.out.println("Erreur: l'ordre de sortie(FIFO) n'est pas respecte");
				return;
			}
			
			try 
			{
				arrayQueue.pop();
				listQueue.pop();
			} 
			catch (EmptyQueueException e) 
			{
				e.printStackTrace();
				return;
			}
		}
		
		if(!arrayQueue.empty() || !listQueue.empty())
		{
			System.out.println("Erreur: la file devrait etre vide, mais elle ne l'est pas");
			return;
		}
		
		if(arrayQueue.peek() != null || listQueue.peek() != null)
		{
			System.out.println("Erreur: peek doit retourner null lorsque la file est vide");
			return;
		}
		
		try 
		{
			arrayQueue.pop();
			System.out.println("Erreur: Pop doit lancer une exception lorsque la file est vide");
			return;
		} 
		catch (EmptyQueueException e){}
		
		try 
		{
			listQueue.pop();
			System.out.println("Erreur: Pop doit lancer une exception lorsque la file est vide");
			return;
		} 
		catch (EmptyQueueException e){}
		
		
		System.out.print("It's all good");
	}
}
