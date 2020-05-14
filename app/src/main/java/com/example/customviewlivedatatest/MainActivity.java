package com.example.customviewlivedatatest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.os.Handler;

import com.example.customviewlivedatatest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mBinding;

    private MediatorLiveData<String> testText;
    private MutableLiveData<String> changeText;

    public LiveData<String> getText() {
        return testText;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        testText = new MediatorLiveData<String>();
        testText.setValue("one");

        changeText = new MutableLiveData<String>();
        testText.addSource(changeText, value -> testText.setValue(value));

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setLifecycleOwner(this);
        mBinding.setActivity(this);

        new Handler().postDelayed((Runnable) () -> {
            testText.postValue("two");
        }, 2000);

        new Handler().postDelayed((Runnable) () -> {
            changeText.postValue("three");
        }, 4000);

        //setContentView(R.layout.activity_main);
    }
}
