package com.clone.metube.authentication.oauth2.interfaces;

public interface OAuth2UserInfo {
    String getProviderId();

    String getEmail();

    String getName();
}