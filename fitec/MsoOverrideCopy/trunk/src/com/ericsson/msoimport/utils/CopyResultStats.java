package com.ericsson.msoimport.utils;

public class CopyResultStats {

	private int numMsosCopied;
	private int numMsoOverrideCopied;
	private int numMsosDeletedFromDest;
	
	private int numMsosFromSite;
	
	private int numMsosToSite;
	
	private int numMsosToSiteAfterCopy;
	
	private int numErrors;	
	
	public int getNumMsosFromSite() {
		return numMsosFromSite;
	}
	public void setNumMsosFromSite(int numMsosFromSite) {
		this.numMsosFromSite = numMsosFromSite;
	}
	public int getNumMsosCopied() {
		return numMsosCopied;
	}
	public void addNumMsosCopied() {
		this.numMsosCopied++;
	}
	public int getNumMsoOverrideCopied() {
		return numMsoOverrideCopied;
	}
	public void addNumMsoOverrideCopied() {
		this.numMsoOverrideCopied++;
	}
	public int getNumMsosDeletedFromDest() {
		return numMsosDeletedFromDest;
	}
	public void addNumMsosDeletedFromDest() {
		this.numMsosDeletedFromDest++;
	}
	public int getNumErrors() {
		return numErrors;
	}
	public void addNumErrors() {
		this.numErrors++;
	}
	public int getNumMsosToSite() {
		return numMsosToSite;
	}
	public void setNumMsosToSite(int numMsosToSite) {
		this.numMsosToSite = numMsosToSite;
	}
	public int getNumMsosToSiteAfterCopy() {
		return numMsosToSiteAfterCopy;
	}
	public void setNumMsosToSiteAfterCopy(int numMsosToSiteAfterCopy) {
		this.numMsosToSiteAfterCopy = numMsosToSiteAfterCopy;
	}
	
	
}
