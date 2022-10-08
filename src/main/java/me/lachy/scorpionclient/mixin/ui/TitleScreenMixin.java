package me.lachy.scorpionclient.mixin.ui;

import com.google.common.util.concurrent.Runnables;
import me.lachy.scorpionclient.Scorpion;
import me.lachy.scorpionclient.ui.ScorpionConfigScreen;
import net.minecraft.client.gui.CubeMapRenderer;
import net.minecraft.client.gui.screen.ConnectScreen;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.client.network.ServerAddress;
import net.minecraft.client.network.ServerInfo;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

import static net.minecraft.client.gui.screen.TitleScreen.COPYRIGHT;

@SuppressWarnings("UnstableApiUsage")
@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    @Shadow public abstract void removed();

    private final Style HYPIXEL_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(new Color(253, 172, 54).getRGB()));
    private final ServerInfo HYPIXEL = new ServerInfo("Hypixel", "mc.hypixel.net", false);
//
    public TitleScreenMixin() {
        super(Text.empty());
    }

    @Inject(at = @At("HEAD"), method = "init()V", cancellable = true)
    private void init(CallbackInfo ci) {
        int l = this.height / 4 + 48;

        if (this.client == null) {
            ci.cancel();
        }

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l, 200, 20, Text.literal("Singleplayer"),
                button -> this.client.setScreen(new SelectWorldScreen(this))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 24, 200, 20, Text.literal("Multiplayer"),
                button -> this.client.setScreen(new MultiplayerScreen(this))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 48, 98, 20, Text.literal("Hypixel").setStyle(this.HYPIXEL_STYLE),
                button -> ConnectScreen.connect(new MultiplayerScreen(this), this.client, ServerAddress.parse(this.HYPIXEL.address), this.HYPIXEL)));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 2, l + 48, 98, 20, Text.literal("Scorpion").setStyle(Scorpion.SCORPION_STYLE),
                button -> this.client.setScreen(new ScorpionConfigScreen(this, this.client))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, l + 72, 98, 20, Text.translatable("menu.options"),
                button -> this.client.setScreen(new OptionsScreen(this, this.client.options))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 2, l + 72, 98, 20, Text.translatable("menu.quit"),
                button -> this.client.scheduleStop()));

        int i = this.textRenderer.getWidth(COPYRIGHT);
        this.addDrawableChild(new PressableTextWidget(this.width - i - 2, this.height - 10, i, 10, COPYRIGHT,
                button -> this.client.setScreen(new CreditsScreen(false, Runnables.doNothing())), this.textRenderer));

        ci.cancel();
    }
}
