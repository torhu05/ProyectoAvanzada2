
package proyectohotel.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelTest {

	@Test
	public void testGuardarCliente() throws InterruptedException {
		// Inicializa el WebDriver para Chrome
		WebDriver driver = new ChromeDriver();
		
		try{
			// Abre la página web de empleados
			driver.get("http://localhost:8080/Cliente");
			
			int cantidadClienteInicial = 0;//CONSULTO LA CANTIDAD DE EMPLEADOS REALES
			
			new WebDriverWait(driver, ofSeconds(30), ofSeconds(1)).until(titleIs("Empleados"));
			
			//ESPERA 3 SEGUNDOS DESPUES DE CARGAR LA PANTALLA
			//Thread.sleep(3000);
			
			// Localiza el campo de entrada de nombre de usuario
			WebElement cIdentidad = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_identidad']/input"));
			WebElement cNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_nombre']/input"));
			WebElement cApellido = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_apellido']/input"));
			WebElement cCorreo = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_correo']/input"));
			WebElement cTelefono = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_telefono']/input"));
			WebElement cFechaCumpleaños = driver.findElement(By.xpath("//vaadin-number-field[@id='txt_FechaDeCumpleaños']/input"));
			WebElement cSexo = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_Sexo']/input"));
			WebElement cNacionalidad = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_Nacionalidad']/input"));
			WebElement cLugarProcedencia = driver.findElement(By.xpath("//vaadin-text-field[@id='txt_LugarProcedencia']/input"));
	
			WebElement bGuardar = driver.findElement(By.xpath("//vaadin-button[@id='btn_guardar']"));
			WebElement bCancelar = driver.findElement(By.xpath("//vaadin-button[@id='btn_cancelar']"));
			WebElement bEliminar = driver.findElement(By.xpath("//vaadin-button[@id='btn_eliminar']"));
	
			
			// Ingresa el nombre de usuario
			cIdentidad.sendKeys("0801199912345");
			cNombre.sendKeys("Pedro");
			cApellido.sendKeys("Perez");
			cCorreo.sendKeys("grupo1@uth.hn");
			cTelefono.sendKeys("99420000");
			cFechaCumpleaños.sendKeys("23/09/1994");
			cSexo.sendKeys("hombre");
			cNacionalidad.sendKeys("hondureño");
			cLugarProcedencia.sendKeys("roatan");
			
			//ESPERA 3 SEGUNDOS LUEGO DE LLENAR LOS CAMPOS PARA LUEGO DAR CLICK EN EL BOTON GUARDAR
			//Thread.sleep(3000);
			
			bGuardar.click();
			
			int cantidadClienteFinal = 0;//CONSULTO LA CANTIDAD DE EMPLEADOS REALES

			//SI LA CANTIDAD DE EMPLEADOS AL DARLE CLICK A GUARDAR AUMENTA EN 1
			assertTrue(cantidadClienteFinal == (cantidadClienteInicial+1));
		}finally {
			//CIERRA EL NAVEGADOR ABIERTO
			driver.close();
		}
	} 
} 


