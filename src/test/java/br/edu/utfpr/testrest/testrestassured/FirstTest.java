package br.edu.utfpr.testrest.testrestassured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.parsing.Parser;
import io.restassured.specification.ProxySpecification;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.junit.BeforeClass;

public class FirstTest {

    @BeforeClass
    public static void beforeClass() {
        //configuracao do proxy
        /*RestAssured.proxy = ProxySpecification
                .host("10.20.10.50")
                .withPort(3128)
                .withAuth("username", "password");    */
        
        RestAssured.registerParser("text/plain", Parser.JSON);
    }
    
    @Test
    public void testBleachIs269() {
        when().
                get("https://api.jikan.moe/v3/anime/269").
        then()
                .statusCode(200).
                body("title", is("Bleach"));
    }
    
    @Test
    public void testBleachEpisodeIs366() {
        when().
                get("https://api.jikan.moe/v3/anime/269").
        then()
                .statusCode(200).
                body("episodes", equalTo(366));
    }
    
    @Test
    public void testFirstPageofBleachEpisodesHas100() {
        when().
                get("https://api.jikan.moe/v3/anime/269/episodes").
        then()
                .statusCode(200).
                body("episodes.size()", is(100));
    }
}
