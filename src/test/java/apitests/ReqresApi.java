package apitests;


	import static org.junit.Assert.assertEquals;
	import static org.junit.Assert.assertTrue;

	import org.junit.Test;

	import io.restassured.http.ContentType;
	import io.restassured.http.Header;
	import io.restassured.path.json.JsonPath;
	import io.restassured.response.Response;

	import static io.restassured.RestAssured.*;
	public class ReqresApi {
		
		/*
		 * Send a get request to https://reqres.in/api/users
		 * Including query param -> page =2
		 * Accept type is json
		 * Verify status code is 200, verify response body
		 * 
		 */
		@Test
		public void getUsersTest() {
//			given().accept(ContentType.JSON)
//			.and().params("page",2)
//			.when().get("https://reqres.in/api/users")
//			.then().assertThat().statusCode(200);
			
			Response response = given().accept(ContentType.JSON)
			.and().params("page",2)
			.when().get("https://reqres.in/api/users");
			
			System.out.println(response.getStatusLine());
			System.out.println(response.getContentType());
			System.out.println(response.headers());
			System.out.println(response.body().asString());
			
			//add assertions for parts of response
			assertEquals(200,response.statusCode());
			assertTrue(response.contentType().contains("application/json"));
			Header header = new Header("X-Powered-By", "Express");
			assertTrue(response.headers().asList().contains(header));
			
			JsonPath json = response.jsonPath();
			assertEquals(12, json.getInt("total"));
			assertEquals(4, json.getInt("total_pages"));
			
			System.out.println(json.getInt("data.id[0]"));
			
			//verify that Charle's id is 5	
			assertEquals(5,json.getInt("data.find{it.first_name == 'Charles'}.id"));
			
			//Assert using JsonPath that person with id 6, first name is Tracey 
			// and last name is Ramos
			assertEquals(6,json.getInt("data.find{it.first_name == 'Tracey'}.id"));
			assertEquals(6,json.getInt("data.find{it.last_name == 'Ramos'}.id"));
			
			
			
			
			
			
			
	        		
			
		}
	}

