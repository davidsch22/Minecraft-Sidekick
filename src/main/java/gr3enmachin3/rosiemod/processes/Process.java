package gr3enmachin3.rosiemod.processes;

import baritone.api.BaritoneAPI;
import baritone.api.command.manager.ICommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

public abstract class Process {
    protected EntityPlayerSP player = Minecraft.getMinecraft().player;
    protected ICommandManager baritone = BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager();
    protected String requester;

    public abstract void run();
}
