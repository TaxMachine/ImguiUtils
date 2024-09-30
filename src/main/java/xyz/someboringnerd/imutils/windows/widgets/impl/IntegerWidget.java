package xyz.someboringnerd.imutils.windows.widgets.impl;

import imgui.ImGui;
import imgui.type.ImInt;
import lombok.Getter;
import xyz.someboringnerd.imutils.windows.widgets.BaseWidget;

public class IntegerWidget extends BaseWidget<IntegerWidget> {

    @Getter
    private int value;

    private int min = 0, max = 1;

    private boolean useInput = false;

    @Override
    public void render() {
        int temp;
        int[] val = {value};

        // sometime, people accidentally switch min and max.
        // that's why we switch them around if needed.
        // in theory, it would make the slider go from right to left instead of the left to right, but it's more convenient to do that
        if(min > max) {
            int mms = min;
            min = max;
            max = mms;
        }

        if(useInput) {
            ImGui.sliderInt(getName(), val, min, max);
            temp = ((int[]) val)[0];
        }
        else {
            ImInt temp2 = new ImInt(getValue());
            ImGui.inputInt(getName(), temp2);
            temp = temp2.get();
        }
        if (temp != getValue()) {
            value = temp;
            if(value > max) value = max;
            if(value < min) value = min;
            callback.run(value);
        }
    }

    public IntegerWidget setMin(int min) {
        this.min = min;
        return this;
    }

    public IntegerWidget setMax(int max) {
        this.max = max;
        return this;
    }

    public IntegerWidget useInput() {
        useInput = true;
        return this;
    }

}
