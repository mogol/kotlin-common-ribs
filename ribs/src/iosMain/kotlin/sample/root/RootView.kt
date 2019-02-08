package sample.root

import platform.UIKit.UIViewController
import platform.UIKit.addSubview
import platform.UIKit.removeFromSuperview
import platform.UIKit.setFrame
import ribs.RibView

actual class RootView : UIViewController(null, null) {
    private var currentView: RibView? = null

    actual fun show(newView: RibView) {
        currentView?.removeFromSuperview()
        view.addSubview(newView)

        newView.setFrame(view.bounds)
        currentView = newView
    }
}