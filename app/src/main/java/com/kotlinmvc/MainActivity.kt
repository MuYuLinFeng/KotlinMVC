package com.kotlinmvc

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), DoLoginCallBack {
    private val userModel by lazy {
        UserModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initListener()
    }

    private fun initListener() {
        btnLogin.setOnClickListener {
            toLogin()
        }

    }

    private fun toLogin() {
        val account = tvAccountInput.text.toString()
        val password = tvPasswordInput.text.toString()
        if (TextUtils.isEmpty(account)) {
            return
        }
        if (TextUtils.isEmpty(password)) {
            return
        }
        userModel.checkUserState(account) {
            if (0 == it) {
                tvTips.text = "未注册"
            } else {
                userModel.doLogin(callBack = this, account, password)
                btnLogin.isEnabled = false
            }
        }
    }

    override fun startLogin() {
        tvTips.text = "登录中..."
    }

    override fun loginSuccess() {
        tvTips.text = "登录成功"
    }

    override fun loginFailed() {
        tvTips.text = "登录失败"
    }
}