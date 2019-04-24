

public class BasicShader extends Shader {
     
    private static final String VERTEX_FILE = "src/basicVertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/basicFragmentShader.glsl";
    
    private int tvpMatrixLocation;
    
    private Matrix4f transformationMatrix = new Matrix4f().identity(), projectionMatrix = new Matrix4f().identity();
 
    public BasicShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textCoords");
    }

	@Override
	protected void getAllUniforms() {
		tvpMatrixLocation = super.getUniform("tvpMatrix");
	}
	
	public void useMatrices() {
		super.loadMatrixUniform(tvpMatrixLocation, projectionMatrix.mul(transformationMatrix));
	}

    public void loadTransformationMatrix(Matrix4f matrix) {
        transformationMatrix = matrix;
    }

    public void loadProjectionMatrix(Matrix4f matrix) {
        projectionMatrix = matrix;
    }
}