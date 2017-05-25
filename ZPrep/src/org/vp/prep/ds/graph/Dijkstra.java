package org.vp.prep.ds.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Dijkstra {
	
	Map<String, List<Vertex>> graph;
	List<Edge> edges;
	List<Vertex> vertices;
	List<Edge> path = new ArrayList<>();
	
	Comparator<Vertex> vertexComparator = new Comparator<Dijkstra.Vertex>() {
		@Override
		public int compare(Vertex o1, Vertex o2) {
			return o1.distance - o2.distance;
		}
	};
	
	static class Vertex{
		String label;
		String parent = null;
		int distance = Integer.MAX_VALUE;						//min distance to the vertex from source
		public Vertex(String label){
			this.label = label;
		}
	}
	
	static class Edge{
		Vertex src;
		Vertex dst;
		int weight;
		
		public Edge(Vertex src, Vertex dst, int weight){
			this.src = src;
			this.dst = dst;
			this.weight = weight;
		}
	}
	
//    Input graph	
//	  /-- 4-->B--3-->C--7-\
//	A/        |            D
//	 |       \|/8          |
//	 \---5--->E----7----->/
	
	public void initGraph() {
		vertices = new ArrayList<>();
		Vertex a = new Vertex("A");
		a.distance = 0;					//Source vertex
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		vertices.add(a);
		vertices.add(b);
		vertices.add(c);
		vertices.add(d);
		vertices.add(e);
		
		edges = new ArrayList<>();
		edges.add(new Edge(a, b, 4));
		edges.add(new Edge(b,c,3));
		edges.add(new Edge(c, d, 7));
		edges.add(new Edge(b, e, 8));
		edges.add(new Edge(a, e, 5));
		edges.add(new Edge(e, d, 7));
		
		graph = new HashMap<>();
		List<Vertex> alist = new ArrayList<>();
		alist.add(b);
		alist.add(e);
		graph.put("A", alist);

		List<Vertex> blist = new ArrayList<>();
		blist.add(c);
		blist.add(e);
		graph.put("B", blist);

		List<Vertex> clist = new ArrayList<>();
		clist.add(d);
		graph.put("C", clist);

		List<Vertex> elist = new ArrayList<>();
		elist.add(d);
		graph.put("E", elist);
		
	}

	public void evaluatePaths(){
		initGraph();
		
		Queue <Vertex> minQ = new PriorityQueue<Vertex>(10, vertexComparator );
		minQ.addAll(vertices);
		
		while(!minQ.isEmpty()){
			Vertex u = minQ.poll();
			if(graph.get(u.label)==null)
				continue;
			for(Vertex v : graph.get(u.label)){   // For each vertex adjacent to current one
				Edge edge = getEdge(u,v);
				if(v.distance > u.distance + edge.weight){
					v.distance = u.distance + edge.weight;
					v.parent = u.label;
					path.add(edge);
				}
			}
		}
	}
	
	private Edge getEdge(Vertex src, Vertex dst){
		Edge e = null;
		for(Edge edge : edges){
			if(edge.src.label.equals(src.label) && edge.dst.label.equals(dst.label)){
				return edge;
			}
		}
		return e;
	}
	
	public static void main(String[] args) {
		Dijkstra djk = new Dijkstra();
		djk.evaluatePaths();
		for(Vertex v : djk.vertices){
			System.out.println(v.label+", distance:"+ v.distance+" parent:"+v.parent);
		}
		System.out.println("\nPath:");
		for(Edge e : djk.path){
			System.out.println(e.src.label+" - "+e.dst.label+" - "+e.weight);
		}
	}

}
