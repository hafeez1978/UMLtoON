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
public class CardinalityInstances {
    String CName;
    int Instance;
    public void addClassName(String CN)
    {
        CName=CN;
    }
    public void AddAll(String CN, int i)
    {
    CName=CN;
    Instance=i;
    }
    public void addInstance(int i)
    {
    Instance=i;
    }
    public String getClassName()
    {
    return CName;
    }
    public int getInstance()
    {
    return Instance;
    }
    public boolean getInstance(String CN)
    {
        boolean i=false;
        if (CN.equals(CName))
            i=true;
        
        return i;
    }
}