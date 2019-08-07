package solvview.math.camera;

public interface Camera {
	void turnRight(float radians);

	void turnUp(float radians);

	void rollRight(float radians);

	void moveLocalRight(float radians);

	void moveLocalUp(float radians);

	void moveLocalForwards(float radians);

	void repair();

	float getPosX();

	float getPosY();

	float getPosZ();

	float[] getViewMatrix();

}
