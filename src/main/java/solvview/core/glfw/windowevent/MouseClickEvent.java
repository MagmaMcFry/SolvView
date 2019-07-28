package solvview.core.glfw.windowevent;

import solvview.core.glfw.EventHandler;
import solvview.core.glfw.WindowEvent;
import org.lwjgl.glfw.GLFW;

public final class MouseClickEvent implements WindowEvent {

	public final long window;
	public final int button, action, mods;

	public MouseClickEvent(long window, int button, int action, int mods) {
		this.window = window;
		this.button = button;
		this.action = action;
		this.mods = mods;
	}

	@Override
	public void beHandledBy(EventHandler handler) {
		switch (action) {
			case GLFW.GLFW_PRESS:
				handler.onMouseButtonPressed(button, mods);
				break;
			case GLFW.GLFW_RELEASE:
				handler.onMouseButtonReleased(button, mods);
				break;
		}
	}
}
