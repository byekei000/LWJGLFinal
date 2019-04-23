import org.joml.Matrix4f;

public class BasicShader extends Shader {

    private static final String VERTEX_FILE = "./src/basicVertexShader.vs";
    private static final String FRAGMENT_FILE = "./src/basicFragmentShader.fs";

    private int transformationLocation;

    public BasicShader(){
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    public void bindAllAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textCoords");
    }

    @Override
    protected void getAllUniforms() {
        transformationLocation = super.getUniform("transformation");
    }

    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrixUniform(transformationLocation, matrix);
    }
}