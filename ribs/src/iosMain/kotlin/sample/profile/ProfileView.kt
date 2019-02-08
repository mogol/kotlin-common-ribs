package sample.profile

import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.readValue
import platform.CoreGraphics.CGRectZero
import platform.Foundation.NSSelectorFromString
import platform.UIKit.*
import ribs.RibView

actual class ProfileView : RibView(frame = CGRectZero.readValue()) {
    actual var output: ProfileViewOutput? = null

    private val username: UILabel
    private val password: UILabel

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

        username = UILabel().apply {
            translatesAutoresizingMaskIntoConstraints = false
            textColor = UIColor.blackColor
        }

        stackView.addArrangedSubview(username)

        password = UILabel().apply {
            translatesAutoresizingMaskIntoConstraints = false
            textColor = UIColor.blackColor
        }
        stackView.addArrangedSubview(password)

        val button = UIButton().apply {
            translatesAutoresizingMaskIntoConstraints = false
            setTitle("Logout", UIControlStateNormal)
            addTarget(
                target = this@ProfileView,
                action = NSSelectorFromString("logoutDidPress"),
                forControlEvents = UIControlEventTouchUpInside
            )
            setTitleColor(UIColor.blackColor, UIControlStateNormal)
        }
        stackView.addArrangedSubview(button)
    }

    actual fun update(profile: UserProfile) {
        username.text = "Username: ${profile.username}"
        password.text = "Password: ${profile.password}"
    }

    @ObjCAction
    fun logoutDidPress() {
        output?.userDidPressLogout()
    }

}