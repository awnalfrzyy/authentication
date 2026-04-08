package strigops.account.internal.infrastructure.security;

import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitService {
    
   private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

   public boolean tryConsume(String key, int capacity, int refillTokens, Duration refillPeriod) {
       Bucket bucket = buckets.computeIfAbsent(key, k -> createBucket(capacity, refillTokens, refillPeriod));
       return bucket.tryConsume(1);
   }
   
   public boolean tryConsume(String key){
    return tryConsume(key, 10, 10, Duration.ofMinutes(1));
   }

   private Bucket createBucket(int capacity, int refillTokens, Duration refillPeriod) {
        Bandwidth limit = Bandwidth.classic(capacity, Refill.greedy(refillTokens, refillPeriod));
        return Bucket.builder().addLimit(limit).build();
   }
}
