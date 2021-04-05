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
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author bukhari
 */
public class Instances {
OntModel model;
    ArrayList <CardinalityInstances> CA= new ArrayList<CardinalityInstances>() ;
ArrayList<Graph> list;
   public Instances(OntModel model)
   
   {
       
   makeList(model);
   this.model=model;
   getCLasses();
   for (Graph g :list)
        {    
          //  System.out.println(g.getDomain() + " " + g.getProperty() + " " + g.min);
     
        realizer(g);
       
        }
   for (int i=0;i<CA.size();i++) 
        {
        System.out.println(CA.get(i).CName +" " +CA.get(i).getInstance());
        
        }
       
   }
public Graph searchInverse(String p)
   {
       Graph  g1 = null;
        if (p.startsWith("i"))
        p=p.substring(1, p.length());
        else
        p="i"+ p;
        for(Graph g :list)
           if (g.getProperty().equals(p))
              g1=g;
        return g1;
   }
    public void getCLasses()
    {
        ResultSet  Rs_ClassList=SelectQuery(new Queries().ListClasses ,model);
     //   ResultSetFormatter.out(System.out,Rs_ClassList);
        while(Rs_ClassList.hasNext())
        {
          QuerySolution QS=Rs_ClassList.next(); 
          CardinalityInstances C= new CardinalityInstances();
          C.AddAll(QS.get("cla").asNode().getLocalName(), 1);
          CA.add(C);
        }
    }
    
    public ResultSet SelectQuery(String strQuery, Model m)
    {
    //System.out.println(strQuery);
        Query query = QueryFactory.create(strQuery);
        QueryExecution qe = QueryExecutionFactory.create(query, m);
  
         return  qe.execSelect();
         
    }
    
    public void realizer(Graph g)
    {
        System.out.println(g.domain + " "+ g.property + " " + g.range +" " + g.min);
        if (g.min>1)
        {
            Graph ip= searchInverse(g.property);
            //System.out.println(ip.property);
            int c = ip.getMin() * g.getMin();
            int i=getInstances(g.domain);
            if (c>CA.get(i).getInstance())
                CA.get(i).addInstance(c);
        }
       
    }
    public int getInstances(String CN)
    {
    int j=-1;
    for (int i =0 ; i< CA.size();i++)
    {
    if (CA.get(i).getInstance(CN))
    {
        j=i;
    }
     }
    return j;
    }
    
    public void makeList(OntModel  model)
    {
       ResultSet  Rs_WeightGraphList=SelectQuery(new Queries().listClassPropertyDomainRange,model);
       //ResultSetFormatter.out(System.out,Rs_WeightGraphList); 
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
            Graph g = new Graph(d,r,p,min,max);
            list.add(g);
        }
        
    }
    
        
        
    
//    mincard = Integer.parseInt(QS.get("min").asNode().getLiteralValue().toString());
        
    
        
       
}
