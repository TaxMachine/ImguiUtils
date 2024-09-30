package xyz.someboringnerd.imutils.managers;

import imgui.ImGui;
import imgui.flag.ImGuiKey;
import imgui.gl3.ImGuiImplGl3;
import imgui.glfw.ImGuiImplGlfw;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import xyz.someboringnerd.imutils.GlobalVariables;
import xyz.someboringnerd.imutils.interfaces.Callback;

@UtilityClass
public class ImguiManager {
    private final ImGuiImplGlfw implGlfw = new ImGuiImplGlfw();
    private final ImGuiImplGl3 implGl3 = new ImGuiImplGl3();

    private static Callback onRenderFrame;

    @Setter
    private static Callback beforeRenderingFrame, afterRenderingFrame;

    public static void init(long windowHandle, Callback renderAll) {
        onRenderFrame = renderAll;
        ImGui.createContext();

        implGlfw.init(windowHandle, true);
        implGl3.init();
    }

    public static void render() {
        implGlfw.newFrame();
        ImGui.newFrame();

        if(GlobalVariables.disableShiftTabMenu) {
            disableAnnoyingWindows();
        }

        if(beforeRenderingFrame != null)
            beforeRenderingFrame.run();

        onRenderFrame.run();

        ImGui.render();
        implGl3.renderDrawData(ImGui.getDrawData());

        if(afterRenderingFrame != null)
            afterRenderingFrame.run();
    }

    private static void disableAnnoyingWindows() {
        if(ImGui.getIO().getKeyCtrl()
                || ImGui.getIO().getKeysDown(ImGuiKey.Tab)
                || ImGui.getIO().getKeyShift()
                || ImGui.getIO().getKeyAlt()
                || ImGui.getIO().getKeySuper()) {
            ImGui.getIO().setKeyCtrl(false);
            ImGui.getIO().setKeyShift(false);
            ImGui.getIO().setKeyAlt(false);
            ImGui.getIO().setKeySuper(false);
            ImGui.getIO().setKeysDown(ImGuiKey.Tab, false);
        }
    }
}
