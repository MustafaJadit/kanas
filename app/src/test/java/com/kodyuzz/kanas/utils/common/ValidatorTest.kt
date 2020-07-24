package com.kodyuzz.kanas.utils.common

import com.kodyuzz.kanas.R
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.contains
import org.hamcrest.Matchers.hasSize
import org.junit.Test

class ValidatorTest {

    @Test
    fun givenValidEmailAndPwd_whenValidate_ShouldReturnSuccess() {
        val email = "mustafajadit@gmail.com"
        val password = "12345678"
        val validations = Validator.validateLoginField(email, password)
        assertThat(validations, hasSize(2))
        assertThat(
            validations,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }


    @Test
    fun givenInvalidEmailAndValidPwd_WhenValidate_shouldReturnEmailError() {
        val email = "mustafajadit.com"
        val password = "12345678"
        val validation = Validator.validateLoginField(email, password)
        assertThat(validation, hasSize(2))
        assertThat(
            validation,
            contains(
                Validation(Validation.Field.EMAIL, Resource.error(R.string.email_field_invalid)),
                Validation(Validation.Field.PASSWORD, Resource.success())
            )
        )
    }

    @Test
    fun givenValidEmailAndInvalidPwd_WhenValidate_shouldReturnPasswordError() {
        val email = "mustafajadit@gmail.com"
        val password = "pdf"
        val validation = Validator.validateLoginField(email, password)
        assertThat(validation, hasSize(2))
        assertThat(
            validation,
            contains(
                Validation(Validation.Field.EMAIL, Resource.success()),
                Validation(Validation.Field.PASSWORD, Resource.error(R.string.password_field_small_length))
            )
        )
    }
}