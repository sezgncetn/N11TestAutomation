package com.N11;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class N11page extends TestBase {

	Xpaths xpath = new Xpaths();

	public Boolean login() {

		waitLoad();
		waitUntilElementClickable(driver, 5, xpath.signInButtonClassName, "className");
		getWebElement(driver, xpath.signInButtonClassName, "className").click();
		waitUntilElementClickable(driver, 5, xpath.mailId, "id");
		sendKeyId(driver, xpath.mailId, "n11sezgincetin@gmail.com");
		sendKeyId(driver, xpath.passwordId, "sezgin.n11");
		waitUntilElementClickable(driver, 5, xpath.loginBtnId, "id");
		getWebElement(driver, xpath.loginBtnId, "id").click();
		return checkElementIsDisableClassName(driver, xpath.customerCheckClassName);
	}

	public Boolean searchProduct() {

		String pageNumber = "2";
		Boolean isRight = false;
		waitUntilElementClickable(driver, 5, xpath.mainSearchTextboxId, "id");
		getWebElement(driver, xpath.mainSearchTextboxId, "id").click();
		waitUntilElementClickable(driver, 5, xpath.mainSearchTextboxId, "id");
		sendKeyId(driver, xpath.mainSearchTextboxId, "Samsung");
		waitUntilElementClickable(driver, 5, xpath.searchBtnClassName, "className");
		getWebElement(driver, xpath.searchBtnClassName, "className").click();
		if (getWebElement(driver, xpath.searchProductTextClassName, "className").getText().contains("Samsung")) {

			String paginationXpath = xpath.paginationPartOneXpath + pageNumber + xpath.paginationPartTwoXpath;
			waitUntilElementClickable(driver, 5, paginationXpath, "xpath");
			getWebElement(driver, paginationXpath, "xpath").click();
			if (getWebElement(driver, paginationXpath, "xpath").getText().equalsIgnoreCase(pageNumber)) {

				isRight = true;
			} else
				Assert.fail("İstenilen sayfada değiliz, şu anki sayfa: "
						+ getWebElement(driver, paginationXpath, "xpath").getText());

		} else
			Assert.fail("Arama metni içermiyor...");
		return isRight;
	}

	public void processTheProduct() {

		String productNumber = "3";
		String productXpath = xpath.clickFollowPartOneXpath + productNumber + xpath.clickFollowPartTwoXpath;
		String productNameXpath = xpath.clickFollowPartOneXpath + productNumber + "]//div[@id]";
		waitUntilElementClickable(driver, 6, productXpath, "xpath");
		String productId = getWebElement(driver, productNameXpath, "xpath").getAttribute("id");
		getWebElement(driver, productXpath, "xpath").click();
		Actions action = new Actions(driver);
		WebElement we = getWebElement(driver, xpath.myAccountHolderXpath, "xpath");
		action.moveToElement(we).moveToElement(getWebElement(driver, xpath.myAccountHolderXpath, "xpath")).build().perform();
		waitUntilElementClickable(driver, 5, xpath.myProductsXpath, "xpath");
		getWebElement(driver, xpath.myProductsXpath, "xpath").click();
		waitLoad();
		String favProductUrl = getWebElement(driver, xpath.favProductXpath, "xpath").getAttribute("href");
		if (favProductUrl.contains(productId)) {
			
			getWebElement(driver, xpath.favProductXpath, "xpath").click();
			waitUntilElementClickable(driver, 5, xpath.deleteFavProductClassName, "className");
			getWebElement(driver, xpath.deleteFavProductClassName, "className").click();
			waitUntilElementVisable(driver, 5, xpath.deletePopupOkButtonXpath, "xpath");
			getWebElement(driver, xpath.deletePopupOkButtonXpath, "xpath").click();
			waitLoad();
			Assert.assertEquals(getWebElement(driver, xpath.empatyWatchListXpath, "xpath").getText(), "İzlediğiniz bir ürün bulunmamaktadır.");
		}else
			Assert.fail("Ürünler eşlemiyor...");
	}
}
