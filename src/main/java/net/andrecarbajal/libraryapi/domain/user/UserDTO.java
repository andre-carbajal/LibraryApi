package net.andrecarbajal.libraryapi.domain.user;

import lombok.Builder;
import lombok.Getter;
import net.andrecarbajal.libraryapi.domain.role.Role;

import java.util.Set;

@Getter
@Builder
public class UserDTO {
    private Long id;

    private String username;

    private Set<Role> roles;
}
