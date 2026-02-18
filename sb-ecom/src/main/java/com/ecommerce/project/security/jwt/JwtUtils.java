package com.ecommerce.project.security.jwt;

import com.ecommerce.project.security.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtExpirationMS}")
    private int jwtExpirationMs;

    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.ecom.app.jwtCookieName}")
    private String jwtCookie;

    //Getting JWT form Header
//    public String getJwtTokenFromHeader(HttpServletRequest request) {
//        String bearerToken = request.getHeader("Authorization");
//        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
//            logger.debug("Authorization Header:{}", bearerToken);
//            return bearerToken.substring(7);
//        }
//        return null;
//    }

    //getting Jwt From Cookie
    public String getJwtFromCookies(HttpServletRequest servletRequest){
        Cookie cookie= WebUtils.getCookie(servletRequest,jwtCookie);
        if(cookie !=null){
            return cookie.getValue();
        }else {
            return null;
        }

    }
    //generateJWTCookie
    public ResponseCookie generateJwtCookie(UserDetailsImpl userPrincipal){
        String jwt=generateTokenFromUsername(userPrincipal.getUsername());
        ResponseCookie cookie= ResponseCookie.from(jwtCookie,jwt)
                .path("/api")
                .maxAge(24*60*60)
                .httpOnly(false)
                .secure(false)
                .build();
        return cookie;
    }

    public ResponseCookie getCleanJwtCookie(){
                ResponseCookie cookie= ResponseCookie.from(jwtCookie,null)
                .path("/api")
                .build();
        return cookie;
    }



    public String generateTokenFromUsername(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date().getTime() + jwtExpirationMs)))
                .signWith(key())
                .compact();
    }

    //Generating Token from UserName

//    public String generateTokenFromUsername(UserDetails userDetails) {
//        String userName = userDetails.getUsername();
//        return Jwts.builder()
//                .subject(userName)
//                .issuedAt(new Date())
//                .expiration(new Date((new Date().getTime() + jwtExpirationMs)))
//                .signWith(key())
//                .compact();
//    }

    //Generating UserName form Token
    public String getUserNameFromJWTToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }

    //Generate Signing Key
    public Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }
    //Validate JWT Token

    public boolean validateJwtToken(String authToken) {
        try {
            System.out.println("Validate");
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(authToken);
            return true;
        } catch (MalformedJwtException exception) {
            logger.error("Invalid JWT token:{}", exception.getMessage());
        } catch (ExpiredJwtException exception) {
            logger.error("JWT token is expired:{}", exception.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported :{}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty:{}", e.getMessage());
        }
        return false;
    }
}
