
CREATE TABLE IF NOT EXISTS users_entity (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    username VARCHAR(50),
    active BOOLEAN DEFAULT true NOT NULL,
    mfa_enable BOOLEAN DEFAULT false,
    mfa_secret VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    deleted_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS user_sessions (
    session_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    refresh_token VARCHAR(255),
    user_agent TEXT,
    ip_address VARCHAR(45),
    mfa_verified BOOLEAN DEFAULT false NOT NULL,
    revoked BOOLEAN DEFAULT false NOT NULL,
    mfa_challenge_token VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    last_activity_at TIMESTAMP,
    CONSTRAINT fk_user_sessions_user_id 
        FOREIGN KEY (user_id) REFERENCES users_entity(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_users_entity_email ON users_entity(email);
CREATE INDEX IF NOT EXISTS idx_users_entity_username ON users_entity(username);
CREATE INDEX IF NOT EXISTS idx_users_entity_active ON users_entity(active);
CREATE INDEX IF NOT EXISTS idx_users_entity_created_at ON users_entity(created_at);

CREATE INDEX IF NOT EXISTS idx_user_sessions_user_id ON user_sessions(user_id);
CREATE INDEX IF NOT EXISTS idx_user_sessions_expires_at ON user_sessions(expires_at);
CREATE INDEX IF NOT EXISTS idx_user_sessions_mfa_verified ON user_sessions(mfa_verified);
CREATE INDEX IF NOT EXISTS idx_user_sessions_revoked ON user_sessions(revoked);
CREATE INDEX IF NOT EXISTS idx_user_sessions_last_activity_at ON user_sessions(last_activity_at);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
