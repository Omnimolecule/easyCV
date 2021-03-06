package com.zuehlke.training.easycv.ui.editProfile

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.zuehlke.training.easycv.data.local.LocalRepository
import com.zuehlke.training.easycv.data.local.Profile
import com.zuehlke.training.easycv.getOrAwaitValue
import com.zuehlke.training.easycv.ui.editprofile.EditProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class EditProfileViewModelTest {

    private lateinit var profileViewModel: EditProfileViewModel

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        val repositoryMock = mock<LocalRepository>()
        runBlockingTest {
            `when`(repositoryMock.getProfilePlain()).thenReturn(
                Profile(
                    42,
                    "name",
                    "lastname",
                    42L,
                    "street",
                    "zip",
                    "location",
                    "country",
                    "phone",
                    "email",
                    "blabla",
                    null
                )
            )
        }

        profileViewModel = EditProfileViewModel(repositoryMock)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadProfile_withData() {
        profileViewModel.loadProfile()
        val loaded = profileViewModel.profileLoaded.getOrAwaitValue()
        assertThat(loaded, `is`(true))
        assertThat(profileViewModel.id, `is`(42))
        assertThat(profileViewModel.name, `is`("name"))
        assertThat(profileViewModel.lastname, `is`("lastname"))
        assertThat(profileViewModel.birthdate, `is`(42L))
        assertThat(profileViewModel.email, `is`("email"))
        assertThat(profileViewModel.phone, `is`("phone"))
        assertThat(profileViewModel.description, `is`("blabla"))
        assertThat(profileViewModel.street, `is`("street"))
        assertThat(profileViewModel.zip, `is`("zip"))
        assertThat(profileViewModel.city, `is`("location"))
        assertThat(profileViewModel.country, `is`("country"))
    }

    @Test
    fun saveProfile() {
        profileViewModel.apply {
            name = "name"
            lastname = "lastname"
            birthdate = 42L
            street = "street"
            zip = "zip"
            city = "location"
            country = "country"
            phone = "phone"
            email = "email"
            description = "description"
        }
        val result = profileViewModel.saveProfile().getOrAwaitValue()
        assertThat(result, `is`(true))
    }
}