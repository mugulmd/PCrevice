package pcrevice;

import processing.core.PVector;
import processing.core.PGraphics;

/**
 *
 * @author Loïc Vital
 *
 */

public class ParabolaSegment extends AbstractSegment {

	public PVector ctrl;

	public ParabolaSegment(PVector _start, PVector _ctrl, PVector _end) {
		super(_start, _end);
		ctrl = _ctrl;
	}

	@Override
	public PVector vecAt(float t) {
		float u = Math.max(0, Math.min(1, t));
		PVector v = PVector.mult(start, (1-u)*(1-u));
		v.add(PVector.mult(ctrl, 2.f*u*(1-u)));
		v.add(PVector.mult(end, u*u));
		return v;
	}

	@Override
	public PVector dirAt(float t) {
		float u = Math.max(0, Math.min(1, t));
		PVector d = PVector.mult(PVector.sub(ctrl, start), (1-u));
		d.add(PVector.mult(PVector.sub(end, ctrl), u));
		d.normalize();
		return d;
	}

	/**
	 *
	 * @see https://members.loria.fr/samuel.hornus/quadratic-arc-length.html
	 */

	@Override
	public float length() {
		PVector b = PVector.sub(ctrl, start);
		PVector f = PVector.sub(end, ctrl);
		PVector a = PVector.sub(f, b);

		float l = (float)Math.log(a.mag()*f.mag() + PVector.dot(a, f)) - (float)Math.log(a.mag()*b.mag() + PVector.dot(a, b));
		l *= (b.magSq()/a.mag()) - (PVector.dot(a, b)*PVector.dot(a, b)/(a.mag()*a.magSq()));
		l += (f.mag()*PVector.dot(a, f) - b.mag()*PVector.dot(a, b))/a.magSq();

		return l;
	}

	@Override
	public void drawOn(PGraphics canvas) {
		canvas.bezier(start.x, start.y, ctrl.x, ctrl.y, ctrl.x, ctrl.y, end.x, end.y);
	}

	public float supportTriangleArea() {
		float a = PVector.dist(start, ctrl);
		float b = PVector.dist(ctrl, end);
		float c = PVector.dist(end, start);
		float s = 0.5f*(a+b+c);
		return (float)Math.sqrt(s*(s-a)*(s-b)*(s-c));
	}

	public float supportAngle() {
		return Math.abs(PVector.angleBetween(PVector.sub(start, ctrl), PVector.sub(end, ctrl)));
	}

}
