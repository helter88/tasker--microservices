//package com.tasker.project.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.io.IOException;
//import io.jsonwebtoken.security.Keys;
//import jakarta.annotation.PostConstruct;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.lang.NonNull;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final String jwtSecret;
//    private static Key key;
//
//    @PostConstruct
//    public void init() {
//        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
//        key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public JwtAuthenticationFilter(String jwtSecret) {
//        this.jwtSecret = jwtSecret;
//    }
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
//            throws ServletException, IOException, java.io.IOException {
//        try {
//            String jwt = getJwtFromRequest(request);
//
//
//            if (jwt != null && validateToken(jwt)) {
//                Claims claims = getClaimsFromJWT(jwt);
//                String username = claims.getSubject();
//                List<SimpleGrantedAuthority> authorities = getAuthoritiesFromClaims(claims);
//
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        username, null, authorities);
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        } catch (Exception ex) {
//            logger.error("Could not set user authentication in security context", ex);
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//    private List<SimpleGrantedAuthority> getAuthoritiesFromClaims(Claims claims) {
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        Object rolesObject = claims.get("roles");
//        if (rolesObject instanceof Collection<?>) {
//            for (Object roleObject : (Collection<?>) rolesObject) {
//                if (roleObject instanceof String) {
//                    authorities.add(new SimpleGrantedAuthority((String) roleObject));
//                }
//            }
//        }
//        return authorities;
//    }
//
//    private String getJwtFromRequest(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    private boolean validateToken(String authToken) {
//        try {
//            Jwts.parser().verifyWith((SecretKey) key).build().parseSignedClaims(authToken);
//            return true;
//        } catch (Exception ex) {
//            logger.error("Invalid JWT token", ex);
//        }
//        return false;
//    }
//
//    private String getUsernameFromJWT(String token) {
//        return Jwts.parser()
//                .verifyWith((SecretKey) key)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload()
//                .getSubject();
//    }
//
//    private Claims getClaimsFromJWT(String token) {
//        return Jwts.parser()
//                .verifyWith((SecretKey)key)
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//    }
//}