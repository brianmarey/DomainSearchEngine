package com.careydevelopment.dse.parse;

public class DomainParserFactory {

	public static final int GODADDY = 1;
	
	public static DomainParser getDomainParser(int source) {
		switch (source) {
			case GODADDY:
				return new GoDaddyParser();
				
			default:
				return null;
		}
	}
}
