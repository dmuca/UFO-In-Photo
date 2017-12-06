package com.kaizen.hoymm.ufoinphoto;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.SwapRotateToManagementFooterPanelAnimation;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by hoymm on 21.11.17.
 */

public class SwapRotateToManagementFooterPanelAnimationTest {
    private static MainActivity mainActivity;

    @BeforeClass
    public static void beforeClass(){
        mainActivity = new MainActivity();
    }

    @Test(expected = NullPointerException.class)
    public void testTryToInitEditImageCommunicationNullPointerException(){
        SwapRotateToManagementFooterPanelAnimation.tryToInitEditImageCommunication(null);
    }

    @Test(expected = ClassCastException.class)
    public void testTryToInitEditImageCommunicationClassCastException(){
        SwapRotateToManagementFooterPanelAnimation.tryToInitEditImageCommunication(mainActivity);
    }
}
