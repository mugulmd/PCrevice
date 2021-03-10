package pcrevice;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Loïc Vital
 *
 */

public class Edge {

	public int id;
	public Node source, target;
	public AbstractSegment segment;
	public float affineWeight, supportAngle;

	public Edge(int _id, Node _source, Node _target) {
		id = _id;
		source = _source;
		target = _target;
	}

	public void setGeoLine() {
		segment = new LineSegment(source.elt.pos, target.elt.pos);
		affineWeight = 0.f;
		supportAngle = PApplet.PI;
	}

	public void setGeoParabola(PVector ctrl) {
		ParabolaSegment parabola = new ParabolaSegment(source.elt.pos, ctrl, target.elt.pos);
		segment = parabola;
		affineWeight = (float)Math.pow(parabola.supportTriangleArea(), 1./3.);
		supportAngle = parabola.supportAngle();
	}

}
