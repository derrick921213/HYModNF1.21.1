package huanyu_mod.core.otherAction;

import org.lwjgl.glfw.GLFW;

public class keyAction {
    public static boolean keyIsNormal(int key) {
        return  (key >= 48 && key <= 57) ||
                (key >= 65 && key <= 90) ||
                (key >= 97 && key <= 122) ||
                key == 95;
    }

    public static boolean keyIsFunc(int key) {
        return  key == GLFW.GLFW_KEY_BACKSPACE ||
                key == GLFW.GLFW_KEY_DELETE ||
                key == GLFW.GLFW_KEY_LEFT ||
                key == GLFW.GLFW_KEY_RIGHT;
    }

    public static boolean keyIsESC(int key) {
        return  key == GLFW.GLFW_KEY_ESCAPE ||
                key == GLFW.GLFW_KEY_ENTER;
    }
}
