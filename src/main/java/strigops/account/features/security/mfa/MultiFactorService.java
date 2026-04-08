package strigops.account.features.security.mfa;

import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import strigops.account.features.identity.repository.UsersRepository;
import strigops.shared.exceptions.UserNotFoundException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MultiFactorService {
    @Autowired
    UsersRepository userRepository;

    public String generateNewSecret() {
        return Base32.random();
    }

    public boolean verifyCode(String secret, String code) {
        if (secret == null || code == null)
            return false;
        try {
            Totp totp = new Totp(secret);
            return totp.verify(code);
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public void enableMfa(UUID userId, String secret, String code) {
        if (verifyCode(secret, code)) {
            var user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
            user.setMfaSecret(secret);
            user.setMfaEnable(true);
            userRepository.save(user);
        } else {
            throw new IllegalArgumentException("Code MFA not valid!");
        }
    }
}
