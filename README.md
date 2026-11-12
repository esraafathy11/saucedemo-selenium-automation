# SauceDemo UI Automation Project

A beginner-friendly UI automation project for https://www.saucedemo.com/

## Tools
- Java 21
- Selenium WebDriver 4.27.0
- TestNG 7.10.2
- Maven
- IntelliJ IDEA

## Project structure

```text
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

## Test coverage
1. Valid login
2. Invalid login
3. Locked-out user
4. Products are displayed
5. Sort products by price (low to high)
6. Add and remove a product from cart
7. Cart displays the added product
8. Checkout required-field validation
9. Complete checkout
10. Logout

## Test account
- Username: `standard_user`
- Password: `secret_sauce`

Other useful SauceDemo users:
- `locked_out_user`
- `problem_user`
- `performance_glitch_user`

Password for the demo users: `secret_sauce`

## Requirements
- JDK 21
- IntelliJ IDEA
- Google Chrome
- Maven

Selenium Manager handles the ChromeDriver automatically in this project.

## How to run

Open the project folder in IntelliJ IDEA and wait for Maven to download dependencies.

Run all tests from the terminal:

```bash
mvn clean test
```

On Windows, if Maven is not added to PATH, use the Maven executable path:

```powershell
& "C:\Tools\apache-maven-3.10.0\bin\mvn.cmd" clean test
```

If JAVA_HOME is not configured in the current terminal, run:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
& "C:\Tools\apache-maven-3.10.0\bin\mvn.cmd" clean test
```

## Notes
- Each test opens a fresh browser and closes it after execution.
- Explicit waits are used for the page transition after login.
- This is intentionally a small learning project. Add more tests only after understanding the existing ones.
