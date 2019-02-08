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

    var router: RootRouter?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        let dependencies = OSSpecificDependencies()
        
        let router = RootBuilder(dependencies: dependencies).build()
        window = UIWindow(frame: UIScreen.main.bounds)
        window?.rootViewController = router.view.controller
        router.activate()
        window?.makeKeyAndVisible()
        
        self.router = router
        
        return true
    }
}

