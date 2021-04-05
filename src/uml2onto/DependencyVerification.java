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
import com.hp.hpl.jena.reasoner.ValidityReport;
import com.hp.hpl.jena.reasoner.ValidityReport.Report;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import org.mindswap.pellet.PelletOptions;
import org.mindswap.pellet.exceptions.InconsistentOntologyException;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.mindswap.pellet.jena.PelletReasonerFactory;

/**
 *
 * @author bukhari
 */
public class DependencyVerification 
{
String feedback=null;
       
  public boolean Verification(OntModel model)
       {
        boolean res=true;
        PelletOptions.USE_TRACING=true;
        Reasoner reasoner = PelletReasonerFactory.theInstance().create();
        InfModel inmodel = ModelFactory.createInfModel( reasoner, model );
        PelletInfGraph pellet = (PelletInfGraph) inmodel.getGraph();
        ValidityReport vr=pellet.validate();
        Iterator <Report> r = vr.getReports();
        String s= "";
        while (r.hasNext())
            {
                Report r1 = r.next();
                s+=r1.type + " " + r1.description.split("#")[1] +"\n";
                res=false;
                
        }
       feedback=s;
        return res;
       }
  
}
    

