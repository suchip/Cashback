package com.example.cashback.ui.home


import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bookursalon.util.NetWorkConection
import com.example.cashback.*
import com.example.cashback.models.GetUserResponse

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger

class Profile_Screen : Fragment() {

    lateinit var homesharedPreferences: SharedPreferences
    private val STORAGE_REQUEST_CODE = 101
    private val CAMERA_REQUEST_CODE = 123
    lateinit var username: TextView
    lateinit var usergender: TextView
    lateinit var useraddress: TextView
    lateinit var phonenum: TextView
    lateinit var useremail: TextView
    lateinit var profilepicview: ImageView
    lateinit var editlayout: LinearLayout
    lateinit var profilelayout: LinearLayout
    lateinit var profileupdatebutton: Button
    lateinit var editusername: EditText
    lateinit var editphonenum: EditText
    lateinit var edituseremail: EditText
    lateinit var edituseraddress: EditText
    lateinit var updatephoneno: String
    private var postPath: String? = null
    private var camerapath: String? = null
    private var picturePath: String? = null
    private var fileUri: Uri? = null
    lateinit var imagename: MultipartBody.Part

    private var mImageFileLocation = ""

    lateinit var profileprogressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val profileview = inflater.inflate(
            R.layout.profile_screen,
            container, false
        )
//        usergender = profileview.findViewById(R.id.gender) as TextView


        val editimage = profileview.findViewById(R.id.editimage) as ImageView
        username = profileview.findViewById(R.id.username) as TextView
        useraddress = profileview.findViewById(R.id.useraddress) as TextView
        phonenum = profileview.findViewById(R.id.phonenum) as TextView
        useremail = profileview.findViewById(R.id.useremail) as TextView
        profilepicview = profileview.findViewById(R.id.profilepicview) as ImageView
        editlayout = profileview.findViewById(R.id.editlayout) as LinearLayout
        profilelayout = profileview.findViewById(R.id.profilelayout) as LinearLayout
        profileupdatebutton = profileview.findViewById(R.id.profileupdatebutton) as Button
        editusername = profileview.findViewById(R.id.editusername) as EditText
        editphonenum = profileview.findViewById(R.id.editphonenum) as EditText
        edituseremail = profileview.findViewById(R.id.edituseremail) as EditText
        edituseraddress = profileview.findViewById(R.id.edituseraddress) as EditText
        profileprogressBar = profileview.findViewById(R.id.profileprogressBar) as ProgressBar

        homesharedPreferences = requireActivity().getSharedPreferences("loginprefs", Context.MODE_PRIVATE)

        setupPermissions()

        editimage.setOnClickListener {

            editlayout.visibility = View.VISIBLE
            profilelayout.visibility = View.GONE
            profileupdatebutton.visibility = View.VISIBLE


            editusername.setText(homesharedPreferences.getString("updateusename", ""))
            edituseremail.setText(homesharedPreferences.getString("updateuseremail", ""))
            edituseraddress.setText(homesharedPreferences.getString("updateusercity", ""))
            editphonenum.setText(homesharedPreferences.getString("updateuserpfmobile", ""))


        }


        profileupdatebutton.setOnClickListener {

            userUpdate()

        }
        profilepicview.setOnClickListener {

            selectImage()

        }

        getusersProfile()

        return profileview
    }


    //allow camera and storage permissions
    private fun setupPermissions() {
        val permission = activity?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }

        val permisison2 =
            activity?.let { ContextCompat.checkSelfPermission(it, Manifest.permission.CAMERA) }
        if (permisison2 != PackageManager.PERMISSION_GRANTED) {
            makeRequest2()
        }

        val permisison3 =
            activity?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            }
        if (permisison3 != PackageManager.PERMISSION_GRANTED) {
            makeRequest3()
        }

    }

    private fun makeRequest() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                STORAGE_REQUEST_CODE
            )
        }
    }

    private fun makeRequest3() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                STORAGE_REQUEST_CODE
            )
        }
    }

    private fun makeRequest2() {
        activity?.let {
            ActivityCompat.requestPermissions(
                it,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            STORAGE_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(activity, "Permission Denied", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }


    private fun getusersProfile() {


        try {

            if (activity?.let { NetWorkConection.isNEtworkConnected(it) }!!) {


                //Set the Adapter to the RecyclerView//

                var apiServices = APIClient_Main.client.create(Api::class.java)

                Log.e("API : ", "" + apiServices)

                var token = homesharedPreferences.getString("token", "")

                Log.e("token : ", "" + token)

                val call = apiServices.get_profile("Token $token")
                Log.e("API Categories : ", "" + call)
//


                call.enqueue(object : Callback<GetUserResponse> {
                    override fun onResponse(call: Call<GetUserResponse>, response: Response<GetUserResponse>) {

                        if (response.isSuccessful) {

                            try {


                                Log.e("user response", response.toString())

                                Log.e("get user response: ", "" + response.body()!!.profile_pic)

                                val pfname: String? = response.body()!!.first_name
                                val pfemail: String? = response.body()!!.email
//                                val pfcity: String? = response.body()!!.ad
                                val pfmobile = response.body()!!.mobile_number
                                val pfimage: String? = response.body()!!.profile_pic

                                val editor = homesharedPreferences.edit()
//                                editor.putString("updateusercity", pfcity)
                                editor.putString("updateusename", pfname)
                                editor.putString("updateuseremail", pfemail)
//                                editor.putString("updateusergender", pfgender)
                                editor.putString("updateuserpfimage", pfimage)
                                editor.putString("updateuserpfmobile", pfmobile)

                                editor.commit()

                                Log.e("updateuserpfmobile", pfmobile)
                                username.text = pfname
                                useremail.text = pfemail
//                                useraddress.text = pfcity
                                phonenum.text = pfmobile

                                activity?.let {
                                    Glide.with(it).load(pfimage)
                                        .placeholder(R.drawable.ic_baseline_account_circle_24)
                                        .apply(RequestOptions().centerCrop())

                                        .into(profilepicview)
                                }

                            } catch (e: NullPointerException) {
                                e.printStackTrace()
                            }
                        } else {
                            try {
                                Toast.makeText(activity, "No Data", Toast.LENGTH_LONG).show()

                            } catch (e: NullPointerException) {
                                e.printStackTrace()
                            }


                        }
                    }

                    override fun onFailure(call: Call<GetUserResponse>, t: Throwable?) {
                        Log.e("response", t.toString())
                    }
                })


            } else {


                Toast.makeText(activity, "Please Check your internet", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(activity, "" + e.message, Toast.LENGTH_LONG).show()

        } catch (e1: JSONException) {
            e1.printStackTrace()
        }


    }

//    private fun userUpdateImage() {
//
//
//        profileprogressBar.visibility = View.VISIBLE
//
//        var token = homesharedPreferences.getString("token", "")
//
//        if (postPath == null || postPath == "") {
//            Toast.makeText(activity, "please select an image ", Toast.LENGTH_LONG).show()
//            return
//        } else {
//            val file = File(postPath!!)
//
//            val reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//
//            // passing the value of the requestbody while creating a data form for upload. and also takes the name of the picked image
//            imagename =
//                MultipartBody.Part.createFormData("profile_image", File(picturePath).name, reqFile)
//
//            // choose the image that will be uploaded with glide to imgUpload.
//            activity?.let {
//                Glide.with(it).load(picturePath).placeholder(R.drawable.ic_baseline_account_circle_24)
//                    .into(profilepicview)
//            }
//
////             Map is used to multipart the file using okhttp3.RequestBody
//            val map = HashMap<String, RequestBody>()
//
//            map.put("file\"; filename=\"" + file.name + "\"", reqFile)
//            Log.e("imagename", "" + postPath)
//
//
//            val call1 = RetrofitClient
//                .instance
//                .userUpdateProfileImage("token " + token, imagename)
//
//
//            call1.enqueue(object : retrofit2.Callback<DefaultResponse> {
//                override fun onResponse(
//                    call: Call<DefaultResponse>,
//                    response: Response<DefaultResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        profileprogressBar.visibility = View.GONE
//                        val dr = response.body()
//                        Toast.makeText(activity, "Profile Updated Sucessful", Toast.LENGTH_LONG)
//                            .show()
//
//
//                        var clickintent = Intent(activity, Home_Screen::class.java)
//                        startActivity(clickintent)
//                        activity!!.finish()
//                    }
//                }
//
//                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
//                    profileprogressBar.visibility = View.GONE
//
//                    Toast.makeText(activity, "Profile updated Failed", Toast.LENGTH_LONG).show()
//
//                }
//            })
//        }
//    }


//    private fun usercamerUpdateImage() {
//
//
//        profileprogressBar.visibility = View.VISIBLE
//
//        var token = homesharedPreferences.getString("token", "")
//
//        if (camerapath == null || camerapath == "") {
//            Toast.makeText(activity, "please select an image ", Toast.LENGTH_LONG).show()
//            return
//        } else {
//            val file = File(camerapath!!)
//
//            val reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)
//
//            // passing the value of the requestbody while creating a data form for upload. and also takes the name of the picked image
//            imagename =
//                MultipartBody.Part.createFormData("profile_image", File(camerapath).name, reqFile)
//
//            // choose the image that will be uploaded with glide to imgUpload.
//            activity?.let {
//                Glide.with(it).load(camerapath).placeholder(R.drawable.ic_user_green)
//                    .into(profilepicview)
//            }
//
////             Map is used to multipart the file using okhttp3.RequestBody
//            val map = HashMap<String, RequestBody>()
//
//
//            val call1 = RetrofitClient
//                .instance
//                .userUpdateProfileImage("token " + token, imagename)
//
//
//            call1.enqueue(object : retrofit2.Callback<DefaultResponse> {
//                override fun onResponse(
//                    call: Call<DefaultResponse>,
//                    response: Response<DefaultResponse>
//                ) {
//                    if (response.isSuccessful) {
//                        profileprogressBar.visibility = View.GONE
//                        val dr = response.body()
//                        Toast.makeText(activity, "Profile Updated Sucessful", Toast.LENGTH_LONG)
//                            .show()
//
////
//                        var clickintent = Intent(activity, Home_Screen::class.java)
//                        startActivity(clickintent)
//                        activity!!.finish()
//
//                    }
//                }
//
//                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
//                    profileprogressBar.visibility = View.GONE
//
//                    Toast.makeText(activity, "Profile updated Failed", Toast.LENGTH_LONG).show()
//
//                }
//            })
//        }
//    }

    private fun selectImage() {
        val options = arrayOf<CharSequence>("Take Photo", "Choose from Gallery", "Cancel")
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Add Photo!")
        builder.setItems(options) { dialog, item ->
            if (options[item] == "Take Photo") {


                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, 1)


            } else if (options[item] == "Choose from Gallery") {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 2)
            } else if (options[item] == "Cancel") {
                dialog.dismiss()
            }
        }
        builder.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1) {

                val extras = data?.extras

                val imageBitmap = extras?.get("data") as Bitmap
                val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmSS").format(Date())
                val imageFileName = timeStamp
                // Here we specify the environment location and the exact path where we want to save the so-created file
                val storageDirectory =
                    (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS))
                Logger.getAnonymousLogger().info("Storage directory set")


                val image = File.createTempFile(imageFileName, ".jpg", storageDirectory)

                // Here the location is saved into the string mImageFileLocation
                Logger.getAnonymousLogger().info("File name and path set")

                var out: FileOutputStream? = null
                try {
                    out = FileOutputStream(image)
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                    out.flush()
                    out.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                camerapath = image.absolutePath
                fileUri = Uri.parse(mImageFileLocation)
                Log.e("camerauri", "" + fileUri)

                profilepicview.setImageBitmap(imageBitmap)
                Log.e("imagename", "" + camerapath)

                postPath = camerapath

               // usercamerUpdateImage()

            } else if (requestCode == 2) {
                if (data != null) {
                    // Get the Image from data
                    val selectedImage = data.data
                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                    val cursor = activity?.contentResolver?.query(
                        selectedImage!!,
                        filePathColumn,
                        null,
                        null,
                        null
                    )
                    assert(cursor != null)
                    cursor!!.moveToFirst()

                    val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                    picturePath = cursor.getString(columnIndex)
                    // Set the Image in ImageView for Previewing the Media
                    profilepicview.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                    cursor.close()

                    Log.e("galleryPath", "" + picturePath)

                    postPath = picturePath

                   // userUpdateImage()
                }

            }
        }
    }

    private fun userUpdate() {

        var updateemail = edituseremail.text.toString().trim()
        var updatefname = editusername.text.toString().trim()
//        var updatelname = edittext_lastname.text.toString().trim()
        var updatecity = edituseraddress.text.toString().trim()
        var updatephoneno = editphonenum.text.toString().trim()


        profileprogressBar.visibility = View.VISIBLE

        var token = homesharedPreferences.getString("token", "")


        val call = RetrofitClient
            .instance
            .get_profile_update(
                "token " + token,
                updatefname,
                updateemail,
                updatephoneno
            )

        call.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                profileprogressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    val dr = response.body()
                    Toast.makeText(activity, "Profile Updated Sucessful", Toast.LENGTH_LONG).show()


                    var clickintent = Intent(activity, Home_Screen::class.java)
                    startActivity(clickintent)
                    activity!!.finish()

                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                profileprogressBar.visibility = View.GONE

                Toast.makeText(activity, "Profile updated Failed", Toast.LENGTH_LONG).show()

            }
        })


    }


    companion object {


        private val TAG = "Profile"
        // Activity request codes

        // key to store image path in savedInstance state
        val KEY_IMAGE_STORAGE_PATH = "image_path"

        val MEDIA_TYPE_IMAGE = 1

        // Bitmap sampling size
        val BITMAP_SAMPLE_SIZE = 8

        // Gallery directory name to store the images or videos
        val GALLERY_DIRECTORY_NAME = "PennyPay"

        // Image and Video file extensions
        val IMAGE_EXTENSION = "jpg"

        /**
         * returning image / video
         */
        private fun getOutputMediaFile(type: Int): File? {

            // External sdcard location
            val mediaStorageDir = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "PennyPay"
            )
            // Create the storage directory if it does not exist
            mediaStorageDir.apply {
                if (!exists()) {
                    if (!mkdirs()) {
                        Log.d("PennyPay", "failed to create directory")
                        return null
                    }
                }
            }
            // Create a media file name
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            return when (type) {
                MEDIA_TYPE_IMAGE -> {
                    File("${mediaStorageDir.path}${File.separator}IMG_$timeStamp.jpg")
                }

                else -> null
            }

            return mediaStorageDir
        }
    }


    @Throws(IOException::class)
    internal fun createImageFile(): File {

        Logger.getAnonymousLogger().info("Generating the image - method started")

        // Here we create a "non-collision file name", alternatively said, "an unique filename" using the "timeStamp" functionality
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmSS").format(Date())
        val imageFileName = "IMAGE_" + timeStamp
        // Here we specify the environment location and the exact path where we want to save the so-created file
        val storageDirectory =
            (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))
        Logger.getAnonymousLogger().info("Storage directory set")


//        try {
//            storageDirectory.createNewFile()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
        // Then we create the storage directory if does not exists
//        if (!storageDirectory.exists()) storageDirectory.mkdirs()

        // Here we create the file using a prefix, a suffix and a directory
//        val image = File(storageDirectory, imageFileName + ".jpg")
        val image = File.createTempFile(imageFileName, ".jpg", storageDirectory)

        // Here the location is saved into the string mImageFileLocation
        Logger.getAnonymousLogger().info("File name and path set")

        var out: FileOutputStream? = null
        try {
            out = FileOutputStream(image)
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        camerapath = "file://" + image.absolutePath
        fileUri = Uri.parse(mImageFileLocation)
        Log.e("camerauri", "" + fileUri)
        // The file is returned to the previous intent across the camera application
        return image
    }

}