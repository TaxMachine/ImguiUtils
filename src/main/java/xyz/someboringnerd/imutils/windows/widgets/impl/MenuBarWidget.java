package xyz.someboringnerd.imutils.windows.widgets.impl;

import imgui.ImGui;
import xyz.someboringnerd.imutils.windows.ImguiMenu;
import xyz.someboringnerd.imutils.windows.widgets.BaseWidget;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MenuBarWidget extends BaseWidget<MenuBarWidget> {

    private final Set<ImguiMenu> childs = new HashSet<>();

    @Override
    public void render() {
        if(ImGui.beginMenuBar()) {
            childs.forEach(ImguiMenu::draw);
            ImGui.endMenuBar();
        }
    }

    public MenuBarWidget addChilds(ImguiMenu... childrens) {
        Arrays.stream(childrens).filter(ImguiMenu::isTabItem).findFirst().ifPresent(child -> {
            throw new RuntimeException("Child " + child.getName() + " is set as a tab item !");
        });
        childs.addAll(List.of(childrens));
        return this;
    }
}