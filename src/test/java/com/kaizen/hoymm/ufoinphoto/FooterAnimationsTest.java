package com.kaizen.hoymm.ufoinphoto;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImage;
import com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterAnimations;

import org.junit.Test;

import static com.kaizen.hoymm.ufoinphoto.EditImageActivity.FooterAnimations.startHideRotateFooterThenShowManagementFooter;

/**
 * Created by hoymm on 21.11.17.
 */

public class FooterAnimationsTest {

    @Test(expected = NullPointerException.class)
    public void testTryToInitEditImageCommunicationNullPointerException(){
        FooterAnimations.tryToInitEditImageCommunication(null);
    }

    @Test(expected = ClassCastException.class)
    public void testTryToInitEditImageCommunicationClassCastException(){
        FooterAnimations.tryToInitEditImageCommunication(new MainActivity());
    }

    @Test(expected = NullPointerException.class)
    public void startHideRotateFooterThenShowManagementFooterTest(){
        startHideRotateFooterThenShowManagementFooter(null, new EditImage());
    }


}
