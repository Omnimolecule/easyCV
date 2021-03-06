package com.zuehlke.training.easycv.ui.skills

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.zuehlke.training.easycv.R
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
class SkillsFragmentTest {

    @Test
    fun testText() {
        launchFragmentInContainer<SkillsFragment>()

        Espresso.onView(ViewMatchers.withId(R.id.text_notifications))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString("skills"))))
    }
}