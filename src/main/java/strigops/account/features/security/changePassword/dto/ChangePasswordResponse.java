package strigops.account.features.security.changePassword.dto;

import java.time.LocalDateTime;

public record ChangePasswordResponse(
        String status,
        String message,
        LocalDateTime timestamp
        ) {

    public static ChangePasswordResponse success() {
        return new ChangePasswordResponse(
                "SUCCESS",
                "Password updated successfully",
                LocalDateTime.now()
        );
    }
}
