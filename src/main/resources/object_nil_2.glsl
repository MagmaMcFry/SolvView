#version 330

const float GRID_WIDTH = 0.03;

vec3 shiftZ(vec3 pos, float amount) {
    return vec3(pos.x, pos.y - pos.x * amount, pos.z - amount);
}

float distanceFunc(vec3 pos) {
    vec3 posForWalls = shiftZ(pos, floor(pos.z));
    float vertGridDistance = max(
    abs(posForWalls.x-round(posForWalls.x)),
    abs(posForWalls.y-round(posForWalls.y))
    );
    vec3 posForFloor = shiftZ(pos, round(pos.z));
    float horizGridDistance = max(abs(posForFloor.z), min(
    abs(posForFloor.x - round(posForFloor.x)),
    abs(posForFloor.y - round(posForFloor.y))
    ));
    float totalGridDistance = min(vertGridDistance, horizGridDistance);
    return 0.5 * totalGridDistance - GRID_WIDTH;
}

vec3 colorFunc(vec3 pos) {
    return clamp(vec3(20*abs(pos.z) - 1, 0.2*pos.x + 0.5, 0.2*pos.y + 0.5), 0, 1);
}
