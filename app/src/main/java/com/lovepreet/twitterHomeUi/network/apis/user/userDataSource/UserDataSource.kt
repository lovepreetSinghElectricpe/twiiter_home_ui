package com.lovepreet.twitterHomeUi.network.apis.user.userDataSource

import com.lovepreet.twitterHomeUi.models.User

/**
 * Created by Lovepreet Singh on 28/07/22.
 */
class UserDataSource {

    fun getUser(): User{
        return User(
            id = "29",
            name = "Nasa",
            twitterUserName = "Nasa",
            profileUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/NASA_logo.svg/400px-NASA_logo.svg.png"
        )
    }
}