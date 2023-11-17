import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

public class Script {
    public static void main(String[] args) {
        // Crea el driver
        WebDriver driver = new ChromeDriver();
        // Consigue la p�gina de contacto de Consultor�a Global
        driver.get("https://www.consultoriaglobal.com.ar/cgweb/?page_id=370");
        // Imprime t�tulo en consola
        System.out.println("Nombre de p�gina: \"" + driver.getTitle() + "\"");

        // Encuentra las entradas por xpath
        WebElement nombre = driver.findElement(By.xpath("//input [@name='your-name']"));
        WebElement spanEmail = driver.findElement(By.xpath("//span[@class='wpcf7-form-control-wrap your-email']"));
        WebElement email = spanEmail.findElement(By.xpath("//input[@name='your-email']"));
        WebElement asunto = driver.findElement(By.xpath("//input[@name='your-subject']"));
        WebElement botonEnviar = driver.findElement(By.xpath("//input[@value='Enviar']"));

        String nombreStr = "prueba";
        // La direcci�n de email es inv�lida a prop�sito
        String emailStr = "invalido";
        String asuntoStr = "asunto";

        // Envia la informaci�n
        enviarString(nombre, nombreStr, "nombre");
        enviarString(email, emailStr, "email");
        enviarString(asunto, asuntoStr, "asunto");
        botonEnviar.click();

        // Establece una condici�n de espera para la alerta
        Wait<WebDriver> espera = new WebDriverWait(driver, Duration.ofSeconds(2));
        
        // La condici�n de espera es que exista el texto en la p�gina
        espera.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='wpcf7-not-valid-tip']")));
        System.out.println("Se detect� un error en una de las entradas.");
        
        // Consigue la alerta dentro del span 
        WebElement alerta = spanEmail.findElement(By.xpath("//span[@class='wpcf7-not-valid-tip']"));
        
        // La imprime en consola
        System.out.println("Mensaje de la p�gina:");
        System.out.println("\"" + alerta.getText() + "\"");
        
        driver.quit();
    }
    
    private static void enviarString(WebElement w, String s, String nombreEntrada) {
    	System.out.println("Enviando \"" + s + "\" a campo de " + nombreEntrada + ".");
        w.sendKeys(s);
    }
}
