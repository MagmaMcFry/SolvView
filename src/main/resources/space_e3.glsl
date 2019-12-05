#version 330

void stepFunc(inout vec3 pos, inout vec3 dpos, float stepsize) {
    pos += stepsize * dpos;
}
