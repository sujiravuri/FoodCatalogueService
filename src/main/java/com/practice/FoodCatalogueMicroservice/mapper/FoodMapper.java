package com.practice.FoodCatalogueMicroservice.mapper;

import com.practice.FoodCatalogueMicroservice.dto.FoodItemDto;
import com.practice.FoodCatalogueMicroservice.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodMapper {

    FoodMapper INSTANCE =
            Mappers.getMapper(FoodMapper.class);

    FoodItem mapFoodItemDtoToFoodItem(FoodItemDto foodItemDto);
    FoodItemDto mapFoodItemToFoodItemDto(FoodItem foodItem);

}

