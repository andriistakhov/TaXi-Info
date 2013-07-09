package com.as_group.taxi_info;

import android.test.AndroidTestCase;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 08.07.13
 * Time: 07:59
 * To change this template use File | Settings | File Templates.
 */
public class DBTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();    //To change body of overridden methods use File | Settings | File Templates.
    }

    public void testDBExist() {
        ApplicationMockapTest applicationMockapTest = new ApplicationMockapTest();
        applicationMockapTest.testCreateDB();
    }
}
