package com.example.composechampions2k22submission.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example.composechampions2k22submission.core.data.Repository
import com.example.composechampions2k22submission.core.data.Resource
import com.example.composechampions2k22submission.core.data.source.local.LocalDataSource
import com.example.composechampions2k22submission.core.data.source.remote.RemoteDataSource
import com.example.composechampions2k22submission.core.domain.model.Anime
import com.example.composechampions2k22submission.core.domain.repository.IRepository
import com.example.composechampions2k22submission.core.domain.usecase.Interactor
import com.example.composechampions2k22submission.core.domain.usecase.UseCase
import com.example.composechampions2k22submission.data.FakeRepository
import com.example.composechampions2k22submission.utils.DummyData
import com.example.composechampions2k22submission.utils.getOrAwaitValue
import com.example.composechampions2k22submission.utils.observeForTesting
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


//class TestCoroutineRule(
//    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
//) : TestWatcher() {
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    override fun starting(description: Description) {
//        super.starting(description)
//        Dispatchers.setMain(testDispatcher)
//    }
//
//    @OptIn(ExperimentalCoroutinesApi::class)
//    override fun finished(description: Description) {
//        super.finished(description)
//        Dispatchers.resetMain()
//        testDispatcher.cleanupTestCoroutines()
//    }
//}

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
internal class HomeViewModelTest {

//    @get:Rule
//    val testCoroutineRule = TestCoroutineRule()
//
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    // KALO PAKE FLOW/LIVEDATA

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    // UNTUK PAKE DISPATCHER UTAMA (BUKAN BACKGROUND)


    // MOCK METHOD
    @Mock
    private lateinit var useCase: UseCase
    private lateinit var homeViewModel: HomeViewModel

    // FAKE METHOD
    private lateinit var repository: IRepository // UNIT THAT WILL BE FAKED
    private lateinit var interactor: Interactor // UNIT THAT WILL BE INJECTED BY FAKE UNIT

    private val dummyAnime = DummyData.generateDummyAnimeData()

    @Before
    fun setUp() {
        // MOCK METHOD
        homeViewModel = HomeViewModel(useCase)

        // FAKE METHOD
        repository = FakeRepository()
        interactor = Interactor(repository)
    }

    // TODO THIS IS FAKE METHOD
    @Test
    fun `when get AnimeList Of Current Season Should Not Null and Return Success using Fake`()
    = runTest {
        val expectedAnime = Resource.Success(DummyData.generateDummyAnimeData())
        val actualAnime = interactor.getAnimeCurrentSeasonFromApi().asLiveData()
        actualAnime.getOrAwaitValue {
            Assert.assertNotNull(actualAnime)
            if (actualAnime.value is Resource.Success) {
                Assert.assertEquals(
                    expectedAnime.data?.size,
                    (actualAnime.value as Resource.Success).data?.size
                )
            }
        }
    }

    // TODO THIS IS MOCK METHOD
    @Test
    fun `when get AnimeList Of Current Season Should Not Null and Return Success`() {
        val expectedAnime = flow<Resource<List<Anime>>> {
            emit(Resource.Success(dummyAnime))
        }
        `when`(useCase.getAnimeCurrentSeasonFromApi()).thenReturn(expectedAnime)

        val actualAnime =
            homeViewModel.getAnimeCurrentSeasonFromApi().getOrAwaitValue()

        Mockito.verify(useCase).getAnimeCurrentSeasonFromApi()
        Assert.assertNotNull(actualAnime)
        Assert.assertTrue(actualAnime is Resource.Success)
        Assert.assertEquals(
            dummyAnime.size,
            (actualAnime as Resource.Success).data?.size
        )
    }

    @Test
    fun `when Network Error Should Return Error`() {
        val expectedAnime = flow<Resource<List<Anime>>> {
            emit(Resource.Error("Network Error"))
        }
        `when`(useCase.getAnimeCurrentSeasonFromApi()).thenReturn(expectedAnime)

        val actualAnime =
            homeViewModel.getAnimeCurrentSeasonFromApi().getOrAwaitValue()

        Mockito.verify(useCase).getAnimeCurrentSeasonFromApi()
        Assert.assertNotNull(actualAnime)
        Assert.assertTrue(actualAnime is Resource.Error)
    }

    @Test
    fun `when get AnimeList Of Current Season Should Emtpy and Return Empty List`() {
        val emptyAnimeList = listOf<Anime>()
        val expectedAnime = flow<Resource<List<Anime>>> {
            emit(Resource.Success(emptyAnimeList))
        }
        `when`(useCase.getAnimeCurrentSeasonFromApi()).thenReturn(expectedAnime)

        val actualAnime =
            homeViewModel.getAnimeCurrentSeasonFromApi().getOrAwaitValue()

        Mockito.verify(useCase).getAnimeCurrentSeasonFromApi()
        Assert.assertNotNull(actualAnime)
        Assert.assertTrue(actualAnime is Resource.Success)
        Assert.assertEquals(
            emptyAnimeList.size,
            (actualAnime as Resource.Success).data?.size
        )
        print(actualAnime.data)
    }
}