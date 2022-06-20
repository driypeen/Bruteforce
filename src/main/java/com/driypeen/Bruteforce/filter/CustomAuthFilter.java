package com.driypeen.Bruteforce.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.driypeen.Bruteforce.exception.TokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter {
    private static final String ALGORITHM = "RSA";

    private AuthenticationManager authenticationManager;

    @Override
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String login = request.getParameter("username");
        String password = request.getParameter("password");

        log.info("Auth request: login={}, password={}", login, password);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);

        return authenticationManager.authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = (User)authResult.getPrincipal();

        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance(ALGORITHM);
            RSAKeyGenParameterSpec spec = new RSAKeyGenParameterSpec(2048, RSAKeyGenParameterSpec.F4);
            keygen.initialize(spec);
            KeyPair keypair = keygen.generateKeyPair();

            RSAPublicKey rsaPublicKey = (RSAPublicKey) keypair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keypair.getPrivate();

            Algorithm algorithm = Algorithm.RSA512(rsaPublicKey, rsaPrivateKey);

            String accessToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .withClaim("roles",
                            user.getAuthorities().stream()
                                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);

            String refreshToken = JWT.create()
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                    .withIssuer(request.getRequestURL().toString())
                    .sign(algorithm);

            response.setHeader("access_token", accessToken);
            response.setHeader("refresh_token", refreshToken);

        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException e) {
            log.error("ERROR IN ALGORITHM WITH NAME = {}", ALGORITHM);
            e.printStackTrace();
            throw new TokenException("Error in algorithm");
        }
    }
}
