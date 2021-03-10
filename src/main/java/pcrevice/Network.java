package pcrevice;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/**
 *
 * @author Loïc Vital
 *
 */

public class Network {

	private Map<Integer, Node> nodes;
	private Map<Integer, Edge> edges;

	public Network() {
		nodes = new HashMap<Integer, Node>();
		edges = new HashMap<Integer, Edge>();
	}

	public void addNode(Node n) {
		nodes.put(n.id, n);
	}

	public Node getNode(int nodeId) {
		return nodes.get(nodeId);
	}

	public Collection<Node> getNodes() {
		return nodes.values();
	}

	public void addEdge(Edge e) {
		edges.put(e.id, e);
		nodes.get(e.source.id).edges.add(e);
	}

	public Edge getEdge(int edgeId) {
		return edges.get(edgeId);
	}

	public Collection<Edge> getEdges() {
		return edges.values();
	}

}
