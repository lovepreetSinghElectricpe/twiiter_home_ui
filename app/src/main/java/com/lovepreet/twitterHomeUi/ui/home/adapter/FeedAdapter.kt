package com.lovepreet.twitterHomeUi.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lovepreet.twitterHomeUi.R
import com.lovepreet.twitterHomeUi.databinding.AdapterFeedBinding
import com.lovepreet.twitterHomeUi.models.FeedModel
import com.lovepreet.twitterHomeUi.models.FeedType
import com.master.exoplayer.ExoPlayerHelper
import com.squareup.picasso.Picasso

/**
 * Created by Lovepreet Singh on 28/07/22.
 */
class FeedAdapter(private val picasso: Picasso): RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    private val feeds: ArrayList<FeedModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: AdapterFeedBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.adapter_feed, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        picasso.load(feeds[position].user?.profileUrl ?: "na")
            .placeholder(R.drawable.ic_profile_black)
            .error(R.drawable.ic_profile_black)
            .into(holder.binding.userImage)
        holder.binding.userName.text = feeds[position].user?.name ?: "Twitter User"
        holder.binding.twitterName.text = "@${feeds[position].user?.twitterUserName} ${feeds[position].getTimeAgo()}"
        holder.binding.description.text = feeds[position].description ?: ""
        when(feeds[position].type){
            FeedType.IMAGE -> handleImage(holder, position)
            FeedType.VIDEO -> handleVideo(holder, position)
            else -> holder.binding.mediaParent.visibility = View.GONE
        }
        holder.binding.comments.text = feeds[position].getComments()
        holder.binding.retweet.text = feeds[position].getRetweets()
        holder.binding.likes.text = feeds[position].getLikes()
    }

    private fun handleImage(holder: ViewHolder, position: Int){
        holder.binding.playerContainer.visibility = View.GONE
        holder.binding.feedImage.visibility = View.VISIBLE
        holder.binding.mediaParent.visibility = View.VISIBLE
        picasso.load(feeds[position].mediaUrl ?: "na")
            .placeholder(R.drawable.no_image_available_dark)
            .error(R.drawable.no_image_available_dark)
            .into(holder.binding.feedImage)
    }

    private fun handleVideo(holder: ViewHolder, position: Int){
        holder.binding.playerContainer.visibility = View.VISIBLE
        holder.binding.feedImage.visibility = View.GONE
        holder.binding.mediaParent.visibility = View.VISIBLE
        holder.binding.exoPlayer.url = feeds[position].mediaUrl ?: "na"
        holder.binding.exoPlayer.imageView = holder.binding.image
        picasso.load(feeds[position].videoThumbnail ?: "na")
            .placeholder(R.drawable.no_image_available_dark)
            .error(R.drawable.no_image_available_dark)
            .into(holder.binding.image)
        holder.binding.exoPlayer
        holder.binding.exoPlayer.listener = object: ExoPlayerHelper.Listener {

            override fun onStart() {
                super.onStart()
                holder.binding.videoSign.visibility = View.GONE
            }

            override fun onStop() {
                super.onStop()
                holder.binding.videoSign.visibility = View.VISIBLE
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return feeds[position].type?.value ?: 0
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(feeds: ArrayList<FeedModel>){
        this.feeds.clear()
        this.feeds.addAll(feeds)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear(){
        feeds.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: AdapterFeedBinding): RecyclerView.ViewHolder(binding.root)
}