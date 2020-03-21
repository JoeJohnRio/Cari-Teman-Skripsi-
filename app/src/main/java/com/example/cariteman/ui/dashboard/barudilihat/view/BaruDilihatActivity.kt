package com.example.cariteman.ui.dashboard.barudilihat.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.BounceInterpolator
import android.view.animation.ScaleAnimation
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import com.example.cariteman.R
import com.example.cariteman.databinding.ActivityListOrangBinding
import com.example.cariteman.ui.base.view.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector

class BaruDilihatActivity : BaseActivity(), BaruDilihatMVPView, HasActivityInjector {
    override fun onFragmentAttached() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onFragmentDetached(tag: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_orang)
        val viewBind: ActivityListOrangBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_list_orang
        )

        val scaleAnimation = ScaleAnimation(
            0.7f,
            1.0f,
            0.7f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.7f,
            Animation.RELATIVE_TO_SELF,
            0.7f
        )
        scaleAnimation?.setDuration(500)
        val bounceInterpolator = BounceInterpolator()
        scaleAnimation?.setInterpolator(bounceInterpolator)

        viewBind.iItemPeople.buttonFavorite.setOnCheckedChangeListener(object :
            View.OnClickListener, CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                p0?.startAnimation(scaleAnimation);
                Log.d(
                    "fav",
                    "am i here"
                ) //To change body of created functions use File | Settings | File Templates.
            }

            override fun onClick(p0: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        });

        viewBind.tvToolbarTitle.text = "Baru Dilihat"

        viewBind.ivBack.setOnClickListener {
            this.finish()
        }
    }

}