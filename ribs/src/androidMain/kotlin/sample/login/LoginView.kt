package sample.login

import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import ribs.RibView
import sample.root.OSSpecificDependencies

actual class LoginView : RibView {
    val username: EditText
    val password: EditText

    constructor(context: Context) : super(context) {
        password = EditText(context)
        password.hint = "Password"
        username = EditText(context)
        username.hint = "Username"

        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.addView(username)
        linearLayout.addView(password)

        val button = Button(context)

        linearLayout.addView(button)
        button.apply {
            setOnClickListener {
                output?.userDidPressLogin(username = username.text.toString(), password = password.text.toString())
            }
            text = "Login"
        }
        addView(linearLayout)
    }

    actual var output: LoginViewOutput? = null

    actual fun clearForm() {
        password.text.clear()
        username.text.clear()
    }
}

actual class LoginViewProvider actual constructor(private val dependencies: OSSpecificDependencies) {
    actual fun getView() = LoginView(dependencies.context)
}