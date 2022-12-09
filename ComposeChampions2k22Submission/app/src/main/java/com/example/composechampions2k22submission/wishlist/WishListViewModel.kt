package com.example.composechampions2k22submission.wishlist

import androidx.lifecycle.ViewModel
import com.example.composechampions2k22submission.core.domain.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class WishListViewModel @Inject constructor(
    private val useCase: UseCase
): ViewModel(){
    val getAllFavouritedAnime = useCase.getAllFavouritedAnime()
}