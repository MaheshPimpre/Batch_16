package com.endpoints;

import java.util.ResourceBundle;

import com.entity.User;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoint {

	static ResourceBundle getUrl(){
		ResourceBundle route = ResourceBundle.getBundle("routes");
		return route;
	}
	
	public static Response createUser(User user){
		String createUrl = getUrl().getString("create_userUrl");
		Response response=RestAssured.given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(user)
		.when()
			.post(createUrl);
		return response;
	}
	
	public static  Response updateUser(User user,String id){
		 String updateUrl = getUrl().getString("update_UserUrl") + "users/" + id;
	        return RestAssured.given()
	                .contentType(ContentType.JSON)
	                .accept(ContentType.JSON)
	                .body(user)
	                .when()
	                .put(updateUrl);
	}
	public static Response getUserDetails(String id) {
		String getSingleUserUrl = getUrl().getString("get_SingleUserUrl") + id;
        return RestAssured.given()
                .when()
                .get(getSingleUserUrl);
		
	}
	public static Response getUsersDetails(int page) {
		String getAllUsersUrl = getUrl().getString("getAll_Users");
		return RestAssured.given()
			.queryParam("page", page)
			.when()
				.get(getAllUsersUrl);
		
	}
	public static Response deleteUser(int id) {
		String deleteUserUrl = getUrl().getString("delete_UserUrl") + id;
        return RestAssured.given()
                .when()
                .delete(deleteUserUrl);
		
	}
}
