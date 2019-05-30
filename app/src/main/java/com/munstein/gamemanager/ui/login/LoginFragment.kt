package com.munstein.gamemanager.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.munstein.gamemanager.R
import com.munstein.gamemanager.base.BaseFragment
import com.munstein.gamemanager.extensions.launchActivity
import com.munstein.gamemanager.ui.home.HomeActivity
import com.munstein.gamemanager.viewmodels.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment() {

    val GOOGLE_SIGN_IN_REQUEST_CODE = 18

    val loginViewModel: LoginViewModel by viewModel()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (loginViewModel.getSignedInAccount() != null) {
            navigateToHome()
        } else {
            init()
        }
    }

    override fun onResume() {
        super.onResume()
        loginViewModel.getSignedInAccount()?.run {
            activity?.finish()
        }
    }

    private fun init() {
        events()
        observers()
    }

    private fun events() {
        fragment_login_btn_signin.setOnClickListener {
            startActivityForResult(loginViewModel.getSignInIntent(), GOOGLE_SIGN_IN_REQUEST_CODE)
        }
    }

    private fun observers() {
        loginViewModel.userIsSignedIn.observe(this, Observer {
            if (it == true) {
                navigateToHome()
            } else {
                showLoginErrorDialog()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.let {
                loginViewModel.signIn(account)
            }
        } catch (e: ApiException) {
            showLoginErrorDialog()
        }
    }

    private fun navigateToHome() {
        activity?.launchActivity<HomeActivity>()
    }

    private fun showLoginErrorDialog() {
        this.context?.run {
            MaterialDialog(this).title(R.string.error).message(R.string.login_error).show()
        }
    }
}
