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
public class AssociationVerification {
   
    ArrayList<Graph> list;
    String feedback="";
   
    
        
    public boolean Verification(OntModel model)
       {
       double w=1.0;
      makeList(model);
      long st=System.nanoTime();
        for (Graph g :list)
        { 
            //System.out.println(g.getDomain() + " " + g.getProperty() + " " + g.getRange()+" " + g.weight);
          w=printAllPaths(g.domain, g.range,g.property);
        break;
        }
        System.out.println(w);
            long et = System.nanoTime()  ;
        
        
       if (w<1) return false; else return true; 
       }
       public ArrayList<Graph> searchInGraph(String r,String p,Map map)
       {
        if (p.startsWith("i"))
        p=p.substring(1, p.length());
        else
        p="i"+ p;
        //map.replace(p,true);
           ArrayList<Graph> ResList = new ArrayList<Graph>();
           
           for(Graph g :list)
           {
              
                //System.out.println(r + " " + g.getRange());
               if ((g.getDomain().equals(r)) && !(g.getProperty().equals(p)))
               {
                  ResList.add(g);
               }
               
                   
           
           }
          
           
        return ResList;
       }
    public double printAllPaths(String s, String r,String p)
    {   
    // Mark all the vertices as not visited
    Map<String, Boolean> map = new HashMap<String, Boolean>();
    
    // Create an array to store paths
    
    String[] path = new String[list.size()];
    int path_index = 0; // Initialize path[] as empty
   
    // Initialize all vertices as not visited
    for (Graph g :list)
        map.put(g.getProperty(), false); 
    // Call the recursive helper function to print all paths
  double w=printAllPathsUtil(s,r,p,map, path, path_index,1);
  if (w<1)
  for (int i=0;i<path.length;i++)
   {
    feedback+= (path[i]==null?"":"Unsatisfiable---->" + path[i]+"\n");
    
   }
  
    return (w);
     
    
    
    }
    
    public double printAllPathsUtil(String s, String r,String p, Map map, String path[], int path_index,double weight)
    {
        boolean rr=false;   
        map.replace(p,true);
           path[path_index] = p;
           path_index++;
           ArrayList <Graph> SR1 =searchInGraph(r,p,map);
           System.out.println(s +" "+ r );
           if ((s.equals(r) || SR1.size()<1))
            {
               
          
                
                return weight;
                    
            }
            else // If current vertex is not destination
            {
        // Recur for all the vertices adjacent to current vertex
           
            for (Graph g: SR1)
            {
             //   System.out.print(g.getProperty());
                Boolean b = (Boolean) map.get(g.getProperty());
                //System.out.println(b);
                weight=weight*g.getWeight();
                if (!b)
                     printAllPathsUtil(s, g.getRange(),g.getProperty(), map, path, path_index,weight);
                
            }     
           // System.out.println();
    }
      
    // Remove current vertex from path[] and mark it as unvisited
    
    map.replace(p, false);
    return weight;
   }
    
    
    @SuppressWarnings("null")
    public void makeList(OntModel model)
    {
       ResultSet  Rs_WeightGraphList=SelectQuery(new Queries().WeightGrpahList + "}",model);
      //ResultSetFormatter.out(System.out,Rs_WeightGraphList); 
       list = new ArrayList<Graph>(); 
        while(Rs_WeightGraphList.hasNext())
        {
            
            QuerySolution QS=Rs_WeightGraphList.next(); 
            String p=QS.get("p").asNode().getLocalName();
            String d =QS.get("d").asNode().getLocalName();
            String r =QS.get("r").asNode().getLocalName();
            Double w=QS.get("w")==null?1000.0:QS.get("w").asLiteral().getDouble(); 
            
            Graph g = new Graph(d,r,p,w);
            list.add(g);
        }
    }
    public ResultSet SelectQuery(String strQuery, Model m)
    {
    //System.out.println(strQuery);
        Query query = QueryFactory.create(strQuery);
        QueryExecution qe = QueryExecutionFactory.create(query, m);
  
         return  qe.execSelect();
         
    }
    }
    
    
        
    
    
        
       
