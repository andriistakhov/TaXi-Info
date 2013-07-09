package com.as_group.taxi_info;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import com.as_group.taxi_info.ui.MainActivity;

import static android.test.ViewAsserts.assertOnScreen;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.as_group.taxi_info.MainActivityTest \
 * com.as_group.taxi_info.tests/android.test.InstrumentationTestRunner
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {


    private MainActivity mActivity;
    private ListView mListView;

    public MainActivityTest() {
        super("com.as_group.taxi_info", MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
        mListView = (ListView) mActivity.findViewById(R.id.listView);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public final void testPreconditions() {
        assertNotNull(mActivity);
        assertNotNull(mListView);
    }

    public final void testListViewOnScreen() {
        final Window window = mActivity.getWindow();
        final View origin = window.getDecorView();
        assertOnScreen(origin, mListView);
    }

    public final void testListViewIsEmpty() {
        assertEquals(0, mListView.getChildCount());
    }

    public final void testListViewAdapterIsEmpty() {
        assertNotNull(mListView.getAdapter());
        assertEquals(0, mListView.getAdapter().getCount());
    }



}
