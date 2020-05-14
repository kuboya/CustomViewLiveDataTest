package com.example.custumviewlivedatatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.os.Bundle;
import android.os.Handler;

import com.example.custumviewlivedatatest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    private MutableLiveData<String> testText;

    public LiveData<String> getText() {
        return testText;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testText = new MutableLiveData<String>();
        testText.setValue("hello");

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        mBinding.setActivity(this);

        new Handler().postDelayed((Runnable) () -> {
            testText.postValue("Test");
        }, 5000);

        //setContentView(R.layout.activity_main);
    }
}
