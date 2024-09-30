# Imgui-Utils

Imgui-Utils is a small project for Spair's Imgui bindings for Java.

It is meant to add code to make ImGui less verbose and simpler to build UIs with, as well as adding widgets you can easely use.

Instead of having a nest hell in your code, you'll have instead a fuckton of builders instead. But in my case, I find it more readable.

## Features
<details>
<summary>Creating an imgui context (opengl)</summary>

Exemple with libgdx : 

```java

public class EngineApp extends ApplicationAdapter {
    @Override
    public void create() {
        ImguiManager.init(((Lwjgl3Graphics) Gdx.graphics).getWindow().getWindowHandle(), (args) -> {
            // some sort of event system here to fire an event to let the entire app know you are asking them to render an imgui window
            // the following code is for the Transcendance Engine, but you may have another way of doing things.
            EventManager.fire(RenderImguiEvent.get(), EventSide.BOTH);
        });
    }

    @Override
    public void render() {
        batch.setProjectionMatrix(RenderUtil.camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        // logic to draw your world goes here
        
        // render imgui after everything else
        EventManager.render();
        batch.end();
    }
}

```

</details>

<details>
<summary>Window builder</summary>

Imgui-Utils have some utils to create windows in a simple manner.

_Without Imgui Utils_
```java

public class ExempleWindow {
    
    private final ImBoolean closed = new ImBoolean(true);
    
    private final boolean closable;
    
    public ExempleWindow(boolean closable) {
        this.closable = closable;
    }

    @Override
    protected void internalRender() {
        if(closable) {
            if (ImGui.begin("Test windows", ImGuiWindowFlags.AlwaysAutoResize, closed)) {
                ImGui.text("hello world !");
            }
            ImGui.end();
            
            if(!closed.get()) {
                // your logic to destroy the object
            }
        } else {
            if (ImGui.begin("Test windows", ImGuiWindowFlags.AlwaysAutoResize)) {
                ImGui.text("hello world !");
            }
            ImGui.end();
        }
    }
}

```

_with Imgui Utils_
```java

public class ExempleWindow extends ImguiWindow {
    public ExempleWindow() {
        super("Test windows", ImGuiWindowFlags.AlwaysAutoResize, true);
    }

    @Override
    protected void internalRender() {
        ImGui.text("Hello world !");
    }
    
    @Override
    public void onTurnedOff() {
        // your logic to destroy the object
    }
}

```
</details>

<details>
<summary>Tab builder</summary>

Imgui-Utils can also create windows with tabs.

_Without Imgui Utils_
```java

public class ExempleWindow {

    private final ImBoolean closed = new ImBoolean(true);

    private final boolean closable;

    public ExempleWindow(boolean closable) {
        this.closable = closable;
    }

    @Override
    protected void internalRender() {
        if(closable) {
            if (ImGui.begin("Test windows", ImGuiWindowFlags.AlwaysAutoResize, closed)) {
                if(ImGui.beginTabBar("Window bar")) {
                    if(Imgui.beginTabItem("Tab 1")) {
                        ImGui.text("Hello tab 1 !");
                        ImGui.endTabItem();
                    }
                    if(Imgui.beginTabItem("Tab 2")) {
                        ImGui.text("Hello tab 2 !");
                        ImGui.endTabItem();
                    }
                    if(Imgui.beginTabItem("Tab 3")) {
                        ImGui.text("Hello tab 3 !");
                        ImGui.endTabItem();
                    }
                    Imgui.endTabBar();
                }
            }
            ImGui.end();

            if(!closed.get()) {
                // your logic to destroy the object
            }
        } else {
            if (ImGui.begin("Test windows", ImGuiWindowFlags.AlwaysAutoResize)) {
                if(ImGui.beginTabBar("Window bar")) {
                    if(Imgui.beginTabItem("Tab 1")) {
                        ImGui.text("Hello tab 1 !");
                        ImGui.endTabItem();
                    }
                    if(Imgui.beginTabItem("Tab 2")) {
                        ImGui.text("Hello tab 2 !");
                        ImGui.endTabItem();
                    }
                    if(Imgui.beginTabItem("Tab 3")) {
                        ImGui.text("Hello tab 3 !");
                        ImGui.endTabItem();
                    }
                    Imgui.endTabBar();
                }
            }
            ImGui.end();
        }
    }
}
```

_With Imgui Utils_
```java

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

```
</details>

<details>
<summary>Menu Bar Builder</summary>

Imgui-Utils can also create windows with menu bars.

_Without Imgui Utils_
```java

public class ExempleWindow {

    private final ImBoolean closed = new ImBoolean(true);

    private final boolean closable;

    public ExempleWindow(boolean closable) {
        this.closable = closable;
    }

    @Override
    protected void internalRender() {
        if(closable) {
            if (ImGui.begin("Test windows", ImGuiWindowFlags.AlwaysAutoResize, closed)) {
                if(ImGui.beginMenuBar()) {
                    if(ImGui.beginMenu("Files")) {
                        if (ImGui.menuItem("Save")) {
                            System.out.println("Saved something, I guess");
                        }
                        if (ImGui.menuItem("Load")) {
                            System.out.println("Saved something, I guess");
                        }
                        ImGui.endMenu();
                    }
                    if(ImGui.beginMenu("Another Menu")) {
                        if (ImGui.beginMenu("Save")) {
                            if (ImGui.menuItem("1")) {
                                System.out.println("test 1");
                            }
                            if (ImGui.menuItem("2")) {
                                System.out.println("test 2");
                            }
                            ImGui.endMenu();
                        }
                        if (ImGui.menuItem("Load")) {
                            System.out.println("I was clicked on");
                        }
                        ImGui.endMenu();
                    }
                    ImGui.endMenuBar();
                }
            }
            ImGui.end();

            if(!closed.get()) {
                // your logic to destroy the object
            }
        } else {
            if (ImGui.begin("Test windows", ImGuiWindowFlags.AlwaysAutoResize)) {
                if(ImGui.beginMenuBar()) {
                    if(ImGui.beginMenu("Files")) {
                        if (ImGui.menuItem("Save")) {
                            System.out.println("Saved something, I guess");
                        }
                        if (ImGui.menuItem("Load")) {
                            System.out.println("Saved something, I guess");
                        }
                        ImGui.endMenu();
                    }
                    if(ImGui.beginMenu("Another Menu")) {
                        if (ImGui.beginMenu("Save")) {
                            if (ImGui.menuItem("1")) {
                                System.out.println("test 1");
                            }
                            if (ImGui.menuItem("2")) {
                                System.out.println("test 2");
                            }
                            ImGui.endMenu();
                        }
                        if (ImGui.menuItem("Load")) {
                            System.out.println("I was clicked on");
                        }
                        ImGui.endMenu();
                    }
                    ImGui.endMenuBar();
                }
            }
            ImGui.end();
        }
    }
}
```

_With Imgui Utils_
```java
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

```
</details>

## Add to project 

<details>
<summary>Maven</summary>

```
<repository>
  <id>reposilite-repository-releases</id>
  <name>Reposilite Repository</name>
  <url>https://maven.self-hosted.lol/releases</url>
</repository>
<dependency>
  <groupId>xyz.someboringnerd</groupId>
  <artifactId>Imgui-Utils</artifactId>
  <version>1.0.1</version>
</dependency>
```
</details>
<details>
<summary>Gradle Groovy</summary>

```
repositories {
    maven {
        url "https://maven.self-hosted.lol/releases"
    }
}
dependencies {
    implementation "xyz.someboringnerd:Imgui-Utils:1.0.1"
}
```
</details>
<details>
<summary>Gradle Kotlin</summary>

```
repositories {
    maven {
        url = uri("https://maven.self-hosted.lol/releases")
    }
}
dependencies {
    implementation "xyz.someboringnerd:Imgui-Utils:1.0.1"
}
```
</details>

## Credits 

ImguiUtils was made possible thank to those libraries : 

|         Library         |               Website               |
|:-----------------------:|:-----------------------------------:|
|  Spair's Imgui Binding  | https://github.com/SpaiR/imgui-java |
|     Project Lombok      |     https://projectlombok.org/      |

_note: you will need Project Lombok's plugin for your chosen IDE or you may run into errors. Code will compile with no issues_