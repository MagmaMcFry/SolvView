package solvview.math;

import solvview.math.camera.Camera;
import solvview.math.camera.E3Camera;
import solvview.math.camera.H3Camera;
import solvview.math.camera.NilCamera;
import solvview.math.camera.SL2Camera;
import solvview.math.camera.SolvCamera;
import solvview.res.Resources;
import solvview.res.StringResource;

import java.util.function.Supplier;

public enum SpaceForm {
	SOLV("Solv", Resources.SOLV_SOURCE, Resources.OBJECT_SOLV_SOURCE, SolvCamera::new),
	NIL("Nil", Resources.NIL_SOURCE, Resources.OBJECT_NIL_SOURCE, NilCamera::new),
	SL2("SL(2,R)", Resources.SL2_SOURCE, Resources.OBJECT_SL2_SOURCE, SL2Camera::new),
	H3("H^3", Resources.H3_SOURCE, Resources.OBJECT_H3_SOURCE, H3Camera::new),
	E3("E^3", Resources.E3_SOURCE, Resources.OBJECT_E3_SOURCE, E3Camera::new);


	private final String name;
	private final StringResource shaderGeometrySource;
	private final StringResource shaderObjectSource;
	private final Supplier<Camera> cameraSupplier;

	SpaceForm(String name, StringResource shaderGeometrySource, StringResource shaderObjectSource, Supplier<Camera> cameraSupplier) {
		this.name = name;
		this.shaderGeometrySource = shaderGeometrySource;
		this.shaderObjectSource = shaderObjectSource;
		this.cameraSupplier = cameraSupplier;
	}

	public String getName() {
		return name;
	}

	public String getShaderGeometrySource() {
		return shaderGeometrySource.get();
	}

	public String getShaderObjectSource() {
		return shaderObjectSource.get();
	}

	public Camera getCamera() {
		return cameraSupplier.get();
	}

	public SpaceForm next() {
		SpaceForm[] forms = values();
		return forms[(ordinal() + 1) % forms.length];
	}
}
