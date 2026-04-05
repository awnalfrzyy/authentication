package strigops.account.features.security.recovery.dto;

public record ResetPasswordResponse(
        String accessToken,
        String message
        ) {

}
