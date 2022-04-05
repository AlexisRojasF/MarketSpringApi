package com.plazit.market.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutheticationRequest {

    private String userName;
    private String password;
}
