package strigops.account.features.identity.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import strigops.account.features.identity.entity.UsersEntity;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {

    Optional<UsersEntity> findByEmail(String email);

    @Override
    @SuppressWarnings("unchecked")
    UsersEntity save(UsersEntity user);

    boolean existsByEmail(String email);
}
