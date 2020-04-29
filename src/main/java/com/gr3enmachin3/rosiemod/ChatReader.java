package com.gr3enmachin3.rosiemod;

import com.gr3enmachin3.rosiemod.tasks.*;
import com.gr3enmachin3.rosiemod.tasks.Task;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=RosieMod.MOD_ID, bus=EventBusSubscriber.Bus.FORGE)
public class ChatReader {
    @SubscribeEvent
    public static void readChat(ClientChatReceivedEvent event) {
        String chat = event.getMessage().getString();

        if (!chat.contains(">")) return;

        String speaker = chat.substring(1, chat.indexOf(">"));
        String message = chat.substring(chat.indexOf(">") + 1).toLowerCase();

        if (!speaker.equals("MSOE_Rosie") && message.contains("rosie")) {
            Task task = null;

            // Follow command
            if (message.contains("follow me")) {
                task = new FollowTask(speaker);
            }

            // Gather command
            else if (message.contains("get") || message.contains("bring")) {
                if (message.contains("wood") || message.contains("logs")) {
                    String num = message.replaceAll("\\D+","");
                    if (!num.equals("")) {
                        task = new GatherTask(speaker, "log", Integer.parseInt(num));
                    } else {
                        task = new GatherTask(speaker, "log");
                    }
                }
            }

            // Stop previous command
            else if (message.contains("stop")) {
                task = new CancelTask(speaker);
            }

            if (task != null) {
                if (Task.taskIsRunning()) {
                    if (!Task.isSameRequester()) return;
                    if (!(task instanceof CancelTask)) new CancelTask(speaker).run(true);
                }
                task.run();
            }
        }
    }
}
