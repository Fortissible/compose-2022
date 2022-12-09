package com.example.composechampions2k22submission.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.example.composechampions2k22submission.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    useCase: UseCase
): ViewModel(){

    val getAnimeDetailFromApi : (id:Int) -> LiveData<Resource<Anime>> = { id ->
        useCase.getAnimeDetailFromApi(id).asLiveData()
    }
    val insertFavourite : (anime:Anime) -> Unit = {
        viewModelScope.launch {
            useCase.insertFavouriteAnime(it)
        }
    }

    val deleteFavourite : (anime:Anime) -> Unit = {
        viewModelScope.launch {
            useCase.deleteFavouriteAnime(it)
        }
    }

}