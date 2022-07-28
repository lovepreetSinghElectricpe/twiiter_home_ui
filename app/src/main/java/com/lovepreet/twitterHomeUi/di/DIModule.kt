package com.lovepreet.twitterHomeUi.di

import android.content.Context
import com.lovepreet.twitterHomeUi.network.apis.feed.feedDataSource.FeedDataSource
import com.lovepreet.twitterHomeUi.network.apis.user.userDataSource.UserDataSource
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Lovepreet Singh on 28/07/22.
 */

@Module
@InstallIn(SingletonComponent::class)
object DIModule {

    @Singleton
    @Provides
    fun providePicasso(@ApplicationContext context: Context): Picasso {
        return Picasso.Builder(context)
            .indicatorsEnabled(true)
            .loggingEnabled(true)
            .listener { _, _, exception -> exception.printStackTrace() }
            .build()
    }

    @Singleton
    @Provides
    fun provideFeedDataSource(): FeedDataSource{
        return FeedDataSource()
    }

    @Singleton
    @Provides
    fun provideUserDataSource(): UserDataSource{
        return UserDataSource()
    }
}