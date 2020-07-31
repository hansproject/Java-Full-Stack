class GraphQueue
{
	private final int SIZE = 20;
	private int[] queArray;
	private int front;
	private int rear;
// -------------------------------------------------------------	
	public GraphQueue()
	{
		queArray = new int[SIZE];
		front = 0;
		rear = -1;
	}
// -------------------------------------------------------------	
	public void insert(int j) //put item at rear of queue
	{
		if(rear == SIZE - 1)
			rear = -1;
		queArray[++rear] = j;
	}
// -------------------------------------------------------------	
	public int remove()	//take item from front of queue
	{
		int temp = queArray[front++];
		if(front == SIZE)
			front = 0;
		return temp;
	}
// -------------------------------------------------------------	
	public boolean isEmpty()
	{
		return (rear + 1 == front || (front+SIZE-1 == rear) );
	}
} //end class GraphQueue
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
class Graph
{
	private final int MAX_VERTS = 20;
	private Vertex vertexList[]; //list of vertices
	private int adjMat[][];	//adjacency matrix
	private int nVerts;	//current number of vertices
	private GraphQueue theQueue;
// -------------------------------------------------------------   	
	public Graph()
	{
		vertexList = new Vertex[MAX_VERTS];
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j = 0; j<MAX_VERTS; j++)			//set adjacency
			for(int k = 0; k<MAX_VERTS; k++)		//matrix to 0
				adjMat[j][k] = 0;
		theQueue = new GraphQueue();
	}
// -------------------------------------------------------------   	
	public void addVertex(char lab)
	{
		vertexList[nVerts++] = new Vertex(lab);
	}
// -------------------------------------------------------------   	
	public void addEdge(int start, int end)
	{
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
// -------------------------------------------------------------   	
	public void displayVertex(int v)
	{
		System.out.print(vertexList[v].label);
	}
// -------------------------------------------------------------   	
	public void bfsVertex()
	{
		vertexList[0].wasVisited = true;
		displayVertex(0);
		theQueue.insert(0);
		int v2;
		
		while(!theQueue.isEmpty())
		{
			int v1 = theQueue.remove();
			while( (v2=getAdjUnvisitedVertex(v1)) != -1)
			{
				vertexList[v2].wasVisited = true;
				displayVertex(v2);
				theQueue.insert(v2);
			}
		}
		
		for(int j = 0; j < nVerts; j++)
			vertexList[j].wasVisited = false;
	} 	
// -------------------------------------------------------------\      
// Begining of my code        
//--------------------------------------------------------------		  
	public void bfs()
	{
		vertexList[0].wasVisited = true;
		theQueue.insert(0);
		int v2;
		
		while(!theQueue.isEmpty())
		{
			int v1 = theQueue.remove();
			while( (v2=getAdjUnvisitedVertex(v1)) != -1)
			{
				vertexList[v2].wasVisited = true;
				displayVertex(v1);
				displayVertex(v2);
				System.out.print(" ");
				theQueue.insert(v2);
			}
		}
		
		for(int j = 0; j < nVerts; j++)
			vertexList[j].wasVisited = false;        
	} //end bfs
//--------------------------------------------------------------		   
// End of my code      
// -------------------------------------------------------------\ 
	public int getAdjUnvisitedVertex(int v)
	{
		for(int j = 0; j < nVerts; j++)
			if(adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
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
      theGraph.addVertex('A');    // 0  (start for bfs)
      theGraph.addVertex('B');    // 1
      theGraph.addVertex('C');    // 2
      theGraph.addVertex('D');    // 3
      theGraph.addVertex('E');    // 4
      theGraph.addVertex('F');    // 5
      theGraph.addVertex('G');    // 6
      theGraph.addVertex('H');    // 7
      theGraph.addVertex('I');    // 8

      theGraph.addEdge(0, 1);     // AB
      theGraph.addEdge(0, 8);     // AI

      theGraph.addEdge(1, 2);     // BC
      theGraph.addEdge(1, 3);     // BD
      theGraph.addEdge(1, 4);     // BE

      theGraph.addEdge(8, 5);     // IF

      theGraph.addEdge(2, 3);     // CD

      theGraph.addEdge(3, 6);     // DG
      theGraph.addEdge(3, 7);     // DH

      theGraph.addEdge(4, 5);     // EF
      theGraph.addEdge(4, 6);     // EG

      theGraph.addEdge(6, 7);     // GH

		
		System.out.print("Minimum Spanning Tree of Graph: ");
		theGraph.bfs();            // breadth-first search
		System.out.println();
      }  // end main()
   }  // end class BFSApp





























