package solvview.core.state;

import solvview.core.glfw.EventHandler;
import solvview.core.glfw.Window;

abstract public class SimpleState implements EventHandler {

	protected final Window window;
	private boolean shouldStop = false;
	private static boolean shouldQuit = false;

	public SimpleState(Window window) {
		this.window = window;
	}

	public void run() {
		shouldStop = false;
		window.selectForGL();
		init();
		LOOP: while (!shouldStop()) {
			window.pollEvents();
			while (window.processNextEvent(this)) {
				if (shouldStop()) break LOOP;
			}
			if (shouldStop()) break LOOP;
			loop();
			if (shouldStop()) break LOOP;
			render();
			window.swapBuffers();
		}
		finish();
	}

	protected void stop() {
		shouldStop = true;
	}

	protected void quit() {
		shouldQuit = true;
	}

	private boolean shouldStop() {
		return shouldStop || shouldQuit || window.shouldClose();
	}

	abstract public void init();
	abstract public void loop();
	abstract public void render();
	abstract public void finish();
}
