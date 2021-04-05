/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml2onto;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

/**
 *
 * @author bukhari
 */
 
public class Models {
 OntModel readmodel ;
 OntModel writemodel ;
 
 public Models()
 {
     writemodel = ModelFactory.createOntologyModel();
     readmodel = ModelFactory.createOntologyModel();
 }
   public void creatModel(String str)
    {
         writemodel.read(str,"TURTLE");
         readmodel.read(str,"TURTLE");
         
    }
}

