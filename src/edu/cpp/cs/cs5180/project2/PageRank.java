package edu.cpp.cs.cs5180.project2;

import java.util.ArrayList;
import java.util.Collections;

public class PageRank {
	
	private ArrayList<Webpage> webpages;
	private int n;
	private final double LAMDA = .15; 
	
	public PageRank(ArrayList<Webpage> webpages) {
		this.webpages = webpages;
		n = this.webpages.size();
	}
	
	
	public void calculate() {
		int iteration = 0;
		ArrayList<Webpage> inlinks;
		double pr;
		System.out.println("Initial");
		printPageRanks();
		// for testing, just do 5 iterations for now
		// need to iterate until convergence
//		while (iteration < 5) {
//			System.out.println("Iteration: " + (iteration+1));
//			for (int i = 0; i < webpages.size(); ++i) {
//				pr = 0.0;
//				// Only do webpages with 1 or more inlinks
//				if (webpages.get(i).getInLinksSize() > 0) {
//					inlinks = webpages.get(i).getInLinks();
//					// Go thru all the inlinks of the current webpage 
//					// and calculate the new PageRank for the current webpage
//					for (int j = 0; j < inlinks.size(); ++j) {
//						pr = pr + inlinks.get(j).getCurPageRank()/inlinks.get(j).getOutLinksSize();
//					}
//					webpages.get(i).setNextPageRank(pr);
//				} else if (webpages.get(i).getInLinksSize() == 0) {
//					System.out.println("Zero inlinks");
//					webpages.get(i).setNextPageRank(webpages.get(i).getCurPageRank());
//				}
//			}
////			need to check if it converged before updating the next PageRank as the current PageRank
//			printPageRanks();
//			updateCurPageRank();
//			iteration++;
////			printPageRanks();
//		}
		
		while (iteration < 100) {
			System.out.println("Iteration: " + (iteration+1));
			for (Webpage wp : webpages) {
				pr = 0.0;
				if (wp.getInLinksSize() > 0) {
					inlinks = wp.getInLinks();
					for (Webpage inlink : inlinks) {
						pr = pr + inlink.getCurPageRank()/inlink.getOutLinksSize();
						pr = LAMDA/n + (1.0-LAMDA)*pr;
					}
					wp.setNextPageRank(pr);
				} else if (wp.getInLinksSize() == 0) {
					pr = LAMDA/n;
					wp.setNextPageRank(pr);
				}
			}
			printPageRanks();
			updateCurPageRank();
			iteration++;
		}
	}
	
	public void updateCurPageRank() {
		for (Webpage wp : webpages) {
			wp.setCurPageRank(wp.getNextPageRank());
		}
	}
	
	public boolean isConvergence() {
		boolean converged = true;
		for (Webpage wp : webpages) {
			if (!wp.isConvergence()) {
				converged = false;
			}
		}
		return converged;
	}
	
	public void printPageRanks() {
//		System.out.println("Number of Webpages: " + webpages.size());
		System.out.println("----------------------------------------------------------------");
		for (int i = 0; i < webpages.size(); ++i) {
//			System.out.println(i+1);
			System.out.println("URL: " + webpages.get(i).getUrl());
			System.out.println("Current PageRank: " + webpages.get(i).getCurPageRank());
			System.out.println("Next PageRank: " + webpages.get(i).getNextPageRank());
			System.out.println("Number of outlinks: " + webpages.get(i).getOutLinksSize());
			System.out.println("Number of inlinks: " + webpages.get(i).getInLinksSize());
			System.out.println("----------------------------------------------------------------");
		}
	}
	
	public void sort() {
		System.out.println("----------------------------------------------------");
		System.out.println("Final Result");
		Collections.sort(webpages);
		
		for (Webpage wp : webpages ) {
			System.out.println("Url: " + wp.getUrl());
			System.out.println("Current PR: " + wp.getCurPageRank());
			System.out.println("Number of Outlinks:  " + wp.getOutLinksSize());
			System.out.println("Number of Inlinks: " + wp.getInLinksSize());
			System.out.println("-----------------------------------------------------");
		}
	}
	
}
