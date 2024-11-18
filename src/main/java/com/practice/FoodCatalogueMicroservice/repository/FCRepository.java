package com.practice.FoodCatalogueMicroservice.repository;

import com.practice.FoodCatalogueMicroservice.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FCRepository extends
        JpaRepository<FoodItem,Integer> {

    List<FoodItem> findRestaurantByid(Integer restaurantid);
}
