import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class AbstractGraphExtended  extends AbstractGraph{
    /**
     * Construct a graph with the specified number of vertices
     * and the directed flag. If the directed flag is true,
     * this is a directed graph.
     *
     * @param numV     The number of vertices
     * @param directed The directed flag
     */
    public AbstractGraphExtended(int numV, boolean directed) {
        super(numV, directed);
    }

    @Override
    public void insert(Edge edge) {}

    @Override
    public boolean isEdge(int source, int dest) {
        return false;
    }

    @Override
    public Edge getEdge(int source, int dest) {
        return null;
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {return null;}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public int addRandomEdgesToGraph (int edgeLimit) throws Exception {
        if(edgeLimit < 0)
            throw new Exception();

        int num = (int) (Math.random() * edgeLimit);
        int source,dest , numAdded = num;

        for(int i = 0 ; i < num ; i++) {
            source = (int) (Math.random() * getNumV());
            dest = (int) (Math.random() * getNumV());
            Edge edge = new Edge(source,dest);


            if((isDirected() && isEdge(source,dest))  || (!isDirected() && isEdge(source,dest) && isEdge(dest,source)))
                --numAdded;
            else
                insert(new Edge(source, dest));
        }
        return numAdded;
    }

    public int[] breadthFirstSearch(int start) {
        Queue<Integer> theQueue = new LinkedList<Integer>();
        int[] bfs = new int[this.getNumV()];
        int index;

        int[] parent = new int[this.getNumV()];
        for (int i = 0; i < this.getNumV(); i++) {
            parent[i] =  -1;
        }

        boolean[] identified = new boolean[this.getNumV()];

        identified[start] = true;
        theQueue.offer(start);

        index = 0;
        while (!theQueue.isEmpty()) {
            int current = theQueue.remove();

            bfs[index++] = current;

            Iterator<Edge> itr = this.edgeIterator(current);
            while (itr.hasNext()) {
                Edge edge = itr.next();
                int neighbor = edge.getDest();

                if (!identified[neighbor]) {

                    identified[neighbor] = true;

                    theQueue.offer(neighbor);

                    parent[neighbor] = current;

                }
            }

        }

        return parent;
    }

    public Graph [] getConnectedComponentUndirectedGraph (){
        Graph[] graphs = null;
        ArrayList<Graph> tempGraphs = new ArrayList<>();
        LinkedList<Integer> v = new LinkedList<>();
        String type = "";

        if(isDirected())
            return null;

        if(this instanceof ListGraph) {
            graphs = new ListGraph[getNumV()];
            type = "List";
        }
        else if(this instanceof MatrixGraph) {
            graphs = new MatrixGraph[getNumV()];
            type = "Matrix";
        }

        for(int k = 0 ; k < getNumV() ; k++){
            v.add(k);
        }

        while(!v.isEmpty()){
            Queue<Edge> temp = new LinkedList<>();

            boolean gone = false;
            int first = v.getFirst();
            int[] bfs = breadthFirstSearch(first);

            for(int j = 0 ; j < bfs.length ; j++) {
                if(bfs[j] != -1) {
                    Edge edge = new Edge(bfs[j],j);
                    temp.add(edge);

                    v.remove((Object)j);

                    gone = true;
                }
            }
            if(gone)
                v.remove((Object)first);

            Graph tmpGraph = (type.equals("List")) ? new ListGraph(getNumV(),false) : new MatrixGraph(getNumV(),false);


            while(!temp.isEmpty()){
                Edge edge = temp.poll();
                tmpGraph.insert(edge);
            }

            tempGraphs.add(tmpGraph);

        }

        graphs = new Graph[tempGraphs.size()];
        for(int m = 0 ; m < tempGraphs.size() ; m++)
            graphs[m] = tempGraphs.get(m);

        return graphs;
    }

    public boolean isBipartiteUndirectedGraph (){

        if(isDirected())
            return false;

        int[] color = new int[getNumV()];
        Queue<Integer> q = new LinkedList<>();
        Iterator<Edge> itr = edgeIterator(0);
        q.add(itr.next().getSource());

        for(int i = 1 ; i < getNumV() ; i++)
            color[i] = -1;

        color[0] = 1;

        while(!q.isEmpty()){
            int s = q.poll();
            for(int d = 0 ; d < getNumV() ; d++){
                if(isEdge(s,d) && color[d] == -1){
                    color[d] = 1 - color[s];
                    q.add(d);
                }
                else if(isEdge(s,d) && color[d] == color[s]){
                    return false;
                }
            }

        }

        return true;
    }


    public void writeGraphToFile (String fileName) throws IOException {
        FileWriter wr = new FileWriter(new File(fileName));
        wr.write(getNumV() + "\n");
        for(int i = 0 ; i < getNumV() ; i++) {
            Iterator itr = edgeIterator(i);
            while (itr.hasNext()) {
                Edge edge = (Edge) itr.next();
                if(edge.getSource() <= edge.getDest())
                    wr.write(edge.getSource() + " " + edge.getDest() + "\n");
            }
        }
        wr.close();
    }

}
