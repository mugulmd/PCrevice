package pcrevice;

import java.util.List;
import java.util.LinkedList;

/**
 *
 * Class to encode a node in the network. Its geometric representation is an affine element.
 *
 * @see Network
 * @see Edge
 * @see AffineElement
 *
 * @author Loïc Vital
 *
 */

public class Node {

	public int id;
	public AffineElement elt;
	public List<Edge> edges;

	/**
	 *
	 * @param _id
	 * @param _elt
	 */

	public Node(int _id, AffineElement _elt) {
		id = _id;
		elt = _elt;
		edges = new LinkedList<Edge>();
	}

}
