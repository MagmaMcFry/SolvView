package solvview.core.gl;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderBuilder {

	private final String name;
	private List<Integer> shaders = new ArrayList<>();
	private int program = 0;

	public ShaderBuilder(String name) {
		this.name = name;
	}

	public void addShader(int shaderType, String shaderSource) {
		int shader = glCreateShader(shaderType);
		glShaderSource(shader, shaderSource);
		glCompileShader(shader);
		if (glGetShaderi(shader, GL_COMPILE_STATUS) != GL_TRUE) {
			throw new RuntimeException("Error compiling shader " + (1 + shaders.size()) + " for program " + name + ": " + glGetShaderInfoLog(shader));
		}
		shaders.add(shader);
	}

	public int build() {
		if (program > 0) return program;
		program = glCreateProgram();
		for (int shader : shaders) {
			glAttachShader(program, shader);
		}
		glLinkProgram(program);
		if (glGetProgrami(program, GL_LINK_STATUS) != GL_TRUE) {
			throw new RuntimeException("Error linking shader program " + name + ": " + glGetProgramInfoLog(program));
		}
		for (int shader : shaders) {
			glDetachShader(program, shader);
			glDeleteShader(shader);
		}
		return program;
	}
}
