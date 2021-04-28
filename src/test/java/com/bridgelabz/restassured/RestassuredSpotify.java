package com.bridgelabz.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RestassuredSpotify {

    public String token = "";
    public static String userID = "";

    @BeforeTest
    public void setup(){
        token = "Bearer BQArZYE3g1h27RxBJOoXQ56G3Yye3xacuVxhBt6rqVQctFlp5LpWHIzGLnYEF_VXCi6cvE1AaS_ccYTTCSS5sYdwiUgUZC5xlwNgZwL3UhZIJlVCMM2_xyxDZL_LAkDRBsP95RdZrq9_91m7GNFiavXS_wtawhHbDJ2zRsIWgKby4Z1z8WUYX6S0XCbMGKf0lxPai0r85eNV7hhHsaRtRFDs25WnjbS9GMa0OYAFY4YxzTa0Ds4A8HMkwzlpfGZzQ83-8GuazoL_C5HYBI9-EMh7WI46UQ0N6vGHz-QS1x65";
    }

    @Test
    public void getCurrent_Users_Profile(){
        Response response = given()
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .header("Authorization", token)
                            .when()
                            .get("https://api.spotify.com/v1/me");
                response.prettyPrint();
                userID = response.path("id");
                System.out.println("UserID :" + userID);
                response.then().assertThat().statusCode(200);
    }

    @Test
    public void getUser_Profile(){
        Response response = given()
                            .contentType(ContentType.JSON)
                            .accept(ContentType.JSON)
                            .header("Authorization", token)
                            .when()
                            .get("https://api.spotify.com/v1/users/" +userID+"/");
                response.then().assertThat().statusCode(200);
                response.prettyPrint();
    }

    @Test
    public void Create_Playlist(){
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .body("{\"name\":\"Hollywood\",\"description\":\"Hollywood songs\",\"public\":\"false\"}")
                .when()
                .post("https://api.spotify.com/v1/users/31xsgkyxho7lfwupi2nxscorfwnq/playlists");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void searchTrack() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .get("https://api.spotify.com/v1/search?q=Diljit&type=track");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void addTrack() {
        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Authorization", token)
                .when()
                .post("https://api.spotify.com/v1/playlists/4wRy60HUxLflyM3VEXgtuZ/tracks?uris=spotify:track:4XxfOvudrnBRdlgzEaq1sd");
        response.prettyPrint();
        response.then().assertThat().statusCode(201);
    }
}

