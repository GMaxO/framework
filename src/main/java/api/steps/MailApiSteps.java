package api.steps;


import asserts.CustomAssert;
import exceptions.MessageNotReceivedException;
import io.qameta.allure.Step;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static api.mail.MailReader.fetchLast5Messages;

public class MailApiSteps {

    @Step("Получаем код из почты {channel}")
    public String getLastCodeWithSubject(String channel, String password, String subject, LocalDateTime date) throws IOException, MessagingException, InterruptedException {
        Document document = getLastDocumentFromMailAfterNowDate(channel, password, subject, date);
        String code = document.getElementsByTag("strong").text();
        System.out.println("Получен код: " + code);
        CustomAssert.assertNotEquals(code, "", "получили код из почты");
        return code;
    }

    @Step("Получаем код из почты {channel}")
    public String getLastCodeWithSubjectRambler(String channel, String password, String subject, LocalDateTime date) throws IOException, MessagingException, InterruptedException {
        Document document = getLastDocumentFromMailAfterNowDate(channel, password, subject, date);
        String code = document.getElementsContainingOwnText("Пароль:").text();
        System.out.println(code);
        CustomAssert.assertNotEquals(code, "", "получили код из почты");
        return code;
    }

    @Step("Получаем ссылку из почты {channel}")
    public String getLastUrlWithSubject(String channel, String password, String subject, LocalDateTime date) throws IOException, MessagingException, InterruptedException {
        Document document = getLastDocumentFromMailAfterNowDate(channel, password, subject, date);
        String url = document.getElementsMatchingOwnText("Открыть").attr("href");
        System.out.println("Получен URL: " + url);
        CustomAssert.assertNotEquals(url, "", "получили ссылку из почты");
        return url;
    }

    @Step("Получаем ссылку из почты {channel}")
    public String getLastUrlWithSubjectForWidgetPayments(String channel, String password, String subject, LocalDateTime date) throws IOException, MessagingException, InterruptedException {
        Document document = getLastDocumentFromMailAfterNowDate(channel, password, subject, date);
        String url = document.getElementsMatchingOwnText("Перейти к ИПР").attr("href");
        System.out.println("Получен URL: " + url);
        CustomAssert.assertNotEquals(url, "", "получили ссылку из почты");
        return url;
    }

    private Document getLastDocumentFromMailAfterNowDate(String channel, String password, String subject, LocalDateTime date) throws IOException, MessagingException, InterruptedException {
        Message message = null;
        boolean messageFound = false;
        int count = 0;
        while (!messageFound && count++ < 4) {
            System.out.println("--- Ждём три секунды и получаем сообщения попытка №" + count + " -----");
            Thread.sleep(3000);
            Message[] messages = fetchLast5Messages(channel, password);
            for (int i = 0; i < messages.length; i++) {
                message = messages[i];
                System.out.println(message.getReceivedDate() + ": " + message.getSubject());
                messageFound = messageFound(message, subject, date);
                if (messageFound) {
                    System.out.println("Сообщение подходит!");
                    break;
                }
                System.out.println("Сообщение НЕ подходит!");
                System.out.println("!!!!!!!!ИСКАЛИ: " + date + " " + subject);
            }
        }
        if (count > 4) {
            throw new MessageNotReceivedException("Письмо не пришло!");
        }
        String html = message.getContent().toString();
        return Jsoup.parse(html);
    }

    private boolean messageFound(Message message, String subject, LocalDateTime date) throws MessagingException {
        Date messageDate = message.getReceivedDate();
        LocalDateTime messageLocalDateTime = convertToLocalDateTimeViaInstant(messageDate);
        String messageSubject = message.getSubject().trim();
        boolean isUnread = !message.isSet(Flags.Flag.SEEN);
        return messageSubject.equals(subject) && messageLocalDateTime.isAfter(date) && isUnread;
    }

    private LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
