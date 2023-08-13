Feature: Employee Management Feature

Scenario: HR can onboard a new employee in the system
Given the HR has onboarded Andy Micheal as a new employee with 1500 GBP monthly stipend
When the HR submit request to create a new record in the system
Then the system should show the expected employee detail

Scenario: HR can deactivate an employee from the system
Given the HR already onboarded Andy Micheal as an employee in the system
When HR processed request for account deactivation
Then the employee account status should be DE_ACTIVE

Scenario: HR can change the line manager of an employee from the system
Given the HR already onboarded Andy Micheal as an employee in the system
When HR processed request for update line manager for employee to Nancy
Then the employee should show under Nancy team hierarchy
