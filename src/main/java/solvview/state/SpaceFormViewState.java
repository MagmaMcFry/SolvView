package solvview.state;

import solvview.core.glfw.Window;
import solvview.core.state.SimpleState;
import org.lwjgl.glfw.GLFW;
import solvview.core.gl.ShaderBuilder;
import solvview.math.Camera;
import solvview.math.SpaceForm;
import solvview.res.Resources;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class SpaceFormViewState extends SimpleState {

	private static final float TURN_SPEED = 0.02f;
	private static final float MOVE_SPEED = 0.02f;

	private int vao;
	private int program;
	private int fovsLocation, startLocation, lookLocation, stepCountLocation, maxStepSizeLocation;

	private int stepCount = 256;
	private float maxStepSize = 0.125f;
	private final Camera cam;

	private final SpaceForm spaceForm;
	private boolean oneMoreSpace = false;

	public SpaceFormViewState(Window w, SpaceForm spaceForm) {
		super(w);
		this.spaceForm = spaceForm;
		cam = spaceForm.getCamera();
	}

	@Override
	public void init() {
		ShaderBuilder sb = new ShaderBuilder(spaceForm.getName() + " Shader");
		sb.addShader(GL_VERTEX_SHADER, Resources.VERTEX_SHADER_SOURCE.get());
		sb.addShader(GL_FRAGMENT_SHADER, spaceForm.getShaderGeometrySource());
		sb.addShader(GL_FRAGMENT_SHADER, spaceForm.getShaderObjectSource());
		sb.addShader(GL_FRAGMENT_SHADER, Resources.FRAGMENT_SHADER_SOURCE.get());
		program = sb.build();

		fovsLocation = glGetUniformLocation(program, "fovs");
		startLocation = glGetUniformLocation(program, "start");
		lookLocation = glGetUniformLocation(program, "look");
		stepCountLocation = glGetUniformLocation(program, "stepCount");
		maxStepSizeLocation = glGetUniformLocation(program, "maxStepSize");

		float[] vertexPositions = new float[]{
				-1, -1,
				-1, 1,
				1, 1,
				-1, -1,
				1, 1,
				1, -1,
		};

		vao = glGenVertexArrays();
		glBindVertexArray(vao);

		int vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, vertexPositions, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
	}

	@Override
	public void loop() {
		if (window.isKeyPressed(GLFW.GLFW_KEY_RIGHT)) {
			cam.turnRight(TURN_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT)) {
			cam.turnRight(-TURN_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_UP)) {
			cam.turnUp(TURN_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_DOWN)) {
			cam.turnUp(-TURN_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_E)) {
			cam.rollRight(TURN_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_Q)) {
			cam.rollRight(-TURN_SPEED);
		}

		if (window.isKeyPressed(GLFW.GLFW_KEY_D)) {
			cam.moveLocalRight(MOVE_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_A)) {
			cam.moveLocalRight(-MOVE_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_W)) {
			cam.moveLocalForwards(MOVE_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_S)) {
			cam.moveLocalForwards(-MOVE_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_SPACE)) {
			cam.moveLocalUp(MOVE_SPEED);
		}
		if (window.isKeyPressed(GLFW.GLFW_KEY_LEFT_SHIFT)) {
			cam.moveLocalUp(-MOVE_SPEED);
		}

		cam.repair();

		window.setTitle("SolvView by MagmaMcFry - Space: " + spaceForm.getName() + " (toggle with M). Ray marching step count: " + stepCount + " (change with I/K). Max step size: " + maxStepSize + " (change with O/L). Controls: Up/Down/Left/Right/Q/E to rotate, W/A/S/D/Shift/Space to move");
	}

	@Override
	public void render() {
		glViewport(0, 0, window.width(), window.height());
		glClearColor(0, 0, 0, 0);
		glClear(GL_COLOR_BUFFER_BIT);
		glUseProgram(program);
		glUniform2f(fovsLocation, window.width() / (float) window.height(), 1);
		glUniform3f(startLocation, cam.getPosX(), cam.getPosY(), cam.getPosZ());
		glUniformMatrix3fv(lookLocation, false, cam.getViewMatrix());
		glUniform1i(stepCountLocation, stepCount);
		glUniform1f(maxStepSizeLocation, maxStepSize);
		glBindVertexArray(vao);
		glDrawArrays(GL_TRIANGLES, 0, 6);
	}

	@Override
	public void finish() {
	}

	@Override
	public void onKeyPressed(int key, int scancode, int mods) {
		switch (key) {
			case GLFW.GLFW_KEY_I:
				stepCount *= 2;
				break;
			case GLFW.GLFW_KEY_K:
				stepCount /= 2;
				break;
			case GLFW.GLFW_KEY_O:
				maxStepSize *= 2;
				break;
			case GLFW.GLFW_KEY_L:
				maxStepSize /= 2;
				break;
			case GLFW.GLFW_KEY_R:
				cam.repair();
				break;
			case GLFW.GLFW_KEY_M:
				oneMoreSpace = true;
				stop();
				break;
		}
	}

	public boolean shouldContinue() {
		return oneMoreSpace;
	}
}
