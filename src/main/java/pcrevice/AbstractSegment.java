package pcrevice;

import processing.core.PVector;
import processing.core.PGraphics;

/**
 *
 * @author Loïc Vital
 *
 */

public abstract class AbstractSegment {

	public PVector start, end;

	public AbstractSegment(PVector _start, PVector _end) {
		start = _start;
		end = _end;
	}

	abstract public PVector vecAt(float t);

	abstract public PVector dirAt(float t);

	abstract public float length();

	abstract public void drawOn(PGraphics canvas);

}
