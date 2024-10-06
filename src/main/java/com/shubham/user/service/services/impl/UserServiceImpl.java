package com.shubham.user.service.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shubham.user.service.entities.Hotel;
import com.shubham.user.service.entities.Rating;
import com.shubham.user.service.entities.User;
import com.shubham.user.service.exceptions.ResourceNotFoundException;
import com.shubham.user.service.external.services.HotelService;
import com.shubham.user.service.repository.UserRepository;
import com.shubham.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userrepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private HotelService hotelService;

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public User saveUser(User user) {

		String uniqueUserID = UUID.randomUUID().toString();
		user.setUserId(uniqueUserID);
		return userrepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userrepository.findAll();
	}

	@Override
	public User getUser(String userId) {

		// fetch data of the user from the RATING SERVICE
		// http://localhost:8083/rating/user/userId

		User user = userrepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("resource not found"));
//	    @SuppressWarnings("unchecked")
		Rating[] ratingObject = restTemplate.getForObject("http://RATING-SERVICE/rating/user/" + user.getUserId(),
				Rating[].class);
		logger.info("{}", ratingObject);

		List<Rating> ratings = Arrays.stream(ratingObject).toList();

		List<Rating> ratingList = ratings.stream().map(rating -> {
//			ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);

			Hotel hotel = hotelService.getHotel(rating.getHotelId());
//			Hotel hotel = forEntity.getBody();

			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());

		user.setRating(ratingList);
		return user;
	}

}
