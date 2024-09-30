package xyz.someboringnerd.imutils.exemples;

import imgui.ImGui;
import xyz.someboringnerd.imutils.windows.ImguiMenu;
import xyz.someboringnerd.imutils.windows.ImguiWindow;
import xyz.someboringnerd.imutils.windows.widgets.impl.TabBarWidget;

public class TabbedWindowExemple extends ImguiWindow {

    private final TabBarWidget windowTabWidget = new TabBarWidget()
        .addChilds(
            new ImguiMenu()
                .setTabItem(true)
                .setName("Tab 1")
                .setCallback((args) -> ImGui.text("Hello tab 1 !")),
            new ImguiMenu()
                .setTabItem(true)
                .setName("Tab 2")
                .setCallback((args) -> ImGui.text("Hello tab 2 !")),
            new ImguiMenu()
                .setTabItem(true)
                .setName("Tab 3")
                .setCallback((args) -> ImGui.text("Hello tab 3 !"))
        )
        .setName("Window bar");

    public TabbedWindowExemple() {
        super("Tabbed Window Exemple", 0, true);
    }

    @Override
    protected void internalRender() {
        windowTabWidget.render();
    }
}
