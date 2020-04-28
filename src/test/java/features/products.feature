Feature: Products page

  Scenario Outline: validate promo code alert is visible when clicking on the special offers link
    Given user navigates to "<URL>" website
    When user clicks on Special Offer text link as "<ELEMENTLOCATOR>"
    Then user should be presented with a promo alert as "<STATUS>"

    Examples: 
      | URL                                                                | ELEMENTLOCATOR  | STATUS      |
      | http://www.webdriveruniversity.com/Page-Object-Model/products.html | #special-offers | NEWCUSTOMER |