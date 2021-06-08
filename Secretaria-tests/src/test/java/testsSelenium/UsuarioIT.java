package testsSelenium;

// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class UsuarioIT {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  private static final String UNIDAD_PERSITENCIA_PRUEBAS = "SecretariaPU";
  
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    
    BaseDatos.inicializaBaseDatos(UNIDAD_PERSITENCIA_PRUEBAS);
    
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void comprobarSesion() {
    driver.get("http://localhost:8080/Secretaria-war/faces/DatosAdmin.xhtml");
    driver.manage().window().setSize(new Dimension(1680, 900));
    driver.findElement(By.id("botonAlumnos")).click();
    driver.findElement(By.cssSelector("html")).click();
    assertThat(driver.findElement(By.cssSelector(".centrarTexto")).getText(), is("No se ha iniciado sesion"));
    driver.findElement(By.cssSelector("td:nth-child(3) > .centrado")).click();
    driver.findElement(By.cssSelector(".centrarTexto")).click();
    assertThat(driver.findElement(By.cssSelector(".centrarTexto")).getText(), is("No se ha iniciado sesion"));
    driver.findElement(By.cssSelector("td:nth-child(5) > .centrado")).click();
    driver.findElement(By.id("botonUsuarios")).click();
    driver.findElement(By.cssSelector(".centrarTexto")).click();
    assertThat(driver.findElement(By.cssSelector(".centrarTexto")).getText(), is("No se ha iniciado sesion"));
    driver.findElement(By.cssSelector("td:nth-child(4) > .centrado")).click();
    driver.findElement(By.id("botonAlumnos")).click();
    driver.findElement(By.cssSelector("td:nth-child(5) > .centrado")).click();
    driver.findElement(By.id("j_idt15:botonCerrarSesion")).click();
  }
  @Test
  public void validarAccesoAdmin() {
    driver.get("http://localhost:8080/Secretaria-war/");
    driver.manage().window().setSize(new Dimension(1028, 723));
    driver.findElement(By.id("inicioSesion:usuario")).click();
    driver.findElement(By.id("inicioSesion:usuario")).sendKeys("admin");
    driver.findElement(By.id("inicioSesion:password")).sendKeys("admin");
    driver.findElement(By.id("inicioSesion:botonInicioSesion")).click();
    driver.findElement(By.cssSelector("table:nth-child(1) td:nth-child(1) > .centrado")).click();
    driver.findElement(By.id("botonUsuarios")).click();
    driver.findElement(By.cssSelector("tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1)")).getText(), is("admin"));
    driver.findElement(By.id("Usuarios:Nombre")).click();
    driver.findElement(By.id("Usuarios:Nombre")).sendKeys("admin");
    driver.findElement(By.id("Usuarios:botonBuscarUsuario")).click();
    driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(1)")).getText(), is("admin"));
    driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(3)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(3)")).getText(), is("Admin"));
    driver.findElement(By.cssSelector("td:nth-child(3) > .centrado")).click();
    driver.findElement(By.id("Usuarios:j_idt9:0:botonEliminarUsuario")).click();
    driver.findElement(By.cssSelector("tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) > tr:nth-child(1) > td:nth-child(1)")).getText(), is("admin"));
    driver.findElement(By.cssSelector("td:nth-child(4) > .centrado")).click();
    driver.findElement(By.id("j_idt15:botonCerrarSesion")).click();
  }
  @Test
  public void validarAccesoAlumno() {
    driver.get("http://localhost:8080/Secretaria-war/");
    driver.manage().window().setSize(new Dimension(1028, 723));
    driver.findElement(By.id("inicioSesion:usuario")).click();
    driver.findElement(By.id("inicioSesion:usuario")).sendKeys("bvl");
    driver.findElement(By.id("inicioSesion:password")).sendKeys("test");
    driver.findElement(By.id("inicioSesion:botonInicioSesion")).click();
    driver.findElement(By.id("botonMisDatos")).click();
    driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(1)")).getText(), is("bvl"));
    driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(2)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(2)")).getText(), is("Alumno"));
    driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(3)")).click();
    driver.findElement(By.cssSelector("td:nth-child(6)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(3)")).getText(), is("125678A"));
    driver.findElement(By.cssSelector(".boton")).click();
    driver.findElement(By.id("j_idt8:botonCerrarSesion")).click();
  }
  @Test
  public void registrarUsuario() {
    driver.get("http://localhost:8080/Secretaria-war/");
    driver.manage().window().setSize(new Dimension(1028, 723));
    driver.findElement(By.id("inicioSesion:botonRegistro")).click();
    driver.findElement(By.id("registroUsuarios:dni")).click();
    driver.findElement(By.id("registroUsuarios:dni")).sendKeys("123456A");
    driver.findElement(By.id("registroUsuarios:usuario")).sendKeys("Fran");
    driver.findElement(By.id("registroUsuarios:password")).sendKeys("test");
    driver.findElement(By.id("registroUsuarios:confirmacionpassword")).sendKeys("test");
    driver.findElement(By.id("registroUsuarios:botonRegistro")).click();
    driver.findElement(By.id("inicioSesion:usuario")).click();
    driver.findElement(By.id("inicioSesion:usuario")).sendKeys("Fran");
    driver.findElement(By.id("inicioSesion:password")).sendKeys("test");
    driver.findElement(By.id("inicioSesion:botonInicioSesion")).click();
    driver.findElement(By.id("botonMisDatos")).click();
    driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(1)")).getText(), is("Fran"));
    driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(2)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(2)")).getText(), is("Alumno"));
    driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(3)")).click();
    assertThat(driver.findElement(By.cssSelector("tbody:nth-child(2) td:nth-child(3)")).getText(), is("123456A"));
    driver.findElement(By.cssSelector(".boton")).click();
    driver.findElement(By.id("j_idt8:botonCerrarSesion")).click();
  }
  @Test
  public void eliminarUsuario() {
    driver.get("http://localhost:8080/Secretaria-war/");
    driver.manage().window().setSize(new Dimension(1028, 723));
    driver.findElement(By.id("inicioSesion:usuario")).click();
    driver.findElement(By.id("inicioSesion:usuario")).sendKeys("admin");
    driver.findElement(By.id("inicioSesion:password")).sendKeys("admin");
    driver.findElement(By.id("inicioSesion:botonInicioSesion")).click();
    driver.findElement(By.cssSelector("table:nth-child(1) td:nth-child(1) > .centrado")).click();
    driver.findElement(By.id("botonUsuarios")).click();
    driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).click();
    assertThat(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).getText(), is("bvl"));
    {
      List<WebElement> elements = driver.findElements(By.id("Usuarios:j_idt9:1:botonEliminarUsuario"));
      assert(elements.size() > 0);
    }
    driver.findElement(By.id("Usuarios:Nombre")).click();
    driver.findElement(By.id("Usuarios:Nombre")).sendKeys("bvl");
    driver.findElement(By.id("Usuarios:botonBuscarUsuario")).click();
    {
      List<WebElement> elements = driver.findElements(By.id("Usuarios:j_idt9:0:botonEliminarUsuario"));
      assert(elements.size() == 0);
    }
    driver.findElement(By.cssSelector("td:nth-child(3) > .centrado")).click();
    driver.findElement(By.id("Usuarios:j_idt9:1:botonEliminarUsuario")).click();
    {
      List<WebElement> elements = driver.findElements(By.id("Usuarios:j_idt9:2:botonEliminarUsuario"));
      assert(elements.size() == 0);
    }
    driver.findElement(By.cssSelector("td:nth-child(4) > .centrado")).click();
    driver.findElement(By.id("j_idt15:botonCerrarSesion")).click();
  }
}
