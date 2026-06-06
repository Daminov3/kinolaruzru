package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "YOUR_BOT_USERNAME";
    }

    @Override
    public String getBotToken() {
        return "YOUR_BOT_TOKEN";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String text = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();

            String fileId = VideoDB.getVideo(text);

            if (fileId != null) {

                SendVideo video = new SendVideo();
                video.setChatId(chatId);
                video.setVideo(new InputFile(fileId));

                try {
                    execute(video);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                SendMessage msg = new SendMessage();
                msg.setChatId(chatId);
                msg.setText("❌ Video topilmadi");

                try {
                    execute(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
