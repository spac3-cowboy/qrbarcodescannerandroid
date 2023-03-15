package com.innova.qrcodescanner.plus.feature.tabs.settings.about
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.innova.qrcodescanner.plus.BuildConfig
import com.innova.qrcodescanner.plus.R
import com.innova.qrcodescanner.plus.databinding.ActivityAboutBinding
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val content = getString(R.string.app_version, BuildConfig.VERSION_NAME)
        binding.itemSettingsMoreAboutVersion.text = content
        binding.itemSettingsMoreAboutIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://innovainfosys.com"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://innovainfosys.com"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutGoogleDev.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://innovainfosys.com"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCPjg8YLRlF-LxPe13Mxw8Vg"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/innovainfosys"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutTwitter.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/innovainfosys"))
            startActivity(intent)
        }

        binding.itemSettingsMoreAboutLibraries.setOnClickListener {
            val intent = Intent(this, OssLicensesMenuActivity::class.java)
            startActivity(intent)
        }
    }
}