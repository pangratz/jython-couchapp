package org.pangratz.couchapp.jython;

import java.net.URL;
import java.util.Properties;
import org.python.core.Py;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class App {

    public static void main(String[] args) {

        // get the absolute OS path to couchapp package in src/main/resources/couchapp
        URL couchappURL = App.class.getResource("/couchapp");
        String couchappPath = couchappURL.getPath();

        // initialize python env
        Properties props = new Properties();
        props.setProperty("python.console.encoding", "UTF8");
        props.setProperty("file.encoding", "UTF8");
        PySystemState.initialize(PySystemState.getBaseProperties(), props, null);

        PySystemState engineSys = new PySystemState();

        // add the standard python library
        // TODO get a jar where these files are included or find another solution to this hard coded way
        engineSys.path.append(new PyString("/usr/local/Cellar/jython/2.5.1/libexec/Lib"));

        // add the couchapp pyton files
        engineSys.path.append(new PyString(couchappPath));
        
        Py.setSystemState(engineSys);

        PythonInterpreter interp = new PythonInterpreter(null, engineSys);
        interp.setOut(System.out);

        // invoke commands from couchapp lib
        interp.exec("from couchapp.dispatch import run");
        interp.exec("run()");
    }
}
