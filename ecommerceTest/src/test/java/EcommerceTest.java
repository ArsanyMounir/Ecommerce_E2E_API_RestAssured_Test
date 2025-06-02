import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetail;
import pojo.Orders;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class EcommerceTest {

    @Test
    public void e2eTest(){
        RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("arshyashraf@gmail.com");
        loginRequest.setUserPassword("aA@123456");


        RequestSpecification requestLoginSpec = given().log().all().spec(requestSpec).body(loginRequest);
        //Login
        LoginResponse loginResponse= requestLoginSpec.when().post("/api/ecom/auth/login")
                .then().extract().response().as(LoginResponse.class);

        String token = loginResponse.getToken();
        String userID = loginResponse.getUserId();

        //Add Product
        RequestSpecification addProductSpec=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token)
                .build();
        RequestSpecification reqAddProduct = given().log().all().spec(addProductSpec).param("productName", "Laptop")
                .param("productAddedBy", userID).param("productCategory", "fashion")
                .param("productSubCategory", "shirts").param("productPrice", "11500")
                .param("productDescription", "Lenovo").param("productFor", "men")
                .multiPart("productImage",new File("D:\\ITI\\Courses\\Udemy Cert\\API Restassured\\ecommerce\\ecommerce\\src\\main\\resources\\images.jpg"));

        String addProductResponse =reqAddProduct.when().post("/api/ecom/product/add-product").
                then().log().all().extract().response().asString();
        JsonPath js = new JsonPath(addProductResponse);
        String productId =js.get("productId");

        //Create Order
        RequestSpecification createOrderSpec=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                                                .addHeader("authorization", token).setContentType(ContentType.JSON)
                                                .build();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setCountry("Egypt");
        orderDetail.setProductOrderedId(productId);

        List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
        orderDetailList.add(orderDetail);
        Orders orders = new Orders();
        orders.setOrders(orderDetailList);

        RequestSpecification createOrderReq=given().log().all().spec(createOrderSpec).body(orders);

        String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(responseAddOrder);



        //Delete Product

        RequestSpecification deleteProdSpec=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                                                .addHeader("authorization", token).setContentType(ContentType.JSON)
                                                .build();

        RequestSpecification deleteProdReq =given().log().all().spec(deleteProdSpec).pathParam("productId",productId);

        String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
                extract().response().asString();

        JsonPath js1 = new JsonPath(deleteProductResponse);

        Assert.assertEquals("Product Deleted Successfully",js1.get("message"));




    }

}
