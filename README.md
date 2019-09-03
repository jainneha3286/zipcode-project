# zipcode-project
Java maven application to merge the zipcode ranges 

----------------------------------------------------------------------------------------------------------------------
# Problem Statement: 
BACKGROUND

Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5-digit codes. 

For example, if the ranges are:

[94133,94133] [94200,94299] [94600,94699]

Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

PROBLEM

Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input. 

NOTES

- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

EXAMPLES

If the input = [94133,94133] [94200,94299] [94600,94699]
Then the output should be = [94133,94133] [94200,94299] [94600,94699]

If the input = [94133,94133] [94200,94299] [94226,94399]
Then the output should be = [94133,94133] [94200,94399]

Evaluation Guidelines

Your work will be evaluated against the following criteria:
- Successful implementation
- Efficiency of the implementation
- Design choices and overall code organization
- Code quality and best practices

---------------------------------------------------------------------------------------------------------------------

# Assumptions made:
1) Assumed input to come as String For instance input [94133,94133] [94200,94299] [94600,94699] as whole under a string.

# DataStructures used:
1) LinkedList
2) Created maven project to help entension of this project for future work

# Java File Description:
1) App.java --> Main driver than reads the input and drives the zipcode processor
2) ZipCode.java --> Data model to store the lower bound and upper bound of zipcode
3) ZipCodeRangeMerger.java --> To sort based on the lower bound of zipcode from the list and Main logic that merges the zipcode ranges and returns the final list and also helps validating the input and load them to linkedlist
4) ZipCodeValidationException.java --> To throw custom exception for any validation errors 
5) ZipCodeConstant.java --> Defines all the constants for validation messages used in whole project 

# Tests:
1) Wrote Junit tests to validate different scenarios  of input

# Static code Analysis:
1) Used SonarQube tool to perform static code analysis to ensure there are no vulnerabilities, bugs or code smells 

# Sample outputs:

  ![alt text](https://github.com/KarthickMahalingam/ZipcodeProcessor/blob/master/Screenshot%20from%202018-02-25%2012-38-26.png)
  
  
  
