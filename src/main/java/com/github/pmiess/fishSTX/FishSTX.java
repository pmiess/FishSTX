/**
 * 
 */
package com.github.pmiess.fishSTX;

import java.io.File;
import java.util.Properties;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * @author pmiess
 *
 */
public class FishSTX {
	
public FishSTX(String[] args) throws TransformerException{
	
	if(args.length != 3){
		return;
	}
	File input = new File(args[0]);
	File transform = new File(args[1]);
	File output = new File(args[2]);
	if(!(transform.exists() && input.exists() && !output.exists())){
		return;
	}
	
	
	// use Joost as transformation engine
	String key = "javax.xml.transform.TransformerFactory";
	String valueJOOST = "net.sf.joost.trax.TransformerFactoryImpl";
	Properties props = System.getProperties();
	props.put(key, valueJOOST);
	System.setProperties(props);
	
	TransformerFactory factory =  net.sf.joost.trax.TransformerFactoryImpl.newInstance();
	
	//use XSLTC to execute XSL transforms
	factory.setAttribute(net.sf.joost.trax.TrAXConstants.KEY_XSLT_FACTORY, "org.apache.xalan.xsltc.trax.TransformerFactoryImpl");
	
	Transformer transformer =
	factory.newTransformer(new StreamSource(args[1]));
	
	transformer.transform(new StreamSource(args[0]), new StreamResult(args[2]));
}

	/**
	 * @param args
	 * @throws TransformerException 
	 */
	public static void main(String[] args) throws TransformerException {
		new FishSTX(args);

	}

}
