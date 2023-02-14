import { createBot } from 'mineflayer';
import { mineflayer as mineflayerViewer } from 'prismarine-viewer';

const options = {
  host: 'localhost',
  port: 52765,
  username: 'deschulz22@gmail.com',
  password: process.argv[2],
  auth: 'microsoft'
};

const bot = createBot(options);

bot.once('spawn', () => {
    mineflayerViewer(bot, { port: 52766, firstPerson: true }); // Port is the Minecraft server port
});

bot.on('chat', (username, message) => {
    if (username === bot.username) return;
    bot.chat(message);
});

// Log errors and kick reasons:
bot.on('kicked', console.log);
bot.on('error', console.log);
