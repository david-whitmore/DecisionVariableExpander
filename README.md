# DecisionVariableExpander
Expanding out decision-variables for enumerating test-cases is time-consuming, boring, and error-prone, so I wrote this Java command-line tool to help.

## Input
The tool takes a config file as input.  Each config file defines the decision-variables for the test cases, along with all their possible values.

## Output
When you run the tool, it creates a decision-table based on the cartesian-product of the values of the decision variables.

The format of the output is tab separated values (tsv).

