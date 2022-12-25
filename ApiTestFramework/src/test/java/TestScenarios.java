
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestScenarios {
	
	static String path = "https://restful-booker.herokuapp.com/booking";
	
	@Test(description = "Verify correct response status code is returned for valid inputs")
	public static void verifyResponseStatusCode(){
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2018-01-01");
		bookingdates.put("checkout", "2019-01-01");
		
		JSONObject request = new JSONObject();
		request.put("firstname", "Jim");
		request.put("lastname", "Brown");
		request.put("totalprice", 111);
		request.put("depositpaid", true);
		request.put("bookingdates",bookingdates );
		request.put("additionalneeds", "Breakfast");
		
		   			given()
		   			.header("Content-Type", "application/json")
		   			.header("Accept", "application/json")
				   .body(request.toJSONString())
				   .when()
                   .post(path)
                   .then().assertThat().statusCode(200);
	}
	
	@Test(description = "Verify correct response body is returned for valid inputs")
	public static void verifyResponseBody(){
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2018-01-01");
		bookingdates.put("checkout", "2019-01-01");
		
		JSONObject request = new JSONObject();
		request.put("firstname", "Jim");
		request.put("lastname", "Brown");
		request.put("totalprice", 111);
		request.put("depositpaid", true);
		request.put("bookingdates",bookingdates );
		request.put("additionalneeds", "Breakfast");
		
		   			given()
		   			.header("Content-Type", "application/json")
		   			.header("Accept", "application/json")
				   .body(request.toJSONString())
				   .when()
                   .post(path)
                   .then()
                   .body("booking.firstname", Matchers.equalTo("Jim"))
		   		   .body("booking.lastname", Matchers.equalTo("Brown"))
		   		   .body("booking.totalprice", Matchers.equalTo(111))
		   		   .body("booking.depositpaid", Matchers.equalTo(true))
		   		   .body("booking.bookingdates.checkin", Matchers.equalTo("2018-01-01"))
		   		   .body("booking.bookingdates.checkout", Matchers.equalTo("2019-01-01"))
		   		.body("booking.additionalneeds", Matchers.equalTo("Breakfast"));

	}
	
	@Test(description = "Verify correct response header is returned")
	public static void verifyResponseHeaders(){
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2018-01-01");
		bookingdates.put("checkout", "2019-01-01");
		
		JSONObject request = new JSONObject();
		request.put("firstname", "Jim");
		request.put("lastname", "Carter");
		request.put("totalprice", 111);
		request.put("depositpaid", true);
		request.put("bookingdates",bookingdates );
		request.put("additionalneeds", "Breakfast");
		
		Response response = 
					given()
		   			.header("Content-Type", "application/json")
		   			.header("Accept", "application/json")
				   .body(request.toJSONString())
				   .when()
                   .post(path);
		
		String contentType = response.contentType();
		System.out.println("Returned content type is "+contentType);
		
		Assert.assertEquals(contentType, "application/json; charset=utf-8", "Returned content type is "+contentType);

	}
	
	@Test(description = "Verify end point is failing for invalid inputs")
	public static void verifyEndPointForInvalidInputs(){
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2018-01-01");
		bookingdates.put("checkout", "2019-01-01");
		
		JSONObject request = new JSONObject();
		request.put("firstname", "Jim");
		request.put("lastname", true);
		request.put("totalprice", 111);
		request.put("depositpaid", true);
		request.put("bookingdates",bookingdates );
		request.put("additionalneeds", "Breakfast");
		
		Response response = 
					given()
		   			.header("Content-Type", "application/json")
		   			.header("Accept", "application/json")
				   .body(request.toJSONString())
				   .when()
                   .post(path);
		
		int statusCode = response.getStatusCode();
		System.out.println("Returned status code for invalid inputs is "+statusCode);		
		Assert.assertNotEquals(statusCode, 200, "Returned status code is "+statusCode);

	}
	
	@Test(description = "Verify end point is failing for incomplete inputs")
	public static void verifyEndPointForIncompleteInputs(){
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2018-01-01");
		bookingdates.put("checkout", "2019-01-01");
		
		JSONObject request = new JSONObject();
		request.put("firstname", "Jim");
		request.put("lastname", "Carter");
		request.put("totalprice", 111);
		request.put("depositpaid", true);
		
		Response response = 
					given()
		   			.header("Content-Type", "application/json")
		   			.header("Accept", "application/json")
				   .body(request.toJSONString())
				   .when()
                   .post(path);
		
		int statusCode = response.getStatusCode();
		System.out.println("Returned status code for incomplete input is "+statusCode);		
		Assert.assertNotEquals(statusCode, 200, "Returned status code is "+statusCode);

	}
	
	@Test(description = "Verify end point is failing for incomplete headers")
	public static void verifyEndPointForIncompleteHeaders(){
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2018-01-01");
		bookingdates.put("checkout", "2019-01-01");
		
		JSONObject request = new JSONObject();
		request.put("firstname", "Jim");
		request.put("lastname", "Carter");
		request.put("totalprice", 111);
		request.put("depositpaid", true);
		request.put("bookingdates",bookingdates );
		request.put("additionalneeds", "Breakfast");
		
		Response response = 
					given()
				   .body(request.toJSONString())
				   .when()
                   .post(path);
		
		int statusCode = response.getStatusCode();
		System.out.println("Returned status code for incomplete headers is "+statusCode);		
		Assert.assertNotEquals(statusCode, 200, "Returned status code is "+statusCode);

	}
	
	@Test(description = "Verify end point response time")
	public static void verifyEndPointResponseTime(){
		
		JSONObject bookingdates = new JSONObject();
		bookingdates.put("checkin", "2018-01-01");
		bookingdates.put("checkout", "2019-01-01");
		
		JSONObject request = new JSONObject();
		request.put("firstname", "Jim");
		request.put("lastname", "Brown");
		request.put("totalprice", 111);
		request.put("depositpaid", true);
		request.put("bookingdates",bookingdates );
		request.put("additionalneeds", "Breakfast");
		
		Response response = 
				given()
	   			.header("Content-Type", "application/json")
	   			.header("Accept", "application/json")
			   .body(request.toJSONString())
			   .when()
               .post(path);
		
		long responseTime = response.timeIn(TimeUnit.MILLISECONDS) ;
		System.out.println("Response time for the endpoint is "+responseTime);
		
		if (responseTime < 1000) {
			Assert.assertTrue(true, "Response time is below 1000ms");			
		}else {
			Assert.assertFalse(true, "Response time is above 1000ms");			
		}
		
	}

}
