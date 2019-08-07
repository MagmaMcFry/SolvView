package solvview.math.camera;

import solvview.math.Vec3;

public class H3Camera extends BaseCamera {

	public H3Camera() {
		super();
	}

	@Override
	protected Vec3 parallelTransportDifferential(Vec3 position, Vec3 transportDirection, Vec3 transportedVector) {

		float G13_1 = 1;
		float G23_2 = 1;

		float G11_3 = -exp(2 * position.z);
		float G22_3 = -exp(2 * position.z);

		return new Vec3(
				-G13_1 * (transportDirection.x * transportedVector.z + transportDirection.z * transportedVector.x),
				-G23_2 * (transportDirection.y * transportedVector.z + transportDirection.z * transportedVector.y),
				-G11_3 * transportDirection.x * transportedVector.x - G22_3 * transportDirection.y * transportedVector.y
		);
	}

	@Override
	public void repair() {

		float dz = pos.z;
		Vec3 regularRight = shiftByZ(right, dz);
		Vec3 regularUp = shiftByZ(up, dz);
		Vec3 regularForwards = shiftByZ(forwards, dz);

		regularForwards.normalize();

		regularRight.orthogonalize(regularForwards);
		regularRight.normalize();

		regularUp.orthogonalize(regularForwards);
		regularUp.orthogonalize(regularRight);
		regularUp.normalize();

		right = shiftByZ(regularRight, -dz);
		up = shiftByZ(regularUp, -dz);
		forwards = shiftByZ(regularForwards, -dz);
	}

	private static Vec3 shiftByZ(Vec3 vec, float z) {
		return new Vec3(vec.x * exp(z), vec.y * exp(z), vec.z);
	}

	private static float exp(float z) {
		return (float) Math.exp(z);
	}
}
