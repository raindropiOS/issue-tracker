//
//  IssueLabels.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/31.
//

import Foundation


struct SingleLabel {
    let label: Label
    let description: String?
    
    init(label: Label, description: String?) {
        self.label = label
        self.description = description
    }
}
