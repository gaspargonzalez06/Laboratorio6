package com.example;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 * Unit test for simple App.
 */
@RunWith(DataProviderRunner.class)
public class AppTest {

    private WebDriver driver;
    By edadLocalizador = By.name("edad");+
    By generoLocalizador = By.name("genero");
    By btn_consultaLocalizador = By.name("btn_consultar");
    By bodySelector=By.ccsSelector("body");

    @DataProvider
    public static Object [][] proveedorDatos(){
        return new Object[][]{
            {"masculino","18","El valor de la prima anual de seguro segun su genero y edad es : 2000 dolares"},
            {"masculino","24","El valor de la prima anual de seguro segun su genero y edad es : 2000 dolares"},
            {"masculino","25","El valor de la prima anual de seguro segun su genero y edad es : 1000 dolares"},
            {"masculino","64","El valor de la prima anual de seguro segun su genero y edad es : 1000 dolares"},
            {"femenino","65","El valor de la prima anual de seguro segun su genero y edad es : 1500 dolares"},
            {"femenino","18","El valor de la prima anual de seguro segun su genero y edad es : 500 dolares"},
            {"femenino","24","El valor de la prima anual de seguro segun su genero y edad es : 500 dolares"},
            {"femenino","25","El valor de la prima anual de seguro segun su genero y edad es : 500 dolares"},
            {"femenino","-1","La edad no puede ser un valor negativo"},
            {"masculino","-1","La edad no puede ser un valor negativo"},
            {"masculino","0","Debe ingresar su edad para poder realizar contizacion"},
            {"femenino","17","La edad del cotizante debe ser mayor o igual a 18 años"},
            {"femenino","","Debe ingresar su edad para poder realizar la cotización"},
            {"femenino","dieciocho","Debe ingresar su edad para poder realizar la cotización"},
            {"femenino","18.5","Debe ingresar su edad para poder realizar la cotización"}
            

        };

    }
    @Before
    public void setUp( ){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost")
    }
    @Test
    @UserDataProvider("proveedorDatos")
     public void Test testConsultarPrimaAnual(String genero,String edad,String salida_esperada) throws InterruptedExeception {
        Thread.sleep(1000);
        driver.findElement(generoLocalizador).sendKeys(genero);
        driver.findElement(edadLocalizador).sendKeys(edad);
        driver.findElement(btn_consultaLocalizador).click();
        assertEquals(salida_esperada,driver.findElement(bodySelector).getText());
    }

    @After
    public void tearDown(){
       driver.quit()
    }
}
