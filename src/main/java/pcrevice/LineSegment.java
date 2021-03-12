package pcrevice;

import processing.core.PVector;
import processing.core.PGraphics;

/**
 *
 * Class to represent a line segment
 *
 * @see AbstractSegment
 *
 * @author Loïc Vital
 *
 */

public class LineSegment extends AbstractSegment {

	public LineSegment(PVector _start, PVector _end) {
		super(_start, _end);
	}

	@Override
	public PVector vecAt(float t) {
		float u = Math.max(0, Math.min(1, t));
		return PVector.add(start, PVector.mult(PVector.sub(end, start), u));
	}

	@Override
	public PVector dirAt(float t) {
		return PVector.sub(end, start).normalize();
	}

	@Override
	public float length() {
		return PVector.dist(start, end);
	}

	@Override
	public void drawOn(PGraphics canvas) {
		canvas.line(start.x, start.y, end.x, end.y);
	}

}
