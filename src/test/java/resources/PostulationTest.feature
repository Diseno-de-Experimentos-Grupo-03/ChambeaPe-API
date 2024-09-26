Feature: Postulation Testing
  Background:
    Given The Postulation Service Endpoint "http://localhost:8080/api/v1/posts/{postId}/postulations" is available

  @get-all-postulations-by-post
  Scenario: Get All Postulations By Post
    When A request is made to get all postulations for post with id 1
    Then A list of postulations with status 200 is received

  @create-postulation
  Scenario: Create a New Postulation
    When A postulation creation request is sent for post id 1 and worker id 2
    Then A new postulation with status 201 is received

  @delete-postulation
  Scenario: Delete Postulation
    When A delete request is made for a postulation with post id 1 and worker id 2
    Then A confirmation with status 200 is received and the response contains "Postulation deleted successfully"

  @update-postulation
  Scenario: Update Postulation If Accepted
    When An update request is made for a postulation with id 1
    Then A confirmation with status 200 is received and the response contains "Postulation was updated successfully"

  @get-all-postulations-by-worker
  Scenario: Get All Postulations By WorkerId
    When A request is made to get all postulations for worker with id 2
    Then A list of postulations with status 200 is received
