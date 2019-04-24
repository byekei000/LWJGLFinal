public class Main {
	private static final int WIDTH = 800, HEIGHT = 600, FPS = 60;
	private static Window window = new Window(WIDTH, HEIGHT, FPS, "LWJGL");
	private static BasicShader shader = new BasicShader();
	private static Renderer renderer = new Renderer(window, shader);
	
    public static void main(String[] args) {
    	window.create();
    	window.setBackgroundColor(1.0f, 0.0f, 0.0f);
    	shader.create();
    	renderer.create();
        
        TexturedModel model = new TexturedModel(new float[] {
				-0.5f,0.5f,-0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,0.5f,-0.5f,

				-0.5f,0.5f,0.5f,
				-0.5f,-0.5f,0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,0.5f,0.5f,

				0.5f,0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,0.5f,0.5f,

				-0.5f,0.5f,0.5f,
				-0.5f,-0.5f,0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,0.5f,0.5f,

				0.5f,0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f,
				0.5f,0.5f,0.5f,

				-0.5f,0.5f,-0.5f,
				-0.5f,-0.5f,-0.5f,
				-0.5f,-0.5f,0.5f,
				-0.5f,0.5f,0.5f,

				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,

				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f,
        }, new float[] {
        		0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0,
				0,0,
				0,1,
				1,1,
				1,0
        }, new int[] {
        		0,1,3,
				3,1,2,
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22
        }, "beautiful.png");
        
        ModelEntity entity = new ModelEntity(model, new Vector3f(0, 0, 2), new Vector3f(0, 0, 0), new Vector3f(1, 1, 1));

        while (!window.closed()) {
        	if (window.isUpdating()) {
        		window.update();
        		entity.addRotation(2,2,0);
	            shader.bind();
	            shader.useMatrices();
	            renderer.renderModelEntity(entity);
	            shader.unbind();
	            window.swapBuffers();
        	}
        }
 
        model.remove();
        shader.remove();
        window.stop();
    }
}