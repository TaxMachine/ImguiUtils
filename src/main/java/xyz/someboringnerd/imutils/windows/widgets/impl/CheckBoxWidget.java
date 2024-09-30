package xyz.someboringnerd.imutils.windows.widgets.impl;

import imgui.ImGui;
import lombok.Getter;
import xyz.someboringnerd.imutils.windows.widgets.BaseWidget;

public class CheckBoxWidget extends BaseWidget<CheckBoxWidget> {

    @Getter
    private boolean state;

    public CheckBoxWidget defaultEnabled(boolean state) {
        this.state = state;
        return this;
    }

    @Override
    public void render() {
        if(ImGui.checkbox(getName(), state)) {
            state = !state;
            callback.run();
        }
    }
}
