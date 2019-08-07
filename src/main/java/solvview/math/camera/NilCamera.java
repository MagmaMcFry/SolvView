package solvview.math.camera;

import solvview.math.Vec3;

public class NilCamera extends BaseCamera {

	public NilCamera() {
		super();
	}

	@Override
	protected Vec3 parallelTransportDifferential(Vec3 position, Vec3 transportDirection, Vec3 transportedVector) {
		float z = position.z;
		float G13_1 = 0.5f * z;
		float G23_1 = -0.5f;
		float G13_2 = 0.5f * (z * z - 1);
		float G23_2 = -0.5f * z;

		float G11_3 = -z;
		float G12_3 = 0.5f;

		return new Vec3(
				-G13_1 * (transportDirection.x * transportedVector.z + transportDirection.z * transportedVector.x)
						- G23_1 * (transportDirection.y * transportedVector.z + transportDirection.z * transportedVector.y),
				-G13_2 * (transportDirection.x * transportedVector.z + transportDirection.z * transportedVector.x)
						- G23_2 * (transportDirection.y * transportedVector.z + transportDirection.z * transportedVector.y),
				-G11_3 * transportDirection.x * transportedVector.x
						- G12_3 * (transportDirection.x * transportedVector.y + transportDirection.y * transportedVector.x)
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
		return new Vec3(
				vec.x,
				vec.y - z * vec.x,
				vec.z);
	}
}
