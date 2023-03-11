//
//  HomeView.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct HomeView: View {
    
    @ObservedObject var viewModel: HomeViewModel
    
    var body: some View {
        NavigationView {
            List(viewModel.state.posts, id: \.id) { post in
                ArticleView(
                    postTitle: post.title,
                    authorName: post.authorName,
                    postBody: post.body
                )
            }
        }
        .navigationTitle("Home")
        .toolbar {
            Button("Logout") {
                viewModel.logout()
            }
        }
        .overlay(viewModel.state.isLoading ? LoadingView() : nil)
    }
}

struct ArticleView: View {
    
    let postTitle: String
    let authorName: String
    let postBody: String
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(postTitle)
                .font(.title2)
            Text(authorName)
                .font(.caption)
                .padding(.vertical, 8)
            Text(postBody)
                .font(.body)
        }
    }
}
