package com.clone.metube.authentication.oauth2.userinfos;

import com.clone.metube.authentication.oauth2.interfaces.OAuth2UserInfo;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {
    private final Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public String getProviderId() {
        Object id = this.attributes.get("id");
        return id == null ? null : id.toString();
    }

    @Override
    public String getEmail() {
        Object email = this.attributes.get("email");
        return email == null ? null : email.toString();
    }

    @Override
    public String getName() {
        Object name = this.attributes.get("name");
        return name == null ? null : name.toString();
    }
}
