package com.ihaydin.nasaroverproject


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.ihaydin.nasaroverproject.databinding.PopupPhotoBinding
import com.ihaydin.nasaroverproject.entity.DataResponse


class PhotoDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<PopupPhotoBinding>(inflater, R.layout.popup_photo, container, false)
        val view: View = binding.getRoot()

        getDialog()!!.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));


        val photo: DataResponse.Photo? = requireArguments().getParcelable("detail")
        binding.photo = photo

        return view
    }
}