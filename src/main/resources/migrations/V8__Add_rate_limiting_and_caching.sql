
CREATE TABLE IF NOT EXISTS rate_limits (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    key VARCHAR(255) NOT NULL UNIQUE,
    limit_per_minute INTEGER NOT NULL DEFAULT 60,
    limit_per_hour INTEGER NOT NULL DEFAULT 1000,
    limit_per_day INTEGER NOT NULL DEFAULT 10000,
    active BOOLEAN DEFAULT true NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS rate_limit_hits (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    rate_limit_key VARCHAR(255) NOT NULL,
    ip_address VARCHAR(45),
    user_id UUID,
    hit_count INTEGER DEFAULT 1 NOT NULL,
    window_start TIMESTAMP NOT NULL,
    window_type VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    CONSTRAINT fk_rate_limit_hits_user_id 
        FOREIGN KEY (user_id) REFERENCES users_entity(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS cache_entries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    cache_key VARCHAR(500) NOT NULL UNIQUE,
    cache_value TEXT,
    cache_type VARCHAR(50) DEFAULT 'GENERAL',
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    accessed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    access_count INTEGER DEFAULT 1
);

INSERT INTO rate_limits (key, limit_per_minute, limit_per_hour, limit_per_day, description) VALUES 
    ('LOGIN_ATTEMPTS', 5, 20, 100, 'Login attempts per time window'),
    ('PASSWORD_RESET', 3, 10, 50, 'Password reset requests'),
    ('EMAIL_VERIFICATION', 5, 20, 100, 'Email verification requests'),
    ('OTP_SEND', 3, 10, 50, 'OTP send requests'),
    ('API_CALLS', 60, 1000, 10000, 'General API calls')
ON CONFLICT (key) DO NOTHING;

CREATE INDEX IF NOT EXISTS idx_rate_limits_key ON rate_limits(key);
CREATE INDEX IF NOT EXISTS idx_rate_limits_active ON rate_limits(active);

CREATE INDEX IF NOT EXISTS idx_rate_limit_hits_key_window ON rate_limit_hits(rate_limit_key, window_type, window_start);
CREATE INDEX IF NOT EXISTS idx_rate_limit_hits_ip_address ON rate_limit_hits(ip_address);
CREATE INDEX IF NOT EXISTS idx_rate_limit_hits_user_id ON rate_limit_hits(user_id);
CREATE INDEX IF NOT EXISTS idx_rate_limit_hits_created_at ON rate_limit_hits(created_at);

CREATE INDEX IF NOT EXISTS idx_cache_entries_key ON cache_entries(cache_key);
CREATE INDEX IF NOT EXISTS idx_cache_entries_type ON cache_entries(cache_type);
CREATE INDEX IF NOT EXISTS idx_cache_entries_expires_at ON cache_entries(expires_at);
CREATE INDEX IF NOT EXISTS idx_cache_entries_accessed_at ON cache_entries(accessed_at);

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'update_rate_limits_updated_at') THEN
        CREATE TRIGGER update_rate_limits_updated_at 
            BEFORE UPDATE ON rate_limits 
            FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();
    END IF;
END $$;

CREATE OR REPLACE FUNCTION clean_expired_cache()
RETURNS void AS $$
BEGIN
    DELETE FROM cache_entries WHERE expires_at < CURRENT_TIMESTAMP;
END;
$$ LANGUAGE plpgsql;

CREATE INDEX IF NOT EXISTS idx_cache_entries_expires_cleanup ON cache_entries(expires_at);
