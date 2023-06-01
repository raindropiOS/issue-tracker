//
//  IssueLabels.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/31.
//

import Foundation


struct SingleLabel {
    let name: String
    let description: String?
    
    init(name: String, description: String?) {
        self.name = name
        self.description = description
    }
}
