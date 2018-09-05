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
		Stack<Integer> copiePile = stack; //La référence de la pile est conserver dans une nouvelle variable.
		stack = new Stack<Integer>(); //Une nouvelle pile est créée.
		stack.push(copiePile.pop()); //Un premier élément est dépilé pour empiler dans la pile vide.
		int oldSize = 0; //La variable oldSize pour sauvegarder la taille de la pile
		Integer tampon = null; //Variable intermediaire pour sauvegarder l'élément depilé.
		while(!copiePile.empty()) //Tant que copie pile n'est pas vide.
		{
			tampon = copiePile.pop(); //Tampon prend l'élément dépilé.
			oldSize = copiePile.size(); //On sauvegarde la taille de la pile originale.
			while((!stack.empty()) && (stack.peek() < tampon)) //Une boucle pour vérifier si le dernier élément de la pile est inférieur à l'élément à empiler.
				copiePile.push(stack.pop());				   //Si c'est le cas on dépile les éléments 1 à 1 jusqu'à trouver un élément qui est plus grand 
																// que l'élément à empiler. Les éléments dépilés sont empilés dans copiePile.
			stack.push(tampon);	// on empile le tampon.
			while(copiePile.size() > oldSize) //On dépile de copiePile les éléments introduits précédemment pour les empiler directement dans stack 
												// car ils sont déjà ordonnés.
				stack.push(copiePile.pop());
		}
		copiePile = null;
		return stack;
	}
}
