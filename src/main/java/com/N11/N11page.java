package com.N11;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class N11page extends TestBase {

	Xpaths xpath = new Xpaths();

	public Boolean uyeGirisi() {

		waitLoad();
		waitUntilElementClickable(driver, 5, xpath.n11UyeGirisClassName, "className");
		getWebElement(driver, xpath.n11UyeGirisClassName, "className").click();
		waitUntilElementClickable(driver, 5, xpath.n11UyeGirisMailId, "id");
		sendKeyId(driver, xpath.n11UyeGirisMailId, "n11sezgincetin@gmail.com");
		sendKeyId(driver, xpath.n11UyeGirisPassId, "sezgin.n11");
		waitUntilElementClickable(driver, 5, xpath.n11UyeGirisBtnId, "id");
		getWebElement(driver, xpath.n11UyeGirisBtnId, "id").click();
		return checkElementIsDisableClassName(driver, xpath.uyevarmiClassName);
	}

	public Boolean searchProduct() {

		String pageNumber = "2";
		Boolean isRight = false;
		waitUntilElementClickable(driver, 5, xpath.n11MainSearchTextboxId, "id");
		getWebElement(driver, xpath.n11MainSearchTextboxId, "id").click();
		waitUntilElementClickable(driver, 5, xpath.n11MainSearchTextboxId, "id");
		sendKeyId(driver, xpath.n11MainSearchTextboxId, "Samsung");
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
