package solvview.math;

import java.util.Locale;

public class Vec3 {
	public float x;
	public float y;
	public float z;

	public Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Vec3 combine(Vec3 v1, float factor1, Vec3 v2, float factor2) {
		return new Vec3(
				factor1 * v1.x + factor2 * v2.x,
				factor1 * v1.y + factor2 * v2.y,
				factor1 * v1.z + factor2 * v2.z
		);
	}

	public Vec3 times(float s) {
		return new Vec3(x * s, y * s, z * s);
	}

	public Vec3 plus(Vec3 other) {
		return new Vec3(x + other.x, y + other.y, z + other.z);
	}

	@Override
	public String toString() {
		return String.format(Locale.ROOT, "[%2.5f, %2.5f, %2.5f]", x, y, z);
	}

	public void normalize() {
		float l = length();
		x /= l;
		y /= l;
		z /= l;
	}

	public void orthogonalize(Vec3 other) {
		float d = dot(other);
		x -= d * other.x;
		y -= d * other.y;
		z -= d * other.z;
	}

	private float dot(Vec3 other) {
		return x * other.x + y * other.y + z * other.z;
	}

	public float length() {
		return (float) Math.sqrt(x * x + y * y + z * z);
	}
}
