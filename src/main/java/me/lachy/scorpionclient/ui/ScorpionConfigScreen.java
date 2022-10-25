package me.lachy.scorpionclient.ui;

import com.sun.jna.platform.KeyboardUtils;
import me.lachy.scorpionclient.Scorpion;
import me.lachy.scorpionclient.modules.ScorpionModules;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings("DuplicatedCode")
public class ScorpionConfigScreen extends Screen {

    private final Screen parent;
    private final ScorpionModules modules;
    private final MinecraftClient client;

    public ScorpionConfigScreen(Screen parent, MinecraftClient client) {
        super(Text.literal("Scorpion Config").setStyle(Scorpion.SCORPION_STYLE));
        this.parent = parent;
        this.client = client;
        this.modules = new ScorpionModules(this, this.client);
    }

    @Override
    protected void init() {
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 27, 200, 20, ScreenTexts.DONE, button -> {
            if (this.client != null) {
                this.client.setScreen(this.parent);
            }
        }));

        ButtonWidget[] widgets = this.modules.getModules();
        for (int i = 0; i < widgets.length; i++) {
            ButtonWidget button = widgets[i];
            button.x = this.width / 2 - 155 + i % 2 * 160;
            button.y = this.height / 6 - 12 + 24 * (i >> 1);
            this.addDrawableChild(button);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        OptionsScreen.drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }

}
