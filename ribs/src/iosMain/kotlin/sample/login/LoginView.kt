package sample.login

import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.ObjCMethod
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSSelectorFromString
import platform.UIKit.*
import platform.objc.sel_registerName
import ribs.RibView

actual class LoginView : RibView(frame = CGRectZero.readValue()) {
    private val username: UITextField
    private val password: UITextField

    actual var output: LoginViewOutput? = null

    init {
        backgroundColor = UIColor.whiteColor

        val stackView = UIStackView().apply {
            translatesAutoresizingMaskIntoConstraints = false
            axis = UILayoutConstraintAxisVertical
            alignment = UIStackViewAlignmentCenter
            distribution = UIStackViewDistributionEqualCentering
        }
        addSubview(stackView)

        NSLayoutConstraint.activateConstraints(
            listOf(
                stackView.leadingAnchor.constraintEqualToAnchor(leadingAnchor),
                stackView.trailingAnchor.constraintEqualToAnchor(trailingAnchor),
                stackView.centerYAnchor.constraintEqualToAnchor(centerYAnchor)
            )
        )

        username = UITextField().apply {
            translatesAutoresizingMaskIntoConstraints = false
            placeholder = "Username"
        }

        stackView.addArrangedSubview(username)

        password = UITextField().apply {
            translatesAutoresizingMaskIntoConstraints = false
            placeholder = "Password"
            secureTextEntry = true
        }
        stackView.addArrangedSubview(password)


        val button = UIButton().apply {
            translatesAutoresizingMaskIntoConstraints = false
            setTitle("Login", UIControlStateNormal)
            addTarget(
                target = this@LoginView,
                action = NSSelectorFromString("loginDidPress"),
                forControlEvents = UIControlEventTouchUpInside
            )
            setTitleColor(UIColor.blackColor, UIControlStateNormal)
        }

        stackView.addArrangedSubview(button)
    }

    @ObjCAction
    fun loginDidPress() {
        output?.userDidPressLogin(username = username.text ?: "", password = password.text ?: "")
    }

    actual fun clearForm() {
        username.text = ""
        password.text = ""
    }
}