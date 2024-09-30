package xyz.someboringnerd.imutils.windows.widgets;

import lombok.Getter;
import lombok.NonNull;
import xyz.someboringnerd.imutils.interfaces.Callback;

public abstract class BaseWidget<T extends BaseWidget<T>> {

    @Getter
    private String name;

    @NonNull
    protected Callback callback;

    public T setCallback(Callback callback) {
        this.callback = callback;
        return (T) this;
    }

    public T setName(String name) {
        this.name = name;
        return (T) this;
    }

    public abstract void render();
}
