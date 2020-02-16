package com.gr3enmachin3.rosiemod;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ChatEventHandler {
    @SubscribeEvent
    public static void readChat(ServerChatEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        IBaritone baritone = BaritoneAPI.getProvider().getPrimaryBaritone();

        String player = event.getUsername();
        String message = event.getMessage().toLowerCase();

        if (!player.equals(mc.player.getName()) && message.contains("rosie")) {
            if (message.contains("follow me")) {
                mc.player.sendChatMessage("Ok, " + player + ", I will follow you.");
                baritone.getFollowProcess().follow(entity -> entity.getName().equals(player));
            }

            if (message.contains("stop")) {
                baritone.getFollowProcess().cancel();
            }
        }
    }
}
