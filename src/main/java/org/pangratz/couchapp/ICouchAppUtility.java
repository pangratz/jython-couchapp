/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pangratz.couchapp;

/**
 *
 * @author clemens
 */
public interface ICouchAppUtility {

    public void generateApp(String dir, String name);

    public void generateView(String dir, String name);

    public void pushApp(String src, String dest);

}
