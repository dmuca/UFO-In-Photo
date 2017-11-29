package com.kaizen.hoymm.ufoinphoto;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterComponentChanges;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by hoymm on 21.11.17.
 */

public class FooterComponentChangesTest {
    private static MainActivity mainActivity;

    @BeforeClass
    public static void beforeClass(){
        mainActivity = new MainActivity();
    }

    @Test(expected = NullPointerException.class)
    public void testTryToInitEditImageCommunicationNullPointerException(){
        FooterComponentChanges.tryToInitEditImageCommunication(null);
    }

    @Test(expected = ClassCastException.class)
    public void testTryToInitEditImageCommunicationClassCastException(){
        FooterComponentChanges.tryToInitEditImageCommunication(mainActivity);
    }
}
