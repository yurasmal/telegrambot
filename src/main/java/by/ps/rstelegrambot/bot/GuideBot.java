package by.ps.rstelegrambot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;
import javax.annotation.PostConstruct;

@Component
@PropertySource("classpath:bot.properties")
public class GuideBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    @Value("${api.url.prefix}")
    private String apiUrlPrefix;

    @Autowired
    ServerResponse serverResponse;

    static {
        ApiContextInitializer.init();
    }

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
    public void onUpdateReceived(Update update) {

        Message message = update.getMessage();

        if (message != null && message.hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String city = update.getMessage().getText();
            String text = this.serverResponse.getResponse(apiUrlPrefix + city);
            sendMsg(chatId, text);
        }
    }

    private synchronized void sendMsg(String chatId, String text){

        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
