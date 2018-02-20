import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        try {


            Scanner sc = new Scanner(new File("ExampleIGraphInputOrOutputFile.txt"));

            MatrixGraph graph = (MatrixGraph) AbstractGraph.createGraph(sc,true,"Matrix");

            System.out.println("Graph");

            for(int i = 0 ; i < graph.getNumV() ; i++) {
                Iterator itr = graph.edgeIterator(i);
                while (itr.hasNext())
                    System.out.println(itr.next());
            }

            System.out.println("After adding random edges");

            System.out.println(graph.addRandomEdgesToGraph(20) + " edges was added");

            for(int i = 0 ; i < graph.getNumV() ; i++) {
                Iterator itr = graph.edgeIterator(i);
                while (itr.hasNext())
                    System.out.println(itr.next());
            }

            System.out.println("Breadth First Search Test : ");
            int[] bds = graph.breadthFirstSearch(0);

            for(int i = 0 ; i < bds.length ; i++)
                System.out.print(bds[i] + " ");

            System.out.println();

            graph.writeGraphToFile("outputGraph.txt");

           /* ListGraph gp = new ListGraph(11,false);
            gp.insert(new Edge(0,1));
            gp.insert(new Edge(1,2));
            gp.insert(new Edge(2,3));
            gp.insert(new Edge(2,4));
            gp.insert(new Edge(4,5));
            gp.insert(new Edge(6,7));
            gp.insert(new Edge(6,8));
            gp.insert(new Edge(6,9));
            gp.insert(new Edge(9,10));


            Graph[] gpp = gp.getConnectedComponentUndirectedGraph();
            System.out.println("getConnectedComponentUndirectedGraph Test : ");

            for(int i = 0 ; i < gpp.length ; i++) {
                System.out.println("Graph[" + i + "] :");
                for(int k = 0 ; k < gpp[i].getNumV() ; k++) {
                    Iterator itr = gpp[i].edgeIterator(k);
                    while (itr.hasNext())
                        System.out.println(itr.next());
                }
            }
            System.out.println("Bipartite Test : \nisbipartite : " + gp.isBipartiteUndirectedGraph());
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
