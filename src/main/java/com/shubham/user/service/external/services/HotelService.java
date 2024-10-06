package com.shubham.user.service.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.shubham.user.service.entities.Hotel;


@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

	@GetMapping("/hotel/{hotelId}")
	Hotel getHotel(@PathVariable String hotelId);
	
}
