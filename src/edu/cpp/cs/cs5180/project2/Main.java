/**
 * 
 */
package edu.cpp.cs.cs5180.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author SYL
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
//		String dir = "repository1";
//		ParseWebpages pwp = new ParseWebpages(dir);
//		PageRank pr = new PageRank(pwp.getWebpages());
//		pr.calculate();
//		pr.sort();
//		pr.printResult();
//		pr.printTop100();
//		pr.printUnusal();	
		
//		File resultFile = new File(dir+".txt");
//		if (resultFile.createNewFile()) {
//			System.out.println("File created" + resultFile.getName());
//		} else {
//			System.out.println("File already exists. Overwriting.");
//		}
		
		mainEngine("repository");
		mainEngine("repository1");
	}
	
	public static void mainEngine(String dir) throws IOException{
		ParseWebpages pwp = new ParseWebpages(dir);
		PageRank pr = new PageRank(pwp.getWebpages());
		File resultFile = new File(dir+".txt");
		if (resultFile.createNewFile()) {
//			System.out.println("File created: " + resultFile.getName());
			PrintStream stream = new PrintStream(resultFile);
			System.setOut(stream);
			pr.calculate();
			pr.sort();
			pr.printResult();
			pr.printTop100();
		} else {
//			System.out.println("File already exists. Overwriting.");
			PrintStream stream = new PrintStream(resultFile);
			System.setOut(stream);
			pr.calculate();
			pr.sort();
			pr.printResult();
			pr.printTop100();
		}
	}
	
}
