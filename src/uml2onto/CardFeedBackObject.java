/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml2onto;

import java.util.ArrayList;

/**
 *
 * @author bukhari
 */
public class CardFeedBackObject {
    String[] path;
    double weight;
    public void addPath(String[] p)
    {
        path=p;
    }
    public void addWeight(double w)
    {
    weight=w;
    }
    public String[] getPath()
    {
    return path;
    }
    public String getElement(int i)
    {
    return path[i];
    }
    public double getweight()
    {
    return weight;
    }
    public boolean criticalCycle()
    {
    if (weight <1) return true; else return false;
    }
    public String Feedback(String Array)
    {
        String s="";
        
        if (weight <1)
        {
            for (String i : path)
            {
                if (i.startsWith("i"))
                {
                i=i.substring(1, i.length());
               if (!find(i,Array))
                s=s + " " + i;
                }
            }
        
        }
        return s ;
    }
    public boolean find (String a,String array)
    {
        boolean r=false;
        if (array.contains(a)) r= true; else r= false;
        return r;
    }
}
