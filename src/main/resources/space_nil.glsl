#version 330

vec3 diff2(vec3 pos, vec3 dpos) {
    vec3 ddpos = vec3(
        -pos.z * dpos.x * dpos.z + dpos.y * dpos.z,
        (1 - pos.z*pos.z) * dpos.x * dpos.z + pos.z * dpos.y * dpos.z,
        pos.z * dpos.x * dpos.x - dpos.x * dpos.y
    );
    return ddpos;
}

void stepNil(inout vec3 pos, inout vec3 dpos, float stepsize) {
    vec3 ddpos = diff2(pos, dpos);
    pos += stepsize * (dpos + 0.5 * stepsize * ddpos);
    dpos += stepsize * ddpos;
}

void stepNilMidpoint(inout vec3 pos, inout vec3 dpos, float stepsize) {
    vec3 ddpos = diff2(pos, dpos);
    vec3 ddpos2 = diff2(pos + 0.5 * stepsize * dpos, dpos + 0.5 * stepsize * ddpos);
    pos += stepsize * (dpos + 0.5 * stepsize * ddpos);
    dpos += stepsize * ddpos2;
}

void stepFunc(inout vec3 pos, inout vec3 dpos, float stepsize) {
    stepNilMidpoint(pos, dpos, stepsize);
}
