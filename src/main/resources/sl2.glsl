#version 330

vec3 diff2(vec3 pos, vec3 dpos) {
    float ez = exp(pos.z);
    vec3 ddpos = vec3(
    -3 * dpos.x * dpos.z + (2/ez) * dpos.y * dpos.z,
    -ez * dpos.x * dpos.z + dpos.y * dpos.z,
    (2*ez*ez) * dpos.x * dpos.x - (2*ez) * dpos.x * dpos.y
    );
    return ddpos;
}

void stepSL2(inout vec3 pos, inout vec3 dpos, float stepsize) {
    vec3 ddpos = diff2(pos, dpos);
    pos += stepsize * (dpos + 0.5 * stepsize * ddpos);
    dpos += stepsize * ddpos;
}

void stepSL2Midpoint(inout vec3 pos, inout vec3 dpos, float stepsize) {
    vec3 ddpos = diff2(pos, dpos);
    vec3 ddpos2 = diff2(pos + 0.5 * stepsize * dpos, dpos + 0.5 * stepsize * ddpos);
    pos += stepsize * (dpos + 0.5 * stepsize * ddpos);
    dpos += stepsize * ddpos2;
}

void stepFunc(inout vec3 pos, inout vec3 dpos, float stepsize) {
    stepSL2Midpoint(pos, dpos, stepsize);
}
