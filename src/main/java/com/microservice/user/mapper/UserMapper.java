package com.microservice.user.mapper;

import com.microservice.user.entity.User;
import com.microservice.user.entity.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserDTO userToUserDTO(User user);
}
