package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class IfTest extends Tests {

	
	@Test
	void testIf() throws IOException {
		  prog = "int main() {" + nl +
		  		"	if(c == 1) {" + nl +
		  		"		c++;" + nl +
		  		"	}" + nl +
		  		"}" + nl;
		  esperado = "int main()/*/CIERRA EN LINEA 5/*/ {" + nl +
		  		"	if(c == 1)/*/CIERRA EN LINEA 4/*/ {" + nl +
		  		"		c++;" + nl +
		  		"	}/*/CIERRA if(c == 1) DE LINEA 2/*/" + nl +
		  		"}/*/CIERRA main() DE LINEA 1/*/" + nl;
		  marcado = b.testMarcado(prog);
		  assertEquals(esperado,marcado, "No son iguales.");
	}
}