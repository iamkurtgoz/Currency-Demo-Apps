//
//  Utils.swift
//  IOS Currency
//
//  Created by iamkurtgoz on 25.01.2019.
//  Copyright © 2019 iamkurtgoz. All rights reserved.
//

import Foundation
import UIKit;

func getProgressIndicator(uiView: UIView) -> UIActivityIndicatorView {
    let loadingBar: UIActivityIndicatorView = UIActivityIndicatorView()
    loadingBar.frame = CGRect(x: 0, y: 0, width: 40, height: 40)
    loadingBar.center = uiView.center;
    loadingBar.hidesWhenStopped = true
    loadingBar.style = UIActivityIndicatorView.Style.gray;
    uiView.addSubview(loadingBar);
    return loadingBar;
}
