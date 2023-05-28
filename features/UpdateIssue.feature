@UpdateIssue
Feature: Update Issue
  I want to use this to check my update feature

  @UI
  Scenario Outline: Validate update issue
    Given user is on homepage
    When user click on report issue button
    And user enter the issue details as "<category>" and "<reproducibility>" and "<severity>" and "<priority>" and "<summary>" and "<description>" click on Submit issue
    Then go to view issue page
    Then User clicks on a issue id
    And User clicks on edit button
    And User updates status as "<status>" and resolution as "<resolution>"
    Then go to view issue page
    Then User clicks on a issue id
    Then validate update issue on issue page with "<status>" and "<resolution>"

    Examples:
      | category                | reproducibility | severity | priority | summary           | description | status    | resolution |
      | [All Projects] Selenium | random          | trivial  | low      | not able to login | description | resolved  | fixed      |
      | [All Projects] Selenium | random          | trivial  | high     | not able to login | description | confirmed | reopened   |
      | [All Projects] Selenium | random          | trivial  | high     | not able to login | description | resolved  | not valid  |