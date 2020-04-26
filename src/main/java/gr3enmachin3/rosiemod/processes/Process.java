package gr3enmachin3.rosiemod.processes;

import baritone.api.BaritoneAPI;
import baritone.api.command.manager.ICommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Process {
    protected static EntityPlayerSP player = Minecraft.getMinecraft().player;
    protected static ICommandManager baritone = BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager();
    protected static String requester;

    public abstract void run();
}
