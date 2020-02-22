package gr3enmachin3.rosiemod.processes;

public class FollowProcess extends Process {
    public FollowProcess(String requester) {
        super.requester = requester;
    }

    @Override
    public void run() {
        player.sendChatMessage("Ok, " + requester + ", I will follow you.");
        baritone.execute("follow player " + requester);
    }
}
