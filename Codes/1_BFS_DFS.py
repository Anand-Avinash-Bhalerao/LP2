class Graph:
    def __init__(self, edges, disjoint=None):
        self.graph = dict()

        self.n = max([max(u, v) for u,v in edges])
        self.verts = set([i for j in edges for i in j])
        for v in self.verts:
            self.graph[v] = []
        if disjoint is not None:
            for j in disjoint:
                self.verts.add(j)
                self.graph[j] = []

        self.bfs_visited = None
        self.bfs_queue = None
        self.bfs_traversal = None

        self.dfs_visited = None
        self.dfs_traversal = None

        if edges is not None:
            for (u, v) in edges:
                self.graph[u].append(v)
                self.graph[v].append(u)
    
    def addEdge(self, u, v):
        self.graph[u].append(v)
        self.graph[v].append(u)
    
    def BFS(self, k=None):
        self.bfs_visited = [False] * (max(self.graph) + 1)
        self.bfs_queue = []
        self.bfs_traversal = []

        if k is not None:
            self.bfs_visited[k] = True
            self.bfs_queue.append(k)
            self._callBFS()

        for i in self.verts:
            if not self.bfs_visited[i]:
                self.bfs_visited[i] = True
                self.bfs_queue.append(i)
                self._callBFS()
        self._printBFS()
        self._resetBFS()
    
    def DFS(self, k=None):
        self.dfs_visited = [False] * (max(self.graph) + 1)
        self.dfs_traversal = []

        if k is not None:
            self.dfs_visited[k] = True
            self.dfs_traversal.append(k)
            self._callDFS(k)

        for i in self.verts:
            if not self.dfs_visited[i]:
                self.dfs_visited[i] = True
                self.dfs_traversal.append(i)
                self._callDFS(i)
        self._printDFS()
        self._resetDFS()
    
    def _callDFS(self, u):
        for v in self.graph[u]:
            if not self.dfs_visited[v]:
                self.dfs_visited[v] = True
                self.dfs_traversal.append(v)
                self._callDFS(v)
    
    def _callBFS(self):
        if not len(self.bfs_queue):
            return
        
        u = self.bfs_queue.pop(0)
        self.bfs_traversal.append(u)

        for v in self.graph[u]:
            if not self.bfs_visited[v]:
                self.bfs_visited[v] = True
                self.bfs_queue.append(v)
        
        self._callBFS()
    
    def _resetBFS(self):
        self.bfs_visited = None
        self.bfs_queue = None
        self.bfs_traversal = None
    
    def _printBFS(self):
        print("BFS:", end="\t")
        for v in self.bfs_traversal:
            print(v, end="  ")
        print("\n")
    
    def _resetDFS(self):
        self.dfs_visited = None
        self.dfs_traversal = None
    
    def _printDFS(self):
        print("DFS:", end="\t")
        for v in self.dfs_traversal:
            print(v, end="  ")
        print("\n")
    
    def printGraph(self):
        print("Graph:", end="\t")
        print(self.graph, end="\n\n")

if __name__ == "__main__":
    edges = [
        (0, 1), (0, 2), (1, 3), (2, 3), (3, 4), (3, 5)
    ]

    g = Graph(edges, disjoint=[8, 9])
    g.printGraph()
    g.BFS(5)
    g.DFS(4)