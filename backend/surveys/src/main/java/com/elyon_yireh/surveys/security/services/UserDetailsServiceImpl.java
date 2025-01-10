package com.elyon_yireh.surveys.security.services;

import com.elyon_yireh.surveys.security.dto.AuthLoginRequest;
import com.elyon_yireh.surveys.security.dto.AuthResponse;
import com.elyon_yireh.surveys.security.entities.UserEntity;
import com.elyon_yireh.surveys.security.repository.UserRepository;
import com.elyon_yireh.surveys.security.utils.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.stereotype.Service;

import javax.xml.datatype.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with username not found"));
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        userEntity.getRoleEntities().forEach(role -> {
            authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getName())));
        });

        userEntity.getRoleEntities().forEach(role -> {
            role.getPermissionEntities().forEach(permission -> {
                authorityList.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getIsEnabled(), userEntity.getIsAccountNoExpired(), userEntity.getIsCredentialNoExpired(), userEntity.getIsAccountNoLocked(), authorityList);
    }

    public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();

        try {
            Authentication authentication = this.authenticate(username, password);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = jwtUtils.createToken(authentication);

            // Set cookie
            ResponseCookie cookie = ResponseCookie.from("access_token", accessToken)
                    .httpOnly(true)
                    .path("/")
                    .secure(true)
                    .maxAge(60 * 60 * 2)
                    .sameSite("Strict")
                    .build();

            // Add cookie to response

            return new AuthResponse(username, "User logged successfully", cookie.toString());
        } catch (Error e) {
            return new AuthResponse(username, "User couldn't login unsuccessfully", null);
        }
    }

    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }
}
