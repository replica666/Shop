package com.alten.shop.mappers;

import com.alten.shop.dto.response.WishListResDto;
import com.alten.shop.entities.WishList;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WishListMapper {

    @Mapping(target = "totalItems", expression = "java(wishList.getProducts().size())")
    WishListResDto toResponse(WishList wishList);
}
