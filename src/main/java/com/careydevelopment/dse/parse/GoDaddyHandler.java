package com.careydevelopment.dse.parse;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.careydevelopment.dse.domain.Domain;

public class GoDaddyHandler extends DefaultHandler {
	
	private String content;
	private List<Domain> domains = new ArrayList<Domain>();
	private Domain currentDomain = null;

	public void startElement(String uri, String localName, String qName, 
			Attributes attributes) throws SAXException {
		
		//System.err.println("in " + qName);
		if (qName.equals("item")) {
			currentDomain = new Domain();
			currentDomain.setSource("GoDaddy");
		}
	}
	
	
	public void endElement(String uri, String localName, String qName) 
			throws SAXException {
		
		//System.err.println("ending " + qName) ;
		if (currentDomain != null) {
			if (qName.equals("title")) {
				currentDomain.setName(content);
			} else if (qName.equals("item")) {
				if (currentDomain.getAge() > 2) domains.add(currentDomain);
			} else if (qName.equals("description")) {
				String[] pieces = content.split(",");
				for (int i=0;i<pieces.length;i++) {
					String[] parts = pieces[i].split(":");
					//System.err.println("Looking at " + parts[0]);
					if (parts[0].trim().equals("Price")) {
						currentDomain.setPrice(parts[1].trim());
					} else if (parts[0].trim().equals("Domain Age")) {
						Integer ii = new Integer(parts[1].trim());
						currentDomain.setAge(ii);
					}
				}
				
				currentDomain.setDescription(content);
			}
		}
		content = null;
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (content == null) content = String.copyValueOf(ch, start, length).trim();
		else content +=  String.copyValueOf(ch, start, length).trim();
	}
	
	
	public List<Domain> getDomains() {
		return domains;
	}
}
