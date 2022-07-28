package com.lovepreet.twitterHomeUi.network.apis.feed.feedDataSource

import com.lovepreet.twitterHomeUi.models.FeedModel
import com.lovepreet.twitterHomeUi.models.FeedType
import com.lovepreet.twitterHomeUi.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

/**
 * Created by Lovepreet Singh on 28/07/22.
 */

class FeedDataSource {

    private var scope: CoroutineScope = CoroutineScope(context = Job())

    suspend fun getFeed(): ArrayList<FeedModel>{
        val nasa = scope.async { getNasaUser() }
        val becca = scope.async { getBeccaUser() }
        val unknown = scope.async { getUnknownUser() }

        delay(1500)

        return arrayListOf(
            FeedModel(
                id = "jdn5skj21j3",
                description = "The colors, Duke, the colors ! \n\nOur telescopes help us see a whole Universe.",
                time = "2022-08-22 05:00:00",
                type = FeedType.IMAGE,
                mediaUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2F4.bp.blogspot.com%2F-wEyiWm7pwa0%2FUTWRskO-NAI%2FAAAAAAAAIZo%2FcJQKw_60k34%2Fs1600%2Fandromeda-galaxy.jpg&f=1&nofb=1",
                totalComments = 10,
                totalRetweets = null,
                totalLikes = 5,
                user = nasa.await()
            ),
            FeedModel(
                id = "j3nf3k221j3",
                description = "lorem ipsum posum, lorem ipsum posum, lorem ipsum posum, lorem ipsum posum Reduce the possibility of KAPT logging warnings due to no processor supporting disableAndroidSuperclassValidation when no Android entry points are in the source module.",
                time = "2022-08-21 08:00:00",
                type = FeedType.NORMAL,
                totalComments = 1,
                totalRetweets = 5,
                totalLikes = 100,
                user = becca.await()
            ),
            FeedModel(
                id = "jd1f5kj21j3",
                description = "lorem ipsum posum, lorem ipsum posum, lorem ipsum posum, lorem ipsum posum",
                time = "2022-08-20 04:05:00",
                type = FeedType.VIDEO,
                mediaUrl = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
                videoThumbnail = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/BigBuckBunny.jpg",
                totalComments = 1,
                totalRetweets = null,
                totalLikes = null,
                user = unknown.await()
            ),
            FeedModel(
                id = "j3nf30221j3",
                description = "lorem ipsum posum, lorem ipsum posum, lorem ipsum posum, lorem ipsum posum Reduce the possibility of KAPT logging warnings due to no processor supporting disableAndroidSuperclassValidation when no Android entry points are in the source module.",
                time = "2022-08-21 08:00:00",
                type = FeedType.NORMAL,
                totalComments = 2,
                user = becca.await()
            ),
            FeedModel(
                id = "jdn5skj21j3",
                description = "The colors, Duke, the colors ! \n\nOur telescopes help us see a whole Universe.",
                time = "2022-08-22 05:00:00",
                type = FeedType.IMAGE,
                mediaUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2F4.bp.blogspot.com%2F-wEyiWm7pwa0%2FUTWRskO-NAI%2FAAAAAAAAIZo%2FcJQKw_60k34%2Fs1600%2Fandromeda-galaxy.jpg&f=1&nofb=1",
                totalComments = 10,
                totalRetweets = null,
                totalLikes = 5,
                user = nasa.await()
            ),
            FeedModel(
                id = "j3nf3k221j3",
                description = "lorem ipsum posum, lorem ipsum posum, lorem ipsum posum, lorem ipsum posum Reduce the possibility of KAPT logging warnings due to no processor supporting disableAndroidSuperclassValidation when no Android entry points are in the source module.",
                time = "2022-08-21 08:00:00",
                type = FeedType.NORMAL,
                totalComments = 1,
                totalRetweets = 5,
                totalLikes = 100,
                user = becca.await()
            ),
            FeedModel(
                id = "jd1f5kj21j3",
                description = "lorem ipsum posum, lorem ipsum posum, lorem ipsum posum, lorem ipsum posum",
                time = "2022-08-20 04:05:00",
                type = FeedType.IMAGE,
                mediaUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2F4.bp.blogspot.com%2F-wEyiWm7pwa0%2FUTWRskO-NAI%2FAAAAAAAAIZo%2FcJQKw_60k34%2Fs1600%2Fandromeda-galaxy.jpg&f=1&nofb=1",
                totalComments = 1,
                totalRetweets = null,
                totalLikes = null,
                user = unknown.await()
            ),
            FeedModel(
                id = "j3nf30221j3",
                description = "lorem ipsum posum, lorem ipsum posum, lorem ipsum posum, lorem ipsum posum Reduce the possibility of KAPT logging warnings due to no processor supporting disableAndroidSuperclassValidation when no Android entry points are in the source module.",
                time = "2022-08-21 08:00:00",
                type = FeedType.NORMAL,
                totalComments = 2,
                user = becca.await()
            ),
        )
    }

    private fun getNasaUser(): User{
        return User(
            id = "29",
            name = "Nasa",
            twitterUserName = "Nasa",
            profileUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e5/NASA_logo.svg/400px-NASA_logo.svg.png"
        )
    }

    private fun getBeccaUser(): User{
        return User(
            id = "54",
            name = "Becca",
            twitterUserName = "Clever24r",
            profileUrl = "https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Favatarfiles.alphacoders.com%2F206%2F206578.jpg&f=1&nofb=1"
        )
    }

    private fun getUnknownUser(): User{
        return User(
            id = "7",
            name = "Unknown",
            twitterUserName = "UnknownUser",
        )
    }
}