Feature: User Management

  Scenario: Nick creates a user account
    Given Nick creates a user account
    When Nick enters "nick" and "Pa$$w0rd" login details
    Then A new account is created for Nick with username "nick"