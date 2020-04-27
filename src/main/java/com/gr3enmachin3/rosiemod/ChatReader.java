package com.gr3enmachin3.rosiemod;

import baritone.api.BaritoneAPI;
import com.gr3enmachin3.rosiemod.processes.CancelProcess;
import com.gr3enmachin3.rosiemod.processes.FollowProcess;
import com.gr3enmachin3.rosiemod.processes.GatherProcess;
import com.gr3enmachin3.rosiemod.processes.Process;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ChatReader {
    private static Process previousProcess;

    @SubscribeEvent
    public static void readChat(ClientChatReceivedEvent event) {
        String chat = event.getMessage().getString();
        if (!chat.contains(">")) return;

        String speaker = chat.substring(1, chat.indexOf(">"));
        String message = chat.substring(chat.indexOf(">") + 1).toLowerCase();

        if (!speaker.equals("MSOE_Rosie") && message.contains("rosie")) {
            Process process = null;

            // Follow command
            if (message.contains("follow me")) {
                process = new FollowProcess(speaker);
            }
            // Gather command
            else if (message.contains("get") || message.contains("bring")) {
                if (message.contains("wood") || message.contains("logs")) {
                    String num = message.replaceAll("\\D+","");
                    if (!num.equals("")) {
                        process = new GatherProcess(speaker, "log", Integer.parseInt(num));
                    } else {
                        process = new GatherProcess(speaker, "log");
                    }
                }
            }
            // Stop previous command
            else if (message.contains("stop")) {
                process = new CancelProcess();
                if (previousProcess instanceof GatherProcess) {
                    ((GatherProcess)previousProcess).setIsGathering(false);
                }
            }

            if (process != null) {
                previousProcess = process;
                process.run();
            }
        }
    }

    @SubscribeEvent
    public static void initBaritone(TickEvent.WorldTickEvent event) {
        if (Process.baritone == null) {
            Process.baritone = BaritoneAPI.getProvider().getPrimaryBaritone().getCommandManager();
        }
    }
}
