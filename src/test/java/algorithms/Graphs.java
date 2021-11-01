package algorithms;
import java.util.*;

import org.junit.Test;

public class Graphs
{


    // Java program to print BFS traversal from a given source vertex.
    // BFS(int s) traverses vertices reachable from s.
    @Test
    public void bfsTraversal()
    {
        final Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        final List<Integer> traversal = g.bfs(2);
        System.out.println(traversal);
    }


    @Test
    public void dfsTraversal()
    {
        final Graph g = new Graph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.dfs(2);
    }


    @Test
    public void findConnectedComponents()
    {

        Map<Integer, List<Integer>> adjacencySets = getAdjacencySets(createGrid());

        System.out.println("Adjacency sets: " + adjacencySets);


        final Map<Integer, Set<Integer>> regions = new HashMap<>();

        Integer regonCounter = 0;
        for(final Integer node : adjacencySets.keySet())
        {
            final List<Integer> path = bfsSearchPath(adjacencySets, node);

            if(!path.isEmpty())
            {
                if(regions.isEmpty())
                {
                    regions.put(regonCounter, new HashSet<>(path));
                    regonCounter++;
                }

                boolean foundMatch = false;
                for(final Integer regionId : new ArrayList<>(regions.keySet()))
                {
                    final Set<Integer> regionSet = regions.get(regionId);
                    if(regionSet.stream().anyMatch(path::contains))
                    {
                        // same region
                        foundMatch = true;
                        break;
                    }
                }
                if(!foundMatch)
                {
                    regions.put(regonCounter, new HashSet<>(path));
                    regonCounter++;
                }
            }
        }


    }


    @Test
    public void findBiggestConnectedArea()
    {
        Map<Integer, List<Integer>> adjacencySets = getAdjacencySets(createGrid());

        System.out.println("Adjacency sets: " + adjacencySets);


        int regionMaxSize = 0;
        for(final Integer node : adjacencySets.keySet())
        {
            final List<Integer> path = bfsSearchPath(adjacencySets, node);

            if(!path.isEmpty())
            {
                regionMaxSize = Math.max(regionMaxSize, path.size());
            }
        }
    }

    public static int maxRegion(List<List<Integer>> grid) {
        // Write your code here
        Map<Integer, List<Integer>> adjacencySets = getAdjacencySets(grid);

        System.out.println("Adjacency sets: " + adjacencySets);


        int regionMaxSize = 0;
        for(final Integer node : adjacencySets.keySet())
        {
            final List<Integer> path = bfsSearchPath(adjacencySets, node);

            if(!path.isEmpty())
            {
                regionMaxSize = Math.max(regionMaxSize, path.size());
            }
        }

        return regionMaxSize;
    }


    private static List<Integer> bfsSearchPath(final Map<Integer, List<Integer>> adjacencySets, final Integer node)
    {
        final Map<Integer, Boolean> visited = new HashMap<>();
        for(final Integer label : adjacencySets.keySet())
        {
            visited.put(label, Boolean.FALSE);
        }

        // mark current node as visited
        visited.put(node, Boolean.TRUE);

        final Queue<Integer> que = new LinkedList<>();
        que.add(node);

        final List<Integer> path = new ArrayList<>();
        while(!que.isEmpty())
        {
            final Integer vV = que.poll();

            final List<Integer> neighbours = adjacencySets.get(vV);

            for(final Integer nV : neighbours)
            {
                if(!visited.get(nV))
                {
                    que.add(nV);
                    visited.put(nV, Boolean.TRUE);
                }
            }
            path.add(vV);
        }

        return path;
    }


    private static List<Integer> dfsSearchPath(final Map<Integer, List<Integer>> adjacencySets, final Integer startNode, final Integer targetNode)
    {
        final Map<Integer, Boolean> visited = new HashMap<>();
        for(final Integer label : adjacencySets.keySet())
        {
            visited.put(label, Boolean.FALSE);
        }

        // mark current node as visited
        visited.put(startNode, Boolean.TRUE);

        final Stack<Integer> stack = new Stack<>();
        stack.add(startNode);

        final List<Integer> path = new ArrayList<>();
        while(!stack.isEmpty())
        {
            final Integer vV = stack.pop();

            final List<Integer> neighbours = adjacencySets.get(vV);

            for(final Integer nV : neighbours)
            {
                if(!visited.get(nV))
                {
                    stack.add(nV);
                    visited.put(nV, Boolean.TRUE);
                }
            }
            path.add(vV);
        }

        return path;
    }


    private static Map<Integer, List<Integer>> getAdjacencySets(final List<List<Integer>> grid)
    {
        final int xX = grid.size();
        final int yY = grid.get(0).size();

        // label each non-zero pixel
        int idCounter = 1;
        for(int iI = 0; iI < xX; iI++)
        {
            for(int jI = 0; jI < yY; jI++)
            {
                if(grid.get(iI).get(jI) > 0)
                {
                    grid.get(iI).set(jI, idCounter);
                    idCounter++;
                }
            }
        }

        for(final List<Integer> list: grid)
        {
            System.out.println(list);
        }

        final Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();

        for(int iI = 0; iI < xX; iI++)
        {
            for(int jJ = 0; jJ < yY; jJ++)
            {
                // on a island
                if(grid.get(iI).get(jJ) > 0)
                {
                    final ArrayList<Integer> neighbours = new ArrayList<>();
                    // look for neighbours.
                    for(int ii = iI - 1; ii <= iI + 1; ii++)
                    {
                        for(int jj = jJ - 1; jj <= jJ + 1; jj++)
                        {
                            if(ii >= 0 && jj >= 0 && ii < xX && jj < yY)
                            {
                                if(!(ii == iI && jj == jJ) && grid.get(ii).get(jj) > 0)
                                {
                                    neighbours.add(grid.get(ii).get(jj));
                                }
                            }
                        }
                    }
                    adjacencyMap.put(grid.get(iI).get(jJ), neighbours);
                    idCounter++;
                }
            }
        }
        return adjacencyMap;
    }


    private final List<List<Integer>> createGrid()
    {
        final int xSize = 5;
        final int ySize = 6;
        final List<List<Integer>> grid = new ArrayList<>(xSize);

        for(int i = 0; i < xSize; i++)
        {
            final ArrayList<Integer> yPixels = new ArrayList<Integer>(ySize);
            for(int j = 0; j < ySize; j++)
            {
                yPixels.add(0);
            }
            grid.add(yPixels);
        }

        // fill some courners
        // island 1
        grid.get(0).set(0, 1);
        grid.get(1).set(0, 1);
        grid.get(0).set(1, 1);

        // island 2
        grid.get(xSize-1).set(0, 1);

        // island 3
        grid.get(xSize-1).set(ySize-1, 1);
        grid.get(xSize-1).set(ySize-2, 1);
        grid.get(xSize-2).set(ySize-1, 1);
        grid.get(xSize-2).set(ySize-2, 1);

//        for(final List<Integer> list: grid)
//        {
//            System.out.println(list);
//        }
        return grid;
    }


    // This class represents a directed graph using adjacency list representation
    private static class Graph
    {
        private int nVerticies; // No. of vertices
        private LinkedList<Integer> adj[]; //Adjacency Lists

        // Constructor
        Graph(int v)
        {
            this.nVerticies = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList();
        }

        // Function to add an edge into the graph
        void addEdge(int v,int w)
        {
            adj[v].add(w);
        }

        // return BFS traversal from a given source v
        public List<Integer> bfs(final int v)
        {
            // Mark all the vertices as not visited(By default
            // set as false)
            boolean visited[] = new boolean[nVerticies];

            // Create a queue for BFS
            LinkedList<Integer> queue = new LinkedList<Integer>();

            // Mark the current node as visited and enqueue it
            visited[v]=true;
            queue.add(v);

            final List<Integer> output = new ArrayList<>();
            while (!queue.isEmpty())
            {
                // Dequeue a vertex from queue and print it
                final int vi = queue.poll();
                output.add(vi);

                // Get all adjacent vertices of the dequeued vertex s
                // If a adjacent has not been visited, then mark it
                // visited and enqueue it
                final Iterator<Integer> i = adj[vi].listIterator();
                while (i.hasNext())
                {
                    int n = i.next();
                    if (!visited[n])
                    {
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
            return output;
        }


        /**
         * Depth first search.
         *
         * @param v
         */
        public void dfs(final int v)
        {
            boolean visited[] = new boolean[nVerticies];

            visited[v] = true;

            dfsSearch(v, visited);
        }


        private void dfsSearch(final int v, final boolean[] visited)
        {
            visited[v] = true;

            final Iterator<Integer> it = adj[v].listIterator();

            System.out.print(v + " ");
            while(it.hasNext())
            {
                final int n = it.next();
                if(!visited[n])
                {
                    dfsSearch(n, visited);
                }
            }
        }

    }

}
