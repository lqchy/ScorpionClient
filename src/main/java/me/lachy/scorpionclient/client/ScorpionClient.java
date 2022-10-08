package me.lachy.scorpionclient.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

@Environment(EnvType.CLIENT)
public class ScorpionClient implements ClientModInitializer {

    public String e = "";

    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            if (client.currentScreen != null) {
                String c = client.currentScreen.getClass().getSimpleName();
                if (!this.e.equals(c)) {
                    this.e = c;
                    System.out.println(c);
                }
            }
        });
    }
}
