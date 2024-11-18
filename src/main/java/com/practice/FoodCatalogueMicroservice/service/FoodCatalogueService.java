package com.practice.FoodCatalogueMicroservice.service;

import com.practice.FoodCatalogueMicroservice.dto.FoodCataloguePage;
import com.practice.FoodCatalogueMicroservice.dto.FoodItemDto;
import com.practice.FoodCatalogueMicroservice.dto.Restaurant;
import com.practice.FoodCatalogueMicroservice.entity.FoodItem;
import com.practice.FoodCatalogueMicroservice.mapper.FoodMapper;
import com.practice.FoodCatalogueMicroservice.repository.FCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueService {

    @Autowired
    FCRepository fcRepository;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDto saveFoodItem(FoodItemDto foodItemDto)
    {
      FoodItem foodItem= fcRepository.save(FoodMapper.INSTANCE.mapFoodItemDtoToFoodItem(foodItemDto));
        return FoodMapper.INSTANCE.mapFoodItemToFoodItemDto(foodItem);
    }

    public FoodCataloguePage fetchfoodcataloguepage(Integer restaurantid) {

        List<FoodItem> foodItemList = fetchFoodItemsList(restaurantid);
        Restaurant restaurantdet = fetchrectaurantdetailsfromRestaurantMS(restaurantid);
        return  createFoodCataloguePage(foodItemList,restaurantdet);
    }

    private FoodCataloguePage createFoodCataloguePage
            (List<FoodItem> foodItemList, Restaurant restaurantdet) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurantdetails(restaurantdet);
        return foodCataloguePage;
    }

    private Restaurant fetchrectaurantdetailsfromRestaurantMS
            (Integer restaurantid) {
        Restaurant restaurant = restTemplate.getForObject
                ("http://RESTAURANTLISTING/restaurants/findById/"+restaurantid,Restaurant.class);
        return restaurant;
    }

    private List<FoodItem> fetchFoodItemsList
            (Integer restaurantid) {
        List<FoodItem> foodItemList = fcRepository.findRestaurantByid(restaurantid);
        return foodItemList;
    }
}

