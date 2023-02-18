import { Bot } from "mineflayer";
import { checkIfBusy, getBusy, setBusy } from "./task-base";

function follow(bot: Bot, player: string) {
    checkIfBusy(bot, player);
    setBusy(true);
    while (getBusy()) {
        bot.lookAt(bot.players[player].entity.position);
        setBusy(false);
    }
}