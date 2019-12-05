package solvview.res;

public class Resources {

	public final static StringResource VERTEX_SHADER_SOURCE = new StringResource("1_vsh.glsl");
	public final static StringResource FRAGMENT_SHADER_SOURCE = new StringResource("2_fsh.glsl");

	public final static StringResource SOLV_SOURCE = new StringResource("space_solv.glsl");
	public final static StringResource[] OBJECT_SOLV_SOURCES = {
			new StringResource("object_solv.glsl"),
			new StringResource("object_solv_2.glsl")
	};

	public final static StringResource NIL_SOURCE = new StringResource("space_nil.glsl");
	public final static StringResource[] OBJECT_NIL_SOURCES = {
			new StringResource("object_nil.glsl"),
			new StringResource("object_nil_2.glsl")
	};

	public static final StringResource SL2_SOURCE = new StringResource("space_sl2.glsl");
	public final static StringResource[] OBJECT_SL2_SOURCES = {
			new StringResource("object_sl2.glsl")
	};

	public static final StringResource H3_SOURCE = new StringResource("space_h3.glsl");
	public final static StringResource[] OBJECT_H3_SOURCES = {
			new StringResource("object_h3.glsl"),
			new StringResource("object_h3_2.glsl")
	};

	public static final StringResource E3_SOURCE = new StringResource("space_e3.glsl");
	public final static StringResource[] OBJECT_E3_SOURCES = {
			new StringResource("object_e3.glsl")
	};
}
