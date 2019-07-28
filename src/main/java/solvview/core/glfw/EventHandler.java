package solvview.core.glfw;

public interface EventHandler {
	default void onMouseButtonPressed(int button, int mods) {
	}

	default void onMouseButtonReleased(int button, int mods) {
	}

	default void onMouseMoved(double xpos, double ypos) {
	}

	default void onKeyPressed(int key, int scancode, int mods) {
	}

	default void onKeyReleased(int key, int scancode, int mods) {
	}

	default void onKeyRepeated(int key, int scancode, int mods) {
	}
}
