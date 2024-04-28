package com.pointofsales.miniproject.model.dto;

import com.pointofsales.miniproject.model.entity.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterRequest {
    private String username;
    private String password;
    private Role role;
}
