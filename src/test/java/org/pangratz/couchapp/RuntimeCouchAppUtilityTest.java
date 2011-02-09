/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.pangratz.couchapp;

/**
 *
 * @author clemens
 */
public class RuntimeCouchAppUtilityTest extends ICouchAppUtilityTest {

    @Override
    protected ICouchAppUtility getCouchAppUtility() {
        return new RuntimeCouchAppUtility();
    }

}
