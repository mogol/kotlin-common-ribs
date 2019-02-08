package sample.root

import ribs.*
import sample.login.LoginBuilder

class RootBuilder(private val dependencies: OSSpecificDependencies) {

    fun build(): RootRouter {
        val router = RootRouter(loginBuilder = LoginBuilder(dependencies))
        val interactor = RootInteractor()
        val view = RootViewProvider(dependencies).getView()

        router.interactor = interactor
        router.view = view

        interactor.router = router

        return router
    }
}

interface RootRouterInput {
    fun showLogin()
}

class RootRouter(val loginBuilder: LoginBuilder) : Router(), RootRouterInput {
    lateinit var view: RootView

    override fun showLogin() {
        val router = loginBuilder.build(viewPresenter = { newView -> view.show(newView) })
        attach(router)
        view.show(router.view)
    }

    fun activate() {
        didAttach()
    }
}

class RootInteractor : Interactor(), RootViewOutput {
    lateinit var router: RootRouterInput

    override fun activate() {
        router.showLogin()
    }

    override fun deactivate() {

    }
}

expect class RootView: RootViewInput

interface RootViewInput {
    fun show(newView: RibView)
}

interface RootViewOutput

expect class RootViewProvider(dependencies: OSSpecificDependencies) {
    fun getView(): RootView
}

expect class OSSpecificDependencies