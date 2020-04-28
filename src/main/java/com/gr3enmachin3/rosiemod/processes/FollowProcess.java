package com.gr3enmachin3.rosiemod.processes;

import com.gr3enmachin3.rosiemod.RosieMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=RosieMod.MOD_ID, bus=EventBusSubscriber.Bus.FORGE)
public class FollowProcess extends Process {
    public static boolean isFollowing = false;

    public FollowProcess(String requester) {
        Process.requester = requester;
    }

    @Override
    public void run() {
        player.sendChatMessage("I'm right behind you, " + requester);
        isFollowing = true;
        baritone.execute("follow player " + requester);
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (isFollowing) {
            // TODO: Face the requester
        }
    }
}
