package by.ps.rstelegrambot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.generics.WebhookBot;

@RestController
@RequestMapping("/")
public class WebHookController {

    @Autowired
    WebhookBot webhookBot;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void telegramUpdates(@RequestBody Update update) {

        webhookBot.onWebhookUpdateReceived(update);
    }

}
