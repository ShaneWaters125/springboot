Feature: get a specific actor


  Scenario Outline: getting a specific actor from the db
    Given an actor exists with id <actorid>
    When i request an actors details
    Then the webpage should show the actors "<firstname>" and "<lastname>"
    Examples:
      | actorid | firstname | lastname |
      | 1       | John      | Doe      |
      | 2       | NICK      | WAHLBERG |
      | 3       | BORIS    | JOHNSON  |