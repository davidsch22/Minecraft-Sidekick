package gr3enmachin3.rosiemod.tasks;

import baritone.api.BaritoneAPI;
import baritone.api.command.manager.ICommandManager;
import gr3enmachin3.rosiemod.RosieMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=RosieMod.MOD_ID, bus=EventBusSubscriber.Bus.FORGE)
public abstract class Task {
    protected static LocalPlayer player;
    protected static ICommandManager baritone;
    protected static String requester;
    protected static String oldRequester;

    public abstract void run();

    @SubscribeEvent
    public static void joinedWorld(RenderLevelLastEvent event) {
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

    public static boolean isSameRequester() {
        if (!requester.equals(oldRequester)) {
            player.chat("Sorry, " + requester + ", only " + oldRequester + " can stop my current task");
            return false;
        }
        return true;
    }
}
