/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml2onto;

/**
 *
 * @author bukhari
 */
public class Graph {
    String domain;
    String range;
    String property;
    double weight;
    int min;
    int max;
    Graph(String d,String r,String p,double w)
    {
    domain=d;
    range=r;
    property=p;
    weight=w;
    }
    Graph(String d,String r,String p,int min)
    {
    domain=d;
    range=r;
    property=p;
    this.min=min;
    }
    Graph(String d,String r,String p,double w, int min)
    {
    domain=d;
    range=r;
    property=p;
    weight=w;
    this.min=min;
    }
    Graph(String d,String r,String p,int min, int max)
    {
    domain=d;
    range=r;
    property=p;
    this.min=min;
    this.max=max;
    }
    Graph(String d,String r,String p,double w, int min,int max)
    {
    domain=d;
    range=r;
    property=p;
    weight=w;
    this.min=min;
    this.max=max;
    }

    public String getDomain(){return domain;}
    public String getRange(){return range;}
    public String getProperty(){return property;}
    public double getWeight(){return weight;}
    public int getMin(){return min;}
    public int getMax(){return max;}
    
}
