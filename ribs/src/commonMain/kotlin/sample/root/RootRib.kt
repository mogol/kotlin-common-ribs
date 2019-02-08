package sample.root

import ribs.*
import sample.login.LoginBuilder

class RootBuilder {
    fun build(): RootRouter {
        val router = RootRouter(loginBuilder = LoginBuilder())
        val interactor = RootInteractor()
        val view = RootView()

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

expect class RootView() {
    fun show(newView: RibView)
}

interface RootViewOutput

