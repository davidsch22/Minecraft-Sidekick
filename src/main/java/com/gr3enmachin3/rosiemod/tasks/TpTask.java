package com.gr3enmachin3.rosiemod.tasks;

public class TpTask extends Task {
    public TpTask(String requester) {
        Task.requester = requester;
    }

    @Override
    public void run() {
        player.sendChatMessage("/tp " + requester);
        player.sendChatMessage("I'm here, " + requester);
        oldRequester = requester;
    }
}
