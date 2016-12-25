package com.careydevelopment.dse.parse;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import com.careydevelopment.dse.domain.Domain;

public class GoDaddyParser implements DomainParser {

	public List<Domain> fetchDomains() {
		List<Domain> domains = new ArrayList<Domain>();
		
		try {
			SAXParserFactory parserFactor = SAXParserFactory.newInstance();
			SAXParser parser = parserFactor.newSAXParser();
			GoDaddyHandler handler = new GoDaddyHandler();
		    
		    InputStream is = new FileInputStream("C:/Users/Administrator/Downloads/auction_end_tomorrow.xml");
		   
		    //Reader reader = new InputStreamReader(is,"ISO-8859-1");
		    //Reader reader = new InputStreamReader(is,"UTF-8");
		    Reader reader = new InputStreamReader(is,"US-ASCII");
		    
		    InputSource ins = new InputSource(reader);
		    //ins.setEncoding("ISO-8859-1");
		    //ins.setEncoding("UTF-8");
		    ins.setEncoding("US-ASCII");
			
			parser.parse(ins, handler); 
			
			domains = handler.getDomains();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return domains;
	}

}
