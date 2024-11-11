package com.jihaddmz.tasksync.feature_auth.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.jihaddmz.tasksync.feature_auth.model.DtoUser
import com.jihaddmz.tasksync.feature_auth.model.ReqUser
import com.jihaddmz.tasksync.feature_auth.usecase.UseCaseSignin
import com.jihaddmz.tasksync.feature_global.helper.BaseViewModel
import com.jihaddmz.tasksync.feature_auth.usecase.UseCaseSignup
import com.jihaddmz.tasksync.feature_global.helper.HelperSharedPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelAuth @Inject constructor(
    private val useCaseSignup: UseCaseSignup,
    private val useCaseSignin: UseCaseSignin
): BaseViewModel() {

    val stateUsername = mutableStateOf("")
    val statePassword = mutableStateOf("")
    val stateRepPassword = mutableStateOf("")

    val stateSignUp: MutableState<DtoUser?> = mutableStateOf(null)
    val stateSignIn: MutableState<DtoUser?> = mutableStateOf(null)

    fun saveDetailsInSharedPref() {
        HelperSharedPreference.save {
            putString(HelperSharedPreference.KEY_USERNAME, stateUsername.value)
            putString(HelperSharedPreference.KEY_PASSWORD, statePassword.value)
        }
    }

    fun signIn() {
        executeUseCase(useCaseSignin, ReqUser(statePassword.value, stateUsername.value), stateSignIn)

    }

    fun signUp() {
        executeUseCase(useCaseSignup, ReqUser(statePassword.value, stateUsername.value), stateSignUp)
    }

    fun isButtonEnabled(): Boolean {
       return statePassword.value.isNotBlank() && stateUsername.value.isNotBlank() && statePassword.value == stateRepPassword.value
    }

    fun isSignInButtonEnabled(): Boolean {
        return statePassword.value.isNotBlank() && stateUsername.value.isNotBlank()
    }
}