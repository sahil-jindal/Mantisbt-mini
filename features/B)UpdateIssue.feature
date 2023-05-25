@UpdateIssue
Feature: Update Issue
  I want to use this to check my update feature

  @UI
  Scenario Outline: Validate update issue
    Given User is on currently on homepage
    And User creates an issue with values "<category>" "<reproducibility>" "<severity>" "<priority>" "<summary>" "<description>" "<status>"
    When User click on view issues button
    Then User clicks on a issue id
    And User clicks on edit button
    And User updates status as "<status>" and resolution as "<resolution>"
    Then go to view issue page for updateIssue
    Then click on issue updated
    Then validate update issue on issue page with "<status>" and "<resolution>"
    Then validate update issue on db with "<status>" and "<resolution>"
    Then validate on summary page "<severity>" and "<category>" and "<status>"

    Examples:
      | category                | reproducibility | severity | priority | summary           | description | status    | resolution |
      | [All Projects] Selenium | random          | trivial  | low      | not able to login | description | resolved  | fixed      |
      | [All Projects] Selenium | random          | trivial  | high     | not able to login | description | confirmed | reopened   |
      | [All Projects] Selenium | random          | trivial  | high     | not able to login | description | resolved  | not valid  |