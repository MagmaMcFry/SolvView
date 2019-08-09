package solvview.math.camera;

import solvview.math.Vec3;

public class SL2Camera extends BaseCamera {

	public SL2Camera() {
		super();
	}

	@Override
	protected Vec3 parallelTransportDifferential(Vec3 position, Vec3 transportDirection, Vec3 transportedVector) {
		float z = position.z;
		float G13_1 = 1.5f;
		float G23_1 = -0.5f * exp(-z);

		float G13_2 = exp(z);
		float G23_2 = -0.5f;

		float G11_3 = -2 * exp(2 * z);
		float G12_3 = 0.5f * exp(z);

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

		float z = pos.z;

		forwards = normalize(forwards, z);

		right = right.plus(forwards.times(-sprod(forwards, right, z)));
		right = normalize(right, z);

		up = up.plus(forwards.times(-sprod(forwards, up, z)));
		up = up.plus(right.times(-sprod(right, up, z)));
		up = normalize(up, z);

	}

	private static float sprod(Vec3 vec1, Vec3 vec2, float z) {
		return 2 * exp(2 * z) * vec1.x * vec2.x
				- exp(z) * vec1.x * vec2.y
				- exp(z) * vec1.y * vec2.x
				+ vec1.y * vec2.y
				+ vec1.z * vec2.z;
	}

	private static float length(Vec3 vec, float z) {
		return (float) Math.sqrt(sprod(vec, vec, z));
	}

	private static Vec3 normalize(Vec3 vec, float z) {
		return vec.times(1 / length(vec, z));
	}

	private static float exp(float z) {
		return (float) Math.exp(z);
	}
}
