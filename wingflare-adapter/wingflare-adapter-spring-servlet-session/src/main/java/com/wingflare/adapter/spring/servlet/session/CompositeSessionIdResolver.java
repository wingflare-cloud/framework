package com.wingflare.adapter.spring.servlet.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.session.web.http.HttpSessionIdResolver;

import java.util.List;
import java.util.stream.Stream;


public class CompositeSessionIdResolver implements HttpSessionIdResolver {

    private final HttpSessionIdResolver[] resolvers;

    public CompositeSessionIdResolver(HttpSessionIdResolver... resolvers) {
        this.resolvers = resolvers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        return Stream.of(this.resolvers)
                .flatMap((resolver) -> resolver.resolveSessionIds(request).stream())
                .distinct()
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        for (HttpSessionIdResolver resolver : this.resolvers) {
            resolver.setSessionId(request, response, sessionId);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        for (HttpSessionIdResolver resolver : this.resolvers) {
            resolver.expireSession(request, response);
        }
    }

}
