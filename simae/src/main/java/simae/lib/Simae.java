package simae.lib;

// import ANTLR's runtime libraries

import java.io.*;
import java.util.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import simae.lib.listener.StringTags;


public class Simae {
	
	//FIXME: reestructurar funcion para que no solo funcione con translationunit
	private static List<AnotacionMarca> iniciaTranslationUnit(CharStream antlrEntrada, Lenguaje lenguaje, String language) {
		StringTags st;
		HashMap<String, String> strings;

		st = new StringTags((language != null) ? language : "");
		strings = st.getStrings();
		Lexer lexer = ANTLRFactory.getLexer(lenguaje, antlrEntrada);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Parser parser = ANTLRFactory.getParser(lenguaje, tokens);
		ParseTree tree = ANTLRFactory.getTree(lenguaje, parser);
		ParseTreeListener extractor = ANTLRFactory.getListener(lenguaje, parser, strings);
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(extractor, tree);
		return ANTLRFactory.getMarcas(lenguaje, extractor);
	}


	private static String fuenteDesmarcado(BufferedReader br, Lenguaje lenguaje) {
		//FIXME: la seleccion de la marca de acuerdo al lenguaje no va aca
		String gramaticaMarca = (lenguaje == Lenguaje.PYTHON3) ? "# /.*/" : "/\\*/[^/]*/\\*/";
		StringBuilder programaCompleto = new StringBuilder();
		br.lines().forEach(linea -> programaCompleto
				.append(linea.replaceAll(gramaticaMarca, ""))
				.append("\n"));
		return programaCompleto.toString();

	}


	public static List<AnotacionMarca> fuenteMarcado(BufferedReader br, Lenguaje programmingLanguage, String language) {
		String armaCompleto = fuenteDesmarcado(br, programmingLanguage);
		CharStream antlrEntrada = CharStreams.fromString(armaCompleto);
		return iniciaTranslationUnit(antlrEntrada, programmingLanguage, language);
	}

}