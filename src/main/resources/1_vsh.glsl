#version 330

layout(location = 0) in vec2 pos;

out vec3 localDir;

uniform vec2 fovs;


void main() {
	localDir = vec3(pos * fovs, 1.0);
	gl_Position = vec4(pos, 0.0, 1.0);
}
