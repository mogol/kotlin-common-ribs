package sample.profile

import ribs.Interactor
import ribs.Router
import ribs.RibView
import sample.login.AuthorizationListener
import sample.root.OSSpecificDependencies


data class UserProfile(val username: String, val password: String)

class ProfileBuilder(private val dependencies: OSSpecificDependencies) {
    fun build(profile: UserProfile, authorizationListener: AuthorizationListener): ProfileRouter {
        val router = ProfileRouter()
        val interactor = ProfileInteractor(profile, authorizationListener)
        val view = ProfileViewProvider(dependencies).getView()

        router.interactor = interactor
        router.view = view

        interactor.router = router
        interactor.view = view

        view.output = interactor

        return router
    }
}

interface ProfileRouterInput

class ProfileRouter : Router(), ProfileRouterInput {
    lateinit var view: ProfileView
}

class ProfileInteractor(private val userProfile: UserProfile, private val listener: AuthorizationListener) : Interactor(),
    ProfileViewOutput {
    lateinit var router: ProfileRouterInput

    lateinit var view: ProfileView

    override fun activate() {
        println("ProfileInteractor.activate with $userProfile")
        view.update(userProfile)
    }

    override fun deactivate() {

    }

    override fun userDidPressLogout() {
        listener.onLogout()
    }

}

interface ProfileViewOutput {
    fun userDidPressLogout()
}

expect class ProfileView : RibView {
    var output: ProfileViewOutput?
    fun update(profile: UserProfile)
}

expect class ProfileViewProvider(dependencies: OSSpecificDependencies) {
    fun getView(): ProfileView
}