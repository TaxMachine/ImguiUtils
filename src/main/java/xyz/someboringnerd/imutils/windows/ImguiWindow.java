package xyz.someboringnerd.imutils.windows;

import imgui.ImGui;
import imgui.type.ImBoolean;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public abstract class ImguiWindow {

    protected final ImBoolean active = new ImBoolean(true);

    @NonNull private String name;
    @NonNull private int imguiProperty;

    @Setter(AccessLevel.PROTECTED)
    @NonNull private boolean closable;

    public boolean isActive() {
        return active.get();
    }

    protected abstract void internalRender();

    public void render() {
        if(active.get()) return;

        if(closable) {
            if(ImGui.begin(name, active, imguiProperty)) {
                internalRender();
            }
        } else {
            if(ImGui.begin(name, imguiProperty)) {
                internalRender();
            }
        }
        ImGui.end();

        if(!active.get())
            onToggleOff();
    }

    protected void onToggleOff() {}
}
