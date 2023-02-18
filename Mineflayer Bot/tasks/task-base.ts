import { Bot } from "mineflayer";

let isBusy = false;

export function checkIfBusy(bot: Bot, player: string) {
    if (isBusy) {
        bot.chat("Sorry, " + player + ", I'm busy");
    }
}

export function getBusy() {
    return isBusy;
}

export function setBusy(busy: boolean) {
    isBusy = busy;
}