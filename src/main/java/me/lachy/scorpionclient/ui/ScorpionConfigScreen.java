package me.lachy.scorpionclient.ui;

import me.lachy.scorpionclient.Scorpion;
import me.lachy.scorpionclient.modules.ScorpionModules;
import me.lachy.scorpionclient.modules.ScorpionOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonListWidget;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ScorpionConfigScreen extends Screen {

    private final Screen parent;
    private final ScorpionOptions options;
    private final ScorpionModules modules;
    private ButtonListWidget list;
    private final MinecraftClient client;

    public ScorpionConfigScreen(Screen parent, MinecraftClient client) {
        super(Text.literal("Scorpion Config").setStyle(Scorpion.SCORPION_STYLE));
        this.parent = parent;
        this.client = client;
        this.options = new ScorpionOptions();
        this.modules = new ScorpionModules();
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
//        this.list.render(matrices, mouseX, mouseY, delta);
        super.render(matrices, mouseX, mouseY, delta);
//        List<OrderedText> list = VideoOptionsScreen.getHoveredButtonTooltip(this.list, mouseX, mouseY);
//        this.renderOrderedTooltip(matrices, list, mouseX, mouseY);
    }
}
