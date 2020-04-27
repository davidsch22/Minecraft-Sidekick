package com.gr3enmachin3.rosiemod.processes;

import baritone.api.command.manager.ICommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;

public abstract class Process {
    protected static ClientPlayerEntity player = Minecraft.getInstance().player;
    public static ICommandManager baritone = null;
    protected static String requester;

    public abstract void run();
}
