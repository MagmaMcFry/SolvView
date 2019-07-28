Shader programs are assembled from the following shader files:

- 1_vsh.glsl (GL_VERTEX_SHADER)
- 2_fsh.glsl (GL_FRAGMENT_SHADER)
- object.glsl (GL_FRAGMENT_SHADER)
- solv.glsl or nil.glsl (GL_FRAGMENT_SHADER)

You can override the built-in files with your own files by extracting certain shader files directly next to the runnable JAR,
then editing them as desired. For example you can edit object.glsl to visualize a different object.
