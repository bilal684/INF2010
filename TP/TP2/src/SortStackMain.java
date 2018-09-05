import java.util.Random;
import java.util.Stack;


public class SortStackMain 
{
	public static void main(String[] args) 
	{
		boolean allGood = true;
		
		Random generator = new Random( System.nanoTime() );
		Stack<Integer> stack = new Stack<Integer>();
		
		for(int i = 0; i < 20; i++)
			stack.push(generator.nextInt(1000));
		
		stack = sortStack(stack);
		
		int tmp = stack.pop();
		while(!stack.isEmpty())
		{
			System.out.print(tmp + ", ");
			
			if(tmp > stack.peek())
				allGood = false;
			
			tmp = stack.pop();
		}
		System.out.println(tmp);
		
		if(allGood)
			System.out.println("It's all good");
		else
			System.out.println("Erreur: le trie a echoue");
	}
	
	static Stack<Integer> sortStack(Stack<Integer> stack)
	{
		//A completer
		if(stack.empty())
			return stack;
		Stack<Integer> copiePile = stack;
		stack = new Stack<Integer>();
		stack.push(copiePile.pop());
		Integer tampon = null;
		while(!copiePile.empty())
		{
			tampon = copiePile.pop();
			while((!stack.empty()) && (stack.peek() < tampon))
				copiePile.push(stack.pop());
			stack.push(tampon);
		}
		copiePile = null;
		return stack;
	}
}
