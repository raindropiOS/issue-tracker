//
//  LabelTabViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/29.
//

import UIKit

class LabelTabViewController: UIViewController {
    private var addButton: UIBarButtonItem!
    private var collectionView: LabelTabCollectionView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setAddButton()
    }
    
    private func setAddButton() {
        addButton = UIBarButtonItem(title: "추가  ", style: .plain, target: self, action: #selector(addButtonTouched))
        self.navigationItem.rightBarButtonItem = addButton
    }
    
    @objc private func addButtonTouched() {
        
    }
    
}
