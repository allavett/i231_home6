/*
 * Graph problems are individual for each student and junit-testing is therefore impossible. You must still submit your
 * program text here, also you find here the pointer structure description (classes Graph, Vertex and Arc) to present
 * graphs. To receive a task you have to visit the lab (problems are in different books and you must choose one, it is
 * not possible to make all problems available electronically). Do not forget that you also have to write a report on
 * your solution and upload it as a pdf-file in Moodle.
 *
 * http://enos.itcollege.ee/~jpoial/algoritmid/kodutqq6.html
 * Koostada meetod etteantud lihtgraafi täiendgraafi leidmiseks.
 *
 * http://enos.itcollege.ee/~jpoial/algoritmid/graafid.html
 * https://et.wikipedia.org/wiki/Graafi_t%C3%A4iend
 * http://www.math.olympiaadid.ut.ee/arhiiv/oppemat/eesti/graafid.pdf
 * https://en.wikipedia.org/wiki/Complement_graph
 */

import java.util.*;

/** Container class to different classes, that makes the whole
 * set of classes one class formally.
 */
public class GraphTask {

   /** Main method. */
   public static void main (String[] args) {
      GraphTask a = new GraphTask();
      a.run();
      //throw new RuntimeException ("Nothing implemented yet!"); // delete this
   }

   /** Actual main method to run examples and everything. */
   public void run() {
/*
      long stime, ftime, diff;
      stime = System.nanoTime();
      Graph g = new Graph ("G");
      g.createRandomSimpleGraph (2500, 3000);
      ftime = System.nanoTime();
      diff = ftime - stime;
      System.out.printf("%34s%11d%n", "Random graph creation: time (ms): ", diff / 1000000);
      //System.out.println (g);
      System.out.println ("Edges: " + g.getNumberOfEdges());

      stime = System.nanoTime();
      Graph testG = g.complement();
      ftime = System.nanoTime();
      diff = ftime - stime;
      System.out.printf("\n%34s%11d%n", "Complement graph creation: time (ms): ", diff / 1000000);
      //System.out.println(testG);
      System.out.println ("Edges: " + testG.getNumberOfEdges());

      stime = System.nanoTime();
      Graph g2 = new Graph ("G2");
      g2.createRandomSimpleGraph (2500, 3120750);
      ftime = System.nanoTime();
      diff = ftime - stime;
      System.out.printf("%34s%11d%n", "Random graph creation: time (ms): ", diff / 1000000);
      //System.out.println (g2);
      System.out.println ("Edges: " + g2.getNumberOfEdges());

      stime = System.nanoTime();
      Graph testG2 = g2.complement();
      ftime = System.nanoTime();
      diff = ftime - stime;
      System.out.printf("\n%34s%11d%n", "Complement graph creation: time (ms): ", diff / 1000000);
      //System.out.println(testG2);
      System.out.println ("Edges: " + testG2.getNumberOfEdges());
*/
      Graph G1 = new Graph ("G1");
      G1.info = 4;
      Vertex v1 = new Vertex("v1");
      Vertex v2 = new Vertex("v2");
      Vertex v3 = new Vertex("v3");
      Vertex v4 = new Vertex("v4");
      G1.first = v1;
      v1.next = v2;
      v2.next = v3;
      v3.next = v4;
      G1.createArc("av1_v2", v1, v2);
      G1.createArc("av2_v1", v2, v1);
      G1.createArc("av2_v3", v2, v3);
      G1.createArc("av3_v2", v3, v2);
      G1.createArc("av3_v4", v3, v4);
      G1.createArc("av4_v3", v4, v3);
      G1.createAdjMatrix();
      System.out.println(G1.toString());
      Graph complementSG = G1.complement();
      System.out.println(complementSG);

      Graph G2 = new Graph ("G2");
      G2.info = 4;
      Vertex v21 = new Vertex("v1");
      Vertex v22 = new Vertex("v2");
      Vertex v23 = new Vertex("v3");
      Vertex v24 = new Vertex("v4");
      G2.first = v21;
      v21.next = v22;
      v22.next = v23;
      v23.next = v24;
      G2.createArc("av1_v3", v21, v23);
      G2.createArc("av1_v4", v21, v24);
      G2.createArc("av3_v2", v23, v21);
      G2.createArc("av4_v1", v24, v21);
      G2.createArc("av4_v3", v24, v23);
      G2.createAdjMatrix();
      System.out.println(G2.toString());
      Graph complementSG2 = G2.complement();
      System.out.println(complementSG2);

      Graph G3 = new Graph ("G3");
      G3.info = 4;
      Vertex v31 = new Vertex("v1");
      Vertex v32 = new Vertex("v2");
      Vertex v33 = new Vertex("v3");
      Vertex v34 = new Vertex("v4");
      G3.first = v31;
      v31.next = v32;
      v32.next = v33;
      v33.next = v34;
      G3.createArc("av1_v2", v31, v32);
      G3.createArc("av1_v3", v31, v33);
      G3.createArc("av1_v4", v31, v34);
      G3.createArc("av2_v1", v32, v31);
      G3.createArc("av2_v3", v32, v33);
      G3.createArc("av2_v4", v32, v34);
      G3.createArc("av3_v1", v33, v31);
      G3.createArc("av3_v2", v33, v32);
      G3.createArc("av3_v4", v33, v34);
      G3.createArc("av4_v1", v34, v31);
      G3.createArc("av4_v2", v34, v32);
      G3.createArc("av4_v3", v34, v33);
      G3.createAdjMatrix();
      System.out.println(G3.toString());
      Graph complementSG3 = G3.complement();
      System.out.println(complementSG3);

   }

   class Vertex {

      private String id;
      private Vertex next;
      private Arc first;
      private int info = 0;
      ArrayList<Integer> arcsTo = new ArrayList<>();
      ArrayList<Integer> complementArcs = new ArrayList<>();

      Vertex (String s, Vertex v, Arc e) {
         id = s;
         next = v;
         first = e;
      }

      Vertex (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }
   }


   /** Arc represents one arrow in the graph. Two-directional edges are
    * represented by two Arc objects (for both directions).
    */
   class Arc {

      private String id;
      private Vertex target;
      private Arc next;
      private int info = 0;

      Arc (String s, Vertex v, Arc a) {
         id = s;
         target = v;
         next = a;
      }

      Arc (String s) {
         this (s, null, null);
      }

      @Override
      public String toString() {
         return id;
      }
   }


   class Graph {

      private String id;
      private Vertex first;
      private int info = 0;

      /**
       * Retrieves the edges count
       * @return number of edges
       */
      private int getNumberOfEdges() {
         return totalArcsCount/2;
      }

      private int totalArcsCount = 0;

      Graph (String s, Vertex v) {
         id = s;
         first = v;
      }

      Graph (String s) {
         this (s, null);
      }

      /**
       * Creates string representation of graph
       * @return graph as string
       */
      @Override
      public String toString() {
         String nl = System.getProperty ("line.separator");
         StringBuffer sb = new StringBuffer (nl);
         sb.append (id);
         sb.append (nl);
         Vertex v = first;
         while (v != null) {
            sb.append (v.toString());
            sb.append (" -->");
            Arc a = v.first;
            while (a != null) {
               sb.append (" ");
               sb.append (a.toString());
               sb.append (" (");
               sb.append (v.toString());
               sb.append ("->");
               sb.append (a.target.toString());
               sb.append (")");
               a = a.next;
            }
            sb.append (nl);
            v = v.next;
         }
         return sb.toString();
      }

      /**
       * Creates new vertex
       * @param vid vertex identifier
       * @return created vertex
       */
      public Vertex createVertex (String vid) {
         Vertex res = new Vertex (vid);
         res.next = first;
         first = res;
         return res;
      }

      /**
       * Creates new arc between vertexes
       * @param aid arc identifier
       * @param from vertex
       * @param to vertex
       * @return formed arc
       */
      public Arc createArc (String aid, Vertex from, Vertex to) {
         Arc res = new Arc (aid);
         res.next = from.first;
         from.first = res;
         res.target = to;
         this.totalArcsCount++;
         from.arcsTo.add(Integer.parseInt(to.id.substring(1)) - 1);
         return res;
      }

      /**
       * Create a connected undirected random tree with n vertices.
       * Each new vertex is connected to some random existing vertex.
       * @param n number of vertices added to this graph
       */
      public void createRandomTree (int n) {
         if (n <= 0)
            return;
         Vertex[] varray = new Vertex [n];
         for (int i = 0; i < n; i++) {
            varray [i] = createVertex ("v" + String.valueOf(n-i));
            if (i > 0) {
               int vnr = (int)(Math.random()*i);
               createArc ("a" + varray [vnr].toString() + "_"
                       + varray [i].toString(), varray [vnr], varray [i]);
               createArc ("a" + varray [i].toString() + "_"
                       + varray [vnr].toString(), varray [i], varray [vnr]);
            } else {}
         }
      }

      /**
       * Create an adjacency matrix of this graph.
       * Side effect: corrupts info fields in the graph
       * @return adjacency matrix
       */
      public int[][] createAdjMatrix() {
         info = 0;
         Vertex v = first;
         while (v != null) {
            v.info = info++;
            v = v.next;
         }
         int[][] res = new int [info][info];
         v = first;
         while (v != null) {
            int i = v.info;
            Arc a = v.first;
            while (a != null) {
               int j = a.target.info;
               v.arcsTo.add(j);
               res [i][j]++;
               a = a.next;
            }
            v = v.next;
         }
         return res;
      }

      /**
       * Create a connected simple (undirected, no loops, no multiple
       * arcs) random graph with n vertices and m edges.
       * @param n number of vertices
       * @param m number of edges
       */
      public void createRandomSimpleGraph (int n, int m) {
         if (n <= 0)
            return;
         if (n > 2500)
            throw new IllegalArgumentException ("Too many vertices: " + n);
         if (m < n-1 || m > n*(n-1)/2)
            throw new IllegalArgumentException
                    ("Impossible number of edges: " + m);
         first = null;
         createRandomTree (n);       // n-1 edges created here
         Vertex[] vert = new Vertex [n];
         Vertex v = first;
         int c = 0;
         while (v != null) {
            vert[c++] = v;
            v = v.next;
         }
         int[][] connected = createAdjMatrix();
         int edgeCount = m - n + 1;  // remaining edges
         while (edgeCount > 0) {
            int i = (int)(Math.random()*n);  // random source
            int j = (int)(Math.random()*n);  // random target
            if (i==j)
               continue;  // no loops
            if (connected [i][j] != 0 || connected [j][i] != 0)
               continue;  // no multiple edges
            Vertex vi = vert [i];
            Vertex vj = vert [j];
            createArc ("a" + vi.toString() + "_" + vj.toString(), vi, vj);
            connected [i][j] = 1;
            createArc ("a" + vj.toString() + "_" + vi.toString(), vj, vi);
            connected [j][i] = 1;
            edgeCount--;  // a new edge happily created
         }
      }

      /**
       * Creates complement graph of base graph
       * @return complement graph
       */
      public Graph complement(){
         Graph complementGraph = this.createNew();
         complementGraph.eachVertex(this);
         return complementGraph;
      }

      /**
       * Creates incomplete graph based on main graph
       * @return cloned graph
       */
      public Graph createNew(){
         Graph newGraph = new Graph("Complement_" + this.id);
         newGraph.info = this.info;
         int vertexesCount = newGraph.info;
         if (vertexesCount > 0) {
            newGraph.first = new Vertex("v1");
            Vertex next = newGraph.first;
            for (int i = 1; i < vertexesCount; i++) {
               next.next = new Vertex("v" + Integer.toString(i + 1));
               next.next.info = i;
               next = next.next;
            }
         }
         return newGraph;
      }

      /**
       * Creates new arcs for complement graph
       * @param base (input) graph
       */
      private void eachVertex(Graph base){
         ArrayList<Vertex> listOfNewVertexes = this.getAllVertexes();
         Vertex baseV = base.first;
         Vertex complementV = this.first;
         while (baseV != null){
            ArrayList<Integer> toConnect = new ArrayList<>();
            for (int j = 0; j < base.info; j++) {
               toConnect.add(j);
            }
            for (Integer arc: baseV.arcsTo) {
               toConnect.remove(arc);
            }
            toConnect.remove((Integer) baseV.info); //remove self
            complementV.complementArcs = toConnect;
            for (int i = complementV.complementArcs.size() - 1; i >= 0; i--) {
               int targetVId = complementV.complementArcs.get(i);
               Vertex targetV = listOfNewVertexes.get(targetVId);
               createArc("a" + complementV.id + "_" + targetV.id, complementV, targetV);
            }
            baseV = baseV.next;
            complementV = complementV.next;
         }
      }

      /**
       * Creates a new list of vertexes of base graph
       * @return list of vertexes
       */
      private ArrayList<Vertex> getAllVertexes(){
         ArrayList<Vertex> listOfVertexes = new ArrayList<>();
         Vertex currentVertex = this.first;
         while (currentVertex != null) {
            listOfVertexes.add(currentVertex);
            currentVertex = currentVertex.next;
         }
         return listOfVertexes;
      }
   }
} 

