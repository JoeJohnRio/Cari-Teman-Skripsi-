package com.example.cariteman.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

public class PageViewModel: ViewModel() {
    /**
     * Live Data Instance
     */
    private var mName: MutableLiveData<String> = MutableLiveData()

    fun setName(name: String) {
        mName.setValue(name);
    }

    fun getName(): LiveData<String> {
        return mName;
    }
}
