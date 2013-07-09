package com.as_group.taxi_info;

import android.test.mock.MockApplication;
import com.as_group.taxi_info.db.SQLiteHelper;

import java.io.File;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * Created with IntelliJ IDEA.
 * User: andriistakhov
 * Date: 05.07.13
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationMockapTest extends MockApplication {

    public void testCreateDB() {
        File database = getApplicationContext().getDatabasePath(SQLiteHelper.DATABASE_NAME);

        if (!database.exists()) {
            // Database does not exist so copy it from assets here
            assertTrue("DB EXIST", true);
        } else {
            fail("DB not exist");
        }
    }
}
