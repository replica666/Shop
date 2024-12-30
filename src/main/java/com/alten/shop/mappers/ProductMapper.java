package com.alten.shop.mappers;

import com.alten.shop.dto.request.ProductReqDto;
import com.alten.shop.dto.request.ProductUpdateReqDto;
import com.alten.shop.dto.response.ProductResDto;
import com.alten.shop.entities.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    ProductResDto toResDto(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product toEntity(ProductReqDto request);

    void updateEntityFromRequest(ProductUpdateReqDto request, @MappingTarget Product product);

}

