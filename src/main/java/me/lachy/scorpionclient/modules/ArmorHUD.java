package me.lachy.scorpionclient.modules;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

@ScorpionModule
public class ArmorHUD {

    private final MinecraftClient client;
    private boolean isEnabled = true;

    public ArmorHUD(MinecraftClient client) {
        this.client = client;

        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            PlayerInventory inventory = this.client.player.getInventory();
            DefaultedList<ItemStack> armor = inventory.armor;
            for (int i = 0; i < armor.size(); i++) {
                ItemStack itemStack = armor.get(i);
                System.out.println(itemStack.getName().getString());
                this.client.getItemRenderer().renderInGui(itemStack, 200, this.client.getWindow().getHeight() / 6 - 12 + 24 * (i >> 1));
            }
        });

        if (this.isEnabled && this.client != null && this.client.player != null && this.client.currentScreen != null) {

        }
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
    }
}
