//
//  HomeViewModel.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

class HomeViewModel: ObservableObject {
    
    @Published var state: HomeUiState = HomeUiState.companion.default()
    
    private let viewModelDelegate: HomeViewModelDelegate
    private let delegate: HomeDelegate
    private var stateSubscription: KmmSubscription!
    
    init(viewModelDelegate: HomeViewModelDelegate, delegate: HomeDelegate) {
        self.viewModelDelegate = viewModelDelegate
        self.delegate = delegate
        subscribeState()
    }
    
    deinit {
        stateSubscription.unsubscribe()
        viewModelDelegate.onCleared()
    }
    
    func logout() {
        viewModelDelegate.logout()
    }
    
    private func subscribeState() {
        stateSubscription = viewModelDelegate.uiState.subscribe(
            onEach: { state in
                self.state = state!
                self.handleLoggedOut()
            },
            onCompletion: { error in
                if let error = error {
                    print(error)
                }
            }
        )
    }
    
    private func handleLoggedOut() {
        if (state.isLoggedOut) {
            delegate.onLoggedOut()
        }
    }
}

protocol HomeDelegate {
    func onLoggedOut()
}
