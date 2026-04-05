package strigops.account.features.security.recovery;

import java.time.LocalDateTime;
public record ForgotPasswordRequestEvent(
        String email,
        String token,
        LocalDateTime requestedAt
) {
    public ForgotPasswordRequestEvent(
            String email,
            String token
    ){
        this(email,token,LocalDateTime.now());
    }
}
