//
//  IssueTabViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/09.
//

import UIKit

class IssueTabViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let view = Bundle.main.loadNibNamed("IssueTabView", owner: self, options: nil)?.first as? UIView {
            self.view = view
        }
        
    }
}
