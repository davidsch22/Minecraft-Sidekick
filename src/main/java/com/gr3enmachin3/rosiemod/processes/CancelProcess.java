package com.gr3enmachin3.rosiemod.processes;

public class CancelProcess extends Process {
    @Override
    public void run() {
        player.sendChatMessage("Ok, stopping");
        baritone.execute("stop");
    }
}
