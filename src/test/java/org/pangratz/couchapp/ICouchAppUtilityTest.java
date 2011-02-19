package org.pangratz.couchapp;

import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;

public abstract class ICouchAppUtilityTest extends TestCase {

    private ICouchAppUtility couchAppUtil;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        couchAppUtil = getCouchAppUtility();
    }

    protected abstract ICouchAppUtility getCouchAppUtility();

    public void testGenerateApp() throws IOException {
        File tmpFile = File.createTempFile("tmpDirPrefix", "suffix");

        File parent = tmpFile.getParentFile();
        parent = new File("/tmp/tmpocuhapp");
        couchAppUtil.generateApp(parent.getPath(), "tmpCouchApp");

        // check if the app structure has been created
        File couchappJsonFile = new File(parent, "couchapp.json");
        assertTrue("there is no couchapp.json", couchappJsonFile.exists());
    }
}
