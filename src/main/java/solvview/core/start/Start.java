package solvview.core.start;

import solvview.core.glfw.Window;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;

public class Start {

	public static void start(Runnable r) {
		try {
			GLFWErrorCallback.createPrint(System.err).set();
			if (!GLFW.glfwInit()) {
				throw new IllegalStateException("<core> Could not glfwInit");
			}
			r.run();
		} finally {
			Window.destroyAllWindows();
			GLFW.glfwTerminate();
			try {
				GLFW.glfwSetErrorCallback(null).free();
			} catch (NullPointerException e) {
				System.err.println("<core> Could not free GLFW error callback");
			}

		}
	}
}
