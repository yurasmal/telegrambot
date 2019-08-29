package by.ps.rstelegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.BotApiMethod;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
@PropertySource("classpath:bot.properties")
public class WebHookBot extends TelegramWebhookBot {

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Value("${api.url.prefix}")
    private String apiUrlPrefix;

    @Autowired
    ServerResponse serverResponse;

    @PostConstruct
    public void initBot() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId().toString());
            String chatId = update.getMessage().getChatId().toString();

            String msg = update.getMessage().getText();

            if (msg.equals("/start")) {
                sendMessage.setText("Hello! Please enter the city name in chat " +
                        "For example: \"Moscow\" or \"London\"");
            } else {
                String text = this.serverResponse.getResponse(apiUrlPrefix + msg);
                sendMessage.setText(text);
            }

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            return sendMessage;
        }
        return null;
    }

    public synchronized void sendMsg(String chatId, String text) {

    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return "https://guidetelegrambot.herokuapp.com/";
    }
}
