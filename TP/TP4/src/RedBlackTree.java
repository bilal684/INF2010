import java.util.LinkedList;
import java.util.Queue;

public class RedBlackTree<T extends Comparable<? super T> > 
{
   private RBNode<T> root;  // Racine de l'arbre
   
   enum ChildType{ left, right }
   
   public RedBlackTree()
   { 
      root = null;
   }
   
   public void printFancyTree()
   {
      printFancyTree( root, "", ChildType.right);
   }
   
   private void printFancyTree( RBNode<T> t, String prefix, ChildType myChildType)
   {
      System.out.print( prefix + "|__"); // un | et trois _
      
      if( t != null )
      {
         boolean isLeaf = (t.isNil()) || ( t.leftChild.isNil() && t.rightChild.isNil() );
         
         System.out.println( t );
         String _prefix = prefix;
         
         if( myChildType == ChildType.left )
            _prefix += "|   "; // un | et trois espaces
         else
            _prefix += "   " ; // trois espaces
         
         if( !isLeaf )
         {
            printFancyTree( t.leftChild, _prefix, ChildType.left );
            printFancyTree( t.rightChild, _prefix, ChildType.right );
         }
      }
      else
         System.out.print("null\n");
   }
   
   public T find(int key)
   {
      return find(root, key);
   }
   
   private T find(RBNode<T> current, int key)
   {
      // À COMPLÉTER
	   T valeur = null;
	   if(!current.isNil())
	   {
		   int hash = current.value.hashCode();
		   if(hash == key)
			   valeur = current.value;   
		   else if(hash > key)
			   valeur = find(current.leftChild, key);
		   else
			   valeur = find(current.rightChild,key);   
	   }
	   return valeur;
   }
   
   public void insert(T val)
   {
      insertNode( new RBNode<T>( val ) );
   }
   
   private void insertNode( RBNode<T> newNode )
   { 
      if (root == null)  // Si arbre vide
         root = newNode;
      else
      {
         RBNode<T> position = root; // On se place a la racine
         
         while( true ) // on insere le noeud (ABR standard)
         {
            int newKey = newNode.value.hashCode();
            int posKey = position.value.hashCode();
            
            if ( newKey < posKey ) 
            {
               if ( position.leftChild.isNil() ) 
               {
                  position.leftChild = newNode;
                  newNode.parent = position;
                  break;
               } 
                  else 
                  position = position.leftChild;
            } 
            else if ( newKey > posKey ) 
            {
               if ( position.rightChild.isNil() )
               {
                  position.rightChild = newNode;
                  newNode.parent = position;
                  break;
               }
               else 
                  position = position.rightChild;
            }
            else // pas de doublons
               return;
         }
      }
      
      insertionCases( newNode );
   }

   private void insertionCases( RBNode<T> X )
   {
      // A MODIFIER/COMPLÉTER
	  if(X != null)
		  insertionCase1( X );
   }
   
   private void insertionCase1 ( RBNode<T> X )
   {
      // A MODIFIER/COMPLÉTER
	  if(root == X)
		  root.setToBlack();
	  else
		  insertionCase2( X );
   }

   private void insertionCase2( RBNode<T> X )
   {
      // A MODIFIER/COMPLÉTER
	   if(!X.parent.isBlack())
		   insertionCase3(X);
   }

   private void insertionCase3( RBNode<T> X )
   {
      // A MODIFIER/COMPLÉTER
	   
	   if(X.parent.isRed() && !X.parent.sibling().isNil() && X.uncle().isRed())
	   {
		   X.parent.setToBlack();
		   X.uncle().setToBlack();
		   X.grandParent().setToRed();
		   insertionCases(X.grandParent());
	   }
	   else
		   insertionCase4( X );
   }

   private void insertionCase4( RBNode<T> X )
   {
      // A MODIFIER/COMPLÉTER
	   if(X.parent.isRed() && (X.uncle().isBlack() || X.parent.sibling().isNil()))
	   {
		   if(X.parent.leftChild == X && X.grandParent().rightChild == X.parent)
		   {
			   rotateRight(X.parent);
			   insertionCase5(X.rightChild);
		   }

		   else if(X.parent.rightChild == X && X.grandParent().leftChild == X.parent)
		   {
			   rotateLeft(X.parent);
			   insertionCase5(X.leftChild);
		   }
		   else
			   insertionCase5( X );
	   }
	   else
		   insertionCase5( X );
   }

   private void insertionCase5( RBNode<T> X )
   {
      // A MODIFIER/COMPLÉTER
	   if(X.parent.isRed() && (X.uncle().isBlack() || X.parent.sibling().isNil()))
	   {
		   if(X.parent.rightChild == X && X.grandParent().rightChild == X.parent)
		   {
			   X.parent.setToBlack();
			   if(X.grandParent().isBlack())
				   X.grandParent().setToRed();
			   else
				   X.grandParent().setToBlack();
			   rotateLeft(X.grandParent());
		   }

		   else if(X.parent.leftChild == X && X.grandParent().leftChild == X.parent)
		   {
			   X.parent.setToBlack();
			   if(X.grandParent().isBlack())
				   X.grandParent().setToRed();
			   else
				   X.grandParent().setToBlack();
			   rotateRight(X.grandParent());
		   }
	   }
      return;
   }
   
   private void rotateLeft( RBNode<T> G )
   {
      // A MODIFIER/COMPLÉTER
	   if(G != null)
	   {
		   RBNode<T> tmp = G.rightChild;
		   if(G.parent != null)
			   if(G.parent.rightChild == G)
				   G.parent.rightChild = tmp;
			   else
				   G.parent.leftChild = tmp;
		   tmp.parent = G.parent;
		   
		   if(tmp.leftChild != null)
			   tmp.leftChild.parent = G;
		   G.rightChild = tmp.leftChild;
		   
		   G.parent = tmp;
		   tmp.leftChild = G;
		   if (G == root)
			   root = tmp;
	   }
   }
   
   private void rotateRight( RBNode<T> G )
   {
      // A MODIFIER/COMPLÉTER
	   if(G != null)
	   {
		   RBNode<T> tmp = G.leftChild;
		   if(G.parent != null)
			   if(G.parent.leftChild == G)
				   G.parent.leftChild = tmp;
			   else
				   G.parent.rightChild = tmp;
		   tmp.parent = G.parent;
		   
		   if(tmp.rightChild != null)
			   tmp.rightChild.parent = G;
		   G.leftChild = tmp.rightChild;
		   
		   G.parent = tmp;
		   tmp.rightChild = G;
		   if (G == root)
			   root = tmp;
	   }
   }

   public void printTreePreOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PreOrdre ( ");
         printTreePreOrder( root );
         System.out.println( " )");
      }
      return;
   }
   
   private void printTreePreOrder( RBNode<T> P )
   {
      // A MODIFIER/COMPLÉTER
	      if(P != null && P.value != null)
	      {
	    	  String couleur = new String();
	    	  if(P.isBlack())
	    		  couleur = "black";
	    	  else
	    		  couleur = "red";
	    	  if(P != root)
	    		  System.out.print(", ");
	    	  System.out.print("{" + P.value + " (" + couleur + ")}");
	    	  printTreePreOrder(P.leftChild);
	    	  printTreePreOrder(P.rightChild);
	      }
	       return;
   }
   
   public void printTreePostOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "PostOrdre ( ");
         printTreePostOrder( root );
         System.out.println( " )");
      }
      return;
   }
  
   private void printTreePostOrder( RBNode<T> P )
   {
      // A MODIFIER/COMPLÉTER
	      if(P != null && P.value != null)
	      {
	    	  String couleur = new String();
	    	  if(P.isBlack())
	    		  couleur = "black";
	    	  else
	    		  couleur = "red";
	    	  printTreePostOrder(P.leftChild);
	    	  printTreePostOrder(P.rightChild);
	    	  System.out.print("{" + P.value + " (" + couleur + ")}");
	    	  if(P != root)
	    		  System.out.print(", ");	    		  
	      }
	       return;
   }
   

   public void printTreeAscendingOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "AscendingOrdre ( ");
         printTreeAscendingOrder( root );
         System.out.println( " )");
      }
      return;
   }
  
   private void printTreeAscendingOrder( RBNode<T> P )
   {
      // A COMPLÉTER
	      if(P != null && P.value != null)
	      {
	    	  String couleur = new String();
	    	  if(P.isBlack())
	    		  couleur = "black";
	    	  else
	    		  couleur = "red";
	    	  
	    	  printTreeAscendingOrder(P.leftChild);
	    	  if(!P.leftChild.isNil())
	    		  System.out.print(", ");
	    	  System.out.print("{" + P.value + " (" + couleur + ")}");
	    	  if(!P.rightChild.isNil())
	    		  System.out.print(", ");
	    	  printTreeAscendingOrder(P.rightChild);		  
	      }
	       return;
   }
   
   
   public void printTreeDescendingOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "DescendingOrdre ( ");
         printTreeDescendingOrder( root );
         System.out.println( " )");
      }
      return;
   }

   private void printTreeDescendingOrder( RBNode<T> P )
   {
      // A COMPLÉTER
	      if(P != null && P.value != null)
	      {
	    	  String couleur = new String();
	    	  if(P.isBlack())
	    		  couleur = "black";
	    	  else
	    		  couleur = "red";
	    	  printTreeDescendingOrder(P.rightChild);
	    	  if(!P.leftChild.isNil())
	    		  System.out.print(", ");
	    	  System.out.print("{" + P.value + " (" + couleur + ")}");
	    	  if(!P.rightChild.isNil())
	    		  System.out.print(", ");
	    	  printTreeDescendingOrder(P.leftChild);		  
	      }
	       return;
   }
   
   public void printTreeLevelOrder()
   {
      if(root == null)
         System.out.println( "Empty tree" );
      else
      {
         System.out.print( "LevelOrdre ( ");
         Queue<RBNode<T>> q = new LinkedList<RBNode<T>>();
         q.add(root);
         //  À COMPLÉTER
         while(!q.isEmpty())
         {
        	 RBNode<T> tmp = q.poll();
        	 String couleur = new String();
        	 if(tmp != null)
        	 {    			 
    	    	 if(tmp.isBlack())
    	    		 couleur = "black";
    	    	 else
    	    		 couleur = "red";
        	 }
             if(!tmp.isNil())
             {
            	 if(tmp != root)
            		 System.out.print(", ");
            	 System.out.print("{" + tmp.value + " (" + couleur + ")}"); 
             }
             
             if(tmp.leftChild !=null)  
            	 q.add(tmp.leftChild);  
             if(tmp.rightChild !=null)  
            	q.add(tmp.rightChild);  
         }
         System.out.println( " )");
      }
      return;
   }
   
   private static class RBNode<T extends Comparable<? super T> > 
   {
      enum RB_COLOR { BLACK, RED }  // Couleur possible
      
      RBNode<T>  parent;      // Noeud parent
      RBNode<T>  leftChild;   // Feuille gauche
      RBNode<T>  rightChild;  // Feuille droite
      RB_COLOR   color;       // Couleur du noeud
      T          value;       // Valeur du noeud
      
      // Constructeur (NIL)   
      RBNode() { setToBlack(); }
      
      // Constructeur (feuille)   
      RBNode(T val)
      {
         setToRed();
         value = val;
         leftChild = new RBNode<T>();
         leftChild.parent = this;
         rightChild = new RBNode<T>();
         rightChild.parent = this;
      }
      
      RBNode<T> grandParent()
      {
         // À COMPLÉTER
    	 return (parent != null && parent.parent != null) ? parent.parent : null;
      }
      
      RBNode<T> uncle()
      {
         // À COMPLÉTER
    	  RBNode<T> oncle = null;
    	  if(parent != null && grandParent() != null)
    	  {
    		  if(grandParent().rightChild == parent)
    			  oncle = grandParent().leftChild;
    		  else
    			  oncle = grandParent().leftChild;
    	  }
    	  return oncle;
      }
      
      RBNode<T> sibling()
      {
         // À COMPLÉTER
    	  return (parent != null && parent.rightChild == this) ? parent.leftChild : parent.rightChild;
      }
      
      public String toString()
      {
         return value + " (" + (color == RB_COLOR.BLACK ? "black" : "red") + ")"; 
      }
      
      boolean isBlack(){ return (color == RB_COLOR.BLACK); }
      boolean isRed(){ return (color == RB_COLOR.RED); }
      boolean isNil(){ return (leftChild == null) && (rightChild == null); }
      
      void setToBlack(){ color = RB_COLOR.BLACK; }
      void setToRed(){ color = RB_COLOR.RED; }
   }
}
