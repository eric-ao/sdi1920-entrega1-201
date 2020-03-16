package com.uniovi.tests.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uniovi.tests.util.SeleniumUtils;

public class PO_UsersView extends PO_NavView {

	static public void fillForm(WebDriver driver, String busquedap) {
		WebElement search = driver.findElement(By.name("searchText"));
		search.click();
		search.clear();
		search.sendKeys(busquedap);
		
		By boton = By.className("btn");
		driver.findElement(boton).click();
		
	}
	
	static public void checkWelcome(WebDriver driver, int language) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		SeleniumUtils.EsperaCargaPagina(driver, "text", p.getString("users.list.title", language), getTimeout());
	}
	
	static public void checkChangeIdiom(WebDriver driver, String textIdiom1, String textIdiom2, int locale1,
			int locale2) {
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_UsersView.checkWelcome(driver, locale1);
		// Cambiamos a segundo idioma
		PO_UsersView.changeIdiom(driver, textIdiom2);
		// COmprobamos que el texto de bienvenida haya cambiado a segundo idioma
		PO_UsersView.checkWelcome(driver, locale2);
		// Volvemos a Español.
		PO_UsersView.changeIdiom(driver, textIdiom1);
		// Esperamos a que se cargue el saludo de bienvenida en Español
		PO_UsersView.checkWelcome(driver, locale1);
	}
	
}