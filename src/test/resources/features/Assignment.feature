#Author: Kameshwar Murali
@Assignment
Feature: Assignment

  #Background: Initiating the config file
  @addNewPetToStore @test1
  Scenario Outline: To Add a new pet to the store using Selenium
    Given I want to initiate the config file
    And I launch the browser
    And I open applicationURL and validate the title "<title>"
    And I click on add new pet "<jsonfilename>"
    And The response status should be "<status>"
    #Retrieve the value from the response 
    Then I should retrieve the petId from UI and store it in Env variable "petId"
    

    Examples: 
      | resourceName | status | title            | jsonfilename |
      | URL          |    200 | Swagger Petstore | addnewpet    |

  @getPetFromStore @test2
  Scenario Outline: Find a pet by Id from Test1 using REST API
    And I pass headers
      | accept | application/json |
    And I pass path parameters
      | petId | ENV-petId |
    And I perform GET operation "<resourceName>"
    Then I should get "<status>" response
    Then I should validate Pet response with the pojo class
    #Then I should retrieve the reponse and store the values in Env variable
      #| id | petId |

    Examples: 
      | resourceName | status |
      | petById      |    200 |

  @addNewPetToStore @test3
  Scenario Outline: Removal of Pet Id from Test1 with verification of removal using REST API
    And I pass path parameters
      | petId | ENV-petId |
    And I perform DELETE operation "<resourceName>"
    Then I should get "<status>" response
    #Get Call
    And I pass path parameters
      | petId | ENV-petId |
    And I perform GET operation "<resourceName>"
    Then I should get "<getResponseStatus>" response
    And I should get the response with the following values
      | code    |             1 |
      | type    | error         |
      | message | Pet not found |

    Examples: 
      | resourceName | status | getResponseStatus |
      | petById      |    200 |               404 |

  @fail @test4
  Scenario Outline: Failed test case- Invalid petId
    And I pass path parameters
      | petId | $$$$-- |
    And I perform DELETE operation "<resourceName>"
    Then I should get "<status>" response

    Examples: 
      | resourceName | status | getResponseStatus |
      | petById      |    200 |               200 |
