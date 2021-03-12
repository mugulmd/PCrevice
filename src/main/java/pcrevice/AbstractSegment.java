package pcrevice;

import processing.core.PVector;
import processing.core.PGraphics;

/**
 * 
 * Abstract class to encapsulate a segment's properties.
 *
 * @see LineSegment
 * @see ParabolaSegment
 *
 * @author Loïc Vital
 *
 */

public abstract class AbstractSegment {

	public PVector start, end;

	/**
	 *
	 * @param _start
	 * @param _end
	 */

	public AbstractSegment(PVector _start, PVector _end) {
		start = _start;
		end = _end;
	}

	/**
	 *
	 * @param t
	 * @return 
	 */

	abstract public PVector vecAt(float t);

	/**
	 *
	 * @param t
	 * @return 
	 */

	abstract public PVector dirAt(float t);

	/**
	 *
	 * @return 
	 */

	abstract public float length();

	/**
	 *
	 * @param canvas
	 */

	abstract public void drawOn(PGraphics canvas);

}
