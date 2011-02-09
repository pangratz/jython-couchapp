/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.pangratz.couchapp;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clemens
 */
public class RuntimeCouchAppUtility implements ICouchAppUtility {

    private final Runtime runtime;
    private final String pathToCouchApp;

    public RuntimeCouchAppUtility() {
        super();

        URL couchappUrl = RuntimeCouchAppUtility.class.getResource("/couchapp");
        pathToCouchApp = couchappUrl.getPath() + "/Couchapp.py";

        runtime = Runtime.getRuntime();
    }

    @Override
    public void generateApp(String dir, String name) {
        generate("app", dir, name);
    }

    @Override
    public void pushApp(String src, String dest) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void generateView(String dir, String name) {
        generate("view", dir, name);
    }

    private void generate(String command, String dir, String param) {
        try {
            String exec = "python " + pathToCouchApp + " generate " + command + " " + dir + " " + param;
            System.out.println(exec);
            runtime.exec(exec);
        } catch (IOException ex) {
            Logger.getLogger(RuntimeCouchAppUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
