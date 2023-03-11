//
//  LoginViewModel.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class LoginViewModel: ObservableObject {
    
    @Published var state: LoginUiState = LoginUiState.companion.default()
    
    private let viewModelDelegate: LoginViewModelDelegate
    private let delegate: LoginDelegate
    private var stateSubscription: KmmSubscription!
    
    init(viewModelDelegate: LoginViewModelDelegate, delegate: LoginDelegate) {
        self.viewModelDelegate = viewModelDelegate
        self.delegate = delegate
        subscribeState()
    }
    
    func login() {
        viewModelDelegate.login()
    }
    
    private func subscribeState() {
        stateSubscription = viewModelDelegate.uiState.subscribe(
            onEach: { state in
                self.state = state!
                self.handleLoggedIn()
            },
            onCompletion: { error in
                if let error = error {
                    print(error)
                }
            }
        )
    }
    
    private func handleLoggedIn() {
        if (state.isLoggedIn) {
            delegate.onLoggedIn()
        }
    }
}

protocol LoginDelegate {
    func onLoggedIn()
}
