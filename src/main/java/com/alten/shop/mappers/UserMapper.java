package com.alten.shop.mappers;

import com.alten.shop.dto.request.UserReqDto;
import com.alten.shop.dto.response.UserResDto;
import com.alten.shop.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResDto toDto(User user);

    User toEntity(UserReqDto userDto);
}
