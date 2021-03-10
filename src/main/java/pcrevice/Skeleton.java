package pcrevice;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Loïc Vital
 *
 */

public class Skeleton {

	public Node rootNode;
	public Edge rootEdge;
	private Map<Integer, List<Integer>> children;

	public Skeleton() {
		rootNode = null;
		rootEdge = null;
		children = new HashMap<Integer, List<Integer>>();
	}

	public void addChildrenIds(int edgeId, List<Integer> childrenIds) {
		children.put(edgeId, childrenIds);
	}

	public List<Integer> getChildrenIds(int edgeId) {
		return children.get(edgeId);
	}

}
