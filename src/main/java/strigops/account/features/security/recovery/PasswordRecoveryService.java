package strigops.account.features.security.recovery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import strigops.account.features.security.recovery.command.ResetPasswordCommand;

import strigops.account.features.identity.entity.PasswordResetToken;
import strigops.account.features.identity.entity.UsersEntity;

import strigops.account.features.identity.repository.UsersRepository;
import strigops.account.features.identity.repository.PasswordResetTokenRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordRecoveryService {

    private final PasswordResetTokenRepository tokenRepository;
    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void resetPassword(ResetPasswordCommand command) {
        PasswordResetToken resetToken = tokenRepository.findByToken(command.token())
                .orElseThrow(() -> new IllegalArgumentException("Token tidak valid atau tidak ditemukan"));

        if (!resetToken.isValid()) {
            tokenRepository.delete(resetToken);
            throw new IllegalStateException("Token sudah kedaluwarsa atau sudah pernah digunakan");
        }

        UsersEntity user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(command.newPassword()));

        userRepository.save(user);

        tokenRepository.delete(resetToken);

        eventPublisher.publishEvent(new PasswordResetEvent(
                user.getEmail(),
                user.getUsername(),
                LocalDateTime.now()
        ));

        log.info("Password successfully reset for user ID: {}", user.getId());
    }
}
