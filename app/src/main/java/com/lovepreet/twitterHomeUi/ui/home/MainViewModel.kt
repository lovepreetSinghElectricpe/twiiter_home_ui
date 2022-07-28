package com.lovepreet.twitterHomeUi.ui.home

import androidx.lifecycle.*
import com.lovepreet.twitterHomeUi.models.FeedModel
import com.lovepreet.twitterHomeUi.network.Resource
import com.lovepreet.twitterHomeUi.network.apis.feed.feedDataSource.FeedDataSource
import com.lovepreet.twitterHomeUi.network.apis.user.userDataSource.UserDataSource
import com.lovepreet.twitterHomeUi.ui.home.adapter.FeedAdapter
import com.squareup.picasso.Picasso
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Lovepreet Singh on 28/07/22.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    var picasso: Picasso,
    private var userDataSource: UserDataSource,
    private var feedDataSource: FeedDataSource
): ViewModel() {

    var feedDataObserver: MutableLiveData<Resource<ArrayList<FeedModel>>> = MutableLiveData()

    val adapter: FeedAdapter = FeedAdapter(picasso)

    fun getUser() = liveData {
        emit(Resource.loading())
        emit(Resource.success(userDataSource.getUser()))
    }

    fun loadFeeds(){
        viewModelScope.launch(Dispatchers.Main) {
            feedDataObserver.value = Resource.loading()
            val response = withContext(Dispatchers.IO){
                feedDataSource.getFeed()
            }
            adapter.addAll(response)
            feedDataObserver.value = Resource.success(response)
        }
    }
}