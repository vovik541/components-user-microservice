package com.microservice.user.response;

import com.microservice.user.entity.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UsersListResponse {

    private List<UserDTO> userDTOS;

}
