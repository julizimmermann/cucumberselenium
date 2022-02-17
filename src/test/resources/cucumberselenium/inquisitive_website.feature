Feature: InQuisitive Website

  Scenario: Open Main Page
    Given inquisitive.nl is open
    When I click on the start page link
    Then The page title should start with "InQuisitive"

  Scenario: Open Blogs Page
    Given inquisitive.nl is open
    When I click on the sandwich menu
    And I click on blogs
    Then The page header should be "BLOGS"
