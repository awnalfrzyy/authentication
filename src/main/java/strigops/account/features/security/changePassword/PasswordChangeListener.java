package strigops.account.features.security.changePassword;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PasswordChangeListener {

    @EventListener
    @Async
    public void handlePasswordChange(PasswordChangeEvent event) {
        log.info("Sending notification email to:{}", event.getEmail());
    }
}
