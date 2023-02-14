package gr3enmachin3.rosiemod.tasks;

public class TpTask extends Task {
    public TpTask(String requester) {
        Task.requester = requester;
    }

    @Override
    public void run() {
        player.chat("/tp " + requester);
        player.chat("I'm here, " + requester);
        oldRequester = requester;
    }
}
