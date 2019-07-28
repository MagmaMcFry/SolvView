#version 330

void stepEucl(inout vec3 pos, inout vec3 dpos, float stepsize) {
    pos += stepsize*dpos;
}

vec3 diff2(vec3 pos, vec3 dpos) {
    vec3 ddpos = vec3(
        -2 * dpos.x * dpos.z,
        2 * dpos.y * dpos.z,
        exp(2 * pos.z) * dpos.x * dpos.x - exp(-2 * pos.z) * dpos.y * dpos.y
    );
    return ddpos;
}

void stepSolv(inout vec3 pos, inout vec3 dpos, float stepsize) {
    vec3 ddpos = diff2(pos, dpos);
    pos += stepsize * (dpos + 0.5 * stepsize * ddpos);
    dpos += stepsize * ddpos;
}

void stepSolvMidpoint(inout vec3 pos, inout vec3 dpos, float stepsize) {
    vec3 ddpos = diff2(pos, dpos);
    vec3 ddpos2 = diff2(pos + 0.5 * stepsize * dpos, dpos + 0.5 * stepsize * ddpos);
    pos += stepsize * (dpos + 0.5 * stepsize * ddpos);
    dpos += stepsize * ddpos2;
}

void stepFunc(inout vec3 pos, inout vec3 dpos, float stepsize) {
    stepSolvMidpoint(pos, dpos, stepsize);
}

float lengthFunc(vec3 pos, vec3 dir) {
    return length(dir * vec3(exp(pos.z), exp(-pos.z), 1));
}
