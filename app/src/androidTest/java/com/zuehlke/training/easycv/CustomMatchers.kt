package com.zuehlke.training.easycv

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {

        override fun describeTo(description: Description?) {
        }

        override fun matchesSafely(item: View?): Boolean {
            if (!(item is TextInputLayout)) {
                return false
            }

            val error = item.error ?: return false

            return expectedErrorText == error
        }
    }
}
