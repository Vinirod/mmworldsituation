package br.com.module.situationworld.views.activities

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.module.situationworld.R
import br.com.module.situationworld.utils.GlideViewUtils
import com.bumptech.glide.Glide

class LaunchScreenActivity : AppCompatActivity() {

    private lateinit var mImgBackground: ImageView
    private lateinit var mLnTop: LinearLayout
    private lateinit var mLnBottom: LinearLayout
    private lateinit var mUpToDown: Animation
    private lateinit var mDownToUp: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch_screen)

        mLnTop = findViewById(R.id.idLnTop)
        mLnBottom = findViewById(R.id.idLnBottom)
        mImgBackground = findViewById(R.id.idImgBackground)

        setImageOnImageView()
        setAnimation()

        Thread(Runnable {
            doWork()
            startApp()
            finish()
        }).start()
    }

    private fun doWork() {
        var progress = 0
        while (progress < 20) {
            try {
                Thread.sleep(2000)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            progress += 10
        }
    }

    private fun startApp() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun setAnimation() {
        mUpToDown = AnimationUtils.loadAnimation(this@LaunchScreenActivity, R.anim.uptodown)
        mDownToUp = AnimationUtils.loadAnimation(this@LaunchScreenActivity, R.anim.downtoup)
        mLnTop.animation = mUpToDown
        mLnBottom.animation = mDownToUp
    }

    private fun setImageOnImageView() {
        GlideViewUtils.initGlidView(this, R.drawable.imgbg, mImgBackground)
    }
}
