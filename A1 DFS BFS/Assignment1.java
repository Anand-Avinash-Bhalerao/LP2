import java.util.*;

class Graph{
    private int verticesCount;                               //number of nodes in the graph
    private ArrayList<Integer> adjacencyList[];              //adjacency list
    int BFSFlag =0;
    int DFSFlag =0;
    
    Graph(int v){
        verticesCount = v;
        adjacencyList = new ArrayList[v];
        
        for (int i=0; i<v; i++){
            adjacencyList[i] = new ArrayList<>();                   //adjacency list for eacg vertex
        }
    }

    void addEdge(int vertex1,int vertex2){
        adjacencyList[vertex1].add(vertex2);   
        adjacencyList[vertex2].add(vertex1);                        //adding an edge to the adjacencyList list
    }
 
    void DFSUtil(int vertex, boolean visited[], int searchVertex){
        visited[vertex] = true;                                      //mark the node as explored
        System.out.print(vertex + " ");
        if(searchVertex == vertex){
            DFSFlag = 1;     
        }
        for (int u: adjacencyList[vertex]){  
            if (!visited[u]){                                       //only propagate to next nodes which haven't been explored
                DFSUtil(u, visited, searchVertex);
            }
        }  
    }
    void DFS(int v,int searchVertex){
        DFSFlag = 0;
        boolean visited[] = new boolean[verticesCount];             //initialize a new boolean array to store the details of explored nodes
        DFSUtil(v, visited,searchVertex);
        if(DFSFlag==1){
            System.out.println("\nElement Found in Graph");
        }else{
            System.out.println("\nElement Not Found in Graph");
        }
    }

    void recursiveBFS(Queue<Integer> q, boolean[] visited, int searchVertex){
        if (q.isEmpty()){
            return;
        }
        int v = q.poll();                       // dequeue front node and print it
        System.out.print(v + " ");
        visited[v] = true;
        if(searchVertex == v){
            BFSFlag = 1;     
        } 
        for (int u: adjacencyList[v]){          // do for every edge (v, u)
            if (!visited[u]){
                visited[u] = true;              // mark it as discovered and enqueue it
                q.add(u);     
            }
        }

        recursiveBFS(q, visited,searchVertex);
    }


    void BFS(int v,int searchVertex){
        BFSFlag =0;
        boolean[] visited = new boolean[verticesCount];
		Queue<Integer> q = new ArrayDeque<>();                  // create a queue for doing BFS
        q.add(v);    
        recursiveBFS(q, visited,searchVertex);
        if(BFSFlag==1){
            System.out.println("\nElement Found in Graph");
        }else{
            System.out.println("\nElement Not Found in Graph");
        }
    }
}

public class Assignment1{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Number of Vertices : ");
        int vertexCount = sc.nextInt();
        Graph graph = new Graph(vertexCount);

        System.out.print("Enter Number of Edges : ");
        int edgeCount = sc.nextInt();
        for(int i=0;i<edgeCount;i++){
            int v1,v2;
            System.out.println("Add Edge ");
            System.out.print("From Vertex  : ");
            v1=sc.nextInt();
            System.out.print("To Vertex  : ");
            v2=sc.nextInt();
            graph.addEdge(v1,v2);
        }

        boolean run = true;
        while(run){
            System.out.print("\nEnter Search Element : ");
            int searchVertex = sc.nextInt();
            System.out.print("Breadth First Search : "); 
            graph.BFS(0,searchVertex);
            System.out.print("\n\nDepth First Search : "); 
            graph.DFS(0,searchVertex);

            System.out.print("\nWant to Seach other element [Y/N]: ");
            if(!(sc.next().equalsIgnoreCase("y"))){
                run = false;
            }
        }
        sc.close();
    }
}
/*
          0
        / | \
       1  3  4
      /    \/
     2      5 
*/