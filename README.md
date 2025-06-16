Certainly! Here's the full `README.md` in **English** with clear explanations for each folder and file in your `testing-calories-tracker` test automation project:

---

````markdown
# 🧪 Testing Calories Tracker

An automated end-to-end testing framework for a Laravel-based Calories Tracker application (both frontend and backend), built using **Java**, **Selenium WebDriver**, **Cucumber**, and **Gherkin**.

---

## 📁 Project Structure Overview

```bash
testing-calories-tracker/
│
├── src/
│   ├── main/java/
│   │   └── pages/
│   │       # Page Object Model (POM)
│   │       # Contains Java classes representing each web page.
│   │       # These classes define reusable methods to interact with UI elements.
│   │
│   └── test/java/
│       ├── stepdefinition/
│       │   # Step Definitions
│       │   # Implements each Gherkin step (@Given, @When, @Then)
│       │   # and connects them to the automation logic.
│       │
│       ├── runner/
│       │   # Test Runner Class
│       │   # Contains the main class to run Cucumber features.
│       │   # Includes plugin setup, tag filters, and feature path config.
│       │
│       └── utils/
│           # Utility Classes
│           # - `ExtentReportManager.java`: Generates HTML reports.
│           # - `ScreenshotUtil.java`: Captures screenshots on failed steps.
│           # - Other helper tools for reporting and diagnostics.
│
├── features/
│   # Gherkin Feature Files
│   # Test scenarios written in natural language using Given-When-Then.
│   # Describes behavior-driven development (BDD) tests.
│
├── testoutput/
│   └── ExtentReport.html
│       # HTML Test Report
│       # Automatically generated report after test execution.
│       # Includes step status, screenshots, timestamps, and descriptions.
│
└── screenshots/
    # Failure Screenshots
    # This folder stores captured images of the web app when a step fails.
    # Helps in debugging and visual validation.
````

---

## ✅ Tech Stack

* **Java** – Programming language for test implementation
* **Selenium WebDriver** – Browser automation
* **Cucumber** – BDD framework
* **Gherkin** – Syntax for writing test scenarios in plain English
* **ExtentReports** – For generating rich HTML test reports
* **Maven** or **Gradle** – Build and dependency management
* **Laravel** – Web application under test (frontend + backend)

---

## 🔁 How It Works

1. Scenarios are written in `.feature` files inside the `features/` directory.
2. Each step is implemented in `stepdefinition/` as Java methods using Cucumber annotations.
3. Web page actions are handled through classes in the `pages/` package using the Page Object Model (POM) pattern.
4. Tests are executed via the runner in `runner/`, and the results are logged with ExtentReports.
5. Screenshots are automatically captured on failure and embedded into the final report.

---

## 📋 Sample Scenario

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
```

---

## 🚀 How to Run the Tests

> **Requirements:** Java 11+, Maven or Gradle, ChromeDriver (or other driver)

### Using Maven:

```bash
mvn test
```

### Using Gradle:

```bash
gradle test
```

After execution:

* 📊 Test report: `testoutput/ExtentReport.html`
* 📸 Screenshots on failure: `screenshots/`

---

## 👨‍💻 Author

Created by Olaf (QA Engineer)

For questions, improvements, or suggestions, feel free to reach out!

```

---

Let me know if you'd like a downloadable version of this file or for me to generate a `.md` file directly for use.
```
