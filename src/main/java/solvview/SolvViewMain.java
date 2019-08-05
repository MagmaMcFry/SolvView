package solvview;

import org.lwjgl.opengl.GLDebugMessageCallback;
import org.lwjgl.system.Configuration;
import solvview.core.glfw.Window;
import solvview.core.start.Start;
import solvview.math.SpaceForm;
import solvview.state.SpaceFormViewState;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL43.*;

public class SolvViewMain {
	public static void main(String[] args) {
		// Set environment variable "SolvViewDebugLWJGL" to enable LWJGL debug messages
		Configuration.DEBUG.set(System.getenv("SolvViewDebugLWJGL") != null);
		Start.start(() -> {
			glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
			glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
			glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
			glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);

			Window w = new Window(1024, 768, "SolvView");
			w.selectForGL();

			// Set environment variable "SolvViewDebugOpenGL" to enable OpenGL debug messages (requires at least OpenGL 4.3+)
			if (System.getenv("SolvViewDebugOpenGL") != null) {
				System.out.println("Debugging features enabled");
				glEnable(GL_DEBUG_OUTPUT);
				glEnable(GL_DEBUG_OUTPUT_SYNCHRONOUS);
				glDebugMessageCallback(new GLDebugMessageCallback() {
					@Override
					public void invoke(int source, int type, int id, int severity, int length, long message, long userParam) {
						if (severity != GL_DEBUG_SEVERITY_NOTIFICATION) {
							System.err.println("GLError: " + getMessage(length, message));
							System.err.println("Source: " + source);
							System.err.println("Type: " + type);
							System.err.println("ID: " + id);
							System.err.println("Severity: " + severity);
							throw new RuntimeException("OpenGL error: " + getMessage(length, message)
									+ ", source = " + source
									+ ", type = " + type
									+ ", id = " + id
									+ ", severity = " + severity);
						}
					}
				}, 0);
			}

			System.out.println();
			System.out.println("  Working directory: " + System.getProperty("user.dir"));
			System.out.println("  Modified shaders placed in this directory will override the predefined shaders.");

			SpaceForm spaceForm = SpaceForm.SOLV;
			SpaceFormViewState spaceFormViewState;
			do {
				spaceFormViewState = new SpaceFormViewState(w, spaceForm);
				spaceFormViewState.run();
				spaceForm = spaceForm.next();
			} while (spaceFormViewState.shouldContinue());
		});
	}
}
