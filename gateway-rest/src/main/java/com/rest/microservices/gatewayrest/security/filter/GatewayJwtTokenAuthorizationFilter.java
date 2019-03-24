package com.rest.microservices.gatewayrest.security.filter;

import com.netflix.zuul.context.RequestContext;
import com.nimbusds.jwt.SignedJWT;
import com.rest.microservices.core.property.JwtConfiguration;
import com.rest.microservices.token.security.filter.JwtTokenAuthorizationFilter;
import com.rest.microservices.token.security.token.converter.TokenConverter;
import com.rest.microservices.token.security.util.SecurityContextUtil;
import lombok.SneakyThrows;
import org.springframework.lang.NonNull;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GatewayJwtTokenAuthorizationFilter extends JwtTokenAuthorizationFilter {

    public GatewayJwtTokenAuthorizationFilter(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration, tokenConverter);
    }

    /**
     * @noinspection Duplicates
     */
    @Override
    @SneakyThrows
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(jwtConfiguration.getHeader().getName());
        if (authorizationHeader == null || !authorizationHeader.startsWith(jwtConfiguration.getHeader().getPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(jwtConfiguration.getHeader().getPrefix(), "");

        String signedToken = tokenConverter.decryptToken(token);
        tokenConverter.validateTokenSignature(signedToken);

        SecurityContextUtil.setSecurityContext(SignedJWT.parse(signedToken));

        if (jwtConfiguration.getType().equalsIgnoreCase("signed")) {
            RequestContext.getCurrentContext().addZuulRequestHeader(
                    jwtConfiguration.getHeader().getName(),
                    jwtConfiguration.getHeader().getPrefix().concat(signedToken));
        }

        filterChain.doFilter(request, response);
    }


}
