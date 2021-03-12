package pcrevice;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * Class to encode an edge in the network. Its geometric representation is a segment.
 *
 * @see Network
 * @see Node
 * @see AbstractSegment
 *
 * @author Loïc Vital
 *
 */

public class Edge {

	public int id;
	public Node source, target;
	public AbstractSegment segment;

	/**
	 *
	 * @param _id
	 * @param _source
	 * @param _target
	 */

	public Edge(int _id, Node _source, Node _target) {
		id = _id;
		source = _source;
		target = _target;
	}

	/**
	 *
	 */

	public void setGeoLine() {
		segment = new LineSegment(source.elt.pos, target.elt.pos);
	}

	/**
	 *
	 * @param ctrl
	 */

	public void setGeoParabola(PVector ctrl) {
		segment = new ParabolaSegment(source.elt.pos, ctrl, target.elt.pos);
	}

}
