import java.util.Random;
import java.util.Stack;


public class SortStackMain 
{
	static final int COUNT = 30;
	static final int MAX_VALUE = 1000;
	
	public static void main(String[] args) 
	{
		boolean sortIsGood = true;
		
		Random generator = new Random( System.nanoTime() );
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < COUNT; i++)
			stack.push(generator.nextInt(MAX_VALUE));
		
		stack = sortStack(stack);
		
		boolean countIsGood = stack.size() == COUNT;
			
		int tmp = stack.pop();
		while(!stack.isEmpty())
		{
			System.out.print(tmp + ", ");
			
			if(tmp > stack.peek())
				sortIsGood = false;
			
			tmp = stack.pop();
		}
		System.out.println(tmp);
		
		if(!countIsGood)
			System.out.println("Erreur: il manque des elements dans la pile");
		else if(!sortIsGood)
			System.out.println("Erreur: le trie a echoue");
		else
			System.out.println("It's all good");
	}
	
	static Stack<Integer> sortStack(Stack<Integer> stack)
	{
		//A completer
		if(stack.empty()) //Si la pile est vide, on ne fait rien (on retourne la pile telle qu'elle).
			return stack;
		Stack<Integer> copiePile = stack; //La r�f�rence de la pile est conserver dans une nouvelle variable.
		stack = new Stack<Integer>(); //Une nouvelle pile est cr��e.
		stack.push(copiePile.pop()); //Un premier �l�ment est d�pil� pour empiler dans la pile vide.
		int oldSize = 0; //La variable oldSize pour sauvegarder la taille de la pile
		Integer tampon = null; //Variable intermediaire pour sauvegarder l'�l�ment depil�.
		while(!copiePile.empty()) //Tant que copie pile n'est pas vide.
		{
			tampon = copiePile.pop(); //Tampon prend l'�l�ment d�pil�.
			oldSize = copiePile.size(); //On sauvegarde la taille de la pile originale.
			while((!stack.empty()) && (stack.peek() < tampon)) //Une boucle pour v�rifier si le dernier �l�ment de la pile est inf�rieur � l'�l�ment � empiler.
				copiePile.push(stack.pop());				   //Si c'est le cas on d�pile les �l�ments 1 � 1 jusqu'� trouver un �l�ment qui est plus grand 
																// que l'�l�ment � empiler. Les �l�ments d�pil�s sont empil�s dans copiePile.
			stack.push(tampon);	// on empile le tampon.
			while(copiePile.size() > oldSize) //On d�pile de copiePile les �l�ments introduits pr�c�demment pour les empiler directement dans stack 
												// car ils sont d�j� ordonn�s.
				stack.push(copiePile.pop());
		}
		copiePile = null;
		return stack;
	}
}
