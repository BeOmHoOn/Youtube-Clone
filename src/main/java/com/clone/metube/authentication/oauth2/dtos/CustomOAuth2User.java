package com.clone.metube.authentication.oauth2.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {
    private String name;
    private String email;
    private Map<String, Object> attributes;
    private Collection<GrantedAuthority> authorities;

    public String getEmail() {
        return this.email;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
