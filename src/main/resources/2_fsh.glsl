#version 330

in vec3 localDir;

out vec3 fragColor;

uniform mat3 look;
uniform vec3 start;
uniform int stepCount;
uniform float maxStepSize;

const float STEP_FACTOR = 0.5;

/* Length function (calculates length of the vector `dir` at position `pos`) */
float lengthFunc(vec3 pos, vec3 dir);

/* Step function (moves geodesically forwards from `pos` in direction `dpos`) */
void stepFunc(inout vec3 pos, inout vec3 dpos, float stepsize);

/* Distance function (returns approximate distance between `pos` and the object to render) */
float distanceFunc(vec3 pos);

/* Color function (returns color of the object at `pos`) */
vec3 colorFunc(vec3 pos);

void main() {
    vec3 pos = start;
    vec3 dpos = look * (localDir / length(localDir));
    //vec3 dpos = (dir / lengthFunc(pos, dir));
    int i = 0;
    float distRan = 0;
    for (int i = 0; i < stepCount; ++i) {
        float stepsize = min(distanceFunc(pos)*STEP_FACTOR, maxStepSize);
        stepFunc(pos, dpos, stepsize);
        distRan += stepsize;
        ++i;
    }
    fragColor = colorFunc(pos) * clamp(exp(-0.2*distRan), 0, 1);
}
