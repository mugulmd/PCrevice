package pcrevice;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 *
 * Class to represent a skeleton, a tree hierarchy of edges in a network.
 * 
 * @see Network
 * @see Node
 * @see Edge
 *
 * @author Loïc Vital
 *
 */

public class Skeleton {

	public Node rootNode;
	public Edge rootEdge;
	private Map<Integer, List<Integer>> children;

	/**
	 *
	 */

	public Skeleton() {
		rootNode = null;
		rootEdge = null;
		children = new HashMap<Integer, List<Integer>>();
	}

	/**
	 *
	 * @param edgeId
	 * @param childrenIds
	 */

	public void addChildrenIds(int edgeId, List<Integer> childrenIds) {
		children.put(edgeId, childrenIds);
	}

	/**
	 *
	 * @param edgeId
	 * @return
	 */

	public List<Integer> getChildrenIds(int edgeId) {
		return children.get(edgeId);
	}

}
