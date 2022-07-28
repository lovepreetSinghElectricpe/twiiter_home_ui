package com.lovepreet.twitterHomeUi.ui.home

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.lovepreet.twitterHomeUi.R
import com.lovepreet.twitterHomeUi.databinding.ActivityMainBinding
import com.lovepreet.twitterHomeUi.network.Resource
import com.lovepreet.twitterHomeUi.ui.customUi.hideLoader
import com.lovepreet.twitterHomeUi.ui.customUi.showLoader
import com.master.exoplayer.MasterExoPlayerHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        handleGui()
    }

    private fun handleGui(){
        setupUserObserver()
        val masterExoPlayerHelper = MasterExoPlayerHelper(mContext = this@MainActivity, id = R.id.exoPlayer, useController = true, defaultMute = false)
        masterExoPlayerHelper.makeLifeCycleAware(this)
        masterExoPlayerHelper.attachToRecyclerView(binding.recyclerView)

        viewModel.loadFeeds()
        setupFeedObserver()
    }

    private fun setupUserObserver(){
        viewModel.getUser().observe(this){
            viewModel.picasso.load(it.data?.profileUrl ?: "na")
                .placeholder(R.drawable.ic_profile_black)
                .error(R.drawable.ic_profile_black)
                .into(binding.userImage)
        }
    }

    private fun setupFeedObserver(){
        viewModel.feedDataObserver.observe(this){
            when(it.status){
                Resource.Status.LOADING -> showLoader("Loading feed...")
                Resource.Status.SUCCESS -> {
                    hideLoader()
                }
                Resource.Status.ERROR -> {
                    hideLoader()
                }
            }
        }
    }
}