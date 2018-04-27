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
      Graph g = new Graph ("G");
      g.createRandomSimpleGraph (2000, 1999);
      //System.out.println (g);
      Graph testG = g.complement();
      //System.out.println(testG.toString());
/*
      Graph sampleGraph = new Graph ("Sample");
      sampleGraph.info = 4;
      Vertex v1 = new Vertex("v1");
      Vertex v2 = new Vertex("v2");
      Vertex v3 = new Vertex("v3");
      Vertex v4 = new Vertex("v4");
      sampleGraph.first = v1;
      v1.next = v2;
      v2.next = v3;
      v3.next = v4;
      sampleGraph.createArc("av1_v2", v1, v2);
      sampleGraph.createArc("av2_v1", v2, v1);
      sampleGraph.createArc("av2_v3", v2, v3);
      sampleGraph.createArc("av3_v2", v3, v2);
      sampleGraph.createArc("av3_v4", v3, v4);
      sampleGraph.createArc("av4_v3", v4, v3);
      sampleGraph.createAdjMatrix();
      System.out.println(sampleGraph.toString());
      sampleGraph.complement();
      System.out.println(sampleGraph.complement().toString());
      */
      // TODO!!! Your experiments here
   }

   // TODO!!! add javadoc relevant to your problem
   class Vertex {

      private String id;
      private Vertex next;
      private Arc first;
      private int info = 0;
      // You can add more fields, if needed
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

      // TODO!!! Your Vertex methods here!

      public ArrayList<Arc> getArcsOfVertex() {
         ArrayList<Arc> arcsOfVertex = new ArrayList<>();
         Arc currentArc = this.first;
         while (currentArc != null){
            arcsOfVertex.add(currentArc);
            currentArc = currentArc.next;
         }
         return arcsOfVertex;
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
      // You can add more fields, if needed


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

      // TODO!!! Your Arc methods here!
   }


   class Graph {

      private String id;
      private Vertex first;
      private int info = 0;
      // You can add more fields, if needed

      Graph (String s, Vertex v) {
         id = s;
         first = v;
      }

      Graph (String s) {
         this (s, null);
      }

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

      public Vertex createVertex (String vid) {
         Vertex res = new Vertex (vid);
         res.next = first;
         first = res;
         return res;
      }

      public Arc createArc (String aid, Vertex from, Vertex to) {
         Arc res = new Arc (aid);
         res.next = from.first;
         from.first = res;
         res.target = to;
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

      // TODO!!! Your Graph methods here! Probably your solution belongs here.

      public Graph complement(){
         Graph complementGraph = this.clone();
         complementGraph.eachVertex(this);
         return complementGraph;
      }

      public void eachVertex(Graph original){
         ArrayList<Vertex> listOfOriginalVertexes = original.getAllVertexes();
         ArrayList<Vertex> listOfClonedVertexes = this.getAllVertexes();
         for (int i = 0; i < listOfClonedVertexes.size(); i++) {
            Vertex currentV = listOfClonedVertexes.get(i);
            ArrayList<Integer> toConnect = new ArrayList<>();
            for (int j = 0; j < original.info; j++) {
               toConnect.add(j);
            }
            for (Integer arc: listOfOriginalVertexes.get(i).arcsTo) {
               toConnect.remove(arc);
            }
            toConnect.remove((Integer) listOfOriginalVertexes.get(i).info); //remove self
            currentV.complementArcs = toConnect;

            for (int targetId: currentV.complementArcs) {
               Vertex targetV = listOfClonedVertexes.get(targetId);
               createArc("a" + currentV.id + "_" + targetV.id, currentV, targetV);
            }
         }
      }

      public ArrayList<Vertex> getAllVertexes(){
         ArrayList<Vertex> listOfVertexes = new ArrayList<>();
         Vertex currentVertex = this.first;
         while (currentVertex != null) {
            listOfVertexes.add(currentVertex);
            currentVertex = currentVertex.next;
         }
         return listOfVertexes;
      }

      @Override
      public Graph clone(){
         Graph clonedGraph = new Graph("Complement_" + this.id);
         int vertexesCount = getAllVertexes().size();
         if (vertexesCount > 0) {
            clonedGraph.first = new Vertex("v1");
            Vertex next = clonedGraph.first;
            for (int i = 1; i < vertexesCount; i++) {
               next.next = new Vertex("v" + Integer.toString(i + 1));
               next = next.next;
            }
         }
         return clonedGraph;
      }
   }

} 

