package org.pangratz.couchapp.jython;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import junit.framework.TestCase;
import org.python.core.Py;
import org.python.core.PyString;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

public class Test extends TestCase {

    public void test() throws IOException {

        System.out.println("prop = " + System.getProperty("python.home"));

        // initialize python env
        Properties props = new Properties();
        props.setProperty("python.console.encoding", "UTF8");
        props.setProperty("file.encoding", "UTF8");
        PySystemState.initialize(PySystemState.getBaseProperties(), props, null);

        PySystemState engineSys = new PySystemState();

        // add the standard python library
        // TODO get a jar where these files are included or find another solution to this hard coded way
        // engineSys.path.append(Py.newString("/usr/local/Cellar/jython/2.5.1/libexec/Lib"));

        // add the couchapp pyton files
        engineSys.path.append(getOSPath("/couchapp"));

        Py.setSystemState(engineSys);

        PythonInterpreter interp = new PythonInterpreter(null, engineSys);
        interp.setOut(System.out);
        // invoke commands from couchapp lib
        // interp.exec("from couchapp.dispatch import run");
        // interp.exec("run()");
        // interp.exec("from couchapp.dispatch import run");
        // interp.exec("import couchapp\nfrom couchapp.dispatch import run\n");
        Runtime runtime = Runtime.getRuntime();
        String cmd = "python " + getOSPath("/couchapp") + "/Couchapp.py help";
        System.out.println(cmd);
        Process exec = runtime.exec(cmd);
        InputStream in = exec.getInputStream();
        byte[] b = new byte[1024];
        while (in.read(b) != -1) {
            System.out.println(new String(b));
        }

    }

    private PyString getOSPath(String ressource) {
        String tmp = Test.class.getResource(ressource).getPath();
        return Py.newString(tmp);
    }
}
