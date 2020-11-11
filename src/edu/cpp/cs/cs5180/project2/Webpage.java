package edu.cpp.cs.cs5180.project2;
import java.util.*;

public class Webpage {
	private static int totalWebpage = 0;
	private double curPageRank;
	private double nextPageRank = 0.0;
	private String url;
	private HashMap<String, Integer> tempOutLinks = new HashMap<>();
//	private ArrayList<String> tempOutLinks = new ArrayList<String>();
	private ArrayList<Webpage> outLinks = new ArrayList<Webpage>();
	private ArrayList<Webpage> inLinks = new ArrayList<Webpage>();
	
	public Webpage(String url) {
		totalWebpage++;
		this.url = url;
	}

	public static int getTotalWebpage() {
		return totalWebpage;
	}

	public static void setTotalWebpage(int totalWebpage) {
		Webpage.totalWebpage = totalWebpage;
	}

	public double getCurPageRank() {
		return curPageRank;
	}

	public void setCurPageRank(double curPageRank) {
		this.curPageRank = curPageRank;
	}

	public double getNextPageRank() {
		return nextPageRank;
	}

	public void setNextPageRank(double nextPageRank) {
		this.nextPageRank = nextPageRank;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public ArrayList<Webpage> getOutLinks() {
		return outLinks;
	}

	public void setOutLinks(ArrayList<Webpage> outLinks) {
		for (int i = 0; i < outLinks.size(); ++i) {
			// If a url is one of temp outlinks, add to the actual outlink list.
			if (tempOutLinks.containsKey(outLinks.get(i).getUrl())) {
//				System.out.println("Found a valid outlink");
				this.outLinks.add(outLinks.get(i));
			}
		}
	}
	
	public void printOutLinks() {
		System.out.println("Number of Outlinks: " + outLinks.size());
		for (int i = 0; i < outLinks.size(); ++i) {
			System.out.println(outLinks.get(i).getUrl());
		}
	}
	
	public void printInLinks() {
		System.out.println("Number of Inlinks: " + inLinks.size());
		for (int i = 0; i < inLinks.size(); ++i) {
			System.out.println(inLinks.get(i).getUrl());
		}
	} 

	public ArrayList<Webpage> getInLinks() {
		return inLinks;
	}

	public void setInLinks(ArrayList<Webpage> inLinks) {
		this.inLinks = inLinks;
	}
	
	public HashMap<String, Integer> getTempOutLinks() {
		return tempOutLinks;
	}
	
	public void setTempOutLinks(ArrayList<String> tempOutLinks) {
		for (int i = 0; i < tempOutLinks.size(); ++i) {
			this.tempOutLinks.put(tempOutLinks.get(i), i);
		}
	}
	
	public boolean isOutLink(Webpage wp) {
		return outLinks.contains(wp);
	}
	
	public boolean isInLink(Webpage wp) {
		return inLinks.contains(wp);
	}
}
