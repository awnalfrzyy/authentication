package strigops.account.features.security.changePassword;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// Ganti import ini:
// import jakarta.ws.rs.BadRequestException; <-- HAPUS
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import strigops.account.features.security.changePassword.dto.ChangePasswordRequest;
import strigops.account.features.identity.repository.UsersRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChangePasswordService {

    private final UsersRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void changePassword(ChangePasswordRequest request) {
        // 1. Validasi request (Biasanya sudah dihandle @Valid di Controller, tapi gak apa-apa buat safety)
        if (request == null) {
            throw new IllegalArgumentException("Request body cannot be null");
        }

        log.info("Initiating password change for user: {}", request.email());

        // 2. Cari User
        var user = userRepository.findByEmail(request.email().toLowerCase())
                .orElseThrow(() -> {
                    log.warn("Change password failed: User with email {} not found", request.email());
                    return new RuntimeException("User not found"); // Pake exception yang lebih umum atau custom
                });

        // 3. Cek Password Lama
        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())) {
            log.warn("Change password failed: Incorrect old password for user {}", request.email());
            throw new RuntimeException("Old password incorrect");
        }

        // 4. Cek Password Baru (Jangan sama dengan yang lama)
        if (passwordEncoder.matches(request.newPassword(), user.getPassword())) {
            throw new RuntimeException("New password cannot be the same as the old password");
        }

        // 5. Update & Save
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        // 6. Publish Event (Bagus buat kirim notifikasi "Password Changed" ke email)
        eventPublisher.publishEvent(new PasswordChangeEvent(this, user.getEmail()));

        log.info("Password successfully changed for user: {}", user.getEmail());
    }
}