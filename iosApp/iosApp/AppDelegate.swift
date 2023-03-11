//
//  AppDelegate.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit

@main
final class AppDelegate: UIResponder, UIApplicationDelegate {

    var coordinator: MainCoordinator?
    var window: UIWindow?

    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
        let navController = UINavigationController()
        let coordinator = MainCoordinator(navController: navController)
        coordinator.start()
        self.coordinator = coordinator

        let window = UIWindow(frame: UIScreen.main.bounds)
        window.rootViewController = navController
        window.makeKeyAndVisible()
        self.window = window

        return true
    }
}
