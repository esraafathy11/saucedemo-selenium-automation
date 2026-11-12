# SauceDemo UI Automation Framework

UI test automation framework for [SauceDemo](https://www.saucedemo.com/), built with **Java, Selenium WebDriver, TestNG and Maven** using the **Page Object Model (POM)** design pattern.

## Tech Stack

- Java 21
- Selenium WebDriver 4.52.0
- TestNG 7.10.2
- Maven
- IntelliJ IDEA

## Project Structure

```
SauceDemoAutomation
├── pom.xml
├── testng.xml
├── README.md
└── src
    └── test
        └── java
            ├── base
            │   └── BaseTest.java
            ├── pages
            │   ├── LoginPage.java
            │   ├── InventoryPage.java
            │   ├── CartPage.java
            │   └── CheckoutPage.java
            └── tests
                └── SauceDemoTests.java
```

## Test Coverage

| # | Scenario |
|---|----------|
| 1 | Valid login |
| 2 | Invalid login |
| 3 | Locked-out user |
| 4 | Products are displayed |
| 5 | Sort products by price (low to high) |
| 6 | Add and remove a product from the cart |
| 7 | Cart displays the added product |
| 8 | Checkout required-field validation |
| 9 | Complete checkout (end-to-end purchase flow) |
| 10 | Logout |

## Test Accounts

SauceDemo publishes these demo credentials on its login page:

- `standard_user`
- `locked_out_user`
- `problem_user`
- `performance_glitch_user`

Password for all users: `secret_sauce`

## Prerequisites

- JDK 21
- Maven
- Google Chrome (ChromeDriver is managed automatically by Selenium Manager)

## How to Run

```bash
# Run all tests
mvn clean test

# Run a single test
mvn -Dtest=SauceDemoTests#completeCheckoutTest test
```

On Windows, if Maven or `JAVA_HOME` is not configured in the terminal:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
& "C:\Tools\apache-maven-3.10.0\bin\mvn.cmd" clean test
```

## Key Design Decisions

- **Page Object Model:** locators and page actions are separated from test logic.
- **Explicit waits** (`WebDriverWait`) instead of `Thread.sleep` to keep tests stable.
- **Isolated tests:** each test runs in a fresh browser session that is closed after execution.
- **Chrome options** disable the password manager and leak-detection popups that can interfere with UI interactions.

## Author

Esraa Fathy
