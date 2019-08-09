#version 330

const float log2 = 0.69314718056;
const float ilog2 = 1/log2;

const float GRID_WIDTH = 0.03;

float distanceFunc(vec3 pos) {
    pos *= ilog2;
    pos.y -= pos.x; // Compensation for crooked metric in SL(2)
    vec3 gridDis = abs(pos - round(pos));
    float medianDis = 0.5 - max(min(gridDis.x, gridDis.y), min(max(gridDis.x, gridDis.y), gridDis.z));
    // The 0.5 is there to compensate for the fact that we just calculated euclidean distance and not
    // intrinsic distance
    return 0.5 * log2 * (max(medianDis, abs(pos.z) - 0.5) - GRID_WIDTH);
}

vec3 colorFunc(vec3 pos) {
    return clamp(vec3(20*abs(pos.z) - 1, 0.2*pos.x + 0.5, 0.2*pos.y + 0.5), 0, 1);
}
