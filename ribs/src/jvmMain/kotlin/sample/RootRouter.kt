package sample

import ribs.Router
import sample.login.LoginInteractor

actual class RootRouter : Router() {}

actual class LoginView actual constructor() {
    actual var interactor: LoginInteractor
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
        set(value) {}
}