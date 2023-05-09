package simae.cli;
import java.io.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import simae.SimaeLauncher;
import simae.lib.Lenguaje;
import simae.lib.Simae;
import simae.lib.AnotacionMarca;

public class CommandLineInterface {

	public static void main(String[] args) {
		String filename = args[0];
		Lenguaje programmingLenguage;
		String languageString = getFileExtension(filename);
		SimaeLauncher launcher = new SimaeLauncher();
		switch (languageString) { //FIXME: esto esta h ardcodeado
			//case "c++":
			case ".cpp":
				programmingLenguage = Lenguaje.CPLUSPLUS;
				languageString = "c++";
				break;
			//case "java8":
			case ".java":
				programmingLenguage = Lenguaje.JAVA8;
				languageString = "java8";
				break;
			//case "python3":
			case ".py":
				programmingLenguage = Lenguaje.PYTHON3;
				languageString = "python3";
				break;
		}

			List<AnotacionMarca> marcas = launcher.obtenerMarcas(new File(filename), languageString);
			for (AnotacionMarca marca : marcas) {
				System.out.println(marca);
			}
	}

		private static String getFileExtension (String name){
			int lastIndexOf = name.lastIndexOf(".");
			if (lastIndexOf == -1) {
				return ""; // empty extension
			}
			return name.substring(lastIndexOf);
		}

}



