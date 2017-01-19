Narrative: Gmail.com test

Scenario: Authorization is possible using valid Login and Password

Given Open "Gmail.com" login page
When Enter automatization.test@gmail.com as username and fw748fg099a as password
Then Page containing Inbox in title should be opened
