package com.kaizen.hoymm.ufoinphoto;

import android.annotation.SuppressLint;
import android.widget.FrameLayout;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.ArrayHasDifferentSizeThenExpected;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImage;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterComponentChanges;

import org.junit.BeforeClass;
import org.junit.Test;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterComponentChanges.startHideRotateFooterThenShowSelectedComponents;
import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterManagementFragment.HOW_MANY_BUTTONS;

/**
 * Created by hoymm on 21.11.17.
 */

public class FooterComponentChangesTest {
    private static boolean [] properSizeArray, wrongSizeArray;
    private static FrameLayout frameLayout;
    private static MainActivity mainActivity;

    @BeforeClass
    public static void beforeClass(){
        properSizeArray = new boolean [HOW_MANY_BUTTONS];
        for (int i = 0; i < HOW_MANY_BUTTONS; ++i)
            properSizeArray[i] = false;


        wrongSizeArray = new boolean [HOW_MANY_BUTTONS-1];
        for (int i = 0; i < HOW_MANY_BUTTONS-1; ++i)
            wrongSizeArray[i] = false;

        frameLayout = new FrameLayout(new MainActivity().getApplicationContext());
        mainActivity = new MainActivity();
    }

    @Test
    public void startHideRotateFooterThenShowSelectedComponentsTest(){
        startHideRotateFooterThenShowSelectedComponents(frameLayout, new EditImage(), properSizeArray);
    }

    @Test(expected = NullPointerException.class)
    public void testTryToInitEditImageCommunicationNullPointerException(){
        FooterComponentChanges.tryToInitEditImageCommunication(null);
    }

    @Test(expected = ClassCastException.class)
    public void testTryToInitEditImageCommunicationClassCastException(){
        FooterComponentChanges.tryToInitEditImageCommunication(mainActivity);
    }

    @Test(expected = NullPointerException.class)
    public void startHideRotateFooterThenShowSelectedComponentsTestNullFrameLayout(){
        startHideRotateFooterThenShowSelectedComponents(null, new EditImage(), properSizeArray);
    }

    @Test(expected = NullPointerException.class)
    public void startHideRotateFooterThenShowSelectedComponentsTestNullContextSend(){
        startHideRotateFooterThenShowSelectedComponents(frameLayout, null, properSizeArray);
    }

    @Test(expected = NullPointerException.class)
    public void startHideRotateFooterThenShowSelectedComponentsTestNullArraySend(){
        startHideRotateFooterThenShowSelectedComponents(frameLayout, new EditImage(), null);
    }

    @Test(expected = NullPointerException.class)
    public void startHideRotateFooterThenShowSelectedComponentsTestNull2_1(){
        startHideRotateFooterThenShowSelectedComponents(null, null, properSizeArray);
    }

    @Test(expected = NullPointerException.class)
    public void startHideRotateFooterThenShowSelectedComponentsTestNull1_2(){
        startHideRotateFooterThenShowSelectedComponents(frameLayout, null, null);
    }

    @Test(expected = NullPointerException.class)
    public void startHideRotateFooterThenShowSelectedComponentsTestAllNull(){
        startHideRotateFooterThenShowSelectedComponents(null, null, null);
    }

    @Test(expected = ArrayHasDifferentSizeThenExpected.class)
    public void startHideRotateFooterThenShowSelectedComponentsTestNotProperArraySend(){
        startHideRotateFooterThenShowSelectedComponents(frameLayout, new EditImage(), wrongSizeArray);
    }
}
