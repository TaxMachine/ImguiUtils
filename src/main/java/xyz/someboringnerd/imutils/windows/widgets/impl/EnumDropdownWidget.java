package xyz.someboringnerd.imutils.windows.widgets.impl;

import imgui.ImGui;
import imgui.type.ImInt;
import lombok.Getter;
import xyz.someboringnerd.imutils.windows.widgets.BaseWidget;

import java.util.Arrays;

public class EnumDropdownWidget extends BaseWidget<EnumDropdownWidget> {
    private String[] options;

    private Enum defaultValue;

    @Getter
    private int index = 0;

    public EnumDropdownWidget setEnum(Enum defaultValue) {
        options = Arrays.stream(defaultValue.getDeclaringClass().getEnumConstants()).map(Object::toString).toArray(String[]::new);
        this.defaultValue = defaultValue;
        return this;
    }

    public Enum getValue() {
        return (Enum) defaultValue.getDeclaringClass().getEnumConstants()[index];
    }

    @Override
    public void render() {
        ImInt tmp = new ImInt(index);
        ImGui.combo(getName(), tmp, options, options.length);

        if (tmp.get() != index) {
            index = tmp.get();
            callback.run(getValue());
        }
    }
}
