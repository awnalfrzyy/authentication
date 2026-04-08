package strigops.account.features.identity.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import strigops.account.features.identity.entity.SocialAccounts;

@Repository
public interface SocialAccountsRepository extends JpaRepository<SocialAccounts, UUID> {

    Optional<SocialAccounts> findByProviderAndProviderUserId(String provider, String providerUserId);

    boolean existsByProviderAndProviderUserId(String provider, String providerUserId);
}
