package xyz.someboringnerd.imutils.windows.widgets.impl;

import imgui.ImGui;
import imgui.type.ImInt;
import lombok.Getter;
import xyz.someboringnerd.imutils.windows.widgets.BaseWidget;

public class StringDropdownWidget extends BaseWidget<StringDropdownWidget> {
    private String[] options;

    @Getter
    private int index = 0;

    public StringDropdownWidget setContent(String[] content) {
        options = content;
        return this;
    }

    public String getValue() {
        return options[index];
    }

    @Override
    public void render() {
        ImInt tmp = new ImInt(index);
        ImGui.combo(getName(), tmp, options, options.length);

        if (tmp.get() != index) {
            index = tmp.get();
            callback.run();
        }
    }
}
