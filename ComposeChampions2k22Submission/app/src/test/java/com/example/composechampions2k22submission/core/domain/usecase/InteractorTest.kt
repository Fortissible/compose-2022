package com.example.composechampions2k22submission.core.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.composechampions2k22submission.core.data.Repository
import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.example.composechampions2k22submission.home.HomeViewModel
import com.example.composechampions2k22submission.utils.DummyData
import com.example.composechampions2k22submission.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class InteractorTest {

    //    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()
//
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    // KALO PAKE FLOW/LIVEDATA

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    // UNTUK PAKE DISPATCHER UTAMA (BUKAN BACKGROUND)

    @Mock
    private lateinit var repository: Repository
    private lateinit var interactor: Interactor

    private val dummyAnime = DummyData.generateDummyAnimeData()

    @Before
    fun setUp() {
        interactor = Interactor(repository)
    }

    @Test
    fun `when get AnimeList Of Current Season Should Not Null and Return Success`() {
        runBlocking {
                val expectedAnime = flow<Resource<List<Anime>>> {
                    emit(Resource.Success(dummyAnime))
                } // VALUE INI (EXPECTED == DUMMY)

                Mockito.`when`(interactor.getAnimeCurrentSeasonFromApi()).thenReturn(expectedAnime)
                // STUBBING

                val actualAnime =
                    interactor.getAnimeCurrentSeasonFromApi() // MIRIP KAYA .first() nya flow di aplikasi
                // VALUE ACTUAL (ACTUAL == DATA DUMMY TAPI CARA GETNYA DARI UNIT YG DI TEST)

//                Mockito.verify(interactor).getAnimeCurrentSeasonFromApi() // CEK APAKAH FUNGSI getAnimeCurrentSeasonFromApi() di UseCase Terpanggil atau tidak.
                Assert.assertNotNull(actualAnime)
                Assert.assertTrue(actualAnime.first() is Resource.Success) // Resource.Error, Resource.Loading, Resource.Success
                Assert.assertEquals(dummyAnime.size, (actualAnime.first() as Resource.Success).data?.size)
        }
    }
}