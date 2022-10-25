package me.lachy.scorpionclient.client;

import com.cecer1.projects.mc.cecermclib.common.modules.ModuleRegistrationCallback;
import me.lachy.scorpionclient.modules.ArmorHUD;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

@Environment(EnvType.CLIENT)
public class ScorpionClient implements ClientModInitializer {

    public String e = "";

    @Override
    public void onInitializeClient() {

        ModuleRegistrationCallback.EVENT.register(ctx -> {
            ctx.registerModule(new ArmorHUD(MinecraftClient.getInstance()));
        });

//        for (Class<?> module : this.getClassesWithAnnotation(ScorpionModule.class.getName())) {
//            try {
//                MinecraftClient client = MinecraftClient.getInstance();
//                module.getDeclaredConstructor(MinecraftClient.class).newInstance(client);
//            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
//                     NoSuchMethodException ex) {
//                throw new RuntimeException(ex);
//            }
//        }

//        new ArmorHUD(MinecraftClient.getInstance());

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
