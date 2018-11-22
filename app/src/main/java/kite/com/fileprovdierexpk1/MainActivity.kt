package kite.com.fileprovdierexpk1

import android.content.ClipData
import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.support.v4.content.FileProvider.getUriForFile
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public fun takePic(v: View) {

        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val imagePath = File(filesDir,"images")
        val newFile = File(imagePath,"default_image.jpg")
        val selectedPhotoPath : Uri = getUriForFile(this,"kite.com.fileprovider",newFile)

        captureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, selectedPhotoPath)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        } else {
            val clip = ClipData.newUri(contentResolver, "A photo", selectedPhotoPath)
            captureIntent.clipData = clip
            captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        }

        startActivityForResult(captureIntent, TAKE_PHOTO_REQUEST_CODE)
    }

    companion object {
        const  private val TAKE_PHOTO_REQUEST_CODE : Int = 0
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.i("MAIN","Request code  $requestCode resultCode $resultCode")
    }
}
