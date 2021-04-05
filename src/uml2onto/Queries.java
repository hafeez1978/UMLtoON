/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.

/**
 *
 * @author bukhari
 */
package uml2onto;
public class Queries {
private String selectedQurey;
public void setSelectedQuery(String s)
{
    selectedQurey =s;
}

String strPrefix = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"+
                            "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"+
                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"+
                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"+
                            "PREFIX afn: <http://jena.hpl.hp.com/ARQ/function#>\n" +
"prefix : <http://www.semanticweb.org/bukhari/ontologies/2016/7/untitled-ontology-308#>";
 String xordisjointsomevaluefromselect= strPrefix + "SELECT distinct ?domain ?property ?range ?min ?max ?Remarks\n" +
"WHERE { \n" +
" ?domain owl:equivalentClass ?EqC .\n" +
" ?EqC owl:unionOf ?Uof.\n" +
"?Uof ?B1 ?B2.\n" +
"?Iof ?B3 ?B4.\n" +
"?B4 owl:onProperty ?property.\n" +
"optional {?p2 owl:propertyDisjointWith ?property.}\n" +
"optional {?property owl:propertyDisjointWith ?p2.}\n" +
"?B4  owl:someValuesFrom ?range.\n" +
"?B4 ?B6 ?range\n" +
"BIND (1 AS ?min). BIND (1000 AS ?max). BIND (\"xor\"^^xsd:string As ?Remarks) }";

String countPropertyInClass = strPrefix + "select distinct   (COUNT(?property) AS ?Count)  ?Range\n" +
" where { \n" +
"?domainclass owl:equivalentClass ?restriction . \n" +
"?restriction owl:intersectionOf ?ingredient1. \n" +
"?ingredient1 ?a ?b. \n" +
"?b ?c ?d.\n" +
"?d owl:intersectionOf ?e. \n" +
"?f ?g ?h. \n" +
"?h owl:minQualifiedCardinality ?rangeclass. \n" +
"?h owl:onProperty ?property. \n" +
"?h owl:onClass   ?Range \n" +
"} group by ?class \n"
        + "HAVING(COUNT(?property) > 1)";

String xorsomevaluefromselect = strPrefix+
                            "SELECT distinct ?domain ?property ?range ?min ?max ?Remarks\n" +
"WHERE { \n" +
" ?domain owl:equivalentClass ?EqC .\n" +
" ?EqC owl:unionOf ?Uof.\n" +
"?Uof ?B1 ?B2.\n" +
"?B2 owl:intersectionOf ?Iof.\n" +
"?Iof ?B3 ?B4.\n" +
"optional {?B4 owl:complementOf ?B5.}\n" +
"?B5 owl:onProperty ?property.\n" +
"?B5  owl:someValuesFrom ?range.\n" +
"?B5 ?B6 ?range\n" +
"BIND (1 AS ?min). BIND (1000 AS ?max). BIND (\"xor\"^^xsd:string As ?Remarks) }";

String SelectAll = strPrefix+ "Select distinct ?domain ?property ?range ?min ?max ?Remarks\n" +
        
"WHERE { \n" +
" {?domain owl:equivalentClass ?EqC .\n" +
" ?EqC owl:unionOf ?Uof.\n" +
"?Uof ?B1 ?B2.\n" +
"?B2 owl:intersectionOf ?Iof.\n" +
"?Iof ?B3 ?B4.\n" +
"optional {?B4 owl:complementOf ?B5.}\n" +
"?B5 owl:onProperty ?property.\n" +
"?B5  owl:someValuesFrom ?range.\n" +
"?B5 ?B6 ?range\n" +
"BIND (1 AS ?min). BIND (1000 AS ?max). BIND (\"xor\"^^xsd:string As ?Remarks) }" +
"UNION \n" +
"{ \n" +
" ?domain owl:equivalentClass ?EqC .\n" +
" ?EqC owl:unionOf ?Uof.\n" +
"?Uof ?B1 ?B2.\n" +
"?Iof ?B3 ?B4.\n" +
"?B4 owl:onProperty ?property.\n" +
"optional {?p2 owl:propertyDisjointWith ?property.}\n" +
"optional {?property owl:propertyDisjointWith ?p2.}\n" +
"?B4  owl:someValuesFrom ?range.\n" +
"?B4 ?B6 ?range\n" +
"BIND (1 AS ?min). BIND (1000 AS ?max). BIND (\"xor\"^^xsd:string As ?Remarks) }}" ;

String xorsomevaluefromconstruct1 = strPrefix + "construct  { ?domain ?property  ?range}\n" +
"WHERE { \n" +
"{ select distinct ?domain ?property  ?range where {\n" +
"?domainclass owl:equivalentClass ?restriction .\n" +
"?restriction owl:unionOf ?ingredient1.\n" +
"?ingredient1 ?a ?b.\n" +
"?b owl:intersectionOf ?d.\n" +
"?d  ?e ?f.\n" +
"optional {?f owl:complementOf ?g.}\n" +
"?g  owl:someValuesFrom ?rangeclass.\n" +
"?g owl:onProperty ?property.\n" +
"?domain rdf:type ?domainclass.\n" +
"?range rdf:type ?rangeclass"
        + "}}}";
String modelcreationConstruct= strPrefix + "construct  { ?domain ?property  ?range} \n" + 
"WHERE { \n" +  
"{select  ?domain ?property ?range \n" + 
"where  {\n " + 
"?domain ?property ?range. \n " +
"?doamin rdf:type ?domainclass. \n " +
"?range rdf:type ?rangeclass. \n " +
"?property rdf:type owl:ObjectProperty. \n " +
"}}}\n "; 
String xorsomevaluefromconstruct2 = strPrefix +"construct  { ?domain ?property  ?range}\n" +
"WHERE { \n" +
"select  ?domain ?property ?range\n" +
"where  {\n" +
"{select distinct ?domain ?property  ?range where { \n" +
"?domainclass owl:equivalentClass ?restriction . \n" +
"?restriction owl:unionOf ?ingredient1. \n" +
"?ingredient1 ?a ?b. \n" +
"?b owl:intersectionOf ?d. \n" +
"?d  ?e ?f. \n" +
"optional {?f owl:complementOf ?g.} \n" +
"?g  owl:someValuesFrom ?rangeclass. \n" +
"?g owl:onProperty ?property. \n" +
"?domain rdf:type ?domainclass. \n" +
"?range rdf:type ?rangeclass\n" +
"         }}\n" +
"{select distinct ?domain1 ?property1  ?range1 where { \n" +
"?domainclass owl:equivalentClass ?restriction . \n" +
"?restriction owl:unionOf ?ingredient1. \n" +
"?ingredient1 ?a ?b. \n" +
"?b owl:intersectionOf ?d. \n" +
"?d  ?e ?f. \n" +
"optional {?f owl:complementOf ?g.} \n" +
"?g  owl:someValuesFrom ?rangeclass. \n" +
"?g owl:onProperty ?property. \n" +
"?domain1 rdf:type ?domainclass. \n" +
"?range1 rdf:type ?rangeclass.\n" +
" Filter Exists {?domain1 ?property1 ?range1} \n" +
"}}\n" +
"Filter (?domain !=?domain1 && ?range != ?range1)\n" +
"}}";
String xorfeedbackselect = strPrefix + "select ?domainclass ?property ?rangeclass \n" +
"WHERE { \n" +
"?domain rdf:type ?domainclass. \n" + 
"?range rdf:type ?rangeclass. \n" +
"?domain ?property ?range }";

//This Query Make the Path fromthe OWLModel 
String CardinalityConstruct =strPrefix + "CONSTRUCT  {?c :path ?p} \n" +
"{\n" +
"{?p rdfs:domain ?c.}\n" +
"union\n" +
"{?c rdfs:range ?p.}\n" +
"}";

String PathCalculation =strPrefix + "select ?B ?x ?p ?y where {\n ?B (:|!:)* ?x . \n ?x :path ?y ."+
"?y (:|!:)* ?B} "; 

String ListProperties=strPrefix + "Select ?d ?p ?r where {?p rdf:type owl:ObjectProperty. \n"
        + "?p rdfs:domain ?d. ?p rdfs:range ?d.\n}";

String WeightGrpahList=strPrefix + "Select distinct ?d ?p ?r ((?max1 * ((1/?min2))) as ?w) ?min1 ?max1 \n" +
"where {\n" +
"{?p rdfs:domain ?d. "
        + "?p  rdfs:range ?c. "
        + "?p owl:inverseOf ?ip. "
        + "?c rdf:type owl:Class; \n" +
"owl:intersectionOf ([ rdf:type owl:Restriction ; owl:onProperty ?p ;    owl:onClass ?r ; owl:minQualifiedCardinality ?min1  ] "
        + "[ rdf:type owl:Restriction ; owl:onProperty ?p ; owl:onClass ?r ; owl:maxQualifiedCardinality ?max1 ]) .\n" +
"?ip rdfs:domain ?d1. "
        + "?ip  rdfs:range ?c1. "
        + "?c1 rdf:type owl:Class; \n" +
"owl:intersectionOf ([ rdf:type owl:Restriction ; owl:onProperty ?ip ;    owl:onClass ?r1 ; owl:minQualifiedCardinality ?min2  ]   "
        + "[ rdf:type owl:Restriction ; owl:onProperty ?ip ; owl:onClass ?r1 ; owl:maxQualifiedCardinality ?max2 ]).}\n" +
"Union\n" +
"{?p rdfs:domain ?d. "
        + "?p  rdfs:range ?c. "
        + "?ip owl:inverseOf ?p."
        + " ?c rdf:type owl:Class; \n" +
"owl:intersectionOf ([ rdf:type owl:Restriction ; owl:onProperty ?p ;    owl:onClass ?r ; owl:minQualifiedCardinality ?min1  ] "
        + "[ rdf:type owl:Restriction ; owl:onProperty ?p ; owl:onClass ?r ; owl:maxQualifiedCardinality ?max1 ]) .\n" +
"?ip rdfs:domain ?d1. "
        + "?ip  rdfs:range ?c1. "
        + "?c1 rdf:type owl:Class; \n" +
"owl:intersectionOf ([ rdf:type owl:Restriction ; owl:onProperty ?ip ;    owl:onClass ?r1 ; owl:minQualifiedCardinality ?min2  ]   "
        + "[ rdf:type owl:Restriction ; owl:onProperty ?ip ; owl:onClass ?r1 ; owl:maxQualifiedCardinality ?max2 ])."
        + "}\n";
        

String CountProperty= strPrefix + "SELECT  (count(?p) as ?totalproperty) \n" +
	"WHERE { ?p rdf:type owl:ObjectProperty.  minus {?ip owl:inverseOf ?p.}}";

String ListClasses =strPrefix +"SELECT  distinct ?cla \n" +
"	WHERE { ?cla rdf:type owl:Class. \n" +
"		?p rdfs:domain ?cla }";

String listClassPropertyDomainRange=strPrefix + "Select distinct ?d ?p ?r  ?min ?max \n" +
"where { \n" +
"?p rdfs:domain ?d.  \n" +
"?p  rdfs:range ?c.     \n" +
"      ?c rdf:type owl:Class;\n" +
"owl:intersectionOf ([ rdf:type owl:Restriction ; owl:onProperty ?p ;    owl:onClass ?r ; owl:minQualifiedCardinality ?min  ]  \n" +
"  [ rdf:type owl:Restriction ; owl:onProperty ?p ; owl:onClass ?r ; owl:maxQualifiedCardinality ?max ]) .\n" +
"}";
@Override

public String toString()
{
    String res=""; 
    switch (selectedQurey)
    {
        case "xorsomevaluefromselect"  :
            res= getxorsomevaluefromselect();
            break;
        case "xorsomevaluefromconstruct2":
            res= getxorsomevaluefromconstruct2();
            break;
        case "xorsomevaluefromconstruct1":
            res= getxorsomevaluefromconstruct1();
            break;
        case "xorfeedbackselect":
            res= getxorfeedbackselect();
            break;
        case "xordisjointsomevaluefromselect":
            res= xordisjointsomevaluefromselect;
            break;
        case "SelectAll":
            res =SelectAll;
            break;
    }       
    return res;
}
 
public String getxorfeedbackselect(){ return xorfeedbackselect; }
public String getxorsomevaluefromconstruct1(){ return xorsomevaluefromconstruct1;}
public String getxorsomevaluefromconstruct2(){ return xorsomevaluefromconstruct2;}
public String getxorsomevaluefromselect(){ return xorsomevaluefromselect;}

}
