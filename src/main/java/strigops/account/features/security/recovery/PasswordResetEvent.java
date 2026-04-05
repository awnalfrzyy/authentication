package strigops.account.features.security.recovery;

import java.time.LocalDateTime;

public record PasswordResetEvent(
        String email,
        String name,
        LocalDateTime timstamp
) {
}
