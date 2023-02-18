import { Bot } from "mineflayer";
import { checkIfBusy } from './task-base';

export function come(bot: Bot, player: string) {
    checkIfBusy(bot, player);
    bot.chat("Be right there, " + player);
    bot.chat("/tp " + player);
}