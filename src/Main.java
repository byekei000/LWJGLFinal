import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.glfwInit;
import static org.lwjgl.glfw.GLFW.glfwWindowHint;
import static org.lwjgl.opengl.GL11.GL_TRUE;

public class Main implements Runnable {

    private int width = 1280;
    private int height = 720;
    private Thread thread;
    private boolean running = false;
    private long window;

    public void start() {
        running = false;
        thread = new Thread(this, "Game");
        thread.start();
    }

    private void init() {
        if(glfwInit() != GL_TRUE){

        }
        glfwWindowHint();
    }

    public void run() {
        init();
        while (running) {
            update();
            render();
        }
    }

    private void update() {

    }

    private void render() {

    }

    public static void main(String[] args) {
        new Main().start();
    }
}