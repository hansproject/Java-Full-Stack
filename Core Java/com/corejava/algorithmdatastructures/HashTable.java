/*
Hans Peter Ndeffo
*/
import java.io.*;
////////////////////////////////////////////////////////////////
class Link
   {                                // (could have more data)
   private int iData;               // data item (key)
//--------------------------------------------------------------
   public Link(int ii)          // constructor
      { iData = ii; }
//--------------------------------------------------------------
   public int getKey()
      { return iData; }
//--------------------------------------------------------------
   }  // end class Link
////////////////////////////////////////////////////////////////
class HashTable
{
	private Link[] hashArray;
	private int arraySize;
	private Link nonItem;
	
	public HashTable(int size)
	{
		arraySize = size;
		hashArray = new Link[arraySize];
		nonItem = new Link(-1); //deleted item key is -1
	}
//--------------------------------------------------------------	
	public void displayTable()
	{
		System.out.print("Table: ");
		for(int j = 0; j < arraySize; j++)
		{
			if(hashArray[j] != null)
				System.out.print(hashArray[j].getKey() + " ");
			else
				System.out.print("** ");
		}
		System.out.println("");
	}
//--------------------------------------------------------------	
	public int hashFunction(int key)
	{
		return key % arraySize;
	}
// -------------------------------------------------------------\      
// Begining of my code        
//--------------------------------------------------------------	
	public void insert(int key,Link item)
	{
      // If we can insert more elements
		int hashVal = hashFunction(key),quadraticValue = 1;

		while(hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1)
		{
			hashVal = hashVal + (quadraticValue * quadraticValue);
			quadraticValue++;
			hashVal %= arraySize;
		}
      
		hashArray[hashVal] = item;
	}
// -------------------------------------------------------------\      
// End of my code      
// -------------------------------------------------------------\ 
	public Link delete(int key)
	{
		int hashVal = hashFunction(key);
		int quadraticValue = 1;
		
		while(hashArray[hashVal] != null)
		{
			if(hashArray[hashVal].getKey() == key)
			{
				Link temp = hashArray[hashVal];
				hashArray[hashVal] = nonItem;
				return temp;
			}
			hashVal += quadraticValue*quadraticValue;
			quadraticValue++;
			hashVal %= arraySize;
		}
		return null;
	}
//--------------------------------------------------------------	
	public Link find(int key)
	   {
		int hashVal = hashFunction(key);
		int quadraticValue = 1;
		while(hashArray[hashVal] != null)
		{
			if(hashArray[hashVal].getKey() == key)
				return hashArray[hashVal];
			hashVal += quadraticValue*quadraticValue;
			quadraticValue++;
			hashVal %= arraySize;
		}
		return null;
	   }
} //end class HashTable
//--------------------------------------------------------------
class HashTableApp
{
	public static void main(String[] args) throws IOException
	{
		Link aLink;
		int  aKey;
      int  size, n;
		
      System.out.print("Enter size of hash table: ");// get sizes
      size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();      
      HashTable theHashTable = new HashTable(size);// make table


      for(int j=0; j<n; j++)      // insert data
         {
         aKey = (int)(java.lang.Math.random() * 2 * size);
         aLink = new Link(aKey);
         theHashTable.insert(aKey, aLink);
         }

      while(true)                 // interact with user
         {
         System.out.print("Enter first letter of ");
         System.out.print("show, insert, delete, or find: ");
         char choice = getChar();
         switch(choice)
            {
            case 's':
               theHashTable.displayTable();
               break;
            case 'i':
               System.out.print("Enter key value to insert: ");
               aKey = getInt();
               aLink = new Link(aKey);
               theHashTable.insert(aKey, aLink);
               break;
            case 'd':
               System.out.print("Enter key value to delete: ");
               aKey = getInt();
               theHashTable.delete(aKey);
               break;
            case 'f':
               System.out.print("Enter key value to find: ");
               aKey = getInt();
               aLink = theHashTable.find(aKey);
               if(aLink != null)
                  System.out.println("Found " + aKey);
               else
                  System.out.println("Could not find " + aKey);
               break;
            default:
               System.out.print("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()
// -------------------------------------------------------------\ 	
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
// -------------------------------------------------------------\ 	
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
// -------------------------------------------------------------\ 	
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
} //end HashTableApp















