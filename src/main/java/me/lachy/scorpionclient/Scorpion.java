package me.lachy.scorpionclient;

import net.fabricmc.api.ModInitializer;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;

import java.awt.*;

public class Scorpion implements ModInitializer {

    public static final Style SCORPION_STYLE = Style.EMPTY.withColor(TextColor.fromRgb(new Color(161, 21, 21).getRGB()));

    @Override
    public void onInitialize() {

    }
}
