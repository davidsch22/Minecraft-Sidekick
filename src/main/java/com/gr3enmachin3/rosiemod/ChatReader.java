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

            // Tp Command
            if (message.contains("here") || message.contains("to me")) {
                task = new TpTask(speaker);
            }

            // Follow Command
            else if (message.contains("follow me")) {
                task = new FollowTask(speaker);
            }

            // Gather Command
            else if (message.contains("get") || message.contains("bring")) {
                String num = message.replaceAll("\\D+","");

                if (message.contains("wood") || message.contains("logs")) {
                    if (!num.equals("")) {
                        task = new GatherTask(speaker, "log", Integer.parseInt(num));
                    } else {
                        task = new GatherTask(speaker, "log");
                    }
                } else if (message.contains("coal")) {
                    if (!num.equals("")) {
                        task = new GatherTask(speaker, "coal_ore", Integer.parseInt(num));
                    } else {
                        task = new GatherTask(speaker, "coal_ore");
                    }
                } else if (message.contains("iron")) {
                    if (!num.equals("")) {
                        task = new GatherTask(speaker, "iron_ore", Integer.parseInt(num));
                    } else {
                        task = new GatherTask(speaker, "iron_ore");
                    }
                } else if (message.contains("diamond")) {
                    if (!num.equals("")) {
                        task = new GatherTask(speaker, "diamond_ore", Integer.parseInt(num));
                    } else {
                        task = new GatherTask(speaker, "diamond_ore");
                    }
                }
            }

            // Stop Running Command
            else if (message.contains("stop")) {
                task = new CancelTask(speaker);
            }

            // Execute Command
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
