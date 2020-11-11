package edu.cpp.cs.cs5180.project2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;

public class ParseWebpages {
	private String dir;
	private ArrayList<Webpage> webpages = new ArrayList<Webpage>();
	
	public ParseWebpages(String dir) {
		this.dir = dir;
		initWebPages();
		initCurPageRanks();
		initOutLinks();
		initInLinks();
	}
	
	public void initWebPages() {
		File folder = new File(dir);
		File[] listOfFiles = folder.listFiles();
		String filePath = "";
		String url = "";
		
		for (File file : listOfFiles) {
			filePath = folder + "/" + file.getName();
			File webPageFile = new File(filePath);
			// Get the url of the current webpage
			url = getURL(webPageFile);
			// Create a webpage object with the url
			Webpage wp = new Webpage(url);
			ArrayList<String> tempOutLinks = new ArrayList<String>();
			try {
				Document doc = Jsoup.parse(webPageFile, "UTF-8", "");
				Elements outLinks = doc.select("a[href]");
				for (Element page : outLinks) {
					String temp = page.attr("abs:href");
					// Remove white spaces
					temp = temp.replaceAll("\\s+", "");
					// Only add non-empty links
					if (!temp.equals("")) {
						tempOutLinks.add(page.attr("abs:href"));
					}
				}
				wp.setTempOutLinks(tempOutLinks);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			webpages.add(wp);
		}
	}
	
	public ArrayList<Webpage> getWebpages() {
		return webpages;
	}
	
	public void initOutLinks() {
		for (int i = 0; i < webpages.size(); ++i) {
			webpages.get(i).setOutLinks(webpages);
		}
	}
	
	public void initInLinks() {
		for (int i = 0; i< webpages.size(); ++i) {
			ArrayList<Webpage> inLinks = new ArrayList<>();
			for (int j = 0; j < webpages.size(); ++j) {
				if (webpages.get(j).isOutLink(webpages.get(i))) {
					inLinks.add(webpages.get(j));
				}
			}
			webpages.get(i).setInLinks(inLinks);
		}
	}
	
	public void initCurPageRanks() {
		int total = webpages.size();
		for (int i = 0; i < total; ++i) {
			webpages.get(i).setCurPageRank(1.0/total);
		}
	}
	
	public String getURL(File file) {
		String url = "";
		try {
			Scanner myReader = new Scanner(file);
			url = myReader.nextLine();
			// Remove comment tag
			url = url.replace("<!--", "");
			url = url.replace("-->", "");
			// Remove white spaces
			url = url.replaceAll("\\s+", "");
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		return url;
	}
	
	public void printPageRanks() {
//		System.out.println("Number of Webpages: " + webpages.size());
		System.out.println("----------------------------------------------------------------");
		for (int i = 0; i < webpages.size(); ++i) {
//			System.out.println(i+1);
			System.out.println("URL: " + webpages.get(i).getUrl());
			System.out.println("Current PageRank: " + webpages.get(i).getCurPageRank());
			System.out.println("Current PageRank: " + webpages.get(i).getNextPageRank());
			System.out.println("Number of outlinks: " + webpages.get(i).getOutLinksSize());
			System.out.println("Number of inlinks: " + webpages.get(i).getInLinksSize());
			System.out.println("----------------------------------------------------------------");
		}
	}
	
	public void printWebPages() {
		System.out.println("Number of Webpages: " + webpages.size());
		System.out.println("----------------------------------------------------------------");
		for (int i = 0; i < webpages.size(); ++i) {
			System.out.println("URL: " + webpages.get(i).getUrl());
			System.out.println("Current PageRank: " + webpages.get(i).getCurPageRank());
			System.out.println();
			webpages.get(i).printOutLinks();
			System.out.println();
			webpages.get(i).printInLinks();
//			HashMap<String, Integer> wp = webpages.get(i).getTempOutLinks();
//			System.out.println("Number of outlinks: " + wp.size());
//			for (Map.Entry<String, Integer> entry : wp.entrySet()) {
//				System.out.println(entry.getKey());
//			}
			System.out.println("----------------------------------------------------------------");
		}
	}
	
}
