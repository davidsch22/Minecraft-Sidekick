package com.gr3enmachin3.rosiemod.processes;

public class FollowProcess extends Process {
    public FollowProcess(String requester) {
        Process.requester = requester;
    }

    @Override
    public void run() {
        player.sendChatMessage("I'm right behind you, " + requester);
        baritone.execute("follow player " + requester);
    }
}
