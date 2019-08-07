package solvview.math.camera;

import solvview.math.Vec3;

public class E3Camera extends BaseCamera {

	public E3Camera() {
		super();
	}

	@Override
	protected Vec3 parallelTransportDifferential(Vec3 position, Vec3 transportDirection, Vec3 transportedVector) {

		return new Vec3(0, 0, 0);
	}

	@Override
	public void repair() {

		forwards.normalize();

		right.orthogonalize(forwards);
		right.normalize();

		up.orthogonalize(forwards);
		up.orthogonalize(right);
		up.normalize();
	}
}
