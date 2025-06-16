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




