package sample.root

import android.content.Context
import android.widget.FrameLayout
import ribs.RibView

actual class RootView : FrameLayout, RootViewInput {
    constructor(context: Context) : super(context)

    override fun show(newView: RibView) {
        removeAllViews()
        addView(newView, LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT))
    }
}


actual class RootViewProvider actual constructor(private val dependencies: OSSpecificDependencies) {
    actual fun getView(): RootView = RootView(dependencies.context)
}

actual class OSSpecificDependencies(val context: Context)