package com.careydevelopment.launch;

import java.util.List;

import com.careydevelopment.dse.domain.Domain;
import com.careydevelopment.dse.parse.DomainParser;
import com.careydevelopment.dse.parse.DomainParserFactory;

public class DomainSearchEngine {

	public static void main(String[] args) {
		DomainParser dp = DomainParserFactory.getDomainParser(DomainParserFactory.GODADDY);
		List<Domain> domains = dp.fetchDomains();
		System.err.println(domains.size());
//		for (Domain d : domains) {
//			System.err.println(d.getName() + " " + d.getAge() + " " + d.getPrice());
//		}
	}

}
