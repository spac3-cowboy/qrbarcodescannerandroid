package com.innova.qrcodescanner.plus.feature.tabs.settings.feedback
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.innova.qrcodescanner.plus.R
import com.innova.qrcodescanner.plus.databinding.ActivityFeedbackBinding
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
class FeedbackActivity : AppCompatActivity() {
    private lateinit var reviewManager: ReviewManager
    private lateinit var binding: ActivityFeedbackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_feedback, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.dev_mail -> {
            val email = Intent(Intent.ACTION_SEND)
            email.type = "text/email"
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("d4rk7355608@gmail.com"))
            email.putExtra(Intent.EXTRA_SUBJECT, "Feedback for Android Studio Tutorials")
            email.putExtra(Intent.EXTRA_TEXT, "Dear developer, ")
            startActivity(Intent.createChooser(email, "Send mail to Developer:"))
            true
        }  else -> {
            super.onOptionsItemSelected(item)
        }
    }
    private fun init() {
        reviewManager = ReviewManagerFactory.create(this)
        binding.buttonRateNow.setOnClickListener {
            showRateDialog()
            Toast.makeText(this@FeedbackActivity, R.string.feedback_toast, Toast.LENGTH_SHORT).show()
        }
    }
    @Suppress("NAME_SHADOWING")
    private fun showRateDialog() {
        val request = reviewManager.requestReviewFlow()
        request.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                val reviewInfo = request.result
                val flow = reviewManager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener {
                    // The flow has finished. The API does not indicate whether the user
                    // reviewed or not, or even whether the review dialog was shown. Thus, no
                    // matter the result, we continue our app flow.
                }
            } else {
                // There was some problem, continue regardless of the result.
                // you can show your own rate dialog alert and redirect user to your app page
                // on play store.
            }
        }
    }
}