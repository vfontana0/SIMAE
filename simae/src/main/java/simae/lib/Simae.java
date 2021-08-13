package simae.lib;

// import ANTLR's runtime libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import cpp14.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Simae {
	
	
	//FIXME: reestructurar funcion para que no solo funcione con translationunit
	private static List<AnotacionMarca> iniciaTranslationUnit(ANTLRInputStream antlrEntrada) throws IOException {

		CPP14Lexer lexer = new CPP14Lexer(antlrEntrada);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CPP14Parser parser = new CPP14Parser(tokens);
		ParseTree tree = parser.translationunit();
		CPPListener extractor = new CPPListener(parser);
		ParseTreeWalker walker = new ParseTreeWalker(); // create standard walker
		walker.walk(extractor, tree); // initiate walk of tree with listener
		
		//System.out.println(extractor.marcas);
		
		return extractor.getMarcas();

	}

	public static void fuenteMarcado(BufferedReader br, PrintWriter pw) throws IOException {
		
		StringBuilder armaCompleto = new StringBuilder();
		
		br.lines().forEach(linea -> armaCompleto
									.append(linea.replaceAll("/\\*/[^/]*/\\*/", ""))
									.append("\n"));
		
		String armaCompletoStr = armaCompleto.toString();
		
		ANTLRInputStream antlrEntrada = new ANTLRInputStream(armaCompletoStr);
		
		BufferedReader brPreprocesado = new BufferedReader(new StringReader(armaCompletoStr));
		
		List<AnotacionMarca> todasMarcas = iniciaTranslationUnit(antlrEntrada);
		
        String entrada = "";
        int nroFila = 1;
        
        /* 
         * Se recorre la lista de marcas 
         * p/cada marca hay que imprimir en
         * la salida las filas hasta que
         * la fila coincida con alguna marca
         * PARA(CadaMarca)
         * MIENTRAS(Fila != FilaMarca) IMPRIMIR(PosicionDentroDeFila, Fila.length())
         * SI NO IMPRIMIR(HastaLaMarca),IMPRIMIR(Marca) <- Guardar PosicionDentroDeFila
        */
        entrada = brPreprocesado.readLine();
        int posEnFila = 0;
        Collections.sort(todasMarcas);
        Iterator<AnotacionMarca> it = todasMarcas.iterator();
        boolean yaEstaCargadaLaMarcaSiguiente = false;
        AnotacionMarca marca = null;
        
    	while(it.hasNext() || yaEstaCargadaLaMarcaSiguiente) {
        	if(!yaEstaCargadaLaMarcaSiguiente) marca = it.next();
            //Si no coincide con la fila, se imprime la linea completa
        	
        	while(nroFila != marca.getFila()) {
        		pw.println(entrada.substring(posEnFila));
        		posEnFila = 0;
        		entrada = brPreprocesado.readLine();
        		nroFila++;
        	}
        	
        	//Coincide la linea, se imprime hasta la marca y luego se imprime la marca.
        	pw.print(entrada.substring(posEnFila,marca.getPosicion() + 1));
        	pw.print(marca.getInicioComentario());
    		pw.print(marca.getMarca());
    		
        	yaEstaCargadaLaMarcaSiguiente = false;
        	while(it.hasNext()) {
        		int filaMarca = marca.getFila();
        		int posMarca = marca.getPosicion();

        		marca = it.next();
        		yaEstaCargadaLaMarcaSiguiente = true;
        		
        		if(filaMarca != marca.getFila() || posMarca != marca.getPosicion()) {
        			posEnFila = posMarca + 1;
        			break;
        		}
        		
        		pw.print(" y " + marca.getMarca());
        	}
        	pw.print(marca.getFinComentario());
        	if(!yaEstaCargadaLaMarcaSiguiente) posEnFila = marca.getPosicion() + 1;
    	}
    	pw.print(entrada.substring(posEnFila));
        entrada = brPreprocesado.readLine();
        
        pw.println();
        while(entrada != null) {
        	pw.println(entrada);
        	entrada = brPreprocesado.readLine();
        }
	}
	
	public String testMarcado(String entrada) throws IOException {

		StringReader srEntrada = new StringReader(entrada);
		BufferedReader reader = new BufferedReader(srEntrada);
		
		StringWriter swSalida = new StringWriter();
		PrintWriter writer = new PrintWriter(swSalida);
		
		fuenteMarcado(reader, writer);
		
		String salida = swSalida.toString();
		
		srEntrada.close();
		swSalida.close();
		return salida;
	}
}