//
//  LoginView.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LoginView: View {
    
    @ObservedObject var viewModel: LoginViewModel
        
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                Spacer()
                Text("Welcome")
                    .font(.largeTitle)
                    .padding(.horizontal, 24)
                    .padding(.vertical, 12)
                Text(viewModel.state.email)
                    .font(.body)
                    .padding(.horizontal, 24)
                    .padding(.vertical, 12)
                Button(
                    action: {
                        viewModel.login()
                    },
                    label: {
                        Text("Login")
                            .frame(maxWidth: .infinity)
                    }
                )
                    .buttonStyle(.borderedProminent)
                    .padding(.horizontal, 24)
                    .padding(.vertical, 12)
                    .disabled(viewModel.state.isLoading)
                Spacer()
            }
            .padding()
        }
        .navigationTitle("Login")
        .overlay(viewModel.state.isLoading ? LoadingView() : nil)
    }
}
