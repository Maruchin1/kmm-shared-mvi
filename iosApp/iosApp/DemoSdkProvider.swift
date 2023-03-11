//
//  DemoSdkProvider.swift
//  iosApp
//
//  Created by Marcin Piekielny on 06/03/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import shared

class DemoSdkProvider {
    
    static let shared = DemoSdkProvider()
    
    let sdk: DemoSdk
    
    private init() {
        let config = DemoConfig(settingsName: "demo_settings")
        sdk = DemoSdk(config: config)
    }
}
