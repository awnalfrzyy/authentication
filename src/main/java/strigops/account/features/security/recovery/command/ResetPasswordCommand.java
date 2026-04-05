package strigops.account.features.security.recovery.command;

public record ResetPasswordCommand(
        String token,
        String newPassword
        ) {

}
