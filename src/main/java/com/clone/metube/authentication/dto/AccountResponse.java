package com.clone.metube.authentication.dto;

import com.clone.metube.authentication.enums.RequestResult;
import com.clone.metube.authentication.enums.RequestType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    RequestType requestType;
    RequestResult requestResult;
    String message;
    Object response;
}
