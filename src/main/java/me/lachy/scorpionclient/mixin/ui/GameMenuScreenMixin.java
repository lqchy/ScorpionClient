package me.lachy.scorpionclient.mixin.ui;

import me.lachy.scorpionclient.Scorpion;
import me.lachy.scorpionclient.ui.ScorpionConfigScreen;
import net.minecraft.SharedConstants;
import net.minecraft.client.gui.screen.*;
import net.minecraft.client.gui.screen.advancement.AdvancementsScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin extends Screen {

    protected GameMenuScreenMixin(Text title) {
        super(Text.translatable("menu.game"));
    }

    @Inject(at = @At("HEAD"), method = "initWidgets", cancellable = true)
    public void initWidgets(CallbackInfo ci) {
        int i = -16;
        int j = 98;
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20, Text.translatable("menu.returnToGame"), button -> {
            this.client.setScreen(null);
            this.client.mouse.lockCursor();
        }));
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 48 + -16, 98, 20, Text.translatable("gui.advancements"), button -> this.client.setScreen(new AdvancementsScreen(this.client.player.networkHandler.getAdvancementHandler()))));
        this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 48 + -16, 98, 20, Text.translatable("gui.stats"), button -> this.client.setScreen(new StatsScreen(this, this.client.player.getStatHandler()))));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 72 + -16, 204, 20, Text.literal("Scorpion").setStyle(Scorpion.SCORPION_STYLE),
            button -> {
                this.client.setScreen(new ScorpionConfigScreen(this, this.client));
                this.client.mouse.lockCursor();
            }
        ));

        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.options"), button -> this.client.setScreen(new OptionsScreen(this, this.client.options))));
        if (this.client.isIntegratedServerRunning() && !this.client.getServer().isRemote()) {
            this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.shareToLan"), button -> this.client.setScreen(new OpenToLanScreen(this))));
        } else {
            this.addDrawableChild(new ButtonWidget(this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, Text.translatable("menu.playerReporting"), buttonWidget -> this.client.setScreen(new SocialInteractionsScreen())));
        }
        MutableText text = this.client.isInSingleplayer() ? Text.translatable("menu.returnToMenu") : Text.translatable("menu.disconnect");
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 102, this.height / 4 + 120 + -16, 204, 20, text, button -> {
            boolean bl = this.client.isInSingleplayer();
            boolean bl2 = this.client.isConnectedToRealms();
            button.active = false;
            this.client.world.disconnect();
            if (bl) {
                this.client.disconnect(new MessageScreen(Text.translatable("menu.savingLevel")));
            } else {
                this.client.disconnect();
            }
            TitleScreen titleScreen = new TitleScreen();
            if (bl) {
                this.client.setScreen(titleScreen);
            } else if (bl2) {
                this.client.setScreen(new RealmsMainScreen(titleScreen));
            } else {
                this.client.setScreen(new MultiplayerScreen(titleScreen));
            }
        }));

        ci.cancel();
    }


}
