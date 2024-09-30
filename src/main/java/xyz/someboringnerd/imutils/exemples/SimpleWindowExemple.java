package xyz.someboringnerd.imutils.exemples;

import imgui.ImGui;
import xyz.someboringnerd.imutils.windows.ImguiWindow;
import xyz.someboringnerd.imutils.windows.widgets.impl.ButtonWidget;
import xyz.someboringnerd.imutils.windows.widgets.impl.IntegerWidget;

public class SimpleWindowExemple extends ImguiWindow {

    private final ButtonWidget button = new ButtonWidget()
        .setCallback((args) -> System.out.println("Hello World !"))
        .setName("Test Button");

    private final IntegerWidget intSlider = new IntegerWidget()
        .setName("Integer Slider")
        .setMin(0)
        .setMax(100)
        .setCallback((args -> System.out.println("intSlider's new value is " + (int)args[0])));

    private final IntegerWidget intInput = new IntegerWidget()
        .setName("Integer intInput")
        .setMin(0)
        .setMax(100)
        .setCallback((args -> System.out.println("intInput's new value is " + (int)args[0])))
        .useInput();

    public SimpleWindowExemple() {
        super("Simple Window Exemple", 0, true);
    }

    @Override
    protected void internalRender() {
        button.render();
        ImGui.separator();
        intSlider.render();
        ImGui.separator();
        intSlider.render();
        intInput.render();
    }
}
