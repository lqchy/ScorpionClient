package me.lachy.scorpionclient.modules;

import com.cecer1.projects.mc.cecermclib.common.CecerMCLib;
import com.cecer1.projects.mc.cecermclib.common.modules.IModule;
import com.cecer1.projects.mc.cecermclib.fabric.modules.input.InputModule;
import com.cecer1.projects.mc.cecermclib.fabric.modules.input.mouse.MouseRegionHandler;
import com.cecer1.projects.mc.cecermclib.fabric.modules.rendering.SafeHudRenderCallback;
import com.cecer1.projects.mc.cecermclib.fabric.modules.rendering.context.RenderContext;
import com.cecer1.projects.mc.cecermclib.fabric.modules.rendering.context.TransformCanvas;
import com.cecer1.projects.mc.cecermclib.fabric.modules.smarttexture.SmartTexture;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArmorHUD implements IModule {

    private final MinecraftClient client;
    private boolean isEnabled = true;

    public ArmorHUD(MinecraftClient client) {
        this.client = client;

        SafeHudRenderCallback.EVENT.register(this::onRender);
    }

    private void onRender(RenderContext ctx) {
        if (this.isEnabled && this.client != null && this.client.player != null) {
            PlayerInventory inventory = this.client.player.getInventory();
            List<ItemStack> armor = new ArrayList<>(inventory.armor);
            Collections.reverse(armor);
            for (int i = 0; i < armor.size(); i++) {
                ItemStack itemStack = armor.get(i);
                if (itemStack.getItem().equals(Items.AIR)) {
                    continue;
                }
                float percent = 100 - (float) itemStack.getDamage() / itemStack.getMaxDamage() * 100;
                int x = this.client.getWindow().getScaledWidth() / 2 - 155 + i % 2 * 160;
                int y = this.client.getWindow().getScaledHeight() / 6 - 12 + 24 * (i >> 1);
                this.client.textRenderer.drawWithShadow(ctx.getMatrixStack(), Text.literal(String.valueOf(percent)), x + 20, y, new Color(1f, 1f, 1f, percent / 100).getRGB());
                this.client.getItemRenderer().renderInGui(itemStack, x, y);
            }
        }
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}

//         try (TransformCanvas canvas = ctx.getCanvas().transform()
//                .translate(10, 5)
//                .scale(2)
//                .margin(10, 10, 10, 10)
//                .openTransformation()) {
//            MouseRegionHandler mouseRegionHandler = new MouseRegionHandler();
//            mouseRegionHandler.setRelativeRegion(canvas, 2, 2, 50, 50);
//
//            CecerMCLib.get(InputModule.class).getMouseInputManager()
//                    .registerHandler(mouseRegionHandler);
//        }
