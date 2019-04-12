import org.lwjgl.glfw.GLFW;

public class Main{
    public static final int WIDTH = 800, HEIGHT = 600, FPS = 60;
    public static Window window = new Window(WIDTH, HEIGHT, FPS, "LWJGL");
    public static void main(String[] args){
        window.create();
        window.setBackgroundColor(1.0f, 0.0f, 0.0f);

        while(!window.closed()){
            if(window.isUpdating()){
                window.update();

                window.swapBuffers();
            }
        }
        window.stop();
    }
}