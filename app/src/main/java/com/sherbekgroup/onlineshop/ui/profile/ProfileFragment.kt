package com.sherbekgroup.onlineshop.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.adapter.MyResAccountAdapter
import com.sherbekgroup.onlineshop.databinding.FragmentProfileBinding
import com.sherbekgroup.onlineshop.model.AccountModel


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var adapter : MyResAccountAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        adapter = MyResAccountAdapter(initList())
        binding.rv.adapter = adapter
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
    private fun initList(): List<AccountModel> {
        return  listOf(
            AccountModel(R.drawable.ic_user, "O`roqov Sherbek"),
            AccountModel(R.drawable.ic_phone, "+998 93 359 34 35"),
            AccountModel(R.drawable.ic_notification, "Notification / History"),
            AccountModel(R.drawable.ic_info, "Help"),
            AccountModel(R.drawable.ic_setting, "Settings"),
            AccountModel(R.drawable.ic_logout, "Logout")
        )
    }
}