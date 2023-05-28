@DeleteIssue
Feature: Delete Feature
  I want to use this to check my delete feature

  @DI
  Scenario Outline: Validate delete issue
    Given user is on homepage
    When user click on report issue button
    And user enter the issue details as "<category>" and "<reproducibility>" and "<severity>" and "<priority>" and "<summary>" and "<description>" click on Submit issue
    Then go to view issue page
    Then User clicks on a issue id
    Then User should reach on a delete issue page and click on delete
    Then go to view issue page
    Then validate for issue deleted

    Examples:
      | category                | reproducibility | severity | priority | summary           | description |
      | [All Projects] Selenium | random          | trivial  | low      | not able to login | description |
      | [All Projects] Selenium | random          | trial    | low      | not able to login | description |