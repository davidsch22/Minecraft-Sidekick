package com.gr3enmachin3.sidekickmod.tasks;

public class FarmTask extends Task {
    public FarmTask(String requester) {
        Task.requester = requester;
    }

    @Override
    public void run() {
        player.sendChatMessage("Ok, " + requester + ", I'll start farming for you");
        baritone.execute("farm");
    }
}
