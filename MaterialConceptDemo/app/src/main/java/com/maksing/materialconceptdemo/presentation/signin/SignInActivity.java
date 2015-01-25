package com.maksing.materialconceptdemo.presentation.signin;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.maksing.materialconceptdemo.R;
import com.maksing.materialconceptdemo.presentation.PresenterActivity;

/**
 * Created by maksing on 25/12/14.
 */
public class SignInActivity extends PresenterActivity<SignInPresenter> implements SignInView{

    private EditText mUsername;
    private EditText mPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected SignInPresenter onCreatePresenter() {
        return new SignInPresenter();
    }
}
