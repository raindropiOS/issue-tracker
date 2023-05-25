//
//  SubIconView.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/16.
//

import UIKit

class SubIconView: UIImageView {

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        self.image = image?.withTintColor(.gray, renderingMode: .alwaysOriginal)
    }

    override func layoutSubviews() {
        super.layoutSubviews()
        guard let superview = superview else {
            return
        }
        
        self.translatesAutoresizingMaskIntoConstraints = false
        
    }
    }
}
