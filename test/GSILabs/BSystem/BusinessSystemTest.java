/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package GSILabs.BSystem;

import GSILabs.BModel.Bar;
import GSILabs.BModel.Cliente;
import GSILabs.BModel.Contestacion;
import GSILabs.BModel.Direccion;
import GSILabs.BModel.Local;
import GSILabs.BModel.Propietario;
import GSILabs.BModel.Pub;
import GSILabs.BModel.Reserva;
import GSILabs.BModel.Reservable;
import GSILabs.BModel.Restaurante;
import GSILabs.BModel.Review;
import GSILabs.BModel.Usuario;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author yeray
 */
public class BusinessSystemTest {
    
    public BusinessSystemTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of nuevoUsuario method, of class BusinessSystem.
     */
    @Test
    public void testNuevoUsuario() {
        System.out.println("nuevoUsuario");
        Usuario u = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.nuevoUsuario(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminaUsuario method, of class BusinessSystem.
     */
    @Test
    public void testEliminaUsuario() {
        System.out.println("eliminaUsuario");
        Usuario u = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.eliminaUsuario(u);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modificaUsuario method, of class BusinessSystem.
     */
    @Test
    public void testModificaUsuario() {
        System.out.println("modificaUsuario");
        Usuario u = null;
        Usuario nuevoU = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.modificaUsuario(u, nuevoU);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existeNick method, of class BusinessSystem.
     */
    @Test
    public void testExisteNick() {
        System.out.println("existeNick");
        String nick = "";
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.existeNick(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerUsuario method, of class BusinessSystem.
     */
    @Test
    public void testObtenerUsuario() {
        System.out.println("obtenerUsuario");
        String nick = "";
        BusinessSystem instance = new BusinessSystem();
        Usuario expResult = null;
        Usuario result = instance.obtenerUsuario(nick);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevaReview method, of class BusinessSystem.
     */
    @Test
    public void testNuevaReview() {
        System.out.println("nuevaReview");
        Review r = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.nuevaReview(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminaReview method, of class BusinessSystem.
     */
    @Test
    public void testEliminaReview() {
        System.out.println("eliminaReview");
        Review r = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.eliminaReview(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existeRewiew method, of class BusinessSystem.
     */
    @Test
    public void testExisteRewiew() {
        System.out.println("existeRewiew");
        Usuario u = null;
        Local l = null;
        LocalDate ld = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.existeRewiew(u, l, ld);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevaContestacion method, of class BusinessSystem.
     */
    @Test
    public void testNuevaContestacion() {
        System.out.println("nuevaContestacion");
        Contestacion c = null;
        Review r = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.nuevaContestacion(c, r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tieneContestacion method, of class BusinessSystem.
     */
    @Test
    public void testTieneContestacion() {
        System.out.println("tieneContestacion");
        Review r = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.tieneContestacion(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerContestacion method, of class BusinessSystem.
     */
    @Test
    public void testObtenerContestacion() {
        System.out.println("obtenerContestacion");
        Review r = null;
        BusinessSystem instance = new BusinessSystem();
        Contestacion expResult = null;
        Contestacion result = instance.obtenerContestacion(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminaContestacion method, of class BusinessSystem.
     */
    @Test
    public void testEliminaContestacion_Contestacion() {
        System.out.println("eliminaContestacion");
        Contestacion c = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.eliminaContestacion(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminaContestacion method, of class BusinessSystem.
     */
    @Test
    public void testEliminaContestacion_Review() {
        System.out.println("eliminaContestacion");
        Review r = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.eliminaContestacion(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevoLocal method, of class BusinessSystem.
     */
    @Test
    public void testNuevoLocal() {
        System.out.println("nuevoLocal");
        Local l = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.nuevoLocal(l);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarLocal method, of class BusinessSystem.
     */
    @Test
    public void testEliminarLocal() {
        System.out.println("eliminarLocal");
        Local l = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.eliminarLocal(l);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerLocal method, of class BusinessSystem.
     */
    @Test
    public void testObtenerLocal() {
        System.out.println("obtenerLocal");
        Direccion d = null;
        BusinessSystem instance = new BusinessSystem();
        Local expResult = null;
        Local result = instance.obtenerLocal(d);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of asociarLocal method, of class BusinessSystem.
     */
    @Test
    public void testAsociarLocal() {
        System.out.println("asociarLocal");
        Local l = null;
        Propietario p = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.asociarLocal(l, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desasociarLocal method, of class BusinessSystem.
     */
    @Test
    public void testDesasociarLocal() {
        System.out.println("desasociarLocal");
        Local l = null;
        Propietario p = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.desasociarLocal(l, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of actualizarLocal method, of class BusinessSystem.
     */
    @Test
    public void testActualizarLocal() {
        System.out.println("actualizarLocal");
        Local viejoL = null;
        Local nuevoL = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.actualizarLocal(viejoL, nuevoL);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verReviews method, of class BusinessSystem.
     */
    @Test
    public void testVerReviews() {
        System.out.println("verReviews");
        Local l = null;
        BusinessSystem instance = new BusinessSystem();
        Review[] expResult = null;
        Review[] result = instance.verReviews(l);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nuevaReserva method, of class BusinessSystem.
     */
    @Test
    public void testNuevaReserva() {
        System.out.println("nuevaReserva");
        Cliente c = null;
        Reservable r = null;
        LocalDate ld = null;
        LocalTime lt = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.nuevaReserva(c, r, ld, lt);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerReservas method, of class BusinessSystem.
     */
    @Test
    public void testObtenerReservas_Cliente() {
        System.out.println("obtenerReservas");
        Cliente c = null;
        BusinessSystem instance = new BusinessSystem();
        Reserva[] expResult = null;
        Reserva[] result = instance.obtenerReservas(c);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerReservas method, of class BusinessSystem.
     */
    @Test
    public void testObtenerReservas_Reservable() {
        System.out.println("obtenerReservas");
        Reservable r = null;
        BusinessSystem instance = new BusinessSystem();
        Reserva[] expResult = null;
        Reserva[] result = instance.obtenerReservas(r);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerReservas method, of class BusinessSystem.
     */
    @Test
    public void testObtenerReservas_LocalDate() {
        System.out.println("obtenerReservas");
        LocalDate ld = null;
        BusinessSystem instance = new BusinessSystem();
        Reserva[] expResult = null;
        Reserva[] result = instance.obtenerReservas(ld);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of eliminarReserva method, of class BusinessSystem.
     */
    @Test
    public void testEliminarReserva() {
        System.out.println("eliminarReserva");
        Reserva r = null;
        BusinessSystem instance = new BusinessSystem();
        boolean expResult = false;
        boolean result = instance.eliminarReserva(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarLocales method, of class BusinessSystem.
     */
    @Test
    public void testListarLocales() {
        System.out.println("listarLocales");
        String ciudad = "";
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Local[] expResult = null;
        Local[] result = instance.listarLocales(ciudad, provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarBares method, of class BusinessSystem.
     */
    @Test
    public void testListarBares() {
        System.out.println("listarBares");
        String ciudad = "";
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Bar[] expResult = null;
        Bar[] result = instance.listarBares(ciudad, provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarRestaurantes method, of class BusinessSystem.
     */
    @Test
    public void testListarRestaurantes() {
        System.out.println("listarRestaurantes");
        String ciudad = "";
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Restaurante[] expResult = null;
        Restaurante[] result = instance.listarRestaurantes(ciudad, provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listarPubs method, of class BusinessSystem.
     */
    @Test
    public void testListarPubs() {
        System.out.println("listarPubs");
        String ciudad = "";
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Pub[] expResult = null;
        Pub[] result = instance.listarPubs(ciudad, provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerValoracionMedia method, of class BusinessSystem.
     */
    @Test
    public void testObtenerValoracionMedia_Local() {
        System.out.println("obtenerValoracionMedia");
        Local l = null;
        BusinessSystem instance = new BusinessSystem();
        float expResult = 0.0F;
        float result = instance.obtenerValoracionMedia(l);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerValoracionMedia method, of class BusinessSystem.
     */
    @Test
    public void testObtenerValoracionMedia_Propietario() {
        System.out.println("obtenerValoracionMedia");
        Propietario p = null;
        BusinessSystem instance = new BusinessSystem();
        float expResult = 0.0F;
        float result = instance.obtenerValoracionMedia(p);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerValoracionMedia method, of class BusinessSystem.
     */
    @Test
    public void testObtenerValoracionMedia_3args() {
        System.out.println("obtenerValoracionMedia");
        Local l = null;
        int edadEntre = 0;
        int edadHasta = 0;
        BusinessSystem instance = new BusinessSystem();
        float expResult = 0.0F;
        float result = instance.obtenerValoracionMedia(l, edadEntre, edadHasta);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerLocalesOrdenados method, of class BusinessSystem.
     */
    @Test
    public void testObtenerLocalesOrdenados_String_String() {
        System.out.println("obtenerLocalesOrdenados");
        String ciudad = "";
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Local[] expResult = null;
        Local[] result = instance.obtenerLocalesOrdenados(ciudad, provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerLocalesOrdenados method, of class BusinessSystem.
     */
    @Test
    public void testObtenerLocalesOrdenados_String() {
        System.out.println("obtenerLocalesOrdenados");
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Local[] expResult = null;
        Local[] result = instance.obtenerLocalesOrdenados(provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerBaresOrdenados method, of class BusinessSystem.
     */
    @Test
    public void testObtenerBaresOrdenados() {
        System.out.println("obtenerBaresOrdenados");
        String ciudad = "";
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Bar[] expResult = null;
        Bar[] result = instance.obtenerBaresOrdenados(ciudad, provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerRestaurantesOrdenados method, of class BusinessSystem.
     */
    @Test
    public void testObtenerRestaurantesOrdenados() {
        System.out.println("obtenerRestaurantesOrdenados");
        String ciudad = "";
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Restaurante[] expResult = null;
        Restaurante[] result = instance.obtenerRestaurantesOrdenados(ciudad, provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of obtenerPubOrdenados method, of class BusinessSystem.
     */
    @Test
    public void testObtenerPubOrdenados() {
        System.out.println("obtenerPubOrdenados");
        String ciudad = "";
        String provincia = "";
        BusinessSystem instance = new BusinessSystem();
        Pub[] expResult = null;
        Pub[] result = instance.obtenerPubOrdenados(ciudad, provincia);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importaPubs method, of class BusinessSystem.
     */
    @Test
    public void testImportaPubs() {
        System.out.println("importaPubs");
        File f = null;
        BusinessSystem instance = new BusinessSystem();
        int expResult = 0;
        int result = instance.importaPubs(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of parseXMLFile method, of class BusinessSystem.
     */
    @Test
    public void testParseXMLFile() throws Exception {
        System.out.println("parseXMLFile");
        File f = null;
        BusinessSystem expResult = null;
        BusinessSystem result = BusinessSystem.parseXMLFile(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadXMLFile method, of class BusinessSystem.
     */
    @Test
    public void testLoadXMLFile() throws Exception {
        System.out.println("loadXMLFile");
        File f = null;
        boolean expResult = false;
        boolean result = BusinessSystem.loadXMLFile(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
