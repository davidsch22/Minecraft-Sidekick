package com.gr3enmachin3.rosiemod.processes;

import baritone.api.BaritoneAPI;
import baritone.api.command.manager.ICommandManager;
import com.gr3enmachin3.rosiemod.RosieMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=RosieMod.MOD_ID, bus=EventBusSubscriber.Bus.FORGE)
public abstract class Task {
    protected static ClientPlayerEntity player;
    protected static ICommandManager baritone;
    protected static String requester;
    protected static String oldRequester;

    public abstract void run();

    @SubscribeEvent
    public static void joinedWorld(RenderWorldLastEvent event) {
        if (baritone == null) {
            baritone = BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager();
        }
        if (player == null) {
            player = Minecraft.getInstance().player;
        }
    }

    public static boolean taskIsRunning() {
        return FollowTask.isFollowing || GatherTask.isGathering || GatherTask.isReturning;
    }
}
