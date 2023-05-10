package simae;

import simae.lib.Lenguaje;
import java.util.*;
import java.io.*;
import simae.lib.AnotacionMarca;
import simae.lib.Simae;

public class SimaeLauncher {

    private static String VERSION = "SIMAE 0.2.2";
    public SimaeLauncher() {
    }

    BufferedReader inputReader = null;

    public static Lenguaje lenguaje(String lenguajeString) {
        switch(lenguajeString) {
            case "c++":
                return Lenguaje.CPLUSPLUS;
            case "java":
            case "java8":
                return Lenguaje.JAVA8;
            case "python3":
                return Lenguaje.PYTHON3;
            default:
                System.out.println("Lenguaje invalido");
                return null;
        }
    }
    
    public boolean prepareSimae(File inputFile) {

        try {
            inputReader = new BufferedReader(new FileReader(inputFile));
        } catch (IOException e) {
            System.out.println("Fallo algo en los argumentos");
            return false;
        }

        return true;
    }

    public List<AnotacionMarca> obtenerMarcas(File inputFile, String lenguajeString) {
        prepareSimae(inputFile);
        Lenguaje lenguaje = lenguaje(lenguajeString.toLowerCase());
        return Simae.fuenteMarcado(inputReader, lenguaje, null);
    }


}
