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
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class ChatHandler {
    private static Minecraft mc = Minecraft.getMinecraft();
    private static ICommandManager baritone = BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager();

    private static String requester;

    private static boolean isGetActive;
    private static int bringBlockId;
    private static int desiredAmount;

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (isGetActive) {
            Item item = Item.getItemById(bringBlockId);
            ItemStack itemStack = new ItemStack(item);
            int slot = mc.player.inventory.getSlotFor(itemStack);
            if (mc.player.inventory.getStackInSlot(slot).getCount() >= desiredAmount) {
                isGetActive = false;
                bringBack(slot, itemStack);
            }
        }
    }

    @SubscribeEvent
    public static void readChat(ClientChatReceivedEvent event) {
        String chat = event.getMessage().getUnformattedText();
        if (!chat.contains(">")) return;

        requester = chat.substring(1, chat.indexOf(">"));
        String message = chat.substring(chat.indexOf(">") + 1).toLowerCase();

        if (!requester.equals(mc.player.getName()) && message.contains("rosie")) {
            if (message.contains("follow me")) {
                mc.player.sendChatMessage("Ok, " + requester + ", I will follow you.");
                baritone.execute("follow player " + requester);
                return;
            }

            if (message.contains("get") || message.contains("bring")) {
                String command = "mine ";
                if (message.contains("wood") || message.contains("logs")) {
                    mc.player.sendChatMessage("Ok, " + requester + ", I'll be back with what you need.");

                    bringBlockId = 17;
                    command += "log ";

                    String num = message.replaceAll("\\D+","");
                    if (!num.equals("")) {
                        desiredAmount = Integer.parseInt(num);
                    } else {
                        desiredAmount = 16;
                    }
                    command += desiredAmount;

                    baritone.execute(command);
                    isGetActive = true;
                }
                return;
            }

            if (message.contains("stop")) {
                baritone.execute("stop");
            }
        }
    }

    private static void bringBack(int slot, ItemStack stack) {
        mc.player.sendChatMessage("/tp " + requester);
        mc.player.sendChatMessage("Here you go, " + requester + ".");
        EntityItem logEntity = new EntityItem(mc.player.world, mc.player.posX, mc.player.posY+1, mc.player.posZ, stack);
        logEntity.setDefaultPickupDelay();
        mc.player.world.spawnEntity(logEntity);
        mc.player.inventory.setInventorySlotContents(slot, null);
    }
}
