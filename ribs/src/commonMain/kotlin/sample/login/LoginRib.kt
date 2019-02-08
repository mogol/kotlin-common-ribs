package sample.login

import ribs.*
import sample.profile.ProfileBuilder
import sample.profile.ProfileRouter
import sample.profile.UserProfile
import sample.root.OSSpecificDependencies

class LoginBuilder(private val dependencies: OSSpecificDependencies) {
    fun build(viewPresenter: RibViewPresenter): LoginRouter {
        val router = LoginRouter(profileBuilder = ProfileBuilder(dependencies), viewPresenter = viewPresenter)
        val interactor = LoginInteractor()
        val view = LoginViewProvider(dependencies).getView()

        router.interactor = interactor
        router.view = view

        interactor.router = router
        interactor.view = view

        view.output = interactor

        return router
    }
}

class LoginRouter(private val profileBuilder: ProfileBuilder, private val viewPresenter: RibViewPresenter) : Router() {
    lateinit var view: LoginView

    var profile: ProfileRouter? = null

    fun attachProfile(userProfile: UserProfile, listener: AuthorizationListener) {
        val router = profileBuilder.build(userProfile, listener)
        attach(router)
        viewPresenter(router.view)
    }

    fun detachProfile() {
        viewPresenter(view)
        profile?.let { detach(it) }
    }
}

interface AuthorizationListener {
    fun onLogout()
}

class LoginInteractor : Interactor(), LoginViewOutput, AuthorizationListener {
    lateinit var view: LoginView
    lateinit var router: LoginRouter

    override fun onLogout() {
        router.detachProfile()
    }

    override fun deactivate() {
    }

    override fun activate() {
    }

    override fun userDidPressLogin(username: String, password: String) {
        val userProfile = UserProfile(username, password)
        router.attachProfile(userProfile, this)
        view.clearForm()
    }
}

interface LoginViewOutput {
    fun userDidPressLogin(username: String, password: String)
}

expect class LoginView : RibView {
    var output: LoginViewOutput?
    fun clearForm()
}

expect class LoginViewProvider(dependencies: OSSpecificDependencies) {
    fun getView(): LoginView
}