package com.microservice.user.mapper;

import com.microservice.user.entity.User;
import com.microservice.user.entity.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);
    List<User> userDTOsToUser(List<UserDTO> userDTOS);

    List<UserDTO> usersToUserDTOs(List<User> userDTOS);
}
