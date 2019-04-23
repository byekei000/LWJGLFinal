import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

public class Main {
    public static final int WIDTH = 800, HEIGHT = 600, FPS = 60;
    public static Window window = new Window(WIDTH, HEIGHT, FPS, "LWJGL");
    public static BasicShader shader = new BasicShader();
    public static Renderer renderer = new Renderer(shader);

    public static void main(String[] args) {
        window.create();
        window.setBackgroundColor(1.0f, 0.0f, 0.0f);
        shader.create();

        TexturedModel model = new TexturedModel(new float[]{
                -0.5f,  0.5f, 0f,  // top left 0
                 0.5f,  0.5f, 0f,  // top right 1
                 0.5f, -0.5f, 0f,  // bottom right 2
                -0.5f, -0.5f, 0f,  // bottom left 3
        }, new float[]{
                0, 0,
                1, 0,
                1, 1,
                0, 1
        }, new int[]{
                0, 1, 2,
                2, 3, 0
        }, "beautiful.png");

        ModelEntity entity = new ModelEntity(model, new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(1,1, 1));

        while (!window.closed()) {
            if (window.isUpdating()) {
                window.update();
                if(window.isKeyDown(GLFW.GLFW_KEY_A))entity.addRotationX(-0.01f);
                if(window.isKeyDown(GLFW.GLFW_KEY_D))entity.addRotationX(0.01f);
                if(window.isKeyDown(GLFW.GLFW_KEY_W))entity.addRotationY(0.01f);
                if(window.isKeyDown(GLFW.GLFW_KEY_S))entity.addRotationY(-0.01f);
                shader.bind();
                renderer.renderModelEntitiy(entity);
                window.swapBuffers();
            }
        }
        model.remove();
        shader.remove();
        window.stop();
    }
}