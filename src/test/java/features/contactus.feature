@ContactUs
Feature: Verifying contact us form functionality of webdriveruniversity.com
         As a user of webdriveruniversity.com
         User want to pass a message
         In order to complain about an placed order

  Scenario Outline: Submit valid data via contact us form
    Given user navigates to webdriveruniversity contact us form "<Url>"
    When user enters a valid firstname as "<FirstName>"
    When user enters a valid lastname as "<LastName>"
    And user enters a valid email address as "<EmailAddress>"
    And user enters comment as "<Comment>"
    When user clicks on the submit button"
    Then user gets successful welcome message as "<Status>"

    Examples: Valid credentials
      | Url                                                          | FirstName | LastName | EmailAddress      | Comment                | Status                      |
      | http://www.webdriveruniversity.com/Contact-Us/contactus.html | James     | Bond     | jamesbond@aol.com | This is James Bond 007 | Thank You for your Message! |

  Scenario Outline: Submit invalid data via contact us form
    Given user navigates to webdriveruniversity contact us form "<Url>"
    When user enters a valid firstname as "<FirstName>"
    When user enters a valid lastname as "<LastName>"
    And user enters an invalid email address as "<EmailAddress>"
    And user enters comment as "<Comment>"
    When user clicks on the submit button"
    Then user gets an error welcome message as "<Status>"

    Examples: Invalid credentials
      | Url                                                          | FirstName | LastName | EmailAddress | Comment                               | Status                       |
      | http://www.webdriveruniversity.com/Contact-Us/contactus.html | James     | Bond     | jamesbond@   | This is not James Bond 007 but BatMan | Error: Invalid email address |
