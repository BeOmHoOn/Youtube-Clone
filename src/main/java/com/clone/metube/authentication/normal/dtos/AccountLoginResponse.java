package com.clone.metube.authentication.normal.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountLoginResponse {
    private String AccessToken;
    private String RefreshToken;
}
