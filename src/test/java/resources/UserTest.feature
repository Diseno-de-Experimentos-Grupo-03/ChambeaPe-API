Feature: User Testing
  Background:
    Given The User Service Endpoint "http://localhost:8080/api/v1/users" is available

  @get-user-by-id
  Scenario: Get User By Id
    When A request is made to get a user with id 1
    Then A user with status 200 is received

  @get-all-users
  Scenario: Get All Users
    When A request is made to get all users
    Then A list of users with status 200 is received

  @get-user-by-email-and-password
  Scenario: Get User By Email and Password
    When A login request is made with email "user@example.com" and password "password123"
    Then A user with status 200 is received

  @create-user
  Scenario: Create a New User
    When A user creation request is sent with firstName "John", lastName "Doe", email "john.doe@example.com", password "password123", phoneNumber "1234567890", birthdate "1990-01-01T00:00:00Z", gender "M", dni "12345678", profilePic "http://example.com/profile.jpg", description "A brief description", and userRole "W"
    Then A new user with status 201 is received
