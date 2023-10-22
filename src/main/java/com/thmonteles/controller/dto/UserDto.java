package com.thmonteles.controller.dto;

import com.thmonteles.domain.model.User;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

public record UserDto(
        Long id,
        String name,
        String email,
        String password
) {

    public UserDto(User model) {
        this(
                model.getId(),
                model.getName(),
                model.getEmail(),
                model.getPassword()
        );
    }

    public User toModel() {
        User model = new User();
        model.setId(this.id);
        model.setName(this.name);
        model.setEmail(this.email);
        model.setPassword(this.password);
        return model;
    }

}

