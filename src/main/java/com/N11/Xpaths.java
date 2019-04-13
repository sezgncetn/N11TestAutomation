package com.N11;

public class Xpaths {

	//LoginXpaths
	public String mainSearchTextboxId = "searchData";
	public String signInButtonClassName = "btnSignIn";
	public String mailId = "email";
	public String passwordId = "password";
	public String loginBtnId = "loginButton";
	public String customerCheckClassName = "menuLink";
	
	//SearchProcuctXpaths
	public String searchBtnClassName = "searchBtn";
	public String searchProductTextClassName = "resultText";
	public String paginationPartOneXpath =  "//div[@class='pagination']/a[(text()= '";
	public String paginationPartTwoXpath = "')]";
	public String clickFollowPartOneXpath = "//section[contains(@class,'resultListGroup')]//li[";
	
	//ProcessProduct
	public String clickFollowPartTwoXpath = "]//span[contains(@class, 'followBtn')]";
	public String myAccountHolderXpath= "//div[contains(@class, 'myAccountHolder')]";
	public String myProductsXpath =  "//a[contains(text(), 'Favori')]";
	public String favProductXpath = "//ul[@class = 'listItemProductList']//a";
	public String deleteFavProductClassName = "deleteProFromFavorites";
	public String deletePopupOkButtonXpath = "//div[@class = 'btnHolder']//span";
	public String empatyWatchListXpath = "//div[contains(@class, 'emptyWatchList')]";
	
}
