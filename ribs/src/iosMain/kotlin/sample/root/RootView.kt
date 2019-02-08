package sample.root

import platform.UIKit.UIViewController
import platform.UIKit.addSubview
import platform.UIKit.removeFromSuperview
import platform.UIKit.setFrame
import ribs.RibView

actual class RootView : RootViewInput {
    public val controller = UIViewController(null, null)
    private var currentView: RibView? = null

    override fun show(newView: RibView) {
        currentView?.removeFromSuperview()
        controller.view.addSubview(newView)

        newView.setFrame(controller.view.bounds)
        currentView = newView
    }
}

actual class RootViewProvider actual constructor(private val dependencies: OSSpecificDependencies) {
    actual fun getView() = RootView()
}

actual class OSSpecificDependencies