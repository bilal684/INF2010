import java.util.*;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> extends AbstractQueue<AnyType>
{
    private static final int DEFAULT_CAPACITY = 100;
    private int currentSize;      // Nombre d'elements
    private AnyType [ ] array;    // Tableau contenant les donnees (premier element a l'indice 1)
    private boolean min;
    private int modifications;    // Nombre de modifications apportees a ce monceau
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( boolean min )
    {
	this.min = min;
	currentSize = 0;
	array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
	modifications = 0;
    }
    
    @SuppressWarnings("unchecked")
    public BinaryHeap( AnyType[] items, boolean min )
    {
	this.min = min;
	
	// COMPLETEZ

	array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
	currentSize = 0;
	modifications = 0;
	for(int i = 1; i < items.length + 1; i++)
	{
		array[i] = items[i-1];
		currentSize++;
		modifications++;
	}
	// invoquez buildMinHeap() ou buildMaxHeap() en fonction du parametre min;
	
	if(this.min)
		buildMinHeap();
	else
		buildMaxHeap();
    }
    public boolean offer( AnyType x )
    {
	if (x == null)
	    throw new NullPointerException("Cannot insert null in a BinaryHeap");
	
	if( currentSize + 1 == array.length )
	    doubleArray();
	
	// COMPLETEZ
	int pos = 0;
	if(this.min)
		for(pos = ++currentSize; pos > 1 && x.compareTo(array[pos/2]) < 0; pos /= 2)
			array[pos] = array[pos/2];
	else
		for(pos = ++currentSize; pos > 1 && x.compareTo(array[pos/2]) > 0; pos /= 2)
			array[pos] = array[pos/2];
	
	array[pos] = x;
	modifications++;
	return true;
    }
    
    public AnyType peek()
    {
	if(!isEmpty())
	    return array[1];
	
	return null;
    }
    
    public AnyType poll()
    {
	//COMPLETEZ
		if(isEmpty())
			return null;
		AnyType element = array[1];
		array[1] = array[currentSize--];
    	if(min)
    		percolateDownMinHeap(1, currentSize + 1);
    	else
    		percolateDownMaxHeap(1, currentSize + 1);

    	modifications++;
    	return element;
    }
    
    public Iterator<AnyType> iterator()
    {
	return new HeapIterator();
    }
    
    private void buildMinHeap()
    {
	//COMPLETEZ
    	for(int i = currentSize / 2; i > 0; i--)
    		percolateDownMinHeap(i, currentSize);
    }
    
    private void buildMaxHeap()
    {
	//COMPLETEZ
    	for(int i = currentSize / 2; i > 0; i--)
    		percolateDownMaxHeap(i, currentSize);
    }
    
    public boolean isEmpty()
    {
	return currentSize == 0;
    }
    
    public int size()
    {
	return currentSize;
    }
    
    public void clear()
    {
	currentSize = 0;
	modifications = 0;
	array = (AnyType[]) new Comparable[ DEFAULT_CAPACITY + 1];
    }
    
    private static int leftChild( int i, boolean heapIndexing )
    {
	//COMPLETEZ
    	return heapIndexing ? 2 * i : 2 * i + 1;
    }
    
    private void swapReferences( int index1, int index2 )
    {
	swapReferences(array, index1, index2);
    }
    
    private static <AnyType extends Comparable<? super AnyType>>
				    void swapReferences( AnyType[] array, int index1, int index2 )
    {
	AnyType tmp = array[ index1 ];
	array[ index1 ] = array[ index2 ];
	array[ index2 ] = tmp;
    }
    
    @SuppressWarnings("unchecked")
	private void doubleArray()
    {
	AnyType [ ] newArray;
	
	newArray = (AnyType []) new Comparable[ array.length * 2 ];
	for( int i = 0; i < array.length; i++ )
	    newArray[ i ] = array[ i ];
	array = newArray;
    }
    
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMinHeap( int hole, int size )
    {
	percolateDownMinHeap(array, hole, size, true);
    }
    
    /**
     * @param array   Tableau d'element
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>>
				    void percolateDownMinHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
	//COMPLETEZ
    	if(!heapIndexing)
    		size--;
		int fils = 0;
		AnyType tmp = array[hole];
		for(; leftChild(hole,heapIndexing) <= size; hole = fils)
		{
			fils = leftChild(hole,heapIndexing);
			if(fils < size && array[fils + 1].compareTo(array[fils]) < 0)
				fils++;
			if(array[fils].compareTo(tmp) < 0)
				array[hole] = array[fils];
			else
				break;
		}
		array[hole] = tmp;
    }
    
    /**
     * @param hole    Position a percoler
     * @param size    Indice max du tableau
     */
    private void percolateDownMaxHeap( int hole, int size )
    {
	percolateDownMaxHeap(array, hole, size, true);
    }
    
    /**
     * @param array         Tableau d'element
     * @param hole          Position a percoler
     * @param size          Indice max du tableau
     * @param heapIndexing  True si les elements commencent a l'index 1, false sinon
     */
    private static <AnyType extends Comparable<? super AnyType>> 
				    void percolateDownMaxHeap( AnyType[] array, int hole, int size, boolean heapIndexing )
    {
	//COMPLETEZ
    	if(!heapIndexing)
    		size--;
		int fils = 0;
		AnyType tmp = array[hole];
		for(; leftChild(hole,heapIndexing) <= size; hole = fils)
		{
			fils = leftChild(hole,heapIndexing);
			if(fils < size && array[fils + 1].compareTo(array[fils]) > 0)
				fils++;
			if(array[fils].compareTo(tmp) > 0)
				array[hole] = array[fils];
			else
				break;
		}
		array[hole] = tmp;
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSort( AnyType[] a )
    {
	//COMPLETEZ
    	for(int i = a.length / 2; i >= 0; i--)
    		percolateDownMaxHeap(a, i, a.length, false);
    	for(int i = a.length - 1; i > 0; i--)
    	{
    		swapReferences(a, 0, i);
    		
    		percolateDownMaxHeap(a, 0, i, false);
    	}
    	
    }
    
    public static <AnyType extends Comparable<? super AnyType>>
				   void heapSortReverse( AnyType[] a )
    {
	//COMPLETEZ
    	for(int i = a.length / 2; i >= 0; i--)
    		percolateDownMinHeap(a, i, a.length, false);
    	for(int i = a.length - 1; i > 0; i--)
    	{
    		swapReferences(a, 0, i);
    		
    		percolateDownMinHeap(a, 0, i, false);
    	}
    	
    }
    
    public String nonRecursivePrintFancyTree()
    {
    	String outputString = "";
	
    	//COMPLETEZ

        Integer pos = new Integer(1);
        Stack<Integer> st = new Stack<Integer>();
        
        st.push(pos);
        
        while(!st.empty())
        {
        	Integer [ ] items = new Integer[ pos + 2 ];
        	pos = st.pop();
            
            if(pos <= currentSize / 2) 
            {
                st.push(2*pos+1);
                st.push(2*pos);
            }
            
            for( int i = pos, j = 1; i > 0; i /= 2, j++)	
                items[j] = i;			
            
            for( int i = items.length-1; i > 1 ; i-- )
            {
            	if(items[i] != null)
            	{
            		if( items[i] % 2 == 0 )
                    	outputString += "|  ";
            		else
                        outputString += "   ";
            	}
            }
            if( pos <= currentSize )
                outputString += "|__" + array[pos] + "\n";
            else
                outputString += "|__"  + "null" + "\n";
        }
        
        return outputString;
    }
    
    public String printFancyTree()
    {
	return printFancyTree(1, "");
    }
    
    private String printFancyTree( int index, String prefix)
    {
	String outputString = "";
	
	outputString = prefix + "|__";
	
	if( index <= currentSize )
	    {
		boolean isLeaf = index > currentSize/2;
		
		outputString += array[ index ] + "\n";
		
		String _prefix = prefix;
		
		if( index%2 == 0 )
		    _prefix += "|  "; // un | et trois espace
		else
		    _prefix += "   " ; // quatre espaces
		
		if( !isLeaf ) {
		    outputString += printFancyTree( 2*index, _prefix);
		    outputString += printFancyTree( 2*index + 1, _prefix);
		}
	    }
	else
	    outputString += "null\n";
	
	return outputString;
    }
    
    private class HeapIterator implements Iterator {
	
    	private int indexCourant = 1;
    	private int nbEdit =  modifications;
	public boolean hasNext() 
	{
	    //COMPLETEZ
		return indexCourant <= currentSize;
	}

	public Object next() throws NoSuchElementException, 
				    ConcurrentModificationException, 
				    UnsupportedOperationException {
	    //COMPLETEZ
		if(nbEdit != modifications)
			throw new ConcurrentModificationException();
		
		if(!hasNext())
			throw new NoSuchElementException();
		
		return array[indexCourant++];
	}
	
	public void remove() 
	{
	    throw new UnsupportedOperationException();
	}
    }
}