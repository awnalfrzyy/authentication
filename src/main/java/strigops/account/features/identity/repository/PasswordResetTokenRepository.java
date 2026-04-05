package strigops.account.features.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import strigops.account.features.identity.entity.PasswordResetToken;
import strigops.account.features.identity.entity.UsersEntity;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

    void deleteByUser(UsersEntity user);
}
