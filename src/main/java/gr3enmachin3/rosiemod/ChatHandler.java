package gr3enmachin3.rosiemod;

import baritone.api.BaritoneAPI;
import baritone.api.command.manager.ICommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ChatHandler {
    @SubscribeEvent
    public static void readChat(ClientChatReceivedEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        ICommandManager baritone = BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager();

        String chat = event.getMessage().getUnformattedText();
        if (!chat.contains(">")) return;

        String player = chat.substring(1, chat.indexOf(">"));
        String message = chat.substring(chat.indexOf(">") + 1).toLowerCase();

        String command = "";

        if (!player.equals(mc.player.getName()) && message.contains("rosie")) {
            if (message.contains("follow me")) {
                mc.player.sendChatMessage("Ok, " + player + ", I will follow you.");
                command += "follow player " + player;
                baritone.execute(command);
                return;
            }

            if (message.contains("get") || message.contains("bring")) {
                command += "mine ";
                if (message.contains("wood") || message.contains("logs")) {
                    command += "log ";

                    String num = message.replaceAll("\\D+","");
                    if (!num.equals("")) {
                        command += num;
                    } else {
                        command += 16;
                    }
                    baritone.execute(command);

                    Item log = Item.getItemById(17);
                    ItemStack logStack = new ItemStack(log);
                    mc.player.inventory.hasItemStack(logStack);
                    while (mc.player.inventory.getSlotFor(new ItemStack(log))) {

                    }
                }
                return;
            }

            if (message.contains("stop")) {
                command = "cancel";
                baritone.execute(command);
            }
        }
    }
}
