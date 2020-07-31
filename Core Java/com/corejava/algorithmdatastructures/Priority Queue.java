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
	public void toss(int key) //just throws a new node at the end of the array
	{
		aHpNode newNode = new aHpNode(key);
		heapArray[currentSize] = newNode;
		currentSize++;
	}
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
	
//--------------------------------------------------------------		
	public boolean change(int index, int newValue)
	{
		if(index<0 || index>=currentSize)
			return false;
		int oldValue = heapArray[index].getKey();
		heapArray[index].setKey(newValue);
		
		if(oldValue > newValue)
			trickleUp(index); // Trickle up when new value is < than old value 
		else
			trickleDown(index);
		return true;
	}
//--------------------------------------------------------------		   
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
////////////////////////////////////////////////////////////////
// -------------------------------------------------------------\      
// Begining of my code        
//--------------------------------------------------------------			
class PriorityQ
{
	Heap aHeap;
//--------------------------------------------------------------		
	public PriorityQ(int size)
	{
		aHeap = new Heap(size);
	}
//--------------------------------------------------------------		
	public boolean insert(int value)// Use the insert method of the heap to insert
	{
		return aHeap.insert(value);
	}
//--------------------------------------------------------------		
	public int remove()
	{
		int tempNode = aHeap.remove().getKey();
		return tempNode;
	}
//--------------------------------------------------------------		
	public boolean isEmpty()// The Pq is empty if the heap is empty
	{
		return aHeap.isEmpty();
	}
//--------------------------------------------------------------		
	public void displayQueue()
	{
		aHeap.displayHeap();
	}
//--------------------------------------------------------------	   
   public boolean change(int val1, int val2)
   {
      if(aHeap.change(val1, val2))
         return true;
      else 
         return false;  
   }
}
//--------------------------------------------------------------		   
// End of my code      
// -------------------------------------------------------------\ 
//End PQ class
//--------------------------------------------------------------	
class HeapQueueApp
{
	public static void main(String[] args) throws IOException
	{
      int maxSize = 100;            // max size of priority Q
      int value, value2;
      boolean success;
                                    // make a priority Q
      PriorityQ thePQ = new PriorityQ(maxSize);

      thePQ.insert(70);             // insert 10 items
      thePQ.insert(40);
      thePQ.insert(50);
      thePQ.insert(20);
      thePQ.insert(60);
      thePQ.insert(100);
      thePQ.insert(80);
      thePQ.insert(30);
      thePQ.insert(10);
      thePQ.insert(90);

      while(true)                   // until [Ctrl]-[C]
         {
         System.out.print("Enter first letter of ");
         System.out.print("show, insert, remove, change: ");
         int choice = getChar();
         switch(choice)
            {
            case 's':                        // show
               thePQ.displayQueue();
               break;
            case 'i':                        // insert
               System.out.print("Enter value to insert: ");
               value = getInt();
               success = thePQ.insert(value);
               if( !success )
                  System.out.println("Can't insert; heap full");
               break;
            case 'r':                        // remove
               if( !thePQ.isEmpty() )
                  thePQ.remove();
               else
                  System.out.println("Can't remove; PQ empty");
               break;
            case 'c':                        // change
               System.out.print("Enter index of item: ");
               value = getInt();
               System.out.print("Enter new key: ");
               value2 = getInt();
               success = thePQ.change(value, value2);
               if( !success )
                  System.out.println("invalid index");
               break;
            default:
               System.out.print("Invalid entry\n");
            }  // end switch
         }  // end while
                  
	} //end main
	
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
} //end class HeapQueueApp