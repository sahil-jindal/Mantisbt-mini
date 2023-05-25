@FilterIssue
Feature: Filter Issue

  @OI
  Scenario Outline: Validate Filter Issues
    Given user is on the home page
    Then navigate to view issue page
    Then apply priority filter as "<priority>"
    And apply severity filter as "<severity>"
    And apply status filter as "<status>"
    Then validate all filters with values "<priority>" "<severity>" "<status>"

    Examples:
      | priority | severity | status |
      | high     | major    | new    |
      | [any]    | [any]    | [any]  |
      | invalid  | [any]    | new    |
      | low      | major    | new    |
      | none     | major    | new    |
      | [any]    | text     | new    |
      | urgent   | [any]    | none   |