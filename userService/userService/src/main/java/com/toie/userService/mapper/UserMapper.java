package com.toie.userService.mapper;

import com.toie.userService.domain.User;
import com.toie.userService.model.RegisterRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper
public interface UserMapper {

    @Mapping(target = "password", source = "password")
    User map(RegisterRequestDto registerRequestDto, UUID id, String password);
}
