import org.lwjgl.glfw.GLFW;

public class Main{
    public static final int WIDTH = 800, HEIGHT = 600, FPS = 60;
    public static Window window = new Window(WIDTH, HEIGHT, FPS, "LWJGL");
    public static Renderer renderer = new Renderer();
    public static void main(String[] args){
        window.create();
        window.setBackgroundColor(0.0f, 1.0f, 0.0f);

        Model model = new Model(new float[]{
                -0.5f, 0.5f,  0.0f,  // top left 0
                0.5f,  0.5f,  0.0f,  // top right 1
                -0.5f, -0.5f, 0.0f,  // bottom left 2
                0.5f,  -0.5f, 0.0f,  // bottom right 3
        }, new int[] {
                0,1,2,
                2,3,1
        });
        model.create();

        while(!window.closed()){
            if(window.isUpdating()){
                window.update();
                renderer.renderModel(model);
                window.swapBuffers();
            }
        }
        window.stop();
        model.remove();
    }
}