package xyz.someboringnerd.imutils.exemples;

import xyz.someboringnerd.imutils.windows.ImguiMenu;
import xyz.someboringnerd.imutils.windows.ImguiWindow;
import xyz.someboringnerd.imutils.windows.widgets.impl.MenuBarWidget;

public class WindowWithMenuBar extends ImguiWindow {

    private final MenuBarWidget barWidget = new MenuBarWidget()
        .setName("bar")
        .addChilds(
            new ImguiMenu()
                .setName("Files")
                .addChild(
                    new ImguiMenu()
                        .setName("Save")
                        .setMenuItem(true)
                        .setCallback((args) -> System.out.println("Saved something, I guess")),
                    new ImguiMenu()
                        .setName("Load")
                        .setMenuItem(true)
                        .setCallback((args) -> System.out.println("Loaded something, I guess"))
                ),
            new ImguiMenu()
                .setName("Another Menu")
                .addChild(new ImguiMenu()
                    .setName("List")
                    .addChild(
                        new ImguiMenu()
                            .setName("1")
                            .setCallback((args) -> System.out.println("test 1"))
                            .setMenuItem(true),
                        new ImguiMenu()
                            .setName("2")
                            .setCallback((args) -> System.out.println("test 2"))
                            .setMenuItem(true)
                    ),
                    new ImguiMenu()
                        .setName("Yet Another Useless Tab")
                        .setMenuItem(true)
                        .setCallback((args) -> System.out.println("I was clicked on"))
                )
        );

    public WindowWithMenuBar() {
        super("Window with menu bar", 0, true);
    }

    @Override
    protected void internalRender() {
        barWidget.render();
    }
}