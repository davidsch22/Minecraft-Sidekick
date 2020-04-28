package com.gr3enmachin3.rosiemod.processes;

import com.gr3enmachin3.rosiemod.RosieMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=RosieMod.MOD_ID, bus=EventBusSubscriber.Bus.FORGE)
public class GatherTask extends Task {
    private static String blockName;
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
        player.sendChatMessage("Ok, " + requester + ", I'll be back with " + desiredAmount + " " + blockName + "s");

        // player.world.getBiomeManager().getBiome(player.getPosition())
        Biome thisBiome = player.world.func_225523_d_().func_226836_a_(player.getPosition());
        //if (thisBiome == Biomes.) {
            // TODO: Set the specific type of log depending on current biome
        //}

        blockName = "oak_" + blockName; // Temporary until specific type can be set

        String command = "mine " + desiredAmount + " " + blockName;

        FollowTask.isFollowing = false;
        isGathering = true;
        baritone.execute(command);
        oldRequester = requester;
    }

    @SubscribeEvent
    public static void tick(TickEvent.PlayerTickEvent event) {
        if (isGathering) {
            ItemStack itemStack = player.inventory.mainInventory.stream().filter(selStack ->
                    selStack.getItem().getRegistryName().getPath().equals(blockName)).findFirst().orElse(null);
            if (itemStack == null) {
                itemStack = player.inventory.offHandInventory.stream().filter(selStack ->
                        selStack.getItem().getRegistryName().getPath().equals(blockName)).findFirst().orElse(null);
            }
            if (itemStack == null) return;

            slot = player.inventory.getSlotFor(itemStack);
            if (slot != -1 && player.inventory.getStackInSlot(slot).getCount() >= desiredAmount) {
                isGathering = false;
                comeBack();
            }
        }

        if (isReturning) {
            PlayerEntity reqPlayer = player.world.getPlayers().stream().filter(entPlayer ->
                    entPlayer.getName().getString().equals(requester)).findFirst().orElse(null);
            if (reqPlayer != null) {
                FollowTask.isFollowing = true;

                double distance = player.getPositionVector().distanceTo(reqPlayer.getPositionVector());
                if (distance <= 4) {
                    isReturning = false;
                    baritone.execute("stop");
                    dropStack();
                }
            }
        }
    }

    private static void comeBack() {
        player.sendChatMessage("I have what you wanted, " + requester + ". I'm coming back");
        isReturning = true;
        baritone.execute("follow player " + requester);
    }

    private static void dropStack() {
        player.sendChatMessage("Here you go, " + requester);

        FollowTask.isFollowing = false;

        InventoryScreen invScreen = new InventoryScreen(player);
        Minecraft.getInstance().displayGuiScreen(invScreen);

        if (slot >= 0 && slot <= 8) {
            slot += 36;
        }
        ItemStack stack = Minecraft.getInstance().playerController.windowClick(invScreen.getContainer().windowId, slot, 1, ClickType.THROW, player);
        player.inventory.setItemStack(stack);
        player.closeScreen();
    }
}
