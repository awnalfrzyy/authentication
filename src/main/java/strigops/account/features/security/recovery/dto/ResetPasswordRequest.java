package strigops.account.features.security.recovery.dto;

public record ResetPasswordRequest(
        String token,
        String newPassword
        ) {

}
