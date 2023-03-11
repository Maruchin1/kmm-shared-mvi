//
//  MainCoordinator.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit

class MainCoordinator: LoginDelegate, HomeDelegate {
    
    private let navController: UINavigationController
    
    init(navController: UINavigationController) {
        self.navController = navController
    }
    
    func start() {
        let controller = buildLoginController(delegate: self)
        navController.pushViewController(controller, animated: false)
    }
    
    func onLoggedIn() {
        let controller = buildHomeController(delegate: self)
        navController.setViewControllers([controller], animated: true)
    }
    
    func onLoggedOut() {
        let controller = buildLoginController(delegate: self)
        navController.setViewControllers([controller], animated: true)
    }
}
