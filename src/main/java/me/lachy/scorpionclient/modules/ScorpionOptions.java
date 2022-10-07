package me.lachy.scorpionclient.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.Window;

public class ScorpionOptions {

    private final SimpleOption<Boolean> option = SimpleOption.ofBoolean("options.rawMouseInput", true, value -> {
        Window window = MinecraftClient.getInstance().getWindow();
        if (window != null) {
            window.setRawMouseMotion(value);
        }
    });

    public SimpleOption<?>[] getOptions() {
        return new SimpleOption[] {
                this.option
        };
    }
}
