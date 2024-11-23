package com.example.javaappium.page;

import io.appium.java_client.AppiumBy;
import io.github.ashwith.flutter.FlutterFinder;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.finder = new FlutterFinder(driver);
    }

    private final RemoteWebDriver driver;
    private WebDriverWait wait;
    private FlutterFinder finder;

    private final String iconeMenuBusca2 = "new UiSelector().description('Busca Guia 2 de 5')";
    private final String iconeMenuBusca = "//android.widget.ImageView[@text='Busca Guia 2 de 5']";
    private final String tituloNaveguePor = "//android.view.View[@content-desc='Navegue por:']";
    private final String inputPesquisa = "android.widget.EditText";
    //    private final String produtoEncontrado = "//android.widget.ImageView[@content-desc='12,99 19,26 Dipirona 1g Medley Genérico com 10 Comprimidos']";
    private final String buscandoProduto = "//android.widget.ImageView[contains(@content-desc, '%s')]";
    private final String botaoComprar = "//android.widget.Button[@content-desc='Comprar']";
    private final String botaoMaisQuantidade = "//android.view.View[@content-desc='1']";
    private final String quantidadeProdutoCesta = "//android.widget.ImageView[@content-desc='%d']";
    private final String acaoVerCesta = "//android.view.View[contains(@content-desc, 'Ver cesta')]";
    private final String botaoEntrar = "//android.widget.Button[@content-desc='Entrar']";

    public void fazerCheckout() {
        clicarMenuBusca();
        selecionarProduto();
        sleep(2000L);
    }

    private void clicarMenuBusca() {
//        WebElement menuBuscaEncontrado = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.androidUIAutomator(iconeMenuBusca)));
//        WebElement menuBuscaEncontrado = wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.xpath(iconeMenuBusca)));
        WebElement menuBuscaEncontrado = finder.byText("Busca Guia 2 de 5");
        menuBuscaEncontrado.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(tituloNaveguePor)));
        driver.findElement(AppiumBy.className(inputPesquisa)).sendKeys("Dipirona 1g medley");
        driver.findElement(AppiumBy.className(inputPesquisa)).sendKeys(Keys.ENTER);
        WebElement produtoEncontrado = wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(String.format(buscandoProduto, "Dipirona 1g Medley"))));
        produtoEncontrado.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(botaoComprar)));
    }

    private void selecionarProduto() {
        WebElement botaoMaisQuantidadeEncontrado = driver.findElement(AppiumBy.xpath(botaoMaisQuantidade));
        botaoMaisQuantidadeEncontrado.click();
        driver.findElement(AppiumBy.xpath(botaoComprar)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(String.format(quantidadeProdutoCesta, 2))));
        driver.findElement(AppiumBy.xpath(acaoVerCesta)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.xpath(botaoEntrar)));
    }

    private void sleep(Long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
