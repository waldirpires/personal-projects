package com.ericsson.msoimport.domain;

public class MsoOverride {
	String fieldXPath;
	String searchValue;
	String replacementValue;

	@Override
	public String toString() {
		return String.format("$s %s %s", fieldXPath, searchValue, replacementValue);
	}

	public String getFieldXPath() {
		return fieldXPath;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public String getReplacementValue() {
		return replacementValue;
	}

	public void setFieldXPath(String fieldXPath) {
		this.fieldXPath = fieldXPath;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public void setReplacementValue(String replacementValue) {
		this.replacementValue = replacementValue;
	}

	
}
