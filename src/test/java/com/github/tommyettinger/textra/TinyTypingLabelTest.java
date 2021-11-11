package com.github.tommyettinger.textra;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class TinyTypingLabelTest extends ApplicationAdapter {
    Skin        skin;
    Stage       stage;
    SpriteBatch batch;
    TypingLabel label;
    TextButton  buttonPause;
    TextButton  buttonResume;
    TextButton  buttonRestart;
    TextButton  buttonRebuild;
    TextButton  buttonSkip;

    @Override
    public void create() {
        adjustTypingConfigs();

        batch = new SpriteBatch();
        skin = new Skin(Gdx.files.internal("uiskin2.json"));
//        skin.getAtlas().getTextures().iterator().next().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        float scale = 0.5f;
        skin.getFont("default-font").getData().setScale(scale);
        stage = new Stage(new ScreenViewport(), batch);
        Gdx.input.setInputProcessor(stage);

        final Table table = new Table();
        stage.addActor(table);
        table.setFillParent(true);

        label = createTypingLabel();

        buttonPause = new TextButton("Pause", skin);
        buttonPause.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                label.pause();
            }
        });

        buttonResume = new TextButton("Resume", skin);
        buttonResume.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                label.resume();
            }
        });

        buttonRestart = new TextButton("Restart", skin);
        buttonRestart.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                label.restart();
            }
        });

        buttonRebuild = new TextButton("Rebuild", skin);
        buttonRebuild.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                adjustTypingConfigs();
                Cell<TypingLabel> labelCell = table.getCell(label);
                label = createTypingLabel();
                labelCell.setActor(label);
            }
        });

        buttonSkip = new TextButton("Skip", skin);
        buttonSkip.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                label.skipToTheEnd();
            }
        });

        table.pad(50f);
        table.add(label).colspan(5).growX();
        table.row();
        table.row().uniform().expand().growX().space(40).center();
        table.add(buttonPause, buttonResume, buttonRestart, buttonSkip, buttonRebuild);

        table.pack();
    }

    public void adjustTypingConfigs() {
        // Only allow two chars per frame
        TypingConfig.CHAR_LIMIT_PER_FRAME = 2;

        // Change color used by CLEARCOLOR token
        TypingConfig.DEFAULT_CLEAR_COLOR = Color.WHITE;

        // Create some global variables to handle style
        TypingConfig.GLOBAL_VARS.put("ICE_WIND", "{FASTER}{GRADIENT=88ccff;eef8ff;-0.5;5}{SLOWER}{WIND=2;4;0.25;0.1}");
    }

    public TypingLabel createTypingLabel() {
        // Create label
//        final TypingLabel label = new TypingLabel("WELCOME {STYLE=OBLIQUE}TO THE {STYLE=bold}{COLOR=11bb00}JUNGLE{RESET}, WE'VE GOT A MAN, A PLAN, A CANAL: PANAMA!",
        final TypingLabel label = new TypingLabel("{JOLT=1;1.2;inf;0.3;9944aa;fff0cc}There's a [/]{STYLE=bold}STORM{RESET} on the way, she's {WIND=3;2;0.2;0.2} blowin' on down{RESET}," +
                "whippin' her way through the [*]whole dang[*] town! Sure as [/]I reckon[], if we meet our {HANG}fate{RESET}, this storm will be there on clouds one through eight!",
//        final TypingLabel label = new TypingLabel("[/][*][GREEN]JUNGLE[*][WHITE] TO THE[/] WELCOME!",
//        final TypingLabel label = new TypingLabel("WELCOME [/]TO THE [*][GREEN]JUNGLE[]!",
                new Font(new BitmapFont(Gdx.files.internal("OpenSans.fnt")), Font.DistanceFieldType.STANDARD,
                        0f, 0f, 0f, 0f).scale(0.5f, 0.5f).setTextureFilter());
//        final TypingLabel label = new TypingLabel("WELCOME [/]TO THE [*][GREEN]JUNGLE[]!", skin);
//        final TypingLabel label = new TypingLabel("{WAIT=1}{SLOWER}Welcome, {VAR=title}!", skin);
        label.setDefaultToken("{EASE}{FADE=0;1;0.33}");

        // Make the label wrap to new lines, respecting the table's layout.
        label.layout.maxLines = 15;
        label.layout.setTargetWidth(Gdx.graphics.getBackBufferWidth() - 100);

        // Set variable replacements for the {VAR} token
        label.setVariable("title", "curious human");

        // Set an event listener for when the {EVENT} token is reached and for the char progression ends.
        label.setTypingListener(new TypingAdapter() {
            @Override
            public void event(String event) {
                System.out.println("Event: " + event);
            }

            @Override
            public void end() {
                System.out.println("End");
            }
        });

        // Finally parse tokens in the label text.
        label.parseTokens();

        return label;
    }

    public void update(float delta) {
        stage.act(delta);
    }

    @Override
    public void render() {
        update(Gdx.graphics.getDeltaTime());

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
        Gdx.graphics.setTitle(Gdx.graphics.getFramesPerSecond() + " FPS");
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("TypingLabel Test");
        config.setWindowedMode(720, 405);
        config.setResizable(false);
        config.setForegroundFPS(60);
        config.useVsync(true);
        new Lwjgl3Application(new TinyTypingLabelTest(), config);
    }
}