package utilities;

import io.qameta.allure.Allure;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AllureLogger {
    public StringBuilder stringBuilder;

    public AllureLogger() {
        stringBuilder = new StringBuilder();
    }

    public void info(String message) {
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a");
        stringBuilder
                .append("\n " + "**************")
                .append(timeFormatter.format(time))
                .append("*************" + "\n ")
                .append(message + "\n ");
    }

    public void addAttachmentAllure() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter shortDateTime = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        Allure.addAttachment("Логирование " + shortDateTime.format(date), String.valueOf(stringBuilder));
        stringBuilder.delete(0, stringBuilder.length());
    }
}
