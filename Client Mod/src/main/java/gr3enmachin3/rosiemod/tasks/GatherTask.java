package gr3enmachin3.rosiemod.tasks;

import gr3enmachin3.rosiemod.RosieMod;
import gr3enmachin3.rosiemod.maps.BiomeLogMap;
import gr3enmachin3.rosiemod.maps.BlockItemMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=RosieMod.MOD_ID, bus=EventBusSubscriber.Bus.FORGE)
public class GatherTask extends Task {
    private static String blockName;
    private static String itemName;
    private static int slot;
    private static int desiredAmount;
    protected static boolean isGathering;
    protected static boolean isReturning;

    public GatherTask(String requester, String blockName) {
        this(requester, blockName, 16);
    }

    public GatherTask(String requester, String blockName, int desiredAmount) {
        Task.requester = requester;
        GatherTask.blockName = blockName;
        GatherTask.slot = -1;
        GatherTask.desiredAmount = desiredAmount;
    }

    @Override
    public void run() {
        if (blockName.equals("log")) {
            Holder<Biome> thisBiome = player.level.getBiome(new BlockPos(player.position()));
            String log = BiomeLogMap.get(thisBiome.value());
            if (log.equals("none")) {
                player.chat("Sorry, " + requester + ", we are not in a biome that has logs");
                return;
            }
            blockName = log + blockName;
        }

        itemName = BlockItemMap.get(blockName);
        player.chat("Ok, " + requester + ", I'll be back with " + desiredAmount + " " + itemName + "(s)");

        String command = "mine " + desiredAmount + " " + blockName;

        FollowTask.isFollowing = false;
        isGathering = true;
        baritone.execute(command);
        oldRequester = requester;
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (isGathering) {
            // ItemStack itemStack = player.inventory.mainInventory.stream().filter(selStack ->
            //        selStack.getItem().getRegistryName().getPath().equals(itemName)).findFirst().orElse(null);
            ItemStack itemStack = player.getInventory().items.stream().filter(selStack ->
                    selStack.getItem().getRegistryName().getPath().equals(itemName)).findFirst().orElse(null);
            if (itemStack == null) return;

            slot = player.getInventory().findSlotMatchingItem(itemStack);
            if (slot != -1 && player.inventory.getStackInSlot(slot).getCount() >= desiredAmount) {
                isGathering = false;
                isReturning = true;
                player.chat("I have what you wanted, " + requester + ". I'm coming back");
                baritone.execute("follow player " + requester);
            }
        }

        if (isReturning) {
            Player reqPlayer = player.world.getPlayers().stream().filter(entPlayer ->
                    entPlayer.getName().getString().equals(requester)).findFirst().orElse(null);
            if (reqPlayer != null) {
                double distance = player.getPositionVector().distanceTo(reqPlayer.getPositionVector());
                if (distance <= 4) {
                    isReturning = false;
                    FollowTask.isFollowing = true;
                    baritone.execute("stop");
                    dropStack();
                }
            }
        }
    }

    private static void dropStack() {
        player.chat("Here you go, " + requester);

        InventoryScreen invScreen = new InventoryScreen(player);
        Minecraft.getInstance().displayGuiScreen(invScreen);

        new Thread(() -> {
            try {
                Thread.sleep(1000);

                if (slot >= 0 && slot <= 8) {
                    slot += 36;
                }
                ItemStack stack = Minecraft.getInstance().playerController.windowClick(invScreen.getContainer().windowId, slot, 1, ClickType.THROW, player);
                player.inventory.setItemStack(stack);
                player.closeScreen();

                FollowTask.isFollowing = false;
            } catch (InterruptedException e) {
                // Do nothing. Thread.sleep() requires this.
            }
        }).start();
    }
}
