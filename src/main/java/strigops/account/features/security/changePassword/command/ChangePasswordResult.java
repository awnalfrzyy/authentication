package strigops.account.features.security.changePassword.command;

public record ChangePasswordResult(
        String email,
        boolean success
        ) {

}
