package strigops.account.features.identity.entity;

import jakarta.persistence.*;
import lombok.*;
import strigops.account.features.identity.entity.UsersEntity;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_sessions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsersSession {
    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UsersEntity user;

    private String refreshToken;
    private String userAgent;
    private String ipAddress;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime lastActivityAt;

    private boolean revoked;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiresAt) || revoked;
    }
}
