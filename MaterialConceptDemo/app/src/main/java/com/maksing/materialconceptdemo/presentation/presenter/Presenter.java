/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.maksing.materialconceptdemo.presentation.presenter;

import com.maksing.materialconceptdemo.presentation.view.View;

/**
 * Interface representing a Presenter in a model view presenter (MVP) pattern.
 */
public interface Presenter {

    /**
    * Method that control the lifecycle of the view. It should be called in the view's
    * (Activity or Fragment) onResume() method.
    */
    abstract void resume();

    /**
    * Method that control the lifecycle of the view. It should be called in the view's
    * (Activity or Fragment) onPause() method.
    */
    abstract void pause();
}
