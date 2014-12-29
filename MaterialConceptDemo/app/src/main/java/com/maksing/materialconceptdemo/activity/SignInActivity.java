package com.maksing.materialconceptdemo.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.activity.BaseActivity;
import com.maksing.materialconceptdemo.presentation.presenter.Presenter;
import com.maksing.materialconceptdemo.presentation.presenter.SignInPresenter;
import com.maksing.materialconceptdemo.presentation.view.SignInView;

/**
 * Created by maksing on 25/12/14.
 */
public class SignInActivity extends BaseActivity implements SignInView{

    private SignInPresenter mSignInPresenter;
    private EditText mUsername;
    private EditText mPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected Presenter onCreatePresenter(Presenter presenter) {
        if (presenter == null) {
            presenter = new SignInPresenter();
        }
        mSignInPresenter = (SignInPresenter)presenter;
        return presenter;
    }
}
