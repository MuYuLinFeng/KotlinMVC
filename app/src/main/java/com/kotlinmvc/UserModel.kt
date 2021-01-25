package com.kotlinmvc

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

open class UserModel {
    private val api by lazy { API() }
    private val random = Random()
    fun doLogin(callBack: DoLoginCallBack, account: String, password: String) {
        MainScope().launch {
            callBack.startLogin()
            api.login()
            val randomValue: Int = random.nextInt(3)
            delay(randomValue.toLong())
            if (randomValue == 0) {
                callBack.loginSuccess()
            } else {
                callBack.loginFailed()
            }
        }
    }

    fun checkUserState(account: String, block: (Int) -> Unit) {
        block.invoke(random.nextInt(2))
    }
}

interface DoLoginCallBack {
    fun startLogin()
    fun loginSuccess()
    fun loginFailed()
}