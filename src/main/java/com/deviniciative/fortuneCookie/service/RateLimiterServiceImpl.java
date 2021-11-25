package com.deviniciative.fortuneCookie.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterServiceImpl implements RateLimiterService{

   Map<String, Bucket> bucketCache = new ConcurrentHashMap<>();

    @Override
    public Bucket resolveBucket(String chaveApi) {
        return bucketCache.computeIfAbsent(chaveApi, this::newBucket);
    }

    private Bucket newBucket(String s) {
        return Bucket4j
                .builder()
                .addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1))))
                .build();
    }
}
