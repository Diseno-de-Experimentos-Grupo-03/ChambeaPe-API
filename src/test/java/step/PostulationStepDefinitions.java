package step;

import com.digitaldark.ChambeaPe_Api.postulation.dto.request.PostulationRequestDTO;
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

public class PostulationStepDefinitions {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int randomServerPort;
    private String endpointPath;
    private ResponseEntity<String> responseEntity;

    @Given("The Postulation Service Endpoint {string} is available")
    public void thePostulationServiceEndpointIsAvailable(String endpointPath) {
        this.endpointPath = String.format("http://localhost:%s" + endpointPath, randomServerPort);
    }

    @When("A request is made to get all postulations for post with id {int}")
    public void aRequestIsMadeToGetAllPostulationsForPostWithId(int postId) {
        responseEntity = testRestTemplate.getForEntity(endpointPath + "/posts/{postId}/postulations", String.class, postId);
    }

    @When("A postulation creation request is sent for post id {int} and worker id {int}")
    public void aPostulationCreationRequestIsSentForPostIdAndWorkerId(int postId, int workerId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostulationRequestDTO> request = new HttpEntity<>(new PostulationRequestDTO(), headers);
        responseEntity = testRestTemplate.postForEntity(endpointPath + "/posts/{postId}/postulations/{workerId}", request, String.class, postId, workerId);
    }

    @When("A delete request is made for a postulation with post id {int} and worker id {int}")
    public void aDeleteRequestIsMadeForAPostulationWithPostIdAndWorkerId(int postId, int workerId) {
        testRestTemplate.delete(endpointPath + "/posts/{postId}/postulations/{workerId}", postId, workerId);
        responseEntity = new ResponseEntity<>("Postulation deleted successfully", HttpStatus.OK);
    }

    @When("An update request is made for a postulation with id {int}")
    public void anUpdateRequestIsMadeForAPostulationWithId(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PostulationRequestDTO> request = new HttpEntity<>(new PostulationRequestDTO(), headers);
        responseEntity = testRestTemplate.exchange(endpointPath + "/postulations/{id}", HttpMethod.PUT, request, String.class, id);
    }

    @When("A request is made to get all postulations for worker with id {int}")
    public void aRequestIsMadeToGetAllPostulationsForWorkerWithId(int workerId) {
        Map<String, Integer> params = new HashMap<>();
        params.put("userId", workerId);
        responseEntity = testRestTemplate.getForEntity(endpointPath + "/postulations?userId={userId}", String.class, params);
    }

    @Then("A list of postulations with status {int} is received")
    public void aListOfPostulationsWithStatusIsReceived(int expectedStatusCode) {
        int actualStatus = responseEntity.getStatusCodeValue();
        assertThat(actualStatus).isEqualTo(expectedStatusCode);
    }

    @Then("A new postulation with status {int} is received")
    public void aNewPostulationWithStatusIsReceived(int expectedStatusCode) {
        aListOfPostulationsWithStatusIsReceived(expectedStatusCode);
    }

    @Then("A confirmation with status {int} is received and the response contains {string}")
    public void aConfirmationWithStatusIsReceivedAndTheResponseContains(int expectedStatusCode, String expectedMessage) {
        int actualStatus = responseEntity.getStatusCodeValue();
        assertThat(actualStatus).isEqualTo(expectedStatusCode);
        assertThat(responseEntity.getBody()).contains(expectedMessage);
    }
}
