package com.montanha.gerenciador.viagens;

import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class ViagensTest {
    @Test
    public void testDadoUmAdministradorQuandoCadastroViagensEntaoObtenhoStatusCode201() {
        // Configurar o caminho de acesso comum da minha API Rest
        baseURI = "http://localhost";
        port = 8089;
        basePath = "/api";
        // Login na API Rest com administrador
            String token = given()
                .body("{\n" +
                        "  \"email\": \"admin@email.com\",\n" +
                        "  \"senha\": \"654321\"\n" +
                        "}")
                .contentType(ContentType.JSON)
              .when()
                  .post("/v1/auth")
              .then()
               // .log().all() //Serve para trazer relatório
                .extract()
                  .path("data.token");

         //System.out.println(token);// - Serve para imprimir na tela

        // Cadastrar a viagem
        given()
                .header("Authorization", token)
                .body("{\n" +
                        "  \"acompanhante\": \"Isabelle\",\n" +
                        "  \"dataPartida\": \"2021-02-07\",\n" +
                        "  \"dataRetorno\": \"2021-03-07\",\n" +
                        "  \"localDeDestino\": \"Manaus\",\n" +
                        "  \"regiao\": \"Norte\"\n" +
                        "}")

                .contentType(ContentType.JSON)
        .when()
                .post("/v1/viagens")

                .then()
                .assertThat()
                    .statusCode(201);

    }
}
