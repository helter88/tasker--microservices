package com.tasker.auth_manager.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class UserSummary {
    private UUID id;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
}
