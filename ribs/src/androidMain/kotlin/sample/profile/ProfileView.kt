package sample.profile

import android.content.Context
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import ribs.RibView
import sample.root.OSSpecificDependencies

actual class ProfileView : RibView {
    constructor(context: Context) : super(context) {
        username = TextView(context)
        password = TextView(context)

        val button = Button(context)
        button.apply {
            text = "Logout"
            setOnClickListener {
                output?.userDidPressLogout()
            }
        }

        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        layout.addView(username)
        layout.addView(password)
        layout.addView(button)

        addView(layout, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }

    private val username: TextView
    private val password: TextView
    actual var output: ProfileViewOutput? = null

    actual fun update(profile: UserProfile) {
        username.text = profile.username
        password.text = profile.password
    }
}

actual class ProfileViewProvider actual constructor(private val dependencies: OSSpecificDependencies) {
    actual fun getView(): ProfileView = ProfileView(dependencies.context)
}