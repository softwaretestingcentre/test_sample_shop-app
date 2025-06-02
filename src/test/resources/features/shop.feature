Feature: Customers can buy artwork

  Scenario: Betty chooses "Unusable Security"
    Given Betty adds 1 item of "Unusable Security" to her basket
    When she checks her basket
    Then she can see her basket contains only 1 item of "Unusable Security"

  Scenario: Betty chooses multiple copies of "Experimental"
    Given Betty adds 3 items of "Experimental" to her basket
    When she checks her basket
    Then she can see her basket contains only 3 items of "Experimental"

#  Scenario: Betty buys "Unusable Security"
#    Given Betty adds "Unusable Security" to her basket
#    When she completes her order
#    Then her order is processed successfully
