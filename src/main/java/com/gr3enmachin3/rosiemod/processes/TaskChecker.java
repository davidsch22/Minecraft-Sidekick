package com.gr3enmachin3.rosiemod.processes;

public class TaskChecker {
    public static boolean taskIsRunning() {
        if (FollowTask.isFollowing || GatherTask.isGathering || GatherTask.isReturning) {
            Task.player.sendChatMessage("Sorry, " + Task.requester + ", I'm already currently doing something");
            return true;
        }
        return false;
    }
}
