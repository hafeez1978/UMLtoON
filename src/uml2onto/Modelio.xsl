<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xalan="http://xml.apache.org/xalan"
 xmlns:uml="http://www.omg.org/spec/UML/20110701" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" 
 xmlns:rdfs="http://www.w3.org/2000/01/rdf-schema#" xmlns:owl="http://www.w3.org/2002/07/owl#"
xmlns:mf="http://localhost:8080/customFunctions"  xmlns:xs="http://www.w3.org/2001/XMLSchema"         exclude-result-prefixes="rdf" >
<xsl:output method="text" encoding="UTF-8" indent="yes" xalan:indent-amount="4" omit-xml-declaration="yes"/>
<xsl:key name="Classid" match="/uml:Model/packagedElement" use="@xmi:id"/>
<xsl:key name="consid" match="/uml:Model/packagedElement/ownedEnd" use="@xmi:id"/> 


<xsl:template match="/uml:Model/packagedElement">
<xsl:if test="./@xmi:type='uml:Dependency'"> 
    <xsl:if test="@name='Create'">
                 :<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty.
                 :A<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty,
                 owl:AsymmetricProperty;
                  rdfs:subPropertyOf :<xsl:value-of select="@name"/>.
 
                  :T<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty,
                 owl:TransitiveProperty ;
                  rdfs:subPropertyOf :<xsl:value-of select="@name"/>.
                  :<xsl:value-of select="key('Classid',@client)/@name"/> owl:equivalentClass 
                  [ rdf:type owl:Class ;
                  owl:intersectionOf ( [ rdf:type owl:Class ;
                                                owl:complementOf [ rdf:type owl:Restriction ;
                                                                   owl:onProperty :Create ;
                                                                   owl:someValuesFrom :<xsl:value-of select="key('Classid',@client)/@name"/>
                                                                 ]
                                              ]
                                              [ rdf:type owl:Restriction ;
                                                owl:onProperty :ACreate ;
                                                owl:someValuesFrom :<xsl:value-of select="key('Classid',@supplier)/@name"/>
                                              ]
                                              [ rdf:type owl:Restriction ;
                                                owl:onProperty :TCreate ;
                                                owl:someValuesFrom :<xsl:value-of select="key('Classid',@supplier)/@name"/>
                                              ]
                                            )
                       ] .


                
   </xsl:if>       
    <xsl:if test="@name='Drive'">
                 :<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty.
                 :A<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty,
                 owl:AsymmetricProperty;
                  rdfs:subPropertyOf :<xsl:value-of select="@name"/>.
 
                  :T<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty,
                 owl:TransitiveProperty ;
                  rdfs:subPropertyOf :<xsl:value-of select="@name"/>.
                  :<xsl:value-of select="key('Classid',@client)/@name"/> owl:equivalentClass 
                  [ rdf:type owl:Class ;
                  owl:intersectionOf ( [ rdf:type owl:Class ;
                                                owl:complementOf [ rdf:type owl:Restriction ;
                                                                   owl:onProperty :Drive ;
                                                                   owl:someValuesFrom :<xsl:value-of select="key('Classid',@client)/@name"/>
                                                                 ]
                                              ]
                                              [ rdf:type owl:Restriction ;
                                                owl:onProperty :ADrive ;
                                                owl:someValuesFrom :<xsl:value-of select="key('Classid',@supplier)/@name"/>
                                              ]
                                              [ rdf:type owl:Restriction ;
                                                owl:onProperty :TDrive ;
                                                owl:someValuesFrom :<xsl:value-of select="key('Classid',@supplier)/@name"/>
                                              ]
                                            )
                       ] .

    </xsl:if>

<xsl:if test="@name='Call'">
                 :<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty;
                 owl:TransitiveProperty.
</xsl:if>
<xsl:if test="@name='Use'">
                 :<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty;
                 owl:TransitiveProperty.
</xsl:if>
</xsl:if>             
    
    <xsl:if test="./@xmi:type='uml:Class'"> 
        :<xsl:value-of select="@name"/> rdf:type owl:Class.
        <xsl:for-each select="./ownedAttribute">
            :<xsl:value-of select="@name"/> rdf:type owl:DatatypeProperty;
                                       rdfs:domain :<xsl:value-of select="../@name"/>;
                                            
            <xsl:if test="./type/@href = 'http://www.omg.org/spec/UML/20110701/PrimitiveTypes.xmi#Integer'">
                                        rdfs:range  xsd:Integer .
            </xsl:if>
            <xsl:if test="././type/@href = 'http://www.omg.org/spec/UML/20110701/PrimitiveTypes.xmi#String'">
                                        rdfs:range xsd:String .
            </xsl:if>
        </xsl:for-each>
        <xsl:for-each select="./generalization">
            :<xsl:value-of select="../@name"/> rdfs:subClassOf :<xsl:value-of select="key('Classid',@general)/@name"/>.
        </xsl:for-each>
    </xsl:if>
        <xsl:if test="./@xmi:type='uml:Association'">
        <xsl:choose>
            <xsl:when test="mf:check(./ownedEnd/@xmi:id,../ownedRule/@constrainedElement)">
            </xsl:when>
        <xsl:otherwise>
        :<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty;
        
       <xsl:for-each select="./ownedEnd">
        <xsl:if test="position()!=1">
            
                rdfs:domain :<xsl:value-of select="key('Classid',@type)/@name"/>;
        </xsl:if>      
        <xsl:if test="position()!=1">
            rdfs:range [ rdf:type owl:Class ;
            owl:intersectionOf ( [ rdf:type owl:Restriction; 
                                        owl:onProperty :<xsl:value-of select="../@name"/>;
                                        owl:onClass :<xsl:value-of select="key('Classid',./preceding-sibling::*[1]/@type)/@name"/>;
                                        owl:minQualifiedCardinality "<xsl:choose> 
                                            <xsl:when test="not(./preceding-sibling::*[1]/lowerValue)">1</xsl:when>  
                                            <xsl:when test="./preceding-sibling::*[1]/lowerValue/@value">  
                                                <xsl:value-of select="./preceding-sibling::*[1]/lowerValue/@value"/>  
                                            </xsl:when>  
                                            <xsl:otherwise>0</xsl:otherwise>  
                                        </xsl:choose>"^^xsd:nonNegativeInteger]
                                     [ rdf:type owl:Restriction ;
                                        owl:onProperty :<xsl:value-of select="../@name"/>;
                                        owl:onClass :<xsl:value-of select="key('Classid',./preceding-sibling::*[1]/@type)/@name"/>;
                                        owl:maxQualifiedCardinality "<xsl:choose> 
                                            <xsl:when test="./preceding-sibling::*[1]/upperValue/@value='*'">1000</xsl:when> 
                                            <xsl:when test="not(./preceding-sibling::*[1]/upperValue)">1</xsl:when> 
                                            <xsl:when test="./preceding-sibling::*[1]/upperValue/@value">
                                                <xsl:value-of select="./preceding-sibling::*[1]/upperValue/@value"/> 
                                            </xsl:when> 
                                            <xsl:otherwise>0</xsl:otherwise> 
                                        </xsl:choose>"^^xsd:nonNegativeInteger])].

        </xsl:if>
              
                
        </xsl:for-each>    
        :i<xsl:value-of select="@name"/> rdf:type owl:ObjectProperty;
            owl:inverseOf :<xsl:value-of select="@name"/>;
        <xsl:for-each select="./ownedEnd">
                <xsl:if test="position()=1">
                rdfs:domain :<xsl:value-of select="key('Classid',@type)/@name"/>;
                </xsl:if>
                
                <xsl:if test="position()=1">
                  rdfs:range [ rdf:type owl:Class ;
                owl:intersectionOf ( [ rdf:type owl:Restriction; 
                                        owl:onProperty :i<xsl:value-of select="../@name"/>;
                                        owl:onClass :<xsl:value-of select="key('Classid',./following-sibling::*[1]/@type)/@name"/>;
                                        owl:minQualifiedCardinality "<xsl:choose>  
                                            <xsl:when test="not(./following-sibling::*[1]/lowerValue)">1</xsl:when>  
                                            <xsl:when test="./following-sibling::*[1]/lowerValue/@value">  
                                                <xsl:value-of select="./following-sibling::*[1]/lowerValue/@value"/>  
                                            </xsl:when>  
                                            <xsl:otherwise>0</xsl:otherwise>  
                                        </xsl:choose>"^^xsd:nonNegativeInteger]
                                     [ rdf:type owl:Restriction ;
                                        owl:onProperty :i<xsl:value-of select="../@name"/>;
                                        owl:onClass :<xsl:value-of select="key('Classid',./following-sibling::*[1]/@type)/@name"/>;
                                        owl:maxQualifiedCardinality "<xsl:choose> 
                                            <xsl:when test="./following-sibling::*[1]/upperValue/@value='*'">1000</xsl:when> 
                                            <xsl:when test="not(./following-sibling::*[1]/upperValue)">1</xsl:when> 
                                            <xsl:when test="./following-sibling::*[1]/upperValue/@value">
                                                <xsl:value-of select="./following-sibling::*[1]/upperValue/@value"/> 
                                            </xsl:when> 
                                            <xsl:otherwise>0</xsl:otherwise> 
                                        </xsl:choose>"^^xsd:nonNegativeInteger])].

                </xsl:if>
                
        </xsl:for-each>
        </xsl:otherwise>
        </xsl:choose>      
        </xsl:if>
       
    
        
</xsl:template>

<xsl:template match="/uml:Model/ownedRule">
    <xsl:if test="@name='xor'">
    <xsl:variable name="abc" select="tokenize(@constrainedElement,' ')"/>
    <xsl:variable name= "key1" select="key('consid',$abc[1])/@association"/>
    <xsl:variable name= "key2" select="key('consid',$abc[2])/@association"/>
    <xsl:variable name="Ass1" select="key('Classid',$key1)/@name"/>
    <xsl:variable name ="Ass2" select="key('Classid',$key2)/@name"/>
    <xsl:variable name="Ass1End" select="key('Classid',$key1)/@memberEnd"/>
    <xsl:variable name ="Ass2End" select="key('Classid',$key2)/@memberEnd"/>
    <xsl:variable name="leftAssownedend1" select="mf:searchobject($Ass1End,2)"/>
            <xsl:variable name="leftclassid1" select="key('consid',$leftAssownedend1)/@type"/>
            
            <xsl:variable name="rightAssownedend1" select="mf:searchobject($Ass1End,1)"/>
            <xsl:variable name="rightclassid1" select="key('consid',$rightAssownedend1)/@type"/>
            
            <xsl:variable name="rightAssownedend2" select="mf:searchobject($Ass2End,1)"/>
            <xsl:variable name="rightclassid2" select="key('consid',$rightAssownedend2)/@type"/>
            
    <xsl:choose>
        <xsl:when test ="$Ass1=$Ass2">
            :<xsl:value-of select="$Ass1"/> rdf:type owl:ObjectProperty.
                     
            :<xsl:value-of select="key('Classid',$leftclassid1)/@name"/> 
        owl:equivalentClass [rdf:type owl:Class ;    
                    owl:intersectionOf ( [ rdf:type owl:Class ;
                                    owl:intersectionOf ( [ rdf:type owl:Class ;
                                     owl:complementOf [ rdf:type owl:Restriction ;
                                         owl:onProperty :<xsl:value-of select="$Ass1"/> ;
                                         owl:someValuesFrom :<xsl:value-of select="key('Classid',$rightclassid1)/@name"/>]]
                                      [ rdf:type owl:Restriction ;
                                        owl:onProperty :<xsl:value-of select="$Ass1"/> ;
                                       owl:someValuesFrom :<xsl:value-of select="key('Classid',$rightclassid2)/@name"/>
                                       ])]
                                      [ rdf:type owl:Class ;
                                         owl:unionOf ( [ rdf:type owl:Restriction ;
                                         owl:onProperty :<xsl:value-of select="$Ass1"/>  ;
                                         owl:someValuesFrom :<xsl:value-of select="key('Classid',$rightclassid1)/@name"/>]
                                        [ rdf:type owl:Restriction ;
                                          owl:onProperty :<xsl:value-of select="$Ass1"/> ;
                                          owl:someValuesFrom :<xsl:value-of select="key('Classid',$rightclassid2)/@name"/>])])].
     
          
        </xsl:when>
        
        <xsl:otherwise>
            :<xsl:value-of select="key('Classid',$leftclassid1)/@name"/> 
                owl:equivalentClass [rdf:type owl:Class ;
                           owl:unionOf ( [ rdf:type owl:Restriction ;
                                           owl:onProperty :<xsl:value-of select="$Ass1"/> ;
                                           owl:someValuesFrom :<xsl:value-of select="key('Classid',$rightclassid1)/@name"/>
                                         ]
                                         [ rdf:type owl:Restriction ;
                                           owl:onProperty :<xsl:value-of select="$Ass2"/> ;
                                           owl:someValuesFrom :<xsl:value-of select="key('Classid',$rightclassid1)/@name"/>
                                         ]
                                       )
                         ] .
        
            :<xsl:value-of select="$Ass1"/> rdf:type owl:ObjectProperty.
            :<xsl:value-of select="$Ass2"/> rdf:type owl:ObjectProperty.
            :<xsl:value-of select="$Ass1"/> owl:propertyDisjointWith :<xsl:value-of select="$Ass2"/>.
            
            </xsl:otherwise>
    </xsl:choose>
         
    </xsl:if>
  
    
</xsl:template>
<xsl:function name="mf:check" as="xs:boolean">
  <xsl:param name="input" as="xs:string"/>
  <xsl:param name="input2" as="xs:string"/>
  <xsl:variable name="abc" select="tokenize($input2,' ')"/>  
  <xsl:choose>
<xsl:when test="$input=$abc[1] or $input=$abc[2]">
  <xsl:sequence select="true()"/>   
</xsl:when>
<xsl:otherwise>
<xsl:sequence select="false()"/>   
</xsl:otherwise>
  </xsl:choose> 
    
</xsl:function>
<xsl:function name="mf:searchobject" as="xs:string">
  <xsl:param name="input" as="xs:string"/>
  <xsl:param name="position" as="xs:string"/>
  <xsl:variable name="abc" select="tokenize($input,' ')"/>  
  <xsl:choose>
<xsl:when test="$position=1">
  <xsl:sequence select="$abc[1]"/>   
</xsl:when>
<xsl:otherwise>
<xsl:sequence select="$abc[2]"/>   
</xsl:otherwise>
  </xsl:choose> 
    
</xsl:function>

<!-- [End -->
</xsl:stylesheet>
