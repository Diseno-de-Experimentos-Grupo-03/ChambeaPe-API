Feature: Contract Testing
  Background:
    Given The Endpoint "http://localhost:8080/api/v1/contracts" is available

  @post-contract
  Scenario: Add Contract
    When A contract request is sent with values workerId 1, employerId 2, startDay "2024-01-01T08:00:00Z", endDay "2024-12-31T18:00:00Z", salary 5000.0, state "1", postId 3
    Then A contract with status 201 is received

  @delete-contract
  Scenario: Delete Contract
    When A contract delete is sent with id value "1"
    Then A contract with status 201 is received

  @get-contract-by-worker-and-employer
  Scenario: Get Contract By WorkerId and EmployerId
    When A contract is requested with workerId 1 and employerId 2
    Then A contract with status 201 is received

  @get-contracts-by-user
  Scenario: Get All Contracts By UserId
    When A request is made for all contracts with userId 1
    Then A list of contracts with status 201 is received