package com.oropeza.ec02_asot

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.oropeza.ec02_asot.databinding.FragmentCameraBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private lateinit var openCameraLauncher : ActivityResultLauncher<Intent>

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddPhoto.setOnClickListener {
            if (permissionValidated()) {
                openCamera()
            }
        }

        openCameraLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val photo = result.data?.extras?.get("data") as Bitmap
                binding.imgPhoto.setImageBitmap(photo)
            }
        }
    }

    private fun permissionValidated(): Boolean {
        val cameraPermission = android.Manifest.permission.CAMERA
        val storagePermission = android.Manifest.permission.WRITE_EXTERNAL_STORAGE

        val cameraPermissionGranted = ContextCompat.checkSelfPermission(requireContext(), cameraPermission) == PackageManager.PERMISSION_GRANTED
        val storagePermissionGranted = ContextCompat.checkSelfPermission(requireContext(), storagePermission) == PackageManager.PERMISSION_GRANTED

        if (!cameraPermissionGranted || !storagePermissionGranted) {
            requestPermissions(arrayOf(cameraPermission, storagePermission), 0)
            return false
        }
        return true
    }

    private fun openCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        openCameraLauncher.launch(intent)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CamereFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CameraFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}