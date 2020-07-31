// tree.java
// demonstrates binary tree
// to run this program: C>java TreeApp
import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class Node
   {
   public char iData;              // data item (key)
   public char dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
   
   public Node() {}
   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(iData);
      System.out.print(", ");
      System.out.print(dData);
      System.out.print("} ");
      }
   }  // end class Node
//////////////////////
//////////////////////////////////////////
class BottomUp
{
   private String aStr;
   private Node   forest[] = null;
   private Tree   tree;
   private int    ind;
// -------------------------------------------------------------   
   public BottomUp(String str)
	{
	   aStr = str;  
      forest = new Node[str.length()];   
      tree   = new Tree();
      tree.root = new Node();
      tree.root.leftChild = new Node();
      tree.root.rightChild = new Node();                      
	}
// -------------------------------------------------------------	
	public void balanced()
   { 
      int forestSize = aStr.length();
      Node current   = new Node();
      current.iData  = '+';                                    
      for(int i = 0; i < aStr.length() ; i ++)
      {
         forest[i]            = new Node();
         forest[i].rightChild = new Node();
         forest[i].leftChild  = new Node();      
         forest[i].rightChild = null;    
         forest[i].leftChild  = null;                       
         forest[i].iData      = aStr.charAt(i);                    
      }   
      if(aStr.length() == 1)
      {
         tree.root.leftChild.iData = '+';
         tree.root.rightChild.iData = aStr.charAt(0);
      }  		
      else
      {
         while(forestSize > 1)
         {
            int currentIndex   = 0;  
            for(int i = 0; i < forestSize; i++) 
            {  
               if(i % 2 == 0 && i == forestSize - 1)
               {
                  forest[currentIndex] = forest[i];
                  System.out.print(forest[currentIndex].iData);
               }                                 
               if(i % 2 == 1)
               { 
                  Node newNode         = new Node();
                  newNode.rightChild   = new Node();
                  newNode.leftChild    = new Node();  

                  newNode.iData        = '+';                   
                  newNode.leftChild    = forest[i - 1];                  
                  newNode.rightChild   = forest[i];                     
                  forest[currentIndex] = newNode; 
                  currentIndex++;   
              }                
            }  
            forestSize = forestSize - currentIndex;                           
         }
         tree.root = forest[0];			
      }
	}
// -------------------------------------------------------------   
	public void unbalanced()
   { 
      Node current = new Node();
      Node temp    = new Node();    
      current.iData = '+';                                    
      for(int i = 0; i < aStr.length() ; i ++){
         forest[i]            = new Node();
         forest[i].rightChild = new Node();
         forest[i].leftChild  = new Node();      
         forest[i].rightChild = null;    
         forest[i].leftChild  = null;                       
         forest[i].iData      = aStr.charAt(i);       
      }         
      if(aStr.length() == 1){
         tree.root.leftChild.iData = '+';
         tree.root.rightChild.iData = aStr.charAt(0);
      }  
      else {  
         current.leftChild  =  forest[0];
         current.rightChild =  forest[1];       
         for(int i = 2; i < aStr.length(); i++) {  
            Node newNode = new Node();
            newNode.iData      = '+';
            newNode.rightChild = new Node();
            newNode.leftChild  = new Node();      
            newNode.leftChild  = current;
            newNode.rightChild = forest[i];
            current            = newNode;                        
         }
         tree.root = current;
       }
   }  
// -------------------------------------------------------------   
   public Tree getTree()
   {
      return tree;
   } 
}
// -------------------------------------------------------------
class Tree
   {
   public Node root;             // first node of tree

// -------------------------------------------------------------
   public Tree()                  // constructor
      { 
		   root = null; 
		}            // no nodes in tree yet
// -------------------------------------------------------------
   public void displayTree()
      {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty==false)
         {
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false)
            {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
               {
               System.out.print(temp.iData);
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null ||
                                   temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("-");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push( localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println(
      
      "......................................................");
      }  // end displayTree()
// -------------------------------------------------------------
   public void traverse(int traverseType)
      {
      switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         System.out.print(localRoot.iData + " ");
         preOrder(localRoot.leftChild);
         preOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         inOrder(localRoot.leftChild);
         System.out.print(localRoot.iData + " ");
         inOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void postOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         postOrder(localRoot.leftChild);
         postOrder(localRoot.rightChild);
         System.out.print(localRoot.iData + " ");
         }
      }
// -------------------------------------------------------------
   // returns node with next-highest value after delNode
   // goes to right child, then right child's left descendents
   private Node getSuccessor(Node delNode)
      {
      Node successorParent = delNode;
      Node successor = delNode;
      Node current = delNode.rightChild;   // go to right child
      while(current != null)               // until no more
         {                                 // left children,
         successorParent = successor;
         successor = current;
         current = current.leftChild;      // go to left child
         }
                                           // if successor not
      if(successor != delNode.rightChild)  // right child,
         {                                 // make connections
         successorParent.leftChild = successor.rightChild;
         successor.rightChild = delNode.rightChild;
         }
      return successor;
      }
// -------------------------------------------------------------
   }  // end class Tree
////////////////////////////////////////////////////////////////
class TreeApp
   {
/* You need to write classes Node, Tree, BottomUp, and BottomUpApp with main method */
public static void main(String[] args) throws IOException
      {
      BottomUp bup;
      Tree theTree = null;
      int value;
      String str;

      while(true)
         {
         System.out.print("Enter first letter of balanced, ");
         System.out.print("unbalanced, show, or traverse: ");
         char choice = getChar();
         switch(choice)
            {
            case 'b':
               System.out.print("Enter string: ");
               str = getString();
               bup = new BottomUp(str);
               bup.balanced();
               theTree = bup.getTree();
               break;
            case 'u':
               System.out.print("Enter string: ");
               str = getString();
               bup = new BottomUp(str);
               bup.unbalanced();
               theTree = bup.getTree();
               break;
            case 's':
               theTree.displayTree();
               break;
            case 't':
               System.out.print("Enter type 1, 2 or 3: ");
               value = getInt();
               theTree.traverse(value);
               break;
            default:
               System.out.print("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()

// -------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
// -------------------------------------------------------------
   public static char getChar() throws IOException
      {
      String s = getString();
      return s.charAt(0);
      }
//-------------------------------------------------------------
   public static int getInt() throws IOException
      {
      String s = getString();
      return Integer.parseInt(s);
      }
// -------------------------------------------------------------
   }  // end class TreeApp
////////////////////////////////////////////////////////////////
