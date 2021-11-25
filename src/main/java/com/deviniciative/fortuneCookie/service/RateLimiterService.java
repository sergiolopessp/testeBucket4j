package com.deviniciative.fortuneCookie.service;

import io.github.bucket4j.Bucket;

public interface RateLimiterService {
    Bucket resolveBucket(String chaveApi);
}

