package gr3enmachin3.rosiemod;

import baritone.api.BaritoneAPI;
import baritone.api.command.manager.ICommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
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
                    mc.player.sendChatMessage("Ok, " + player + ", I'll be back with what you need.");

                    command += "log ";

                    String num = message.replaceAll("\\D+","");
                    int amount;
                    if (!num.equals("")) {
                        amount = Integer.parseInt(num);
                    } else {
                        amount = 16;
                    }
                    command += amount;
                    baritone.execute(command);

                    Item log = Item.getItemById(17);
                    ItemStack logStack = new ItemStack(log);
                    int slot = mc.player.inventory.getSlotFor(logStack);
                    while (mc.player.inventory.getStackInSlot(slot).getCount() < amount) {
                        // Do nothing
                    }

                    mc.player.sendChatMessage("/tp " + player);
                    mc.player.sendChatMessage("Here you go, " + player + ".");
                    EntityItem logEntity = new EntityItem(mc.player.world, mc.player.posX, mc.player.posY+1, mc.player.posZ, logStack);
                    logEntity.setDefaultPickupDelay();
                    mc.player.world.spawnEntity(logEntity);
                    mc.player.inventory.setInventorySlotContents(slot, null);
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
