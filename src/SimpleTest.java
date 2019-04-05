import org.lwjgl.*;
import org.lwjgl.opengl.*;

import java.awt.*;

public class SimpleTest {

    public static void main(String[] args) throws LWJGLException {
        Display.setDisplayMode(new DisplayMode(800, 600));
        Display.create();

        while (!Display.isCloseRequested()) {
            // render here

            Display.update();
            Display.sync(60);
        }

        Display.destroy();
    }
}