package dev.suhrob.kattabozor.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.suhrob.kattabozor.R
import dev.suhrob.kattabozor.core.extensions.animateToolBarTittle
import dev.suhrob.kattabozor.core.extensions.gone
import dev.suhrob.kattabozor.core.extensions.visible
import dev.suhrob.kattabozor.core.utils.SharedPreference
import dev.suhrob.kattabozor.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    @Inject
    lateinit var sharedPreference: SharedPreference

    private val isToolBarGone = mutableListOf(
        R.id.firstFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentMain) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.name = navController.currentDestination?.label.toString()
            Log.d("KKKKKDDD", "onCreate: ${navController.currentDestination?.label.toString()}")
            animateToolBarTittle(binding.toolbar.title)
            if (isToolBarGone.contains(destination.id)) {
                binding.toolbar.toolbar.visible()
            } else {
                binding.toolbar.toolbar.gone()
            }
        }
        binding.toolbar.back.setOnClickListener {
            navController.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun hideProgress() {
        binding.progress.gone()
    }

    fun showProgress() {
        binding.progress.visible()
    }

    fun setMainToolbarText(text: String) {
        binding.toolbar.title.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreference.type = 0
    }
}