 package uml2onto;
import java.io.File;  
import javax.xml.transform.Transformer;  
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;  
import javax.xml.transform.stream.StreamResult;  
import javax.xml.transform.stream.StreamSource;  


public class saxon9 {  
  
    /** 
     * Simple transformation method. 
     * @param sourcePath - Absolute path to source xml file. 
     * @param xsltPath - Absolute path to xslt file. 
     * @param resultDir - Directory where you want to put resulting files. 
     */  
    private File xslFile = new File("src\\uml2onto\\Modelio.xsl");
    private static File outFile = new File("src\\uml2onto\\out.txt");
    private StreamSource source;
    private StreamSource style ;
    private StreamResult out ;
    public saxon9 () 
{
	File f= MDIMain.getXMIFile();
	source = new StreamSource(MDIMain.getXMIFile());
	style = new StreamSource (xslFile);
	out = new StreamResult(outFile);
        
}
public void transformation()
{
    System.setProperty("javax.xml.transform.TransformerFactory",  
                           "net.sf.saxon.TransformerFactoryImpl");
    
	TransformerFactory factory = TransformerFactory.newInstance();
	Transformer t;
	try {
		t = factory.newTransformer(style);
		t.transform(source, out);
               
                
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
                
	}
	

}

public static File getModelFile()
{
    return(outFile);
}
}
    
    