//
//  IssueTabViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/09.
//

import UIKit

class IssueTabViewController: UIViewController {
    
    @IBOutlet var selectButton: UIBarButtonItem!
    
    var cancelButton: UIBarButtonItem?
    
    let fetcher = HTTPDataFetcher()
    
    let collectionView = IssueCollectionView(frame: .zero, collectionViewLayout: UICollectionViewFlowLayout())
    @IBOutlet var backPlane: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        backPlane.addSubview(collectionView)
        
        fetcher.fetchIssueData(completion: { result in
            switch result {
            case .success(let jsonString):
                print(jsonString)
            case .failure(let error):
                print(error.localizedDescription)
            }
        })
        setCancelButton()
    }

    
    private func setCancelButton() {
        cancelButton = UIBarButtonItem(title: "취소  ", style: .plain, target: self, action: #selector(cancelButtonTouched))
    }
    
    @IBAction func filterButtonTouched(_ sender: UIButton) {
        let filterTableViewController = IssueFilterTableViewController()
        show(filterTableViewController, sender: sender)
    }
    
    @IBAction func selectButtonTouched(_ sender: UIButton) {
        
        self.navigationController?.isToolbarHidden = false
        self.tabBarController?.tabBar.isHidden = true
        self.navigationItem.leftBarButtonItem?.isHidden = true
        
        self.navigationItem.rightBarButtonItem = cancelButton
    }
    
    @objc private func cancelButtonTouched() {
        self.navigationController?.isToolbarHidden = true
        self.tabBarController?.tabBar.isHidden = false
        self.navigationItem.leftBarButtonItem?.isHidden = false
        self.navigationItem.rightBarButtonItem = selectButton
    }

}
