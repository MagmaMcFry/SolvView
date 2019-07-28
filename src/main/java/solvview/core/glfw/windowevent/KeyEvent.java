package solvview.core.glfw.windowevent;

import solvview.core.glfw.EventHandler;
import solvview.core.glfw.WindowEvent;
import org.lwjgl.glfw.GLFW;

public final class KeyEvent implements WindowEvent {

	public final long window;
	public final int key, scancode, action, mods;

	public KeyEvent(long window, int key, int scancode, int action, int mods) {
		this.window = window;
		this.key = key;
		this.scancode = scancode;
		this.action = action;
		this.mods = mods;
	}

	@Override
	public void beHandledBy(EventHandler handler) {
		switch (action) {
			case GLFW.GLFW_PRESS:
				handler.onKeyPressed(key, scancode, mods);
				break;
			case GLFW.GLFW_RELEASE:
				handler.onKeyReleased(key, scancode, mods);
				break;
			case GLFW.GLFW_REPEAT:
				handler.onKeyRepeated(key, scancode, mods);
				break;
		}
	}
}
