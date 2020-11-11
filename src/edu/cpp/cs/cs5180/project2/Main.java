/**
 * 
 */
package edu.cpp.cs.cs5180.project2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
		String dir = "webpages";
		ParseWebpages pwp = new ParseWebpages(dir);
		PageRank pr = new PageRank(pwp.getWebpages());
		pr.calculate();
		pr.sort();
	}
	
	// Using Jsoup to connect and download a webpage
//	String url = "https://www.cpp.edu/index.shtml";
//	String fileNames[] = url.split("//");
//	String newFileName = fileNames[1].replace("/", "_");
//	System.out.println("New File Name: " + newFileName);
//	for (String fileName : fileNames) {
//		System.out.println(fileName);
//	}
//	Document doc = Jsoup.connect("https://www.cpp.edu").get();
//	Elements outLinks = doc.select("a[href]");
//	System.out.println(outLinks.size());
////	System.out.println(outLinks);
//	for (Element page : outLinks) {
//		System.out.println(page.attr("abs:href"));
//	}
//	
//	Connection.Response response = Jsoup.connect(url).execute();
//	Document reponseDoc = response.parse();
//	
//	FileWriter fw = new FileWriter("sample2.html", true);
//	fw.write("<!--" + url + "-->" + System.lineSeparator());
//	fw.write(reponseDoc.outerHtml());
//	fw.close();
//	try {
//		File f = new File("sample2.html");
//		Scanner myReader = new Scanner(f);
//		String tempUrl = myReader.nextLine();
//		System.out.println(tempUrl);
//		myReader.close();
//	} catch (FileNotFoundException e) {
//		System.out.println("Cannot find the file");
//	}

}
