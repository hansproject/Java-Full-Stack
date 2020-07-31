////////////////////////////////////////////////////////////////
class StackX
{
	private final int SIZE = 20;
	private int[] st;
	private int top;
// -------------------------------------------------------------		
	public StackX()
	{
		st = new int[SIZE];
		top = -1;
	}
// -------------------------------------------------------------		
	public void push(int j)
	{ st[++top] = j; }
// -------------------------------------------------------------		
	public int pop()
	{ return st[top--]; }
// -------------------------------------------------------------		
	public int peek()
	{ return st[top]; }
// -------------------------------------------------------------		
	public boolean isEmpty()
	{ return top == -1; }
}
////////////////////////////////////////////////////////////////
class Vertex
{
	public char label;	//label (e.g. 'A')
	public boolean wasVisited;
// -------------------------------------------------------------	
	public Vertex(char lab)
	{
		label = lab;
		wasVisited = false;
	}
	
} //end class Vertex
////////////////////////////////////////////////////////////////
class Link
{
	public int vertex;
	public Link next;
	
	
}
////////////////////////////////////////////////////////////////
class LinkList
{
	public Link head;
// -------------------------------------------------------------		
	public LinkList()
	{ 
		head = new Link();
		head.vertex = -1;
	}
// -------------------------------------------------------------		
	public void insert(int v)
	{
		Link currentNode = head;
		while(currentNode.next != null)
      {
			currentNode = currentNode.next;
      }   
		currentNode.next = new Link();
		currentNode.next.vertex = v;
	}
// -------------------------------------------------------------		
	public Link deleteLast()
	{
		if(head == null) return null;
		Link currentNode = head;
		while(currentNode.next.next != null)
      {
			currentNode = currentNode.next;
      }   
		Link temptNode = currentNode.next;
		currentNode.next = null;
		return temptNode;
	}
// -------------------------------------------------------------	
	public boolean contains(int j)
	{
		Link currentNode = head;
		while(currentNode != null)
		{
			if(currentNode.vertex == j)
             return true;
			currentNode = currentNode.next;
		}
		return false;
	}
}
////////////////////////////////////////////////////////////////
class Graph
{
	private final int MAX_VERTS = 20;
	private Vertex[] vertexList;
	private LinkList[] adjMat;
	private int nVerts;
	private StackX theStack;
// -------------------------------------------------------------   	
	public Graph()
	{
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new LinkList[MAX_VERTS];
		nVerts = 0;
		for(int j = 0; j < MAX_VERTS; j++)
			adjMat[j] = new LinkList();
		theStack = new StackX();
	}
// -------------------------------------------------------------   	
	public void addVertex(char lab)
	{
		vertexList[nVerts++] = new Vertex(lab);
	}
// -------------------------------------------------------------   	
	public void addEdge(int start, int end)
	{
		adjMat[start].insert(end);
	}
// -------------------------------------------------------------   	
	public void displayVertex(int v)
	{
		System.out.print(vertexList[v].label);
	}
// -------------------------------------------------------------   	
   public void makeTableDisplay()
   {
      for(int i = 0; i < nVerts; i++)
      {
		vertexList[i].wasVisited = true;
		displayVertex(i);
		theStack.push(i);
		
		while(!theStack.isEmpty())
		{
			int v = getAdjUnvisitedVertex(theStack.peek());
			if(v == -1)
				theStack.pop();
			else
			{
				vertexList[v].wasVisited = true;
				displayVertex(v);
				theStack.push(v);
			}
		}
		System.out.println();
		for(int j = 0; j < nVerts; j++)
			vertexList[j].wasVisited = false;

      } 
      // Skip 1 lines
		System.out.print("\n  "); 
      // Display vertices                  
      for(int i = 0; i < nVerts; i++) 
      { 
         displayVertex(i);  
         System.out.print(" ");
      } 
      // Skip 1 lines      
		System.out.print("\n  ");    
      // Display the columns and lines    
      for(int i = 0; i < 2 * nVerts + 1; i++) 
         System.out.print("="); 
		System.out.println();           
      for(int i = 0; i < nVerts; i++)
      {     
         displayVertex(i); 
         System.out.print(" ");           
         for(int j = 0; j < nVerts; j++)
         {
            if(adjMat[i].contains(j) == false)
               System.out.print("0 ");
            else 
               System.out.print("1 ");              
         }      
		   System.out.println();     
      }                               
   }	
// -------------------------------------------------------------\      
// Begining of my code        
//--------------------------------------------------------------	
	public void makeTable()
	{
      for(int i = 0; i < nVerts; i++)
      {
		vertexList[i].wasVisited = true;
		theStack.push(i);
		
		while(!theStack.isEmpty())
		{
			int v = getAdjUnvisitedVertex(theStack.peek());
			if(v == -1)
				theStack.pop();
			else
			{
				vertexList[v].wasVisited = true;
				theStack.push(v);
			}
		}
		for(int j = 0; j < nVerts; j++)
			vertexList[j].wasVisited = false;

      }      
	} //end makeTable()
//--------------------------------------------------------------		
	public void adjMatDisplay()
	{	
			makeTableDisplay();
	}
//--------------------------------------------------------------		   
// End of my code      
// -------------------------------------------------------------\    
	public int getAdjUnvisitedVertex(int v)
	{
		for(int j = 0; j < nVerts; j++)
			if(adjMat[v].contains(j) && vertexList[j].wasVisited == false)
				return j;
		return -1;
	}
// -------------------------------------------------------------	
} //end class Graph
////////////////////////////////////////////////////////////////
class BFSApp
{
	public static void main(String[] args)
	{
	 Graph theGraph = new Graph();
      theGraph.addVertex('A');    // 0  (start for dfs)
      theGraph.addVertex('B');    // 1
      theGraph.addVertex('C');    // 2
      theGraph.addVertex('D');    // 3
      theGraph.addVertex('E');    // 4

      theGraph.addEdge(0, 2);     // AC
      theGraph.addEdge(1, 0);     // BA
      theGraph.addEdge(1, 4);     // BE
      theGraph.addEdge(3, 4);     // DE
      theGraph.addEdge(4, 2);     // EC

      System.out.println("Connectivity Table");
      theGraph.makeTable();
      System.out.println("\n");
      theGraph.adjMatDisplay();
      }  // end main()
   }  // end class BFSApp





























