$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/Assignment.feature");
formatter.feature({
  "name": "Assignment",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@Assignment"
    }
  ]
});
formatter.scenarioOutline({
  "name": "To Add a new pet to the store using Selenium",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@addNewPetToStore"
    },
    {
      "name": "@test1"
    }
  ]
});
formatter.step({
  "name": "I want to initiate the config file",
  "keyword": "Given "
});
formatter.step({
  "name": "I launch the browser",
  "keyword": "And "
});
formatter.step({
  "name": "I open applicationURL and validate the title \"\u003ctitle\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "I click on add new pet \"\u003cjsonfilename\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "The response status should be \"\u003cstatus\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "I should retrieve the petId from UI and store it in Env variable \"petId\"",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "resourceName",
        "status",
        "title",
        "jsonfilename"
      ]
    },
    {
      "cells": [
        "URL",
        "200",
        "Swagger Petstore",
        "addnewpet"
      ]
    }
  ]
});
formatter.scenario({
  "name": "To Add a new pet to the store using Selenium",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Assignment"
    },
    {
      "name": "@addNewPetToStore"
    },
    {
      "name": "@test1"
    }
  ]
});
formatter.write("********************Scenario begins************************");
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I want to initiate the config file",
  "keyword": "Given "
});
formatter.match({
  "location": "CommonSteps.initiatePropertyFile()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I launch the browser",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.openbrowser()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I open applicationURL and validate the title \"Swagger Petstore\"",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.openPetStorePage(String)"
});
formatter.write("Landed successfully on Swagger petstore page");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I click on add new pet \"addnewpet\"",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.addnewPetInPetStore(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "The response status should be \"200\"",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.responseStatusValidation(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should retrieve the petId from UI and store it in Env variable \"petId\"",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.retrievePetIdAndStoreInEnv(String)"
});
formatter.write("Retrieved Id: 123");
formatter.result({
  "status": "passed"
});
formatter.write("********************Scenario ends************************");
formatter.after({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Find a pet by Id from Test1 using REST API",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@getPetFromStore"
    },
    {
      "name": "@test2"
    }
  ]
});
formatter.step({
  "name": "I pass headers",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "accept",
        "application/json"
      ]
    }
  ]
});
formatter.step({
  "name": "I pass path parameters",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "petId",
        "ENV-petId"
      ]
    }
  ]
});
formatter.step({
  "name": "I perform GET operation \"\u003cresourceName\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "I should get \"\u003cstatus\u003e\" response",
  "keyword": "Then "
});
formatter.step({
  "name": "I should validate Pet response with the pojo class",
  "keyword": "Then "
});
formatter.step({
  "name": "I should retrieve the reponse and store the values in Env variable",
  "keyword": "Then ",
  "rows": [
    {
      "cells": [
        "id",
        "petId"
      ]
    }
  ]
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "resourceName",
        "status"
      ]
    },
    {
      "cells": [
        "petById",
        "200"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Find a pet by Id from Test1 using REST API",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Assignment"
    },
    {
      "name": "@getPetFromStore"
    },
    {
      "name": "@test2"
    }
  ]
});
formatter.write("********************Scenario begins************************");
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I pass headers",
  "rows": [
    {
      "cells": [
        "accept",
        "application/json"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.setHeaders(String,String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I pass path parameters",
  "rows": [
    {
      "cells": [
        "petId",
        "ENV-petId"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.passpathparameters(String,String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I perform GET operation \"petById\"",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.invokeGETOperation(String)"
});
formatter.write("Time taken to respond:3332");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should get \"200\" response",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.validateHttpStatusCode(String)"
});
formatter.write("Response Status code:200");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should validate Pet response with the pojo class",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.validatePetResponseWithPojo()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should retrieve the reponse and store the values in Env variable",
  "rows": [
    {
      "cells": [
        "id",
        "petId"
      ]
    }
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.retrieveValueFromJsonAndStoreInEnv(String,String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.write("********************Scenario ends************************");
formatter.write("**************REQUEST********************");
formatter.write("Request method:\tGET\r\nRequest URI:\thttps://petstore.swagger.io/v2/pet/123\r\nProxy:\t\t\t\u003cnone\u003e\r\nRequest params:\t\u003cnone\u003e\r\nQuery params:\t\u003cnone\u003e\r\nForm params:\t\u003cnone\u003e\r\nPath params:\tpetId\u003d123\r\nHeaders:\t\taccept\u003dapplication/json\r\nCookies:\t\t\u003cnone\u003e\r\nMultiparts:\t\t\u003cnone\u003e\r\nBody:\t\t\t\u003cnone\u003e\r\n");
formatter.write("**************RESPONSE********************");
formatter.write(" 200 OK\r\nDate: Thu, 21 Oct 2021 23:06:51 GMT\r\nContent-Type: application/json\r\nTransfer-Encoding: chunked\r\nConnection: keep-alive\r\nAccess-Control-Allow-Origin: *\r\nAccess-Control-Allow-Methods: GET, POST, DELETE, PUT\r\nAccess-Control-Allow-Headers: Content-Type, api_key, Authorization\r\nServer: Jetty(9.2.9.v20150224)\r\n\r\n{\n    \"id\": 123,\n    \"category\": {\n        \"id\": 0,\n        \"name\": \"string\"\n    },\n    \"name\": \"doggie\",\n    \"photoUrls\": [\n        \"string\"\n    ],\n    \"tags\": [\n        {\n            \"id\": 0,\n            \"name\": \"string\"\n        }\n    ],\n    \"status\": \"available\"\n}\r\n");
formatter.after({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Removal of Pet Id from Test1 with verification of removal using REST API",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@addNewPetToStore"
    },
    {
      "name": "@test3"
    }
  ]
});
formatter.step({
  "name": "I pass path parameters",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "petId",
        "ENV-petId"
      ]
    }
  ]
});
formatter.step({
  "name": "I perform DELETE operation \"\u003cresourceName\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "I should get \"\u003cstatus\u003e\" response",
  "keyword": "Then "
});
formatter.step({
  "name": "I pass path parameters",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "petId",
        "ENV-petId"
      ]
    }
  ]
});
formatter.step({
  "name": "I perform GET operation \"\u003cresourceName\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "I should get \"\u003cgetResponseStatus\u003e\" response",
  "keyword": "Then "
});
formatter.step({
  "name": "I should get the response with the following values",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "code",
        "1"
      ]
    },
    {
      "cells": [
        "type",
        "error"
      ]
    },
    {
      "cells": [
        "message",
        "Pet not found"
      ]
    }
  ]
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "resourceName",
        "status",
        "getResponseStatus"
      ]
    },
    {
      "cells": [
        "petById",
        "200",
        "404"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Removal of Pet Id from Test1 with verification of removal using REST API",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Assignment"
    },
    {
      "name": "@addNewPetToStore"
    },
    {
      "name": "@test3"
    }
  ]
});
formatter.write("********************Scenario begins************************");
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I pass path parameters",
  "rows": [
    {
      "cells": [
        "petId",
        "ENV-petId"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.passpathparameters(String,String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I perform DELETE operation \"petById\"",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.invokePUTOperation(String)"
});
formatter.write("Time taken to respond:3541");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should get \"200\" response",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.validateHttpStatusCode(String)"
});
formatter.write("Response Status code:200");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I pass path parameters",
  "rows": [
    {
      "cells": [
        "petId",
        "ENV-petId"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.passpathparameters(String,String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I perform GET operation \"petById\"",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.invokeGETOperation(String)"
});
formatter.write("Time taken to respond:1609");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should get \"404\" response",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.validateHttpStatusCode(String)"
});
formatter.write("Response Status code:404");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should get the response with the following values",
  "rows": [
    {
      "cells": [
        "code",
        "1"
      ]
    },
    {
      "cells": [
        "type",
        "error"
      ]
    },
    {
      "cells": [
        "message",
        "Pet not found"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.verifyJsonResponseValues(String,String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.write("********************Scenario ends************************");
formatter.write("**************REQUEST********************");
formatter.write("Request method:\tDELETE\r\nRequest URI:\thttps://petstore.swagger.io/v2/pet/123\r\nProxy:\t\t\t\u003cnone\u003e\r\nRequest params:\t\u003cnone\u003e\r\nQuery params:\t\u003cnone\u003e\r\nForm params:\t\u003cnone\u003e\r\nPath params:\tpetId\u003d123\r\nHeaders:\t\tAccept\u003d*/*\r\nCookies:\t\t\u003cnone\u003e\r\nMultiparts:\t\t\u003cnone\u003e\r\nBody:\t\t\t\u003cnone\u003e\r\nRequest method:\tGET\r\nRequest URI:\thttps://petstore.swagger.io/v2/pet/123\r\nProxy:\t\t\t\u003cnone\u003e\r\nRequest params:\t\u003cnone\u003e\r\nQuery params:\t\u003cnone\u003e\r\nForm params:\t\u003cnone\u003e\r\nPath params:\tpetId\u003d123\r\nHeaders:\t\tAccept\u003d*/*\r\nCookies:\t\t\u003cnone\u003e\r\nMultiparts:\t\t\u003cnone\u003e\r\nBody:\t\t\t\u003cnone\u003e\r\n");
formatter.write("**************RESPONSE********************");
formatter.write(" 200 OK\r\nDate: Thu, 21 Oct 2021 23:06:55 GMT\r\nContent-Type: application/json\r\nTransfer-Encoding: chunked\r\nConnection: keep-alive\r\nAccess-Control-Allow-Origin: *\r\nAccess-Control-Allow-Methods: GET, POST, DELETE, PUT\r\nAccess-Control-Allow-Headers: Content-Type, api_key, Authorization\r\nServer: Jetty(9.2.9.v20150224)\r\n\r\n{\n    \"code\": 200,\n    \"type\": \"unknown\",\n    \"message\": \"123\"\n}\r\n");
formatter.write("**************RESPONSE********************");
formatter.write(" 404 Not Found\r\nDate: Thu, 21 Oct 2021 23:06:57 GMT\r\nContent-Type: application/json\r\nTransfer-Encoding: chunked\r\nConnection: keep-alive\r\nAccess-Control-Allow-Origin: *\r\nAccess-Control-Allow-Methods: GET, POST, DELETE, PUT\r\nAccess-Control-Allow-Headers: Content-Type, api_key, Authorization\r\nServer: Jetty(9.2.9.v20150224)\r\n\r\n{\n    \"code\": 1,\n    \"type\": \"error\",\n    \"message\": \"Pet not found\"\n}\r\n");
formatter.after({
  "status": "passed"
});
formatter.scenarioOutline({
  "name": "Failed test so that it contains a failure report with necessary information",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@fail"
    },
    {
      "name": "@test4"
    }
  ]
});
formatter.step({
  "name": "I pass path parameters",
  "keyword": "And ",
  "rows": [
    {
      "cells": [
        "petId",
        "$$$$--"
      ]
    }
  ]
});
formatter.step({
  "name": "I perform DELETE operation \"\u003cresourceName\u003e\"",
  "keyword": "And "
});
formatter.step({
  "name": "I should get \"\u003cstatus\u003e\" response",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "resourceName",
        "status",
        "getResponseStatus"
      ]
    },
    {
      "cells": [
        "petById",
        "200",
        "200"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Failed test so that it contains a failure report with necessary information",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Assignment"
    },
    {
      "name": "@fail"
    },
    {
      "name": "@test4"
    }
  ]
});
formatter.write("********************Scenario begins************************");
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "I pass path parameters",
  "rows": [
    {
      "cells": [
        "petId",
        "$$$$--"
      ]
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.passpathparameters(String,String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I perform DELETE operation \"petById\"",
  "keyword": "And "
});
formatter.match({
  "location": "CommonSteps.invokePUTOperation(String)"
});
formatter.write("Time taken to respond:2371");
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "I should get \"200\" response",
  "keyword": "Then "
});
formatter.match({
  "location": "CommonSteps.validateHttpStatusCode(String)"
});
formatter.write("Response Status code:404");
formatter.result({
  "error_message": "java.lang.AssertionError: Response http statusCode is not 200\r\n\tat org.junit.Assert.fail(Assert.java:89)\r\n\tat org.junit.Assert.assertTrue(Assert.java:42)\r\n\tat com.kamesh.stepdefs.CommonSteps.validateHttpStatusCode(CommonSteps.java:173)\r\n\tat ✽.I should get \"200\" response(src/test/resources/features/Assignment.feature:62)\r\n",
  "status": "failed"
});
formatter.write("********************Scenario ends************************");
formatter.write("**************REQUEST********************");
formatter.write("Request method:\tDELETE\r\nRequest URI:\thttps://petstore.swagger.io/v2/pet/%24%24%24%24--\r\nProxy:\t\t\t\u003cnone\u003e\r\nRequest params:\t\u003cnone\u003e\r\nQuery params:\t\u003cnone\u003e\r\nForm params:\t\u003cnone\u003e\r\nPath params:\tpetId\u003d$$$$--\r\nHeaders:\t\tAccept\u003d*/*\r\nCookies:\t\t\u003cnone\u003e\r\nMultiparts:\t\t\u003cnone\u003e\r\nBody:\t\t\t\u003cnone\u003e\r\n");
formatter.write("**************RESPONSE********************");
formatter.write(" 404 Not Found\r\nDate: Thu, 21 Oct 2021 23:06:59 GMT\r\nContent-Type: application/json\r\nTransfer-Encoding: chunked\r\nConnection: keep-alive\r\nAccess-Control-Allow-Origin: *\r\nAccess-Control-Allow-Methods: GET, POST, DELETE, PUT\r\nAccess-Control-Allow-Headers: Content-Type, api_key, Authorization\r\nServer: Jetty(9.2.9.v20150224)\r\n\r\n{\n    \"code\": 404,\n    \"type\": \"unknown\",\n    \"message\": \"java.lang.NumberFormatException: For input string: \\\"$$$$--\\\"\"\n}\r\n");
formatter.after({
  "status": "passed"
});
});