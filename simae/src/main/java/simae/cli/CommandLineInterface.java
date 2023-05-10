package simae.cli;
import java.io.*;
import java.util.List;
import simae.SimaeLauncher;
import simae.lib.AnotacionMarca;

public class CommandLineInterface {

	public static void main(String[] args) {
		String filename = args[0];
		String languageString = getFileExtension(filename);
		SimaeLauncher launcher = new SimaeLauncher();
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



