package probleme1;

import java.util.ArrayList;
import java.util.Random;

public class HashFunctions 
{
   static int p = 46337;
   
   public static void main(String[] args) 
   {
      // Donnees brutes
      Integer[] array = { 100,  75,  64,  25,  36, 101,  11,  92,  
                     200, 175, 164, 125, 136, 201, 111, 192,
                     300, 275, 264, 225, 236, 301, 311, 292};
      
      // Les donnees sont inserees dans un ArrayList
      ArrayList<Integer> al = new ArrayList<Integer>(array.length);
      
      for(Integer item : array) al.add( item );
      
      /**
       * Exercice 1
       */
      // On cree un QuadraticSpacePerfectHashing et insere les donnees
      System.out.println( "QuadraticSpacePerfectHashing:");
      System.out.println();
      
      QuadraticSpacePerfectHashing<Integer> e = new QuadraticSpacePerfectHashing<Integer>( al ); 
      
      // Verifie les proprietes d'occupation d'espace
      System.out.println( "Number of elements: " + al.size() );
      System.out.println( "Size: " + e.Size() );
      System.out.println();
      
      // Verifie qu'il fonctionne comme prevu
      System.out.println( 100 + " est present: " + e.contains(100) );
      System.out.println(  99 + " est present: " + e.contains( 99) );
      System.out.println( 200 + " est present: " + e.contains(200) );
      System.out.println( 199 + " est present: " + e.contains(199) );
      System.out.println( 300 + " est present: " + e.contains(300) );
      System.out.println( 299 + " est present: " + e.contains(299) );
      System.out.println();
      
      
      // Tester la suppression 
      e.remove(100);
      System.out.println( 100 + " est present: " + e.contains(100) );
      e.remove(99);
      System.out.println(  99 + " est present: " + e.contains( 99) );
      e.remove(200);
      System.out.println( 200 + " est present: " + e.contains(200) );
      e.remove(199);
      System.out.println( 199 + " est present: " + e.contains(199) );
      e.remove(300);
      System.out.println( 300 + " est present: " + e.contains(300) );
      e.remove(299);
      System.out.println( 299 + " est present: " + e.contains(299) );
      System.out.println();
      
      /**
       * Exercice 2
       */
      // On cree un LinearSpacePerfectHashing et insere les memes donnees
      System.out.println( "LinearSpacePerfectHashing:");
      System.out.println();
      
      LinearSpacePerfectHashing<Integer> pfhash = new LinearSpacePerfectHashing<Integer>( al );
      
      // Verifie les proprietes d'occupation d'espace
      System.out.println( "Number of elements: " + al.size() );
      System.out.println( "Size: " + pfhash.Size() );
      System.out.println();
      
      // Verifie qu'il fonctionne comme prevu
      System.out.println( 100 + " est present: " + pfhash.contains(100) );
      System.out.println(  99 + " est present: " + pfhash.contains( 99) );
      System.out.println( 200 + " est present: " + pfhash.contains(200) );
      System.out.println( 199 + " est present: " + pfhash.contains(199) );
      System.out.println( 300 + " est present: " + pfhash.contains(300) );
      System.out.println( 299 + " est present: " + pfhash.contains(299) );
      System.out.println();
      
      // Tester la suppression
      pfhash.remove(100);
      System.out.println( 100 + " est present: " + pfhash.contains(100) );
      pfhash.remove(99);
      System.out.println(  99 + " est present: " + pfhash.contains( 99) );
      pfhash.remove(200);
      System.out.println( 200 + " est present: " + pfhash.contains(200) );
      pfhash.remove(199);
      System.out.println( 199 + " est present: " + pfhash.contains(199) );
      pfhash.remove(300);
      System.out.println( 300 + " est present: " + pfhash.contains(300) );
      pfhash.remove(299);
      System.out.println( 299 + " est present: " + pfhash.contains(299) );
      System.out.println();
     
      /**
       * Question 1 (confirmation des resultats de Exercice 2) 
       */
      // Effectues quelques tests aleatoires pour verifier les proprietes de taille
      pfhash = new LinearSpacePerfectHashing<Integer>();
      System.out.println("Tests aleatoires");
      
      for(int i=0, nbElements = 10; i<40; ++i, nbElements += 10)
      {
         pfhash.SetArray( randomIntegers( nbElements ) );			
         System.out.println( nbElements + "\t" + pfhash.Size());
      }
   }
 
   /**
    * Question 1
    */
   public static ArrayList<Integer> randomIntegers(int length)
   {
	   if(length != 0)
	   {
		   Random generator = new Random( System.nanoTime() );
		   ArrayList<Integer> tab = new ArrayList<Integer>(length);
		   for(int i = 0; i < length; i++)
		   {	
			   int valeur;
			   boolean estPresent = false;
			   do
			   {
				   estPresent = false;
				   valeur = generator.nextInt(p);
				   if(tab == null || !tab.contains(valeur))
					   tab.add(new Integer(valeur));
				   else
					   estPresent = true;				   	   
			   }
			   while(estPresent);   			
		   }
		   return tab;
	   }
	   return null;
   }
}


