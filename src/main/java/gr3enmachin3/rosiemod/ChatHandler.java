package gr3enmachin3.rosiemod;

import baritone.api.BaritoneAPI;
import baritone.api.IBaritone;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ChatHandler {
    @SubscribeEvent
    public static void readChat(ClientChatReceivedEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        IBaritone baritone = BaritoneAPI.getProvider().getPrimaryBaritone();

        String chat = event.getMessage().getUnformattedText();
        if (!chat.contains(">")) return;

        String player = chat.substring(1, chat.indexOf(">"));
        String message = chat.substring(chat.indexOf(">") + 1).toLowerCase();

        if (!player.equals(mc.player.getName()) && message.contains("rosie")) {
            if (message.contains("follow me")) {
                mc.player.sendChatMessage("Ok, " + player + ", I will follow you.");
                baritone.getCommandManager().execute("follow player " + player);
            }

            if (message.contains("stop")) {
                baritone.getFollowProcess().cancel();
            }
        }
    }
}
