package com.kaizen.hoymm.ufoinphoto.EditImageActivity;

/**
 * Created by Damian Muca (Kaizen) on 21.12.17.
 */

public interface SelectImage {
    void selectLastUFOObject();
    void selectUFOObjectByIndex(int index);
    void selectUFOObject(UFOImageView ufoImageView);
    void selectCurrent();
    void deselectCurrent();
}
