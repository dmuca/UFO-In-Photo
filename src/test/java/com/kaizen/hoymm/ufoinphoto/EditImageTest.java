package com.kaizen.hoymm.ufoinphoto;

import com.kaizen.hoymm.ufoinphoto.EditImageActivity.EditImage;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hoymm on 14.11.17.
 */

public class EditImageTest {
    private static EditImage editImage;
    @BeforeClass
    public static void beforeClass(){
        editImage = new EditImage();
    }

    @Before
    public void initObject(){

    }

    @Test (expected = NullPointerException.class)
    public void testTryLoadImage(){
        editImage.setImageUsingUri(null);
    }
}
