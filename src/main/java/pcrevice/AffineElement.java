package pcrevice;

import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * Class to represent an affine element, a geometric primitive consisting of a position in 2D space and a direction.
 *
 * @author Loïc Vital
 *
 */

public class AffineElement {

	public PVector pos, dir;
	public float angle;

	/**
	 *
	 * @param _pos
	 * @param _angle
	 */

	public AffineElement(PVector _pos, float _angle) {
		pos = _pos;
		angle = _angle;
		dir = PVector.fromAngle(angle);
	}

	/**
	 *
	 * @param _pos
	 * @param _dir
	 */

	public AffineElement(PVector _pos, PVector _dir) {
		pos = _pos;
		dir = _dir; dir.normalize();
		angle = PApplet.atan2(dir.y, dir.x);
	}

	/**
	 *
	 * @param fst
	 * @param snd
	 * @return 
	 */

	public static PVector intersection(AffineElement fst, AffineElement snd) {
		float det = fst.dir.x*snd.dir.y - fst.dir.y*snd.dir.x;
		if(Math.abs(det) < 1e-3) return null;

		float lambda1 = (snd.dir.y*(snd.pos.x-fst.pos.x) - snd.dir.x*(snd.pos.y-fst.pos.y))/det;
		float lambda2 = (fst.dir.y*(snd.pos.x-fst.pos.x) - fst.dir.x*(snd.pos.y-fst.pos.y))/det;
		if(lambda1 <= 0.f || lambda2 >= 0.f) return null;

		return PVector.add(fst.pos, PVector.mult(fst.dir, lambda1));
	}

}
