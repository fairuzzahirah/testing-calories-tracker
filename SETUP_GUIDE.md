# Environment Setup Guide

## Prerequisites Checklist

Before running the automation tests, ensure the following are installed and configured:

### ✅ Required Software

1. **Java Development Kit (JDK) 21+**
   ```bash
   java -version
   # Should show Java 21 or higher
   ```

2. **Apache Maven 3.6+**
   ```bash
   mvn -version
   # Should show Maven 3.6 or higher
   ```

3. **Google Chrome Browser**
   - Latest stable version recommended
   - ChromeDriver is auto-managed by WebDriverManager

4. **Git** (for version control)
   ```bash
   git --version
   ```

### 🌐 Application Setup

1. **Start the Calories Tracker Application**
   - Backend should be running on `http://localhost:8080`
   - Verify by visiting the URL in your browser

2. **Database Setup**
   - Ensure the database is properly configured
   - Demo user should be available: `demo@example.com`

### 🔧 IDE Configuration (Optional)

#### IntelliJ IDEA
1. Import as Maven project
2. Set Project SDK to Java 21
3. Enable Cucumber plugin
4. Configure run configurations for test runners

#### Visual Studio Code
1. Install Java Extension Pack
2. Install Cucumber (Gherkin) Full Support
3. Configure Maven integration

### 🧪 Quick Verification

Run a simple test to verify everything is working:

```bash
# Navigate to project directory
cd testing-calories-tracker

# Clean and compile
mvn clean compile

# Run a single proven test
mvn test -Dtest=TC044Runner

# If successful, you should see:
# Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
# BUILD SUCCESS
```

### 🚨 Troubleshooting

#### Common Issues and Solutions

1. **ChromeDriver Issues**
   ```
   Error: Could not start a new session
   Solution: Update Chrome browser to latest version
   ```

2. **Java Version Issues**
   ```
   Error: Unsupported class file major version
   Solution: Ensure JAVA_HOME points to JDK 21+
   ```

3. **Maven Dependency Issues**
   ```bash
   # Clear Maven cache and reinstall dependencies
   mvn dependency:purge-local-repository
   mvn clean install
   ```

4. **Application Connection Issues**
   ```
   Error: Connection refused to localhost:8080
   Solution: Verify the Calories Tracker app is running
   ```

### 📊 Performance Tips

1. **Parallel Execution**
   - Configure in `pom.xml` for faster test execution
   - Recommended for CI/CD pipelines

2. **Headless Mode**
   - Add system property: `-Dheadless=true`
   - Useful for CI/CD environments

3. **Test Data Management**
   - Use fresh test data for each run
   - Clean up test data after execution

### 🔄 CI/CD Integration

#### GitHub Actions Example
```yaml
name: Automation Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-java@v3
        with:
          java-version: '21'
      - run: mvn test -Dtest=ProvenTestsRunner
```

#### Jenkins Pipeline Example
```groovy
pipeline {
    agent any
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean test -Dtest=SequentialTestRunner'
            }
            post {
                always {
                    publishHTML([
                        allowMissing: false,
                        alwaysLinkToLastBuild: true,
                        keepAll: true,
                        reportDir: 'target/cucumber-html-report-all-tests',
                        reportFiles: 'index.html',
                        reportName: 'Cucumber Report'
                    ])
                }
            }
        }
    }
}
```

### 📧 Support

If you encounter issues not covered here:
1. Check the main README.md
2. Review test execution logs
3. Contact the QA team
