package me.lachy.scorpionclient.modules;

import me.lachy.scorpionclient.ui.modules.ArmorHUDConfigScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Style;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class ScorpionModules {

    private Screen mainConfigScreen = null;
    private MinecraftClient client;

    public ScorpionModules(Screen parent, MinecraftClient client) {
        this.client = client;
        this.mainConfigScreen = parent;
    }

    private final ThreadLocalRandom random = ThreadLocalRandom.current();
    private final ButtonWidget test = new ButtonWidget(0, 0, 150, 20, Text.literal("Armor HUD"), button ->
        this.client.setScreen(new ArmorHUDConfigScreen(this.mainConfigScreen, this.client)));

    private final ButtonWidget test2 = new ButtonWidget(0, 0, 150, 20, Text.literal("Test 2"), button ->
            button.setMessage(button.getMessage().copy()
                    .setStyle(Style.EMPTY.withColor(new Color(this.random.nextInt(255), this.random.nextInt(255), this.random.nextInt(255)).getRGB()))
            ));

    private final ButtonWidget test3 = new ButtonWidget(0, 0, 150, 20, Text.literal("Test 3"), button ->
            button.setMessage(button.getMessage().copy()
                    .setStyle(Style.EMPTY.withColor(new Color(this.random.nextInt(255), this.random.nextInt(255), this.random.nextInt(255)).getRGB()))
            ));

    public ButtonWidget[] getModules() {
        return new ButtonWidget[] {
                this.test,
                this.test2,
                this.test3,
        };
    }

}
