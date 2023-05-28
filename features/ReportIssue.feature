@ReportIssue
Feature: Create a report Issue
  I want to use this template for my feature file

  @RI
  Scenario Outline: Validate report issue
    Given user is on homepage
    When user click on report issue button
    And user enter the issue details as "<category>" and "<reproducibility>" and "<severity>" and "<priority>" and "<summary>" and "<description>" click on Submit issue
    Then go to view issue page
    Then User clicks on a issue id
    Then validate on issue page for values "<category>" and "<reproducibility>" and "<severity>" and "<priority>" and "<summary>" and "<description>"

    Examples:
      | category                | reproducibility | severity | priority  | summary           | description |
      | [All Projects] Selenium | random          | trivial  | low       |                   | description |
      | [All Projects] Selenium | random          | major    | urgent    | not able to login | description |
      | [All Projects] Selnium  | random          | minor    | high      | not able to login | description |
      |                         | random          | feature  | immediate | not able to login | description |
      | [Al Projects] Selenium  | random          | text     | high      | not able to login | description |
      | [All Projects] Selenium | randm           | tweak    | low       | not able to login | description |
      | [All Projects] Selenium | random          | major    | none      | not able to login |             |
