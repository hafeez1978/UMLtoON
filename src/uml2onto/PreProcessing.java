/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml2onto;

import java.io.*;
import java.util.Scanner;


public class PreProcessing {
 // construct temporary file
public File RemoveExporter(String f)
{

 File inputFile = new File(f);
 File tempFile = new File("src\\uml2onto\\out.ttl");

 try
 {
 // construct temporary file
 
 BufferedReader br = new BufferedReader (new FileReader(inputFile));
 PrintWriter Pwr = new PrintWriter(new FileWriter (tempFile));
 String line = null;
 Pwr.println("@prefix : <http://www.semanticweb.org/bukhari/ontologies/2015/11/untitled-ontology-197#> .\n" +
"@prefix owl: <http://www.w3.org/2002/07/owl#>.\n" +
"@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>.\n" +
"@prefix xml: <http://www.w3.org/XML/1998/namespace>.\n" +
"@prefix xsd: <http://www.w3.org/2001/XMLSchema#>.\n" +
"@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>.\n"+
"@base <http://www.semanticweb.org/bukhari/ontologies/2015/11/untitled-ontology-197>. \n" +
"<http://www.semanticweb.org/bukhari/ontologies/2015/11/untitled-ontology-197> rdf:type owl:Ontology ."); 


 //read from original, write to temporary and trim space, while title not found
 while((line = br.readLine()) !=null) {
     
         Pwr.println(line);
         Pwr.flush();

     
 }
 br.close();
 Pwr.close();
 inputFile.delete();
 }
 catch (IOException e)
 {
     
 }
return  tempFile;
}
 
}