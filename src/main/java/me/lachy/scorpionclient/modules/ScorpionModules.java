package me.lachy.scorpionclient.modules;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class ScorpionModules {
    private final ButtonWidget test = new ButtonWidget(0, 0, 150, 20, Text.literal("Test"), button ->
            button.setMessage(button.getMessage()));

    public ButtonWidget[] getModules() {
        return new ButtonWidget[] {
                this.test
        };
    }

}
