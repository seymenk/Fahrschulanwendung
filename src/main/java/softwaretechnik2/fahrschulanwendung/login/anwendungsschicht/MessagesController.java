package softwaretechnik2.fahrschulanwendung.login.anwendungsschicht;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController {
	
	private final Logger logger = LoggerFactory.getLogger(MessagesController.class);

    @GetMapping("/messages")
    public Map<String, String> getMessages() {
        ResourceBundle mybundle = ResourceBundle.getBundle("messages");
        Map<String, String> messages = new HashMap<>();

        Enumeration<String> keys = mybundle.getKeys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            messages.put(key, mybundle.getString(key));
        }
        logger.info("Übersetzung der Seite wurde aufgerufen.");
        return messages;
    }
}
