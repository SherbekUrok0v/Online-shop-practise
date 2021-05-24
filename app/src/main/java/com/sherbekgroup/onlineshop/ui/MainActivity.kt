package com.sherbekgroup.onlineshop.ui
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.orhanobut.hawk.Hawk
import com.sherbekgroup.onlineshop.R
import com.sherbekgroup.onlineshop.api.Api
import com.sherbekgroup.onlineshop.databinding.ActivityMainBinding
import com.sherbekgroup.onlineshop.model.BaseResponse
import com.sherbekgroup.onlineshop.model.OfferModel
import com.sherbekgroup.onlineshop.ui.cart.CartFragment
import com.sherbekgroup.onlineshop.ui.favourite.FavouriteFragment
import com.sherbekgroup.onlineshop.ui.home.HomeFragment
import com.sherbekgroup.onlineshop.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val homeFragment = HomeFragment.newInstance()
    val cartFragment = CartFragment.newInstance()
    val profileFragment = ProfileFragment.newInstance()
    val favouriteFragment = FavouriteFragment.newInstance()
    var activeFragment: Fragment = homeFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Hawk.init(this).build()
        // ekranni tepaga ko`tarish uchun
//        window.setFlags(
//          WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        )
        // status bar text color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        //status bar color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = getColor(R.color.white)
        }
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, homeFragment, homeFragment.tag)
            .hide(homeFragment).commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, favouriteFragment, favouriteFragment.tag)
            .hide(favouriteFragment).commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, cartFragment, cartFragment.tag)
            .hide(cartFragment).commit()

        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, profileFragment, profileFragment.tag)
            .hide(profileFragment).commit()
        supportFragmentManager.beginTransaction().show(activeFragment).commit()

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_home -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(homeFragment).commit()
                    activeFragment = homeFragment
                }
                R.id.bottom_cart -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(cartFragment).commit()
                    activeFragment = cartFragment
                }
                R.id.bottom_favourite -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(favouriteFragment).commit()
                    activeFragment = favouriteFragment
                }
                R.id.bottom_person -> {
                    supportFragmentManager.beginTransaction().hide(activeFragment).show(profileFragment).commit()
                    activeFragment = profileFragment
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }
}