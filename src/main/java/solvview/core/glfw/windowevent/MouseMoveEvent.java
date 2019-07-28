package solvview.core.glfw.windowevent;

import solvview.core.glfw.EventHandler;
import solvview.core.glfw.WindowEvent;

public final class MouseMoveEvent implements WindowEvent {

	public final long window;
	public final double xpos, ypos;

	public MouseMoveEvent(long window, double xpos, double ypos) {
		this.window = window;
		this.xpos = xpos;
		this.ypos = ypos;
	}

	@Override
	public void beHandledBy(EventHandler handler) {
		handler.onMouseMoved(xpos, ypos);
	}
}
