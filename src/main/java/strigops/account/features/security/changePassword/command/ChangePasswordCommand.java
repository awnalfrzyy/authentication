package strigops.account.features.security.changePassword.command;

public record ChangePasswordCommand(
        String email,
        String oldPassword,
        String newPassword
        ) {

}
