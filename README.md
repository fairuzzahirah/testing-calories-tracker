# 🧪 Automation Testing with Cucumber for Laravel Project

This project contains automated end-to-end tests for a **Laravel-based web application** (frontend and backend), written in **Java using Cucumber**, **Gherkin syntax**, and **Selenium WebDriver**.

---

## 📁 Project Structure

```bash
testing-calories-tracker/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── pages/                # Page Object Model (POM) for each page/feature
│   │
│   └── test/
│       └── java/
│           ├── stepdefinition/      # Step definitions matching Gherkin steps
│           ├── runner/              # Cucumber test runner class
│           └── utils/               # ExtentReport manager, Screenshot utility, etc.
│
├── features/                        # Gherkin feature files (*.feature)
│
├── testoutput/
│   └── ExtentReport.html            # Auto-generated HTML report after test run
│
└── screenshots/                     # Captured screenshots for failed steps
```

---

## 🛠️ Tools & Dependencies

- **JDK 21**
- **Cucumber-Java**
- **Selenium WebDriver**
- **JUnit 5**
- **ExtentReports** – for visual HTML reporting
- **WebDriverManager** – for auto browser driver management

---

## 🧬 Feature Files (Gherkin Syntax)

Feature files are located in the `features/` directory and use the `.feature` extension.

Example: `01_register.feature`

```gherkin
Feature: User Registration

  Scenario: Successful registration redirects user to dashboard
    Given User is on the form page
    When User enter username "Frederick"
    And User enter email "AUTO"
    And User enter password "password123"
    And User enter password confirm "password123"
    And User enter age "22"
    And User enter height "175"
    And User enter weight "65"
    And User select gender "female"
    And User select goal "maintain"
    And User select activity level "light"
    And User submits the form
    Then User should be redirected to dashboard





