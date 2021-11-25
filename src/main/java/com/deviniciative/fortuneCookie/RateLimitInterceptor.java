package com.deviniciative.fortuneCookie;

import com.deviniciative.fortuneCookie.service.RateLimiterService;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RateLimiterService rateLimiterService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String chaveApi = request.getHeader("chave-Api");

        if (chaveApi == null || chaveApi.isEmpty()) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Faltou o header: chave-API");
            return false;
        }

        Bucket bucket = rateLimiterService.resolveBucket(chaveApi);
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            response.addHeader("X-Rate-Limit-Remaining", String.valueOf(probe.getRemainingTokens()));
            return true;
        } else {
            long waitForRefill = probe.getNanosToWaitForRefill() / 1_000_000_000;
            response.addHeader("Rate-Limit-Retry-After-Seconds", String.valueOf(waitForRefill));
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "Você gastou sua Quota de Requests");
            return false;
        }
    }
}
