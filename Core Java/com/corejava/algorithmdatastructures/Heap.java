import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
////////////////////////////////////////////////////////////////
class aHpNode
   {
   private int iData;             // data item (key)
// -------------------------------------------------------------
   public aHpNode(int key)           // constructor
      { iData = key; }
// -------------------------------------------------------------
   public int getKey()
      { return iData; }
// -------------------------------------------------------------
   public void setKey(int id)
      { iData = id; }
// -------------------------------------------------------------
   }  // end class aHpNode
////////////////////////////////////////////////////////////////
class Heap 
{
	private aHpNode[] heapArray;
	private int maxSize;
	private int currentSize;
//--------------------------------------------------------------		
	public Heap (int mx)
	{
		maxSize = mx;
		currentSize = 0;
		heapArray = new aHpNode[maxSize];
	}
//--------------------------------------------------------------		
	public boolean isEmpty()
    {return currentSize==0;}
//--------------------------------------------------------------		
	public boolean insert(int key)
	{
		if(currentSize == maxSize)
			return false;
		aHpNode newNode = new aHpNode(key);
		heapArray[currentSize] = newNode;
		trickleUp(currentSize++);
		return true;
	}
//--------------------------------------------------------------		
	public void trickleUp(int index)
	{
		int parent = (index-1) / 2;
		aHpNode bottom = heapArray[index];
		while( index > 0 && heapArray[parent].getKey() < bottom.getKey() )
		{
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent-1)/2;
		}
		heapArray[index] = bottom;
	}
	
//--------------------------------------------------------------		
	public aHpNode remove()
	{
		aHpNode root = heapArray[0]; // (assumes non-empty list)
		heapArray[0] = heapArray[--currentSize];  // delete item with max key
		trickleDown(0);			
		return root;
	}
//--------------------------------------------------------------		
	public void trickleDown(int index)
	{
		int aLargerChild;
		aHpNode top = heapArray[index];
		while(index < currentSize/2) //while node has at least one child
		{
			int leftChild = 2*index+1;
			int rightChild = leftChild+1;
			if(rightChild < currentSize && //rightchild exists?
					heapArray[leftChild].getKey() <
					heapArray[rightChild].getKey() )
               
				aLargerChild = rightChild;
			else
         
				aLargerChild = leftChild;
			if(top.getKey() >= heapArray[aLargerChild].getKey() )
				break;
			
			heapArray[index] = heapArray[aLargerChild];
			index = aLargerChild;
		}
		heapArray[index] = top;
	}
	
// -------------------------------------------------------------\      
// Begining of my code        
//--------------------------------------------------------------			
	public void restoreHeap()
	{
		for(int i =( currentSize / 2 )-1; i >= 0; i --) // visit all the children
      {
			trickleDown(i);
      }   
	}
// -------------------------------------------------------------  
	public boolean toss(int key) // Insert a new node at the end of the array
	{
		aHpNode newNode = new aHpNode(key);
		heapArray[currentSize] = newNode;
      // Increase the size by 1
		currentSize = currentSize + 1;
      return true;
	}  
//--------------------------------------------------------------		   
// End of my code      
// -------------------------------------------------------------\ 
	public boolean change(int index, int newValue)
	{
		if(index<0 || index>=currentSize)
			return false;
		int oldValue = heapArray[index].getKey();
		heapArray[index].setKey(newValue);
		
		if(oldValue < newValue)
			trickleUp(index); // Trickle up when new value is < than old value 
		else
			trickleDown(index);
		return true;
	}
// -------------------------------------------------------------   
	public void displayHeap()
	{
		System.out.print("heapArray: ");
		for(int m = 0; m<currentSize; m++)
			if(heapArray != null)
				System.out.print(heapArray[m].getKey() + " ");
			else
				System.out.print("-- ");
		System.out.println();
		
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "............................";
		System.out.println(dots+dots);
		
		while(currentSize > 0)
		{
			if(column == 0)
				for(int k=0; k<nBlanks; k++)
					System.out.print(' ');
			System.out.print(heapArray[j].getKey());
			if(++j == currentSize)
				break;
			
			if(++column == itemsPerRow)
			{
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			}
			else
				for(int k = 0; k<nBlanks*2-2; k++)
					System.out.print(' ');
		}
		System.out.println("\n"+dots+dots);
	}
} //end class Heap
//--------------------------------------------------------------	
class HeapApp
{
	public static void main(String[] args) throws IOException
	{
      int value, value2;
      Heap theHeap = new Heap(31);  // make a Heap; max size 31
      boolean success;

      theHeap.insert(70);           // insert 6  items
      theHeap.insert(40);
      theHeap.insert(50);
      theHeap.insert(80);
      theHeap.insert(20);
      theHeap.insert(30);

      while(true)                   // until [Ctrl]-[C]
         {
         System.out.print("Enter first letter of ");
         System.out.print("show, insert, toss, restore: ");
         int choice = getChar();
         switch(choice)
            {
            case 's':                        // show
               theHeap.displayHeap();
               break;
            case 'i':                        // insert
               System.out.print("Enter value to insert: ");
               value = getInt();
               success = theHeap.insert(value);
               if( !success )
                  System.out.println("Can't insert; heap full");
               break;
            case 'r':                        // restore heap
               if( !theHeap.isEmpty() )
                  {
                  theHeap.restoreHeap();
                  System.out.println("Heap is restored.");
                  }
               else
                  System.out.println("Can't restore; empty");
               break;
            case 't':                        // toss it on heap
               System.out.print("Enter value to toss: ");
               value = getInt();
               success = theHeap.toss(value);
               if( !success )
                  System.out.println("Can't toss; heap full");
               break;
            default:
               System.out.println("Invalid entry");
            }  // end switch
         }  // end while
	} //end main
//--------------------------------------------------------------		
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
//--------------------------------------------------------------		
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
//--------------------------------------------------------------		
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
} //end class HeapApp