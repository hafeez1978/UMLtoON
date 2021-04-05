/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml2onto;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.reasoner.Reasoner;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.mindswap.pellet.PelletOptions;
import org.mindswap.pellet.exceptions.InconsistentOntologyException;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author bukhari
 */
public class XorVerification {
  //  OntModel readmodel ;
   // OntModel writemodel ;
    String strPrefix ;
    
    public XorVerification()
    {
        
     //   readmodel=Utility.readModels("file:src\\project\\out.ttl",readmodel);
      //  writemodel=Utility.readModels("file:src\\project\\out.ttl",writemodel);
    }
    
  
   
   
    public void objectCreation(Queries Q)
    {
       boolean flage=false;
          //Query query = QueryFactory.create(new Queries().getxorsomevaluefromselect());
         //QueryExecution qe = QueryExecutionFactory.create(query, readmodel);
       
       String s="";
       //ResultSetFormatter.out(System.out, results);
       int i=1;
        }
    
    
    public boolean verification()
    {
        Queries Q =new Queries();
        Q.setSelectedQuery("SelectAll");
        objectCreation(Q);
        PelletOptions.USE_TRACING=true;
        Reasoner reasoner = PelletReasonerFactory.theInstance().create();
        InfModel model = ModelFactory.createInfModel( reasoner, MDIMain.model.writemodel );
        PelletInfGraph pellet = (PelletInfGraph) model.getGraph();
        
        if (!pellet.isConsistent())
        {
        
        //%get%the%explanation%for%current%inconsistency%
       Model explanation=pellet.explainInconsistency();
       return false;
       }
       else
       {
        Proba p= new Proba();
        
        return true;
       }
    }
    
    
   

    
      
    
}
