package simae.lib.cPlusPlus;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import simae.SimaeLauncher;
import simae.lib.Lenguaje;

class ArchivoConDeclaracionDeDosVariablesTest extends Tests{

	@Test
	void testArchivoConDeclaracionDeDosVariablesTest() throws IOException {
		prog = "int a = 4;" + nl +
				 "int b = 4;";
		 esperado = "int a = 4;" + nl +
				 	"int b = 4;" + nl; 
		 marcado = SimaeLarouncher.launchTagging(prog, Lenguaje.CPLUSPLUS, "es");
		 assertEquals(esperado,marcado, "No son iguales.");
	}

}
