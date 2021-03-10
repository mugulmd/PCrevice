package pcrevice;

import java.util.List;
import java.util.LinkedList;

/**
 *
 * @author Loïc Vital
 *
 */

public class Node {

	public int id;
	public AffineElement elt;
	public List<Edge> edges;

	public Node(int _id, AffineElement _elt) {
		id = _id;
		elt = _elt;
		edges = new LinkedList<Edge>();
	}

}
