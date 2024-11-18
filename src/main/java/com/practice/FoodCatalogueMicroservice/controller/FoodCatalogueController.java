package com.practice.FoodCatalogueMicroservice.controller;

import com.practice.FoodCatalogueMicroservice.dto.FoodCataloguePage;
import com.practice.FoodCatalogueMicroservice.dto.FoodItemDto;
import com.practice.FoodCatalogueMicroservice.entity.FoodItem;
import com.practice.FoodCatalogueMicroservice.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/FoodItems")
public class FoodCatalogueController {



    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("/save")
    public ResponseEntity<FoodItemDto> saveFoodItem
            (FoodItemDto foodItemDto) {

        FoodItemDto savedfoodItemDto =
                foodCatalogueService.saveFoodItem(foodItemDto);
        return new ResponseEntity<>
                (savedfoodItemDto, HttpStatus.CREATED);
    }

    @GetMapping("/fetchrestaurantdetailsandfooditems/{restaurantid}")
    public ResponseEntity<FoodCataloguePage>
    fetchRestaurantdetailswithfooditems
            (@PathVariable Integer restaurantid) {
        FoodCataloguePage foodCataloguePage =
                foodCatalogueService.fetchfoodcataloguepage(restaurantid);

return new ResponseEntity<>(foodCataloguePage,HttpStatus.OK);
    }
}