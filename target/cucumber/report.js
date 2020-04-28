$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("contactus.feature");
formatter.feature({
  "line": 2,
  "name": "Verifying contact us form functionality of webdriveruniversity.com",
  "description": "       As a user of webdriveruniversity.com\n       User want to pass a message\n       In order to complain about an placed order",
  "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@ContactUs"
    }
  ]
});
formatter.scenarioOutline({
  "line": 7,
  "name": "Submit valid data via contact us form",
  "description": "",
  "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-valid-data-via-contact-us-form",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 8,
  "name": "user navigates to webdriveruniversity contact us form \"\u003cUrl\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "user enters a valid firstname as \"\u003cFirstName\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "user enters a valid lastname as \"\u003cLastName\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "user enters a valid email address as \"\u003cEmailAddress\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "user enters comment as \"\u003cComment\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "user clicks on the submit button\"",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "user gets successful welcome message as \"\u003cStatus\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 16,
  "name": "Valid credentials",
  "description": "",
  "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-valid-data-via-contact-us-form;valid-credentials",
  "rows": [
    {
      "cells": [
        "Url",
        "FirstName",
        "LastName",
        "EmailAddress",
        "Comment",
        "Status"
      ],
      "line": 17,
      "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-valid-data-via-contact-us-form;valid-credentials;1"
    },
    {
      "cells": [
        "http://www.webdriveruniversity.com/Contact-Us/contactus.html",
        "James",
        "Bond",
        "jamesbond@aol.com",
        "This is James Bond 007",
        "Thank You for your Message!"
      ],
      "line": 18,
      "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-valid-data-via-contact-us-form;valid-credentials;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 22113230700,
  "status": "passed"
});
formatter.scenario({
  "line": 18,
  "name": "Submit valid data via contact us form",
  "description": "",
  "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-valid-data-via-contact-us-form;valid-credentials;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@ContactUs"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "user navigates to webdriveruniversity contact us form \"http://www.webdriveruniversity.com/Contact-Us/contactus.html\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 9,
  "name": "user enters a valid firstname as \"James\"",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "user enters a valid lastname as \"Bond\"",
  "matchedColumns": [
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "user enters a valid email address as \"jamesbond@aol.com\"",
  "matchedColumns": [
    3
  ],
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "user enters comment as \"This is James Bond 007\"",
  "matchedColumns": [
    4
  ],
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "user clicks on the submit button\"",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "user gets successful welcome message as \"Thank You for your Message!\"",
  "matchedColumns": [
    5
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "http://www.webdriveruniversity.com/Contact-Us/contactus.html",
      "offset": 55
    }
  ],
  "location": "ContactUsSteps.user_navigates_to_webdriveruniversity_contact_us_form(String)"
});
formatter.result({
  "duration": 11716354500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "James",
      "offset": 34
    }
  ],
  "location": "ContactUsSteps.user_enters_a_valid_firstname_as(String)"
});
formatter.result({
  "duration": 796055800,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Bond",
      "offset": 33
    }
  ],
  "location": "ContactUsSteps.user_enters_a_valid_lastname_as(String)"
});
formatter.result({
  "duration": 387806100,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "jamesbond@aol.com",
      "offset": 38
    }
  ],
  "location": "ContactUsSteps.user_enters_a_valid_email_address_as(String)"
});
formatter.result({
  "duration": 584165500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "This is James Bond 007",
      "offset": 24
    }
  ],
  "location": "ContactUsSteps.user_enters_comment_as(String)"
});
formatter.result({
  "duration": 592675000,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsSteps.user_clicks_on_the_submit_button()"
});
formatter.result({
  "duration": 1891001300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Thank You for your Message!",
      "offset": 41
    }
  ],
  "location": "ContactUsSteps.user_gets_successful_welcome_message_as(String)"
});
formatter.result({
  "duration": 106763100,
  "status": "passed"
});
formatter.after({
  "duration": 2183174300,
  "status": "passed"
});
formatter.scenarioOutline({
  "line": 20,
  "name": "Submit invalid data via contact us form",
  "description": "",
  "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-invalid-data-via-contact-us-form",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 21,
  "name": "user navigates to webdriveruniversity contact us form \"\u003cUrl\u003e\"",
  "keyword": "Given "
});
formatter.step({
  "line": 22,
  "name": "user enters a valid firstname as \"\u003cFirstName\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "user enters a valid lastname as \"\u003cLastName\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "user enters an invalid email address as \"\u003cEmailAddress\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "user enters comment as \"\u003cComment\u003e\"",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "user clicks on the submit button\"",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "user gets an error welcome message as \"\u003cStatus\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 29,
  "name": "Invalid credentials",
  "description": "",
  "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-invalid-data-via-contact-us-form;invalid-credentials",
  "rows": [
    {
      "cells": [
        "Url",
        "FirstName",
        "LastName",
        "EmailAddress",
        "Comment",
        "Status"
      ],
      "line": 30,
      "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-invalid-data-via-contact-us-form;invalid-credentials;1"
    },
    {
      "cells": [
        "http://www.webdriveruniversity.com/Contact-Us/contactus.html",
        "James",
        "Bond",
        "jamesbond@",
        "This is not James Bond 007 but BatMan",
        "Error: Invalid email address"
      ],
      "line": 31,
      "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-invalid-data-via-contact-us-form;invalid-credentials;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 7094600700,
  "status": "passed"
});
formatter.scenario({
  "line": 31,
  "name": "Submit invalid data via contact us form",
  "description": "",
  "id": "verifying-contact-us-form-functionality-of-webdriveruniversity.com;submit-invalid-data-via-contact-us-form;invalid-credentials;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 1,
      "name": "@ContactUs"
    }
  ]
});
formatter.step({
  "line": 21,
  "name": "user navigates to webdriveruniversity contact us form \"http://www.webdriveruniversity.com/Contact-Us/contactus.html\"",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 22,
  "name": "user enters a valid firstname as \"James\"",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 23,
  "name": "user enters a valid lastname as \"Bond\"",
  "matchedColumns": [
    2
  ],
  "keyword": "When "
});
formatter.step({
  "line": 24,
  "name": "user enters an invalid email address as \"jamesbond@\"",
  "matchedColumns": [
    3
  ],
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "user enters comment as \"This is not James Bond 007 but BatMan\"",
  "matchedColumns": [
    4
  ],
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "user clicks on the submit button\"",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "user gets an error welcome message as \"Error: Invalid email address\"",
  "matchedColumns": [
    5
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "http://www.webdriveruniversity.com/Contact-Us/contactus.html",
      "offset": 55
    }
  ],
  "location": "ContactUsSteps.user_navigates_to_webdriveruniversity_contact_us_form(String)"
});
formatter.result({
  "duration": 11165791600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "James",
      "offset": 34
    }
  ],
  "location": "ContactUsSteps.user_enters_a_valid_firstname_as(String)"
});
formatter.result({
  "duration": 491308600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Bond",
      "offset": 33
    }
  ],
  "location": "ContactUsSteps.user_enters_a_valid_lastname_as(String)"
});
formatter.result({
  "duration": 387079400,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "jamesbond@",
      "offset": 41
    }
  ],
  "location": "ContactUsSteps.user_enters_an_invalid_email_address_as(String)"
});
formatter.result({
  "duration": 468517900,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "This is not James Bond 007 but BatMan",
      "offset": 24
    }
  ],
  "location": "ContactUsSteps.user_enters_comment_as(String)"
});
formatter.result({
  "duration": 818746400,
  "status": "passed"
});
formatter.match({
  "location": "ContactUsSteps.user_clicks_on_the_submit_button()"
});
formatter.result({
  "duration": 876472500,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Error: Invalid email address",
      "offset": 39
    }
  ],
  "location": "ContactUsSteps.user_gets_an_error_welcome_message_as(String)"
});
formatter.result({
  "duration": 136467400,
  "status": "passed"
});
formatter.after({
  "duration": 3004293900,
  "status": "passed"
});
formatter.uri("products.feature");
formatter.feature({
  "line": 1,
  "name": "Products page",
  "description": "",
  "id": "products-page",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "line": 3,
  "name": "validate promo code alert is visible when clicking on the special offers link",
  "description": "",
  "id": "products-page;validate-promo-code-alert-is-visible-when-clicking-on-the-special-offers-link",
  "type": "scenario_outline",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "user navigates to \"\u003cURL\u003e\" website",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "user clicks on Special Offer text link as \"\u003cELEMENTLOCATOR\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "user should be presented with a promo alert as \"\u003cSTATUS\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 8,
  "name": "",
  "description": "",
  "id": "products-page;validate-promo-code-alert-is-visible-when-clicking-on-the-special-offers-link;",
  "rows": [
    {
      "cells": [
        "URL",
        "ELEMENTLOCATOR",
        "STATUS"
      ],
      "line": 9,
      "id": "products-page;validate-promo-code-alert-is-visible-when-clicking-on-the-special-offers-link;;1"
    },
    {
      "cells": [
        "http://www.webdriveruniversity.com/Page-Object-Model/products.html",
        "#special-offers",
        "NEWCUSTOMER"
      ],
      "line": 10,
      "id": "products-page;validate-promo-code-alert-is-visible-when-clicking-on-the-special-offers-link;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 7768628400,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "validate promo code alert is visible when clicking on the special offers link",
  "description": "",
  "id": "products-page;validate-promo-code-alert-is-visible-when-clicking-on-the-special-offers-link;;2",
  "type": "scenario",
  "keyword": "Scenario Outline"
});
formatter.step({
  "line": 4,
  "name": "user navigates to \"http://www.webdriveruniversity.com/Page-Object-Model/products.html\" website",
  "matchedColumns": [
    0
  ],
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "user clicks on Special Offer text link as \"#special-offers\"",
  "matchedColumns": [
    1
  ],
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "user should be presented with a promo alert as \"NEWCUSTOMER\"",
  "matchedColumns": [
    2
  ],
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "http://www.webdriveruniversity.com/Page-Object-Model/products.html",
      "offset": 19
    }
  ],
  "location": "ProductSteps.user_navigates_to_something_website(String)"
});
formatter.result({
  "duration": 12427082600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "#special-offers",
      "offset": 43
    }
  ],
  "location": "ProductSteps.user_clicks_on_Special_Offer_text_link_as(String)"
});
formatter.result({
  "duration": 4952860300,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "NEWCUSTOMER",
      "offset": 48
    }
  ],
  "location": "ProductSteps.user_should_be_presented_with_a_promo_alert_as(String)"
});
formatter.result({
  "duration": 3498385700,
  "status": "passed"
});
formatter.after({
  "duration": 2954829100,
  "status": "passed"
});
});