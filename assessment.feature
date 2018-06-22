Feature: Using OrangeHRM
  As an administrator
  I want to manage the database
  So that the details it holds are up-to-date and relevant
 
Scenario: Add an employee to the CRM
  Given the Add Employee Tab
  When I fill out the Employee Details correctly
  And I choose to create Login Details
  And I fill out the Login Details correctly
  And I click the Save button
  Then I can see information about the user
  


