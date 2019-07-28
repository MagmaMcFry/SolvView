package solvview.math;

abstract public class BaseCamera implements Camera {
	protected Vec3 pos;
	protected Vec3 right;
	protected Vec3 up;
	protected Vec3 forwards;

	public BaseCamera() {
		pos = new Vec3(0, 0, 0);
		right = new Vec3(1, 0, 0);
		up = new Vec3(0, 1, 0);
		forwards = new Vec3(0, 0, 1);
	}

	@Override
	public float[] getViewMatrix() {
		return new float[]{
				right.x, right.y, right.z, up.x, up.y, up.z, forwards.x, forwards.y, forwards.z
		};
	}

	@Override
	public void turnRight(float radians) {
		float c = (float) Math.cos(radians);
		float s = (float) Math.sin(radians);
		Vec3 newForwards = Vec3.combine(forwards, c, right, s);
		Vec3 newRight = Vec3.combine(right, c, forwards, -s);
		forwards = newForwards;
		right = newRight;
	}

	@Override
	public void turnUp(float radians) {
		float c = (float) Math.cos(radians);
		float s = (float) Math.sin(radians);
		Vec3 newForwards = Vec3.combine(forwards, c, up, s);
		Vec3 newUp = Vec3.combine(up, c, forwards, -s);
		forwards = newForwards;
		up = newUp;
	}

	@Override
	public void rollRight(float radians) {
		float c = (float) Math.cos(radians);
		float s = (float) Math.sin(radians);
		Vec3 newRight = Vec3.combine(right, c, up, s);
		Vec3 newUp = Vec3.combine(up, c, right, -s);
		right = newRight;
		up = newUp;
	}

	@Override
	public void moveLocalRight(float units) {
		moveLocal(right, units);
	}

	@Override
	public void moveLocalUp(float units) {
		moveLocal(up, units);
	}

	@Override
	public void moveLocalForwards(float units) {
		moveLocal(forwards, units);
	}


	private void moveLocal(Vec3 dir, float units) {
		Vec3 newRight = Vec3.combine(right, 1, parallelTransportDifferential(pos, dir, right), units);
		Vec3 newUp = Vec3.combine(up, 1, parallelTransportDifferential(pos, dir, up), units);
		Vec3 newForwards = Vec3.combine(forwards, 1, parallelTransportDifferential(pos, dir, forwards), units);

		Vec3 newDir = Vec3.combine(dir, 1, parallelTransportDifferential(pos, dir, dir), units);

		pos = pos.plus(Vec3.combine(dir, 0.5f * units, newDir, 0.5f * units));

		right = newRight;
		up = newUp;
		forwards = newForwards;
	}

	abstract protected Vec3 parallelTransportDifferential(Vec3 position, Vec3 transportDirection, Vec3 transportedVector);

	@Override
	public float getPosX() {
		return pos.x;
	}

	@Override
	public float getPosY() {
		return pos.y;
	}

	@Override
	public float getPosZ() {
		return pos.z;
	}
}
