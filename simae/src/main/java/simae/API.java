package simae;

import simae.lib.AnotacionMarca;

import java.io.File;
import java.util.List;

public class API {

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



