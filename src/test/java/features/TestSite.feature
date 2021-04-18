Feature: Test the APIs and check the data is correctly displayed

  Scenario: Validate user can get all the cities
    Given user invoke the api for cities
    When the user will get 200 response
    Then user will see all the cities

  Scenario Outline: Validate user can get all attractions for a city (New York)
    Given user invoke the api for "<city>"
    When the user will get 200 response for attractions "<city>"
    Then user will see all the attractions "<city>"
    Examples:
      | city |
      |1|

  Scenario Outline: Validate user can get all attractions in city(New York) where the attraction type is ‘MUSEUM’ and ordered by rating descending
    Given user invoke the api for desc"<city>"&"<type>"&"<sort>"&"<order>"
    When the user will get 200 response for attractions desc"<city>"&"<type>"&"<sort>"&"<order>"
    Then user will see all the attractions desc"<city>"&"<type>"&"<sort>"&"<order>"
    Examples:
      |city |type|sort|order|
      |1|MUSEUM  |rating  |Desc|