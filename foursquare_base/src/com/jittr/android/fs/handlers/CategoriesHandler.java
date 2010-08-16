
package com.jittr.android.fs.handlers;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jittr.android.fs.core.ParserInterface;
import com.jittr.android.fs.dto.Category;
import com.jittr.android.fs.dto.User;
import com.jittr.android.fs.utils.Constants;

/**
 * @author rg230v
 *
 */

public class CategoriesHandler extends DefaultHandler implements ParserInterface {

	String data = "";
	private StringBuilder builder;
    private Category category = null;
    private List categories = null;
    
	public CategoriesHandler(String data) {
		this.data = data;
	}
	
	
	public Object parse() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return category;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start document");
		
		categories = new ArrayList();
		
		builder = new StringBuilder();
    }
	
    public void startElement(String uri, String localName, String name,Attributes attributes) throws SAXException {
		super.startElement(uri, localName, name, attributes);
		
		System.out.println("Start Element called,  uri= " + uri +  " localName = " + localName + " name = " + name);
		
		if (localName.equalsIgnoreCase("category")){
			category = new Category();
		 }
		
	  }

	
    public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		builder.append(ch, start, length);
	}
    
	
    public void endElement(String uri, String localName, String name)  throws SAXException {
		super.endElement(uri, localName, name);
		
		System.out.println("end element called uri "+uri+ " localName "+localName+ " name "+name);
		
		if (category != null){
		    if (localName.equalsIgnoreCase(Constants.FS_ID)){
		    	category.setId(builder.toString());
		    } 
		    else if (localName.equalsIgnoreCase(Constants.FS_Full_Path)) {
		    	category.setFullpathName(builder.toString());
		    }
		    else if (localName.equalsIgnoreCase(Constants.FS_Node_Name)) {
		    	category.setNodeName(builder.toString());
		    }
		   
		    if (localName.equalsIgnoreCase("category")){
				categories.add(category);
		    	category = null;
			 }
		    
		    builder.setLength(0);    
		}
		 
	}
	
    
    public List<Object> parseList() {
		
    	try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new DataInputStream(new ByteArrayInputStream(data.getBytes())), this);
            
            return categories;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    	
	}
	
		
}

