package strigops.account.features.identity.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import strigops.account.features.identity.entity.SosialAccounts;

@Repository
public interface SosialAccountsRepository extends JpaRepository<SosialAccounts, UUID> {

    Optional<SosialAccounts> findByProviderAndProviderUserId(String provider, String providerUserId);

    boolean existsByProviderAndProviderUserId(String provider, String providerUserId);
}
