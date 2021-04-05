package uml2onto;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.sparql.resultset.ResultsFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bukhari
 */
public class AssociationVerification2 {
   
   
    public Graph searchInverse(String p)
   {
       Graph  g1 = null;
        if (p.startsWith("I"))
        p=p.substring(1, p.length());
        else
        p="I"+ p;
        for(Graph g :list)
           if (g.getProperty().equals(p))
              g1=g;
        return g1;
   }
        
    public AssociationVerification2()
       {
       }
    public boolean Verification(OntModel model)
    {
        makeList(model);
        for (Graph g :list)
        {
       System.out.println(g.property);
            findPath(g,g.getRange(),g.getDomain());
       break;
        }
        return true;
       }
      
       public Graph searchInGraph(String r,String p)
       {
        if (p.startsWith("I"))
        p=p.substring(1, p.length());
        else
        p="I"+ p;
         Graph g= null;
           for(Graph g1 :list)
           {
    //           System.out.println(r + " " + g.getRange());
               if ((g1.getDomain().equals(r)) && !(g1.getProperty().equals(p)))
               {
                  System.out.println(g1.getDomain() + " "+ r + " "+p );
                   g=g1;
               }
           }
           if (g==null)
           {
            for(Graph g1 :list)
           {   
               if (g1.getDomain().equals(r))
                   g=g1;
           }
           }
        return g;
       }
   public void findPath(Graph g,String r, String s)
   {
       
       ArrayList<String> Path= new ArrayList<>();
       String p=g.getProperty();
       Path.add(p);
       double weight=1.0;
       
      // System.out.print(g.getDomain()+" "+g.getProperty()+ g.getRange()+ "[Min="+ g.getMin()+"][Max="+ g.getMax() +"]\t" );
       while (true)
       { 
         //  System.out.print(g.getDomain()+" "+g.getProperty()+ " " +g.getRange()+ "[Min="+ g.getMin()+"][Max="+ g.getMax() +"]\t");
           Graph g1=searchInverse(p);
           System.out.println(g1.property);
           weight *=(double)g.getMax()*(1.0/ (double)g1.getMin());
           g= searchInGraph(g.getRange(),g.getProperty()); 
           r=g.getRange();
           p=g.getProperty();
           Path.add(p);
           if (s.equals(r))
           {
           g1=searchInverse(p);
           weight *=(double)g.getMax()*(1.0/ (double)g1.getMin());
           break;
           }
       }
       
       //System.out.print("\t" + weight);
       //System.out.println();
   }
    
   
    ArrayList<Graph> list;
    public void makeList(OntModel model)
    {
       ResultSet  Rs_WeightGraphList=SelectQuery(new Queries().listClassPropertyDomainRange,model);
     //  ResultSetFormatter.out(System.out,Rs_WeightGraphList); 
       list = new ArrayList<Graph>(); 
        while(Rs_WeightGraphList.hasNext())
        {
            QuerySolution QS=Rs_WeightGraphList.next(); 
            String p=QS.get("p").asNode().getLocalName();
            String d =QS.get("d").asNode().getLocalName();
            String r =QS.get("r").asNode().getLocalName();
            int min =QS.get("min").asLiteral().getInt();
            min=(min<1)?1 :min;
            int max=QS.get("max").asLiteral().getInt();
            //System.out.println(d + " "+p + " "+r + " "+min + " "+max);
            Graph g = new Graph(d,r,p,min,max);
            list.add(g);
        }
        
    }
    
    
//    mincard = Integer.parseInt(QS.get("min").asNode().getLiteralValue().toString());
        
    
    public ResultSet SelectQuery(String strQuery, Model m)
    {
    //System.out.println(strQuery);
        Query query = QueryFactory.create(strQuery);
        QueryExecution qe = QueryExecutionFactory.create(query, m);
  
         return  qe.execSelect();
         
    }
    
    
        
       
}
