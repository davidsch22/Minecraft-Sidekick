package gr3enmachin3.rosiemod.tasks;

public class CancelTask extends Task {
    public CancelTask(String requester) {
        Task.requester = requester;
    }

    @Override
    public void run() {
        run(false);
    }

    public void run(boolean interrupt) {
        if (!interrupt) player.sendChatMessage("Ok");
        FollowTask.isFollowing = false;
        GatherTask.isGathering = false;
        GatherTask.isReturning = false;
        baritone.execute("stop");
        oldRequester = requester;
    }
}
