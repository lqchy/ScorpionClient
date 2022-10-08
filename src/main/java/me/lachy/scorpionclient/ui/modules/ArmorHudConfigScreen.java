package me.lachy.scorpionclient.ui.modules;

import me.lachy.scorpionclient.modules.ArmorHUD;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

@SuppressWarnings("DuplicatedCode")
public class ArmorHudConfigScreen extends Screen {

    private final Screen parent;
    private final ClickableWidget[] options;
    private final MinecraftClient client;

    public ArmorHudConfigScreen(Screen parent, MinecraftClient client) {
        super(Text.literal("Armor HUD Config"));
        this.parent = parent;
        this.client = client;
        this.options = new ClickableWidget[] {
                SimpleOption.ofBoolean("options.fov", SimpleOption.emptyTooltip(), true).createButton(this.client.options, 0, 0, 150)
        };
        new ArmorHUD(this.client);
    }

    @Override
    protected void init() {
        ClickableWidget[] widgets = this.options;
        for (int i = 0; i < widgets.length; i++) {
            PressableWidget button = (PressableWidget) widgets[i];
            button.x = this.width / 2 - 155 + i % 2 * 160;
            button.y = this.height / 6 - 12 + 24 * (i >> 1);
            this.addDrawableChild(button);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(matrices, mouseX, mouseY, delta);
    }
}
