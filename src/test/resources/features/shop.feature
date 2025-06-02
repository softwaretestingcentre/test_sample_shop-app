Feature: Customers can buy artwork

  Scenario: Betty chooses a single copy of "Unusable Security"
    Given Betty adds items to her basket
      | Item              | Count | Price |
      | Unusable Security | 1     | 2     |
    When she checks her basket
    Then she can see her basket contains only her chosen items

  Scenario: Betty chooses multiple copies of "Experimental"
    Given Betty adds items to her basket
      | Item         | Count | Price |
      | Experimental | 3     | 128   |
    When she checks her basket
    Then she can see her basket contains only her chosen items

  Scenario: Betty chooses a mix of items
    Given Betty adds items to her basket
      | Item                  | Count | Price |
      | Docker Tooling        | 1     | 8     |
      | Docker Babies         | 3     | 64    |
      | Docker for Developers | 5     | 256   |
    When she checks her basket
    Then she can see her basket contains only her chosen items

#  Scenario: Betty buys "Unusable Security"
#    Given Betty adds "Unusable Security" to her basket
#    When she completes her order
#    Then her order is processed successfully
