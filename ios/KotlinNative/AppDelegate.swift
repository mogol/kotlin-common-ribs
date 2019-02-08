//
//  AppDelegate.swift
//  KotlinNative
//
//  Created by German Saprykin on 18/1/19.
//  Copyright © 2019 German Saprykin. All rights reserved.
//

import UIKit
import kotlin_native_ribs

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?

    let router = RootBuilder().build()

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = router.view
        router.activate()
        window?.makeKeyAndVisible()
        return true
    }
}

