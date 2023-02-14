package gr3enmachin3.rosiemod.tasks;

import gr3enmachin3.rosiemod.RosieMod;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=RosieMod.MOD_ID, bus=EventBusSubscriber.Bus.FORGE)
public class FollowTask extends Task {
    protected static boolean isFollowing = false;

    public FollowTask(String requester) {
        Task.requester = requester;
    }

    @Override
    public void run() {
        player.chat("I'm right behind you, " + requester);
        isFollowing = true;
        baritone.execute("follow player " + requester);
        oldRequester = requester;
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (isFollowing) {
            Player reqPlayer = player.getCommandSenderWorld().players().stream().filter(entPlayer ->
                    entPlayer.getName().getString().equals(requester)).findFirst().orElse(null);
            if (reqPlayer != null) {
                Vec3 dir = reqPlayer.position().subtract(player.position()).normalize();
                float yaw = (float)(Math.atan2(dir.z, dir.x) * 180 / Math.PI) - 90;
                float pitch = (float)(-Math.asin(dir.y) * 180 / Math.PI);
                player.setXRot(yaw);
                player.setYRot(pitch);
            }
        }
    }
}
