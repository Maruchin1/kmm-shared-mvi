//
//  HomeController.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

func buildHomeController(delegate: HomeDelegate) -> UIViewController {
    let sdk = DemoSdkProvider.shared.sdk
    let viewModel = HomeViewModel(viewModelDelegate: sdk.homeViewModel, delegate: delegate)
    let view = HomeView(viewModel: viewModel)
    return UIHostingController(rootView: view)
}
