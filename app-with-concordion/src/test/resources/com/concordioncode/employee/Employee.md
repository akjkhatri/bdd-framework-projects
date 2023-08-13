# Employee Management Feature

### HR can onboard a new employee in the system
Given the HR has onboarded [Andy](- "#firstName") [Micheal](- "#lastName") as a new employee with [1500](- "#salaryAmount") [GBP](- "#currency") monthly stipend [ ](- "theHrHasAllRequiredDataForEmployeeOnboarding(#firstName, #lastName, #salaryAmount, #currency)")
When the HR [submit](- "theHrCreateANewRecordInSystem()") request to create a new record in the system
Then the system should [show](- "theSystemShouldReturnAllExpectedEmployeeDetails()") the expected employee detail

### HR can deactivate an employee from the system
Given the HR already onboarded [Andy](- "#firstName") [Micheal](- "#lastName") as an employee in the system [ ](- "theHRAlreadyHaveAnEmployeeInTheSystem(#firstName, #lastName)")
When HR processed [request](- "theHRProcessedRequestForAccountDeactivation()") for account deactivation
Then the employee account status should be [DE_ACTIVE](- "#status") [ ](- "theEmployeeAccountShouldStatusShouldBe(#status)")

### HR can change the line manager of an employee from the system
Given the HR already onboarded [Andy](- "#firstName") [Micheal](- "#lastName") as an employee in the system [ ](- "theHRAlreadyHaveAnEmployeeInTheSystem(#firstName, #lastName)")
When HR processed request for update line manager for employee to [Nancy](- "#newManager")  [ ](- "theHRUpdateEmployeeLineManager(#newManager)")
Then the employee should show under Nancy team hierarchy [ ](- "theEmployeeShouldBeInNewManagerHierarchy()")