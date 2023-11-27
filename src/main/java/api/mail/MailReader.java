package api.mail;

import javax.mail.*;
import java.util.Properties;

public class MailReader {

    public static Message[] fetchLast5Messages(String user, String password) throws MessagingException {
        String host = "imap.rambler.ru";
        Properties properties = new Properties();
        properties.put("mail.store.protocol", "imaps");

        Session emailSession = Session.getDefaultInstance(properties);
        Store store = emailSession.getStore();
        store.connect(host, user, password);

        Folder emailFolder = store.getFolder("INBOX");
        emailFolder.open(Folder.READ_WRITE);

        int end = emailFolder.getMessageCount();
        int start = end - 5 + 1;

        return emailFolder.getMessages(start, end);
    }
}
