//
//  LoadingView.swift
//  iosApp
//
//  Created by Marcin Piekielny on 11/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct LoadingView: View {

    var body: some View {
        ZStack {
            Color.gray.opacity(0.32)
            
            ProgressView()
                .frame(width: 64, height: 64)
                .background(Color.white)
                .cornerRadius(16)
        }.ignoresSafeArea()
    }
}
