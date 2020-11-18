package edu.cpp.cs.cs5180.project2;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class used to perform PageRank. It find the most important pages of the downloaded webpages.
 * @author SYL
 *
 */
public class PageRank {
	private ArrayList<Webpage> webpages;
	// The total number of webpages
	private int n;
	// constant to used for PageRank (with teleporting) calculation
	private final double LAMDA = 0.15; 
	
	public PageRank(ArrayList<Webpage> webpages) {
		this.webpages = webpages;
		n = this.webpages.size();
	}
	
	public int getN() {
		return n;
	}
	
	public void calculate() {
		// Iteration to track how many iterations are done till convergence
		int iteration = 0;
		ArrayList<Webpage> inlinks;
		boolean converged = false;
		zeroInlinkWebpages();
		// Calculate PageRank of each webpage until convergence (no change in the PageRank value of each webpage) 
		while (!converged) {
			for (Webpage wp : webpages) {
				double pr = 0.0;
				// Only the webpages with 1 or more inlinks
				if (wp.getInLinksSize() > 0) {
					// Get the inlinks of the current webpage
					inlinks = wp.getInLinks();
					// Loop through the inlinks to calculate the PageRank of the current webpage
					for (Webpage inlink : inlinks) {
						double curPR = inlink.getCurPageRank();
						double numOfOutlinks = inlink.getOutLinksSize();
						pr += (curPR/numOfOutlinks);
					}
					pr = (LAMDA/n) + ((1.0-LAMDA)*pr);
					wp.setNextPageRank(pr);
				} 
			}
			iteration++;
			// If no covergence, set the next PageRank as the current PageRank
			if (!isConvergence()) {
				updateCurPageRank();
			} else {
				converged = true;
			}
		}

	}
	
	/**
	 * Calculates the sum of PageRank of all the webpages. The sum should be or close to 1.
	 * @return
	 */
	public double getTotalPR() {
		double totalPR = 0.0;
		for (Webpage wp : webpages) {
			totalPR += wp.getCurPageRank();
		}
		return totalPR;
	}
	
	/**
	 * Sets the current and next PageRank of webpages with 0 inlinks as LAMDA/n.
	 */
	public void zeroInlinkWebpages() {
		double pr = LAMDA/n;
		for (Webpage wp : webpages) {
			if (wp.getInLinksSize() == 0) {
				wp.setCurPageRank(pr);
				wp.setNextPageRank(pr);
			}
		}
	}
	
	/**
	 * Sets the next PageRank as the current PageRank.
	 */
	public void updateCurPageRank() {
		double newPR = 0.0;
		for (Webpage wp : webpages) {
			newPR = wp.getNextPageRank();
			wp.setCurPageRank(newPR);
		}
	}
	
	/**
	 * Checks if it converged
	 * @return True if no change in PageRank for every webpage; otherwise, False
	 */
	public boolean isConvergence() {
		boolean converged = true;
		for (Webpage wp : webpages) {
			if (!wp.isConvergence()) {
				converged = false;
			}
		}
		return converged;
	}
	
	/**
	 * Prints only the outlinks of the webpages
	 */
	public void printOutLinks() {
		System.out.println("----------------------------------------------");
		for (Webpage wp : webpages) {
			System.out.println("Num: " + wp.getNum());
			System.out.println("URL: " + wp.getUrl());
			System.out.print("Outlinks: ");
			for (Webpage outlink : wp.getOutLinks()) {
				System.out.print(outlink.getNum() + ", ");
			}
			System.out.println();
			System.out.println("----------------------------------------------");
		}
	}
	
	/**
	 * Prints only the inlinks of the webpages
	 */
	public void printInLinks() {
		System.out.println("----------------------------------------------");
		for (Webpage wp : webpages) {
			System.out.println("Num: " + wp.getNum());
			System.out.println("Num of outlinks: " + wp.getOutLinksSize());
			System.out.println("URL: " + wp.getUrl());
			System.out.print("Inlinks: ");
			for (Webpage inlink : wp.getInLinks()) {
				System.out.print(inlink.getNum() + ", ");
			}
			System.out.println();
			System.out.println("----------------------------------------------");
		}
	}
	
	/**
	 * Prints the information of the passed-in webpage.
	 * @param wp
	 */
	public void printCurWebpageInfo(Webpage wp) {
		System.out.println("----------------------------------------------------");
		System.out.println("URL: " + wp.getUrl());
		System.out.println("Num: " + wp.getNum());
		System.out.println("Current PageRank: " + wp.getCurPageRank());
		System.out.println("Next PageRank: " + wp.getNextPageRank());
		System.out.println("Number of outlinks: " + wp.getOutLinksSize());
		System.out.println("Number of inlinks: " + wp.getInLinksSize());
		System.out.print("Inlinks: ");
		for (Webpage inlink : wp.getInLinks()) {
			System.out.print(inlink.getNum() + ", ");
		}
		System.out.println();
		System.out.println("----------------------------------------------------");
	}
	
	/**
	 * Prints all the information of the webpages.
	 */
	public void printInfo() {
//		System.out.println("Number of Webpages: " + webpages.size());
		System.out.println("----------------------------------------------------------------");
		for (int i = 0; i < webpages.size(); ++i) {
//			System.out.println(i+1);
			System.out.println("URL: " + webpages.get(i).getUrl());
			System.out.println("Num: " + webpages.get(i).getNum());
			System.out.println("Current PageRank: " + webpages.get(i).getCurPageRank());
			System.out.println("Next PageRank: " + webpages.get(i).getNextPageRank());
			System.out.println("Number of outlinks: " + webpages.get(i).getOutLinksSize());
			System.out.println("Number of inlinks: " + webpages.get(i).getInLinksSize());
			System.out.print("Inlinks: ");
			for (Webpage outlink : webpages.get(i).getInLinks()) {
				System.out.print(outlink.getNum() + ", ");
			}
			System.out.println();
			System.out.println("----------------------------------------------------------------");
		}
	}
	
	/**
	 * Sort the webpages in descending PageRank order 
	 */
	public void sort() {
		Collections.sort(webpages);
	}
	
	/**
	 * Prints the result
	 */
	public void printResult() {
		double totalPR = 0.0;
		System.out.println("Final Result");
		System.out.println("----------------------------------------------------");
		for (Webpage wp : webpages ) {
			System.out.println("Url: " + wp.getUrl());
			System.out.println("Current PR: " + wp.getCurPageRank());
			totalPR += wp.getCurPageRank();
			System.out.println("Number of Outlinks: " + wp.getOutLinksSize());
			System.out.println("Number of Inlinks: " + wp.getInLinksSize());
			System.out.println("-----------------------------------------------------");
		}
		System.out.println("Total PR: " + totalPR);
		System.out.println("-----------------------------------------------------");
	}
	
	/**
	 * Prints the 100 top most important pages.
	 */
	public void printTop100() {
		// Rank PR numOfInlinks numOfOutlinks URL
		String format1 = "%-10s %-15s %-20s %-20s %-10s %s %n";
		// int double int int String
		String format2 = "%-10d %-15.6f %-20d %-20d %-10d %s %n";
		int inlinks = 0;
		int outlinks = 0;
		int index = 0;
		int num = 0;
		double pr = 0.0;
		String url = "";
		System.out.printf(format1, "Rank", "PageRank", "# of Inlinks", "# of Outlinks", "Num", "URL");
		for (int i = 0; i < 100; ++i) {
			inlinks = webpages.get(i).getInLinksSize();
			outlinks = webpages.get(i).getOutLinksSize();
			pr = webpages.get(i).getCurPageRank();
			url = webpages.get(i).getUrl();
			num = webpages.get(i).getNum();
			index = i + 1;
			System.out.printf(format2, index, pr, inlinks, outlinks, num, url);
		}
	}
	
	/**
	 * Prints information of webpages that have high PageRank with a few inlinks or low PageRank with many inlinks.
	 */
	public void printUnusal() {
		System.out.println("-----------------------------------------------------");
		for (int i = 12; i < 15; ++i) {
			System.out.println("URL: " + webpages.get(i).getUrl());
			System.out.println("PR: " + webpages.get(i).getCurPageRank());
			System.out.println("# inlinks: " + webpages.get(i).getInLinksSize());
			System.out.println("# outlinks: " + webpages.get(i).getOutLinksSize());
			ArrayList<Webpage> inlinks = webpages.get(i).getInLinks();
			for (Webpage wp : inlinks) {
				System.out.println("Num: " + wp.getNum());
				System.out.println("URL: " + wp.getUrl());
				System.out.println("PR: " + wp.getCurPageRank());
			
			}
			System.out.println("-----------------------------------------------------");
		}
	}
	
	
	/**
	 * Prints the information of the webpage at index i
	 * @param i index of the webpage
	 */
	public void printWebpage(int i) {
		System.out.println("URL: " + webpages.get(i).getUrl());
		System.out.println("PR: " + webpages.get(i).getCurPageRank());
		System.out.println("# inlinks: " + webpages.get(i).getInLinksSize());
		System.out.println("# outlinks: " + webpages.get(i).getOutLinksSize());
		ArrayList<Webpage> inlinks = webpages.get(i).getInLinks();
		for (Webpage wp : inlinks) {
			System.out.println("Num: " + wp.getNum());
			System.out.println("URL: " + wp.getUrl());
			System.out.println("PR: " + wp.getCurPageRank());
		
		}
	}
	
	
}
