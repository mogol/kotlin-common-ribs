package ribs

open class Router {
    private val children = arrayListOf<Router>()

    internal lateinit var interactor: Interactor

    internal open fun didAttach() {
        interactor.activate()
    }

    internal fun attach(router: Router) {
        children.add(router)
        router.didAttach()
    }

    internal fun detach(router: Router) {
        children.remove(router)
        router.didDetach()
    }

    private fun didDetach() {
        interactor.deactivate()
    }
}

abstract class Interactor {
    abstract fun activate()
    abstract fun deactivate()
}

expect open class RibView

typealias RibViewPresenter = (newView: RibView) -> Unit