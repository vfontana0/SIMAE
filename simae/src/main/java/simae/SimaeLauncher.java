package simae;

import simae.lib.Lenguaje;
import simae.lib.Simae;
import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import simae.lib.AnotacionMarca;

public class SimaeLauncher {

    private static String VERSION = "SIMAE 0.2.2";
    public SimaeLauncher() {
    }

    BufferedReader inputReader = null;
    PrintWriter workWriter = null;
    File workFile = null;

    public static String getVERSION() {
        return VERSION;
    }

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
            workFile = new File(inputFile.getPath() + ".work");
            workWriter = new PrintWriter(new FileWriter(workFile));
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
