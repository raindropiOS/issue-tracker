//
//  ViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/09.
//

import UIKit

class ViewController: UIViewController {
    @IBOutlet var button: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        button.addTarget(self, action: #selector(buttonTapped), for: .touchUpInside)
    }
    @objc func buttonTapped() {
        let homeViewStoryboard = UIStoryboard(name: "HomeStoryboard", bundle: nil)
        let homeViewController = homeViewStoryboard.instantiateInitialViewController()!
        navigationController?.pushViewController(homeViewController, animated: true)
    }
}
