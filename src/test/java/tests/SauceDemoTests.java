package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class SauceDemoTests extends BaseTest {

    private void loginAsStandardUser() {
        new LoginPage(driver).login("standard_user", "secret_sauce");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_list")));
    }

    @Test
    public void validLoginTest() {
        loginAsStandardUser();
        Assert.assertEquals(new InventoryPage(driver).getPageTitle(), "Products");
    }

    @Test
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_password");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"));
    }

    @Test
    public void lockedOutUserTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"));
    }

    @Test
    public void productsAreDisplayedTest() {
        loginAsStandardUser();
        Assert.assertEquals(new InventoryPage(driver).getProductsCount(), 6);
    }

    @Test
    public void sortProductsByPriceLowToHighTest() {
        loginAsStandardUser();
        new InventoryPage(driver).sortByPriceLowToHigh();
        String firstProductPrice = driver.findElement(By.className("inventory_item_price")).getText();
        Assert.assertEquals(firstProductPrice, "$7.99");
    }

    @Test
    public void addAndRemoveProductFromCartTest() {
        loginAsStandardUser();
        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeText(), "1");

        inventoryPage.removeBackpackFromCart();
        Assert.assertEquals(driver.findElements(By.className("shopping_cart_badge")).size(), 0);
    }

    @Test
    public void cartDisplaysAddedProductTest() {
        loginAsStandardUser();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart");
        Assert.assertEquals(cartPage.getCartItemsCount(), 1);
    }

    @Test
    public void checkoutRequiredInformationValidationTest() {
        loginAsStandardUser();
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();
        new CartPage(driver).clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"));
    }


    @Test
    public void completeCheckoutTest() {

        loginAsStandardUser();

        InventoryPage inventoryPage = new InventoryPage(driver);

        inventoryPage.addBackpackToCart();
        inventoryPage.openCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartPage.getPageTitle(), "Your Cart");
        Assert.assertEquals(cartPage.getCartItemsCount(), 1);

        cartPage.clickCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterCustomerInformation("Esraa", "QA", "12345");
        checkoutPage.clickContinue();

        // Verify that checkout overview is displayed
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("checkout-step-two.html"),
                "User should be on checkout overview page");
        Assert.assertTrue(checkoutPage.getSummaryTotal().contains("Total:"));




        checkoutPage.clickFinish();


        wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
        Assert.assertTrue(driver.getCurrentUrl().endsWith("checkout-complete.html"),
                "User should be redirected to checkout complete page");

        Assert.assertEquals(checkoutPage.getCompleteMessage(), "Thank you for your order!");

        Assert.assertEquals(
                checkoutPage.getCompleteMessage(),
                "Thank you for your order!"
        );
    }



    @Test
    public void logoutTest() {
        loginAsStandardUser();
        new InventoryPage(driver).openMenuAndLogout();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        Assert.assertTrue(driver.findElement(By.id("login-button")).isDisplayed());
    }
}
