package com.lovepreet.twitterHomeUi.models

import com.lovepreet.twitterHomeUi.extensions.getTimeAgo
import com.lovepreet.twitterHomeUi.extensions.toDate

/**
 * Created by Lovepreet Singh on 28/07/22.
 */

enum class FeedType(val value: Int){
    NORMAL(0),
    IMAGE(1),
    VIDEO(2)
}

data class FeedModel(
    var id: String? = null,
    var description: String? = null,
    var time: String? = null,
    var type: FeedType? = null,
    var totalComments: Int? = null,
    var totalRetweets: Int? = null,
    var totalLikes: Int? = null,
    var mediaUrl: String? = null,
    var videoThumbnail: String? = null,
    var user: User? = null
){
    fun getTimeAgo(): String{
        val t = time?.toDate()?.getTimeAgo()
        return if (t == null){
            ""
        } else{
            " · $t"
        }
    }

    fun getComments(): String{
        val comments = totalComments ?: 0
        return if ((comments) == 0) "" else (if(comments > 9) "9+" else comments.toString())
    }

    fun getRetweets(): String{
        val retweets = totalRetweets ?: 0
        return if ((retweets) == 0) "" else (if(retweets > 9) "9+" else retweets.toString())
    }

    fun getLikes(): String{
        val likes = totalLikes ?: 0
        return if ((likes) == 0) "" else (if(likes > 9) "9+" else likes.toString())
    }
}
