package gr3enmachin3.rosiemod.processes;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber
public class GatherProcess extends Process {
    private String blockName;
    private static int blockId;
    private static int desiredAmount;
    private static boolean isGathering;
    private static boolean isReturning;

    public GatherProcess(String requester, String blockName) {
        this(requester, blockName, 16);
    }

    public GatherProcess(String requester, String blockName, int desiredAmount) {
        Process.requester = requester;
        this.blockName = blockName;
        blockId = 17; // TODO Set to blockName equivalent
        GatherProcess.desiredAmount = desiredAmount;
    }

    @Override
    public void run() {
        player.sendChatMessage("Ok, " + requester + ", I'll be back with " + desiredAmount + " " + blockName + "s");

        String command = "mine " + desiredAmount + " " + blockName;
        baritone.execute(command);

        isGathering = true;
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        int slot = -1;

        if (isGathering) {
            Item item = Item.getItemById(blockId);
            ItemStack itemStack = new ItemStack(item);
            slot = player.inventory.getSlotFor(itemStack);
            if (slot != -1 && player.inventory.getStackInSlot(slot).getCount() >= desiredAmount) {
                isGathering = false;
                comeBack();
            }
        }

        if (isReturning) {
            EntityPlayer reqPlayer = player.world.playerEntities.stream().filter(entPlayer -> entPlayer.getName().equals(requester)).findFirst().orElse(null);
            if (reqPlayer != null) {
                double distance = player.getPositionVector().distanceTo(reqPlayer.getPositionVector());
                if (distance <= 3) {
                    isReturning = false;
                    baritone.execute("stop");
                    dropStack(slot);
                }
            }
        }
    }

    private static void comeBack() {
        player.sendChatMessage("I have what you requested, " + requester + ". I'm on my way back.");
        baritone.execute("follow player " + requester);
        isReturning = true;
    }

    private static void dropStack(int slot) {
        player.sendChatMessage("Here you go, " + requester);

        ItemStack validStackMain = player.inventory.mainInventory.stream().filter(selStack -> Item.getIdFromItem(selStack.getItem()) == blockId).findFirst().orElse(null);
        ItemStack validStackOffhand = player.inventory.offHandInventory.stream().filter(selStack -> Item.getIdFromItem(selStack.getItem()) == blockId).findFirst().orElse(null);
        if (validStackMain != null) {
            player.inventory.currentItem = slot;
        } else if (validStackOffhand != null) {
            player.inventory.openInventory(player);
            player.inventoryContainer.slotClick(slot, 0, ClickType.QUICK_MOVE, player);
            player.inventoryContainer.slotClick(8, 0, ClickType.QUICK_MOVE, player);
            player.inventoryContainer.slotClick(slot, 0, ClickType.QUICK_MOVE, player);
            player.inventory.closeInventory(player);
            player.inventory.currentItem = 8;
        }

        new Thread(() -> {
            try {
                Thread.sleep(2000);
                player.dropItem(true);
            } catch (InterruptedException e) {
                // Do nothing. Thread.sleep() requires this.
            }
        }).start();
    }

    public void setIsGathering(boolean running) {
        isGathering = running;
    }
}
