package gr3enmachin3.rosiemod.processes;

import net.minecraft.inventory.ClickType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import java.util.Optional;

@Mod.EventBusSubscriber
public class GatherProcess extends Process {
    private String blockName;
    private int blockId;
    private int desiredAmount;
    private boolean isRunning;

    public GatherProcess(String requester, String blockName) {
        this(requester, blockName, 16);
    }

    public GatherProcess(String requester, String blockName, int desiredAmount) {
        super.requester = requester;
        this.blockName = blockName;
        blockId = 17; // TODO Set to blockName equivalent
        this.desiredAmount = desiredAmount;
    }

    @Override
    public void run() {
        player.sendChatMessage("Ok, " + requester + ", I'll be back with " + desiredAmount + " " + blockName + "s");

        String command = "mine " + desiredAmount + " " + blockName;
        baritone.execute(command);

        isRunning = true;
    }

    @SubscribeEvent
    public void tick(TickEvent.PlayerTickEvent event) {
        if (isRunning) {
            Item item = Item.getItemById(blockId);
            ItemStack itemStack = new ItemStack(item);
            int slot = player.inventory.getSlotFor(itemStack);
            if (slot != -1 && player.inventory.getStackInSlot(slot).getCount() >= desiredAmount) {
                isRunning = false;
                bringBack(slot, itemStack);
            }
        }
    }

    private void bringBack(int slot, ItemStack stack) {
        try {
            player.sendChatMessage("/tp " + requester);
            Thread.sleep(500);

            player.sendChatMessage("Here you go, " + requester);

            Optional<ItemStack> validStackMain = player.inventory.mainInventory.stream().filter(selStack -> Item.getIdFromItem(selStack.getItem()) == blockId).findFirst();
            Optional<ItemStack> validStackOffhand = player.inventory.offHandInventory.stream().filter(selStack -> Item.getIdFromItem(selStack.getItem()) == blockId).findFirst();
            if (validStackMain.isPresent()) {
                player.inventory.currentItem = slot;
            } else if (validStackOffhand.isPresent()) {
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
        } catch (InterruptedException e) {
            // Do nothing. Thread.sleep() requires this.
        }
    }

    public void setIsRunning(boolean running) {
        isRunning = running;
    }
}
