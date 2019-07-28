package solvview.core.glfw;

import solvview.core.glfw.windowevent.KeyEvent;
import solvview.core.glfw.windowevent.MouseClickEvent;
import solvview.core.glfw.windowevent.MouseMoveEvent;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.util.Vector;

public final class Window {

	private final static Vector<Window> windows = new Vector<>();
	private final long id;
	private final Vector<WindowEvent> eventQueue = new Vector<>();

	public Window(int width, int height, String title) {
		id = GLFW.glfwCreateWindow(width, height, title, MemoryUtil.NULL, MemoryUtil.NULL);
		GLFW.glfwSetKeyCallback(id, (window, key, scancode, action, mods) -> {
			if (key == GLFW.GLFW_KEY_DELETE) {
				GLFW.glfwSetWindowShouldClose(id, true);
			} else {
				eventQueue.add(new KeyEvent(window, key, scancode, action, mods));
			}
		});
		GLFW.glfwSetCursorPosCallback(id, (window, xpos, ypos) -> {
			eventQueue.add(new MouseMoveEvent(window, xpos, ypos));
		});
		GLFW.glfwSetMouseButtonCallback(id, (window, button, action, mods) -> {
			eventQueue.add(new MouseClickEvent(window, button, action, mods));
		});
		GLFW.glfwSetFramebufferSizeCallback(id, (window, w2, h2) -> {
			GL11.glViewport(0,0,w2,h2);
		});
		GLFW.glfwMakeContextCurrent(id);
		GLFW.glfwSwapInterval(1);
		GLFW.glfwShowWindow(id);
		windows.add(this);
	}

	public void pollEvents() {
		GLFW.glfwPollEvents();
	}

	public void processRemainingEvents(EventHandler handler) {
		while(!eventQueue.isEmpty()) {
			processNextEvent(handler);
		}
	}

	public boolean processNextEvent(EventHandler handler) {
		if (eventQueue.isEmpty()) return false;
		eventQueue.remove(0).beHandledBy(handler);
		return true;
	}

	public boolean shouldClose() {
		return GLFW.glfwWindowShouldClose(id);
	}

	public void swapBuffers() {
		GLFW.glfwSwapBuffers(id);
	}

	public void selectForGL() {
		GLFW.glfwMakeContextCurrent(id);
		GL.createCapabilities();
	}

	public int width() {
		int[] buf = new int[1];
		GLFW.glfwGetWindowSize(id,buf,null);
		return buf[0];
	}
	public int height() {
		int[] buf = new int[1];
		GLFW.glfwGetWindowSize(id,null,buf);
		return buf[0];
	}
	public static void destroyAllWindows() {
		for (Window w : windows) {
			Callbacks.glfwFreeCallbacks(w.id);
			GLFW.glfwDestroyWindow(w.id);
		}
	}

	public boolean isKeyPressed(int glfwKey) {
		return GLFW.glfwGetKey(id,glfwKey) == GLFW.GLFW_PRESS;
	}

	public void setCursorGrabbed(boolean yes) {
		GLFW.glfwSetInputMode(id, GLFW.GLFW_CURSOR, yes ? GLFW.GLFW_CURSOR_DISABLED : GLFW.GLFW_CURSOR_NORMAL);
	}

	public boolean isCursorGrabbed() {
		return GLFW.glfwGetInputMode(id, GLFW.GLFW_CURSOR) == GLFW.GLFW_CURSOR_DISABLED;
	}

	public double getCursorX() {
		double[] ans = new double[1];
		GLFW.glfwGetCursorPos(id,ans,null);
		return ans[0];
	}

	public double getCursorY() {
		double[] ans = new double[1];
		GLFW.glfwGetCursorPos(id,null,ans);
		return ans[0];
	}

	public void setTitle(String title) {
		GLFW.glfwSetWindowTitle(id, title);
	}
}
