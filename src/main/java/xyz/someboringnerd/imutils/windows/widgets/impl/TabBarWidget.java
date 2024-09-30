package xyz.someboringnerd.imutils.windows.widgets.impl;

import imgui.ImGui;
import xyz.someboringnerd.imutils.windows.ImguiMenu;
import xyz.someboringnerd.imutils.windows.widgets.BaseWidget;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TabBarWidget extends BaseWidget<TabBarWidget> {

    private final Set<ImguiMenu> childs = new HashSet<>();

    @Override
    public void render() {
        if(ImGui.beginTabBar(getName())) {
            childs.forEach(ImguiMenu::draw);
            ImGui.endTabBar();
        }
    }

    public TabBarWidget addChilds(ImguiMenu... childrens) {
        Arrays.stream(childrens).filter(child -> !child.isTabItem()).findFirst().ifPresent(child -> {
            throw new RuntimeException("Child " + child.getName() + " is not set as a tab item !");
        });
        childs.addAll(List.of(childrens));
        return this;
    }
}
