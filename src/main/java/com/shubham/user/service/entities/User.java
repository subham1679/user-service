package com.shubham.user.service.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;


@Entity
@Table(name = "micro_users")
public class User {
	
		@Id
		@Column(name="Id")
		private String userId;
		
		@Column(name="NAME", length = 20)
		private String name;
		
		@Column(name="EMAIL")
		private String email;
		
		@Column(name="ABOUT")
		private String about;
		
		public String getUserId() {
			return userId;
		}
		
		
		@Transient
		private List<Rating> rating = new ArrayList<>();

		public User() {
			super();
		}
		
		public User(String userId, String name, String email, String about, List<Rating> rating) {
			super();
			this.userId = userId;
			this.name = name;
			this.email = email;
			this.about = about;
			this.rating = rating;
		}

		public List<Rating> getRating() {
			return rating;
		}

		public void setRating(List<Rating> rating) {
			this.rating = rating;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAbout() {
			return about;
		}

		public void setAbout(String about) {
			this.about = about;
		}

		

}
