package com.gr3enmachin3.rosiemod.processes;

public class CancelTask extends Task {
    @Override
    public void run() {
        player.sendChatMessage("Ok");
        FollowTask.isFollowing = false;
        GatherTask.isGathering = false;
        GatherTask.isReturning = false;
        baritone.execute("stop");
    }
}
