package step;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class UserStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The User Service Endpoint {string} is available")
    public void theUserServiceEndpointIsAvailable(String endpointPath) {
        this.endpointPath = String.format("http://localhost:%s" + endpointPath, randomServerPort);
    }

    @When("A request is made to get a user with id {int}")
    public void aRequestIsMadeToGetAUserWithId(int id) {
        responseEntity = testRestTemplate.getForEntity(endpointPath + "/{id}", String.class, id);
    }

    @When("A request is made to get all users")
    public void aRequestIsMadeToGetAllUsers() {
        responseEntity = testRestTemplate.getForEntity(endpointPath, String.class);
    }

    @When("A login request is made with email {string} and password {string}")
    public void aLoginRequestIsMadeWithEmailAndPassword(String email, String password) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> loginInfo = new HashMap<>();
        loginInfo.put("email", email);
        loginInfo.put("password", password);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(loginInfo, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath + "/login", request, String.class);
    }

    @When("A user creation request is sent with firstName {string}, lastName {string}, email {string}, password {string}, phoneNumber {string}, birthdate {string}, gender {string}, dni {string}, profilePic {string}, description {string}, and userRole {string}")
    public void aUserCreationRequestIsSentWith(String firstName, String lastName, String email, String password, String phoneNumber, String birthdate, String gender, String dni, String profilePic, String description, String userRole) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("firstName", firstName);
        userInfo.put("lastName", lastName);
        userInfo.put("email", email);
        userInfo.put("password", password);
        userInfo.put("phoneNumber", phoneNumber);
        userInfo.put("birthdate", birthdate);
        userInfo.put("gender", gender);
        userInfo.put("dni", dni);
        userInfo.put("profilePic", profilePic);
        userInfo.put("description", description);
        userInfo.put("userRole", userRole);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(userInfo, headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath, request, String.class);
    }

    @Then("A user with status {int} is received")
    public void aUserWithStatusIsReceived(int expectedStatusCode) {
        int actualStatus = responseEntity.getStatusCodeValue();
        assertThat(actualStatus).isEqualTo(expectedStatusCode);
    }

    @Then("A list of users with status {int} is received")
    public void aListOfUsersWithStatusIsReceived(int expectedStatusCode) {
        aUserWithStatusIsReceived(expectedStatusCode);
    }

    @Then("A new user with status {int} is received")
    public void aNewUserWithStatusIsReceived(int expectedStatusCode) {
        aUserWithStatusIsReceived(expectedStatusCode);
    }
}
