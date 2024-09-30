package xyz.someboringnerd.imutils.windows.widgets.impl;

import imgui.ImGui;
import xyz.someboringnerd.imutils.windows.widgets.BaseWidget;

public class ButtonWidget extends BaseWidget<ButtonWidget> {

    @Override
    public void render() {
        if(ImGui.button(getName())) {
            callback.run();
        }
    }
}
