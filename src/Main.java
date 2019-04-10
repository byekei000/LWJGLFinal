import org.lwjgl.glfw.GLFW;

public class Main{
    public static void main(String[] args){
        Window window = new Window(800, 600, "LWJGL");
        window.create();

        while(!window.closed()){
            window.update();
            if(window.isKeyPressed(GLFW.GLFW_KEY_A)) System.out.println("Pressed A");
            if(window.isKeyReleased(GLFW.GLFW_KEY_A)) System.out.println("Released A");
            window.swapBuffers();
        }
    }
}