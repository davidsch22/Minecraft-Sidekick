package gr3enmachin3.rosiemod;

import baritone.api.BaritoneAPI;
import baritone.api.command.manager.ICommandManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.Optional;

@Mod.EventBusSubscriber
public class ChatHandler {
    private static EntityPlayerSP player = Minecraft.getMinecraft().player;
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
            int slot = player.inventory.getSlotFor(itemStack);
            if (slot != -1 && player.inventory.getStackInSlot(slot).getCount() >= desiredAmount) {
                isGetActive = false;
                bringBack(slot, itemStack);
            }
        }
    }

    @SubscribeEvent
    public static void readChat(ClientChatReceivedEvent event) {
        String chat = event.getMessage().getUnformattedText();
        if (!chat.contains(">")) return;

        String speaker = chat.substring(1, chat.indexOf(">"));
        String message = chat.substring(chat.indexOf(">") + 1).toLowerCase();

        if (!speaker.equals(player.getName()) && message.contains("rosie")) {
            requester = speaker;

            if (message.contains("follow me")) {
                player.sendChatMessage("Ok, " + requester + ", I will follow you.");
                baritone.execute("follow player " + requester);
                return;
            }

            if (message.contains("get") || message.contains("bring")) {
                String command = "mine ";
                if (message.contains("wood") || message.contains("logs")) {
                    player.sendChatMessage("Ok, " + requester + ", I'll be back with what you need");

                    String num = message.replaceAll("\\D+","");
                    if (!num.equals("")) {
                        desiredAmount = Integer.parseInt(num);
                    } else {
                        desiredAmount = 16;
                    }
                    command += desiredAmount + " ";

                    bringBlockId = 17;
                    command += "log";

                    baritone.execute(command);
                    isGetActive = true;
                }
                return;
            }

            if (message.contains("stop")) {
                isGetActive = false;
                player.sendChatMessage("Ok");
                baritone.execute("stop");
            }
        }
    }

    private static void bringBack(int slot, ItemStack stack) {
        try {
            player.sendChatMessage("/tp " + requester);
            Thread.sleep(500);
            player.sendChatMessage("Here you go, " + requester);
            Optional<ItemStack> validStack = player.inventory.mainInventory.stream().filter(selStack -> selStack.getDisplayName().equals(stack.getDisplayName())).findFirst();
            if (validStack.isPresent()) {
                player.inventory.currentItem = slot;
            } else {
                // TODO if stack is not in the hotbar
            }

            Thread sideTask = new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(2000);
                        player.dropItem(true);
                    } catch (InterruptedException e) {
                        // Do nothing. Thread.sleep() requires this.
                    }
                }
            };
            sideTask.start();
        } catch (InterruptedException e) {
            // Do nothing. Thread.sleep() requires this.
        }
    }
}
