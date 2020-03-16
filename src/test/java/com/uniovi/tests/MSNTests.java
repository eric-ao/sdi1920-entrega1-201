package com.uniovi.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_PostView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_RequestView;
import com.uniovi.tests.pageobjects.PO_UsersView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

import org.junit.runners.MethodSorters;

//Ordenamos las pruebas por el nombre del metodo.
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MSNTests {
	
	//En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas)):
	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Usuario\\Desktop\\material\\geckodriver024win64.exe";
	//Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";
	
	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
	System.setProperty("webdriver.firefox.bin", PathFirefox);
	System.setProperty("webdriver.gecko.driver", Geckdriver);
	WebDriver driver = new FirefoxDriver();
	return driver;
	}
	
	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	
	
	
	
	
	
	
	
	

	//1. REGISTRARSE COMO USUARIO.
	//PR01. Registro de usuario con datos válidos.
	@Test
	public void PR01() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		
		// Rellenamos el formulario.
		PO_RegisterView.fillForm(driver, "test@mail.com", "TestAcc", "Account", "12345", "12345");
		
		// Comprobamos que entramos en la sección privada
		SeleniumUtils.textoPresentePagina(driver, "Bienvenidos a la pagina principal");
	}
	
	//PR02. Registro de usuario inválido. Todo vacio.
	@Test
	public void PR02() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		
		// Formulario vacío.
		PO_RegisterView.fillForm(driver, " ", " ", " ", " ", " ");
		PO_View.getP();
		
		// Para verificar que el Error.empty sale en cada campo vacío, los iremos rellenando de tal forma que al final todos esten probados.
		// Comprobamos los errores.
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		
		PO_RegisterView.fillForm(driver, "test2@mail.com", " ", " ", " ", " ");
		PO_View.getP();
		
		// Comprobamos los errores.
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		
		PO_RegisterView.fillForm(driver, "test2@mail.com", "TestAcc", " ", " ", " ");
		PO_View.getP();
		
		// Comprobamos los errores.
		PO_RegisterView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		
		PO_RegisterView.fillForm(driver, "test2@mail.com", "TestAcc", "Account", " ", " ");
		PO_View.getP();		
	}
	
	//PR03. Registro de usuario inválido. Contraseñas no coinciden.
	@Test
	public void PR03() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
				
		// Formulario con contraseñas no coincidentes.
		PO_RegisterView.fillForm(driver, "test2@mail.com", "Test", "Account", "AAAAA", "BBBBB");
		PO_View.getP();
				
		// Comprobamos los errores.
		PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", PO_Properties.getSPANISH());
	}
	
	//PR04. Registro de usuario inválido. Email ya existe.
	@Test
	public void PR04() {
		// Vamos al formulario de registro
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
					
		// Formulario con contraseñas no coincidentes.
		PO_RegisterView.fillForm(driver, "test@mail.com", "Test", "Account", "AAAAA", "AAAAA");
		PO_View.getP();
					
		// Comprobamos los errores.
		PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", PO_Properties.getSPANISH());
	}
	
	
	
	//2. INICIO DE SESIÓN.
	//PR05. Inicio de sesión válido. (Usuario)
	@Test
	public void PR05() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");
		
		// Comprobamos que entramos en la pagina privada de Alumno
		SeleniumUtils.textoPresentePagina(driver, "Usuarios");
	}
	
	//PR06. Inicio de sesión válido. (Administrador)
	@Test
	public void PR06() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin", "admin");
		
		// Comprobamos que entramos en la pagina privada de Alumno
		SeleniumUtils.textoPresentePagina(driver, "Usuarios");
	}
	
	//PR07. Inicio de sesión inválido, campos vacíos.
	@Test
	public void PR07() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, " ", " ");
		PO_View.getP();
		
		// Comprobamos el error.
		PO_LoginView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
	}
	
	//PR08. Inicio de sesión inválido, email correcto, contraseña no.
	@Test
	public void PR08() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
				
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", " ");
		PO_View.getP();
				
		// Comprobamos el error.
		PO_LoginView.checkKey(driver, "Error.login.error", PO_Properties.getSPANISH());
	}
	
	
	
	//3. FIN DE SESIÓN.
	//PR09. Salir de sesión y ser redirigido al inicio de sesión.
	@Test
	public void PR09() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
				
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");

		//Nos desconectamos
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		
		// Comprobamos que estamos en el inicio de sesión.
		SeleniumUtils.textoPresentePagina(driver, "Identificate");
	}
	
	//PR10. Comprobar que no se puede salir de sesión si uno no está autenticado.
	@Test
	public void PR10() {		
		// Intentamos hacer click en disconect, no podemos porque no esta visible.
		try  {
			PO_HomeView.clickOption(driver, "disconect", "class", "btn btn-primary");
		} catch (TimeoutException e) {
			//Hay un timeout puesto qu no puede acceder al botón.
			assertNotNull(e);
		}
	}
	
	
	
	//4. LISTADO DE USUARIOS.
	//PR11. Mostrar el listado de usuarios y comprobar que se muestran todos los existentes.
	@Test
	public void PR11() {		
		//En este punto de las pruebas, debería haber en la base de datos 17 usuarios.
		//15 creados creados al iniciar la app como base.
		//1 creado por la primera prueba.
		//Vamos a iniciar sesión como administrador porque es el rol que nunca se muestra en la lista.
		//Por tanto apareceran 16 usuarios en la lista.
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin", "admin");

		PO_HomeView.clickOption(driver, "user/list", "text", "Usuarios");
		
		// Contamos el número de filas de usuarios.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		
		// Vamos a la siguiente página y comprobamos que hay otras 5 filas.
		PO_HomeView.clickOption(driver, "?page=1", "text", "2");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		
		// Vamos a la siguiente página y comprobamos que hay otras 5 filas.
		PO_HomeView.clickOption(driver, "?page=2", "text", "3");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
		
		// Vamos a la ultima página y comprobamos que solo hay una fila más.
		//PO_HomeView.clickOption(driver, "?page=3", "text", "4");
		//El método utilizado hasta ahora no nos sirve, ya que da un error de assert debido a que en la paginación tanto el botón
		//del 4 como el de Ultima tienen el mismo href.
		//Usamos el navigate excepcionalmente en esta situacion.
		driver.navigate().to("http://localhost:8090/user/list?page=3");
		elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
	}
	
	
	
	//5. BUSCAR USUARIOS.
	//PR12. Búsqueda vacía.
	@Test
	public void PR12() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin", "admin");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "user/list", "text", "Usuarios");
	
		//Buscamos algo.
		PO_UsersView.fillForm(driver, "");
	
		// Contamos el número de filas de usuarios.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 5);
	}
	
	//PR13. Búsqueda inexistente.
	@Test
	public void PR13() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin", "admin");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "user/list", "text", "Usuarios");
			
		//Buscamos algo.
		PO_UsersView.fillForm(driver, "EstoSeguroQueNoExiste");
			
		// Contamos el número de filas de usuarios.
		try {
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 0);
		} catch(TimeoutException e) {
			//Da timeout porque no puede acceder a la tabla ya que no se ha generado ninguna, está vacía.
			assertNotNull(e);
		}
	}
	
	//PR14. Búsqueda existente.
	@Test
	public void PR14() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin", "admin");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "user/list", "text", "Usuarios");
		
		//Buscamos algo.
		PO_UsersView.fillForm(driver, "ted");
			
		// Contamos el número de filas de usuarios.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
	}
	
	
	
	//6. ENVIAR INVITACIÓN DE AMISTAD.
	//PR15. Enviar invitación, comprobar que al usuario le aparece.
	@Test
	public void PR15() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin", "admin");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "user/list", "text", "Usuarios");
		
		//Hacemos click en enviar solicityd.
		PO_UsersView.clickOption(driver, "friendshiprequest/send/3", "text", "Enviar solicitud");
		
		//Nos desconectamos
		PO_UsersView.clickOption(driver, "logout", "class", "btn btn-primary");
		
		//Nos conectamos con la cuenta a la que acabamos de enviar solicitud.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "friendshiprequest/list", "text", "Solicitudes de amistad");
		
		// Contamos el número de filas de peticiones.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
		
	}
	
	//PR16. Comprobar que no se puede reenviar una petición de amistad.
	@Test
	public void PR16() {
		//En la prueba anterior, el admin habia mandado una solicitud a Ted.
		//Ahora mismo el admin no deberia poder mandar otra solicitud a Ted.
		
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		PO_LoginView.fillForm(driver, "admin", "admin");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "user/list", "text", "Usuarios");
		
		try {
			//Hacemos click en enviar solicityd.
			PO_UsersView.clickOption(driver, "friendshiprequest/send/3", "text", "Enviar solicitud");
		} catch (TimeoutException e) {
			//No puede hacer click en el enlace porque no lo muestra.
			assertNotNull(e);
		}
		
	}
	
	
	//7. LISTAS LAS INVITACIONES DE AMISTAD RECIBIDAS.
	//PR17. Mostrar el listado de varias peticiones de amistad recibidas.
	@Test
	public void PR17() {
		//Ya tenemos una solicitud de Admin enviada en la prueba 15.
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "aron@mail.com", "12345");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "user/list", "text", "Usuarios");
				
		//Hacemos click en enviar solicitud.
		PO_UsersView.clickOption(driver, "friendshiprequest/send/3", "text", "Enviar solicitud");
				
		//Nos desconectamos
		PO_UsersView.clickOption(driver, "logout", "class", "btn btn-primary");
		
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "joe@mail.com", "12345");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "user/list", "text", "Usuarios");
				
		//Hacemos click en enviar solicityd.
		PO_UsersView.clickOption(driver, "friendshiprequest/send/3", "text", "Enviar solicitud");
				
		//Nos desconectamos
		PO_UsersView.clickOption(driver, "logout", "class", "btn btn-primary");
		
		//Nos conectamos con la cuenta a la que acabamos de enviar solicitud.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "friendshiprequest/list", "text", "Solicitudes de amistad");
		
		// Contamos el número de filas de peticiones.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 3);	
	}
	
	
	
	//8. ACEPTAR UNA INVITACIÓN.
	//PR18. Aceptar una y comprobar que desaparece del listado.
	@Test
	public void PR18() {
		//Nos conectamos con la cuenta a la que acabamos de enviar solicitud.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");

		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "friendshiprequest/list", "text", "Solicitudes de amistad");
		
		//Aceptamos la primera solicitud
		PO_RequestView.clickOption(driver, "friendshiprequest/accept/19", "text", "Solicitudes de amistad");
		
		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "friendshiprequest/list", "text", "Solicitudes de amistad");
				
		// Contamos el número de filas de peticiones.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 2);
	}
	
	
	//9. LISTADO DE AMIGOS.
	//PR19. Comprobar que el listado de amigos concuerda con los amigos que tiene alguien.
	@Test
	public void PR19() {
		//La forma más sencilla es, siguiendo las pruebas anteriores, acceder a la lista de amigos y ver que el usuario Ted
		//tiene un amigo, el que acepto en la ultima prueba.
		
		//Nos conectamos con la cuenta.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");
		
		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "user/friendList", "text", "Amigos");
		
		// Contamos el número de filas de usuarios.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 1);
	}
	
	//10. INTERNACIONALIZACIÓN.
	//PR20. Comprobar al menos 4 páginas en Español / Inglés.
	@Test
	public void PR20() {
		//Página principal
		PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		SeleniumUtils.esperarSegundos(driver, 2);
		
		//Página login
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		SeleniumUtils.esperarSegundos(driver, 2);
		
		//Página register
		PO_LoginView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		SeleniumUtils.esperarSegundos(driver, 2);
		
		//Página usuarios
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");			
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");				
		// Comprobamos que entramos en la pagina privada de Alumno
		PO_View.checkElement(driver, "text", "Usuarios", -1, -1);
		
		PO_UsersView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
		SeleniumUtils.esperarSegundos(driver, 2);
		
	}
	
	//11. SEGURIDAD.
	//PR21. Intentar acceder a la lista de usuarios sin loguearse.
	@Test
	public void PR21() {
		driver.navigate().to("http://localhost:8090/user/list");
		
		//Comprobamos que estamos en la pagina de login.
		SeleniumUtils.textoPresentePagina(driver, "Identificate");
	}
	
	//PR22. Intentar acceder a una pagina de un usuario si estar autenticado.
	@Test
	public void PR22() {
		driver.navigate().to("http://localhost:8090/user/3");
		
		//Comprobamos que estamos en la pagina de login.
		SeleniumUtils.textoPresentePagina(driver, "Identificate");
	}
	
	//PR23. Intentar acceder a una pagina de administrador como usuario estandar.
	@Test
	public void PR23() {
		//Nos conectamos con la cuenta.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");
		
		//Intentamos acceder desede la barra de navegacion.
		try {
			PO_HomeView.clickOption(driver, "admin/list", "text", "Lista completa de usuarios");
		} catch(TimeoutException e) {
			//No puede.
			assertNotNull(e);
		}
		//Ahora intentamos acceder desde la url directamente.
		driver.navigate().to("http://localhost:8090/admin/list");
		//Comprobamos que estamos en la pagina del error Forbidden 403.
		SeleniumUtils.textoPresentePagina(driver, "HTTP Status 403 – Forbidden");
	}
	
	
	
	//13. USUARIO REGISTRADO: Crear una nueva publicación.
	//PR24. Rellenar una publicación válida.
	@Test
	public void PR24() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");

		// Pinchamos en la opción de menu de Publicaciones: //li[contains(@id, 'posts-menu')]/a
		PO_View.checkElement(driver, "free", "//li[contains(@id,'posts-menu')]/a", -1, 0);
		// Esperamos a aparezca la opción de añadir profesor: //a[contains(@href, 'post/add')]
		PO_View.checkElement(driver, "free", "//a[contains(@href, 'post/add')]", -1, 0);
			
		//Rellenamos las publicacion.
		PO_PostView.fillForm(driver, "Este es el titulo", "Este es el cuerpo de la publicacion");

		//Ahora nos ha llevado a la lista de posts, comprobamos que está:
		SeleniumUtils.textoPresentePagina(driver, "Este es el titulo");
	}
	
	//PR25. Rellenar una publicación inválida, sin título.
	@Test
	public void PR25() {
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");

		// Pinchamos en la opción de menu de Publicaciones: //li[contains(@id, 'posts-menu')]/a
		PO_View.checkElement(driver, "free", "//li[contains(@id,'posts-menu')]/a", -1, 0);
		// Esperamos a aparezca la opción de añadir profesor: //a[contains(@href, 'post/add')]
		PO_View.checkElement(driver, "free", "//a[contains(@href, 'post/add')]", -1, 0);
					
		//Publicacion sin titulo.
		PO_PostView.fillForm(driver, "Tenemos titulo, no cuerpo", " ");
		// Comprobamos el error.
		PO_LoginView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
		
		//Rellenamos solo el titulo para verificar que el error tambien salta con el cuerpo
		PO_PostView.fillForm(driver, " ", "Tenemos cuerpo, no titulo");
		// Comprobamos el error.
		PO_LoginView.checkKey(driver, "Error.empty", PO_Properties.getSPANISH());
	}
	
	
	//14. USUARIO REGISTRADO: Listado de mis publicaciones.
	//PR26. Comprobar que se muestran todas las publicaciones de un usuario.
	@Test
	public void PR26() {
		//Siguiente la linea de las pruebas anteriores, el usuario ted ya tiene una publicacion.
		//Vamos a añadir 2 mas y comprobar que estan todas.
		// Vamos al formulario de logueo.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "ted@mail.com", "12345");
		
		// Pinchamos en la opción de menu de Publicaciones: //li[contains(@id, 'posts-menu')]/a
		PO_View.checkElement(driver, "free", "//li[contains(@id,'posts-menu')]/a", -1, 0);
		// Esperamos a aparezca la opción de añadir profesor: //a[contains(@href, 'post/add')]
		PO_View.checkElement(driver, "free", "//a[contains(@href, 'post/add')]", -1, 0);
							
		//Rellenamos las publicacion.
		PO_PostView.fillForm(driver, "Esta es la segunda publicacion", "Este es el cuerpo de la publicacion");
				
		// Pinchamos en la opción de menu de Publicaciones: //li[contains(@id, 'posts-menu')]/a
		PO_View.checkElement(driver, "free", "//li[contains(@id,'posts-menu')]/a", -1, 0);
		// Esperamos a aparezca la opción de añadir profesor: //a[contains(@href, 'post/add')]
		PO_View.checkElement(driver, "free", "//a[contains(@href, 'post/add')]", -1, 0);
							
		//Rellenamos las publicacion.
		PO_PostView.fillForm(driver, "Esta es la tercera publicacion", "Este es el cuerpo de la publicacion");
		
		//Ahora nos ha llevado a la lista de posts, comprobamos que están los tres:
		SeleniumUtils.textoPresentePagina(driver, "Este es el titulo");
		SeleniumUtils.textoPresentePagina(driver, "Esta es la segunda publicacion");
		SeleniumUtils.textoPresentePagina(driver, "Esta es la tercera publicacion");
	}
	
	
	//17. ADMINISTRADOR: LISTADO DE USUARIOS.
	//PR31. Mostrar el listado de usuarios y comprobar que se muestran todos los que existen en el sistema.
	@Test
	public void PR31() {
		//Siguien el hilo de las pruebas anteriores, debería haber un total de 17 usuarios en la base de datos.
		//Nos conectamos con la cuenta.
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");					
		// Rellenamos el formulario
		PO_LoginView.fillForm(driver, "admin", "admin");
				
		//Vamos a la página de usuarios.
		PO_HomeView.clickOption(driver, "admin/list", "text", "Lista completa de usuarios");
		// Contamos el número de filas de usuarios.
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
		assertTrue(elementos.size() == 18);
	}
	
}