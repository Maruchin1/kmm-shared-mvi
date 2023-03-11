//
//  LoginController.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

func buildLoginController(delegate: LoginDelegate) -> UIViewController {
    let sdk = DemoSdkProvider.shared.sdk
    let viewModel = LoginViewModel(viewModelDelegate: sdk.loginViewModel, delegate: delegate)
    let view = LoginView(viewModel: viewModel)
    return UIHostingController(rootView: view)
}
