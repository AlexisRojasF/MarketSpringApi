package com.plazit.market.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class AutheticationResponse {

    private String jwt;

    public AutheticationResponse(String jwt) {
        this.jwt = jwt;
    }
}
