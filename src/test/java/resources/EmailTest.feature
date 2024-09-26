Feature: Email Testing
  Background:
    Given The Email Service Endpoint "http://localhost:8080/api/v1/emails" is available

  @send-email
  Scenario: Send Email
    When An email send request is sent with toUser "user@example.com", subject "Test Subject", and message "Test Message"
    Then An email with status 201 is received and the response contains "Correo enviado"

  @generate-otp
  Scenario: Generate OTP Code
    When An OTP generation request is sent with email "user@example.com"
    Then An OTP with status 201 is received and the response contains "Codigo de verificación enviado a su correo"

  @validate-otp
  Scenario: Validate OTP Code
    When An OTP validation request is sent with email "user@example.com" and otp "123456"
    Then A validation response with status 201 is received and the user details are returned
