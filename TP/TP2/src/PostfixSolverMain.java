import java.io.*;
import java.util.Stack;

public class PostfixSolverMain 
{
	public static void main(String[] args) throws IOException 
	{
		Stack<Double> stack = new Stack<Double>();
		
		String s = "25 5 2 * + 15 3 / 5 - +";
		
		//L'expression est separee en tokens selon les espaces
		for(String token : s.split("\\s")) 
		{
			//A completer
			Double operande1;
			Double operande2;
			Double resultat = null;
			if(token.length() == 1)
			{
				switch(token.toCharArray()[0])
				{
				case '+':
					operande1 = (Double) stack.pop();
					operande2 = (Double) stack.pop();
					resultat = new Double(operande2.doubleValue() + operande1.doubleValue());
					break;
				case '-':
					operande1 = (Double) stack.pop();
					operande2 = (Double) stack.pop();
					resultat = new Double(operande2.doubleValue() - operande1.doubleValue());
					break;
				case '*':
					operande1 = (Double) stack.pop();
					operande2 = (Double) stack.pop();
					resultat = new Double(operande2.doubleValue() * operande1.doubleValue());
					break;
				case '/':
					operande1 = (Double) stack.pop();
					operande2 = (Double) stack.pop();
					resultat = new Double(operande2.doubleValue() / operande1.doubleValue());
					break;
				default:
						stack.push(new Double (Character.digit(token.toCharArray()[0], 10)));
				}
				if(resultat != null)
					stack.push(resultat);
			}
			else
			{
				int somme = 0;
				for(int i = token.length() - 1; i >=0; i--)
				{
					somme += Character.digit(token.toCharArray()[i],10) * Math.pow(10,(token.length() - i - 1));
				}
				resultat = new Double (somme);
				stack.push(resultat);
			}
			
			
		}
     
		System.out.println("25 + 5*2 + 15/3 - 5 = "+stack.peek());
		if(stack.peek() == 35)
			System.out.println("It's all good");
		else
			System.out.println("Erreur: mauvais resultat");
     }
}
