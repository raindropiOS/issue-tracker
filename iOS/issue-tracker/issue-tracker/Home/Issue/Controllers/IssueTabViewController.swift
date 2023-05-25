//
//  IssueTabViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/09.
//

import UIKit
import OSLog

class IssueTabViewController: UIViewController {
    @IBOutlet var filterButton: UIBarButtonItem!
    @IBOutlet var selectButton: UIBarButtonItem!
    @IBOutlet var collectionView: IssueCollectionView!
    var cancelButton: UIBarButtonItem?
    let nothingButton = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
    var filterOptionList: FilterOptionsLike = FilterOptionListMock()
    
    let fetcher = HTTPDataFetcher()
    
    private let logger = Logger()
    private let networkManager = NetworkManager()
    private var issueFrames: [IssueFrame]?
    private var currentIssueDataUrlString: String = "http://3.38.73.117:8080/api-ios/issues"
    
    @IBOutlet var backPlane: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setCancelButton()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        fetchData()
    }
    
    private func setCancelButton() {
        cancelButton = UIBarButtonItem(title: "취소  ", style: .plain, target: self, action: #selector(cancelButtonTouched))
    }
    
    @IBAction func filterButtonTouched(_ sender: UIButton) {
        let filterTableViewController = IssueFilterTableViewController()
        filterTableViewController.delegate = self
        filterTableViewController.filterOptionList = filterOptionList
        show(filterTableViewController, sender: sender)
    }
    
    @IBAction func selectButtonTouched(_ sender: UIButton) {
        self.navigationController?.isToolbarHidden = false
        self.tabBarController?.tabBar.isHidden = true
        self.navigationItem.leftBarButtonItem = nothingButton
        
        self.navigationItem.rightBarButtonItem = cancelButton
    }
    
    @objc private func cancelButtonTouched() {
        self.navigationController?.isToolbarHidden = true
        self.tabBarController?.tabBar.isHidden = false
        self.navigationItem.leftBarButtonItem = filterButton
        self.navigationItem.rightBarButtonItem = selectButton
    }
    
    private func fetchData() {
        guard let url = URL(string: currentIssueDataUrlString) else {
            self.logger.log(
                "Invalie URL string : \(self.currentIssueDataUrlString)")
            return
        }
        networkManager.fetchIssueData(with: url) { result in
            switch result {
            case .success(let issueFrameHolder):
                self.issueFrames = issueFrameHolder.issues
                guard let issueFrames = self.issueFrames else { return }
                self.collectionView.issueFrames = issueFrames
                DispatchQueue.main.async {
                    self.collectionView.reloadData()
                }
            case .failure(let error):
                self.logger.error("error : \(error)")
            }
        }
    }
    
    func setUrlString(with urlString: String) {
        currentIssueDataUrlString = urlString
    }
}
