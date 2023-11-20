package com.example.composechampions2k22submission.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.example.composechampions2k22submission.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCase: UseCase,
):ViewModel(){

    val getAnimeCurrentSeasonFromApi : () -> LiveData<Resource<List<Anime>>> = {
        useCase.getAnimeCurrentSeasonFromApi().asLiveData()
    }
}