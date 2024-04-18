package com.pointofsales.miniproject.model.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AuthenticationRequest {
    String username;
    String password;
}
