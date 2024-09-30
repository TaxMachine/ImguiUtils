package xyz.someboringnerd.imutils.windows;

import imgui.ImGui;
import lombok.Getter;
import xyz.someboringnerd.imutils.interfaces.Callback;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * ImguiMenu is a "catch-all" term for menu bars and tabs bar.
 * <br>
 * an ImguiMenu can have 3 states :
 * <br><br>
 *  * <u>isTabItem</u> : this menu is part of a collection of tab in a window<br><br>
 *  * <u>isMenuItem</u> : this menu is a child of another menu and run code when clicked on<br><br>
 *  * <u>neither</u> : by default, this menu can be a parent of the bar, or a child, and is parents of other ImguiMenus. It does not execute code when clicked on. It cannot be part of a tab
 */
public class ImguiMenu {
    private final Set<ImguiMenu> childs = new LinkedHashSet<>();

    @Getter
    private String name;

    private Callback execute;

    @Getter
    private boolean isMenuItem = false;

    @Getter
    private boolean isTabItem = false;

    public final ImguiMenu instance;

    public ImguiMenu() {
        instance = this;
    }

    /**
     * Display name of the element
     * @param name
     * @return : self
     */
    public ImguiMenu setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Add childs to draw inside of the current menu
     * @param child : a collection of instances of other ImguiMenus
     * @return : self
     */
    public ImguiMenu addChild(ImguiMenu... child) {
        childs.addAll(Arrays.asList(child));
        return this;
    }

    public ImguiMenu setCallback(Callback callback) {
        this.execute = callback;
        return this;
    }

    public void draw() {
        if(isMenuItem) {
            if(ImGui.menuItem(name)) {
                if(execute != null)
                    execute.run();
            }
            return;
        }
        if(isTabItem) {
            if(ImGui.beginTabItem(name)) {
                if(execute != null)
                    execute.run();

                ImGui.endTabItem();
            }
            return;
        }
        if(ImGui.beginMenu(name)) {
            childs.forEach(ImguiMenu::draw);
            ImGui.endMenu();
        }
    }

    /**
     * If true, this menu does not contain child (and if it does, it won't draw them)
     * @param b
     * @return : self
     */
    public ImguiMenu setMenuItem(boolean b) {
        this.isMenuItem = b;
        this.isTabItem = !b;
        return this;
    }

    /**
     * If true, this menu is a tab in a set of tabs for a window
     * @param b
     * @return
     */
    public ImguiMenu setTabItem(boolean b) {
        this.isTabItem = b;
        this.isMenuItem = !b;
        return this;
    }
}
