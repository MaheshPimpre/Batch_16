package com.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.endpoints.UserEndPoint;
import com.entity.User;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReqresTest {

	User user;
	static String id="2";
	@BeforeClass
	void setUserData() {
		user=new User();
		user.setId(101);
		user.setName("Jack");
		user.setJob("Shooter");
	}
	
	@Test(priority = 1)
	void testPostUser() {
		Response res = UserEndPoint.createUser(user);
		res.then()
		.log().body();
		JsonPath jsonPath = res.jsonPath();
		// id = jsonPath.get("id");
		 System.out.println("Create user Id: "+id);
		Assert.assertEquals(res.statusCode(), 201);
	}
	
	
	@Test(priority = 2)
	void testGetAllUsers() {
			
			Response res = UserEndPoint.getUsersDetails(2);
			res.then()
			.log().body();
			
			Assert.assertEquals(res.statusCode(), 200);
		}
	
	@Test(priority = 3)
	void testGetSingleAllUsers() {
			
		System.out.println("Fetching details for user ID: " + user.getId());
			Response res = UserEndPoint.getUserDetails("2");
	        res.then().log().body();
	        Assert.assertEquals(res.statusCode(), 200);
		}
}
