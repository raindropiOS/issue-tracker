//
//  IssueTabViewController.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/09.
//

import UIKit
import OSLog

class IssueTabViewController: UIViewController {
    private var toolBar : UIToolbar? = nil
    
    @IBOutlet var filterButton: UIBarButtonItem!
    @IBOutlet var selectButton: UIBarButtonItem!
    @IBOutlet var collectionView: IssueCollectionView!
    var cancelButton: UIBarButtonItem?
    let nothingButton = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
    
    let fetcher = HTTPDataFetcher()
    
    private let logger = Logger()
    private let networkManager = NetworkManager()
    private var issueFrames: [IssueFrame]?
    private var currentIssueDataUrlString: String = "http://3.38.73.117:8080/api-ios/issues"
    
    @IBOutlet var backPlane: UIView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // TODO: fetchData(with: String)로 대체 예정
        networkManager.fetchIssueData { result in
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
        setCancelButton()
        setToolbar()
    }
    
    private func setToolBarItems() -> [UIBarButtonItem] {
        let rightItem = UIBarButtonItem(image: UIImage(systemName: "archivebox"), style: .plain, target: nil, action: nil)
        //TODO: 이슈 선택 개수에 따라 title 변경 기능
        let titleItem = UIBarButtonItem(title: "이슈를 선택하세요.", style: .done, target: nil, action: nil)
        
        titleItem.isEnabled = false
        
        
        let leftItem = UIBarButtonItem(image: UIImage(systemName: "checkmark.circle"), style: .plain, target: nil, action: nil)
        
        let flexibleSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        
        
        return([leftItem, flexibleSpace, titleItem, flexibleSpace, rightItem])
    }
    
    private func setToolbar() {
        guard let tabBar = self.tabBarController?.tabBar else {
            return
        }
        let frame = CGRect(origin: tabBar.frame.origin, size: CGSize(width: tabBar.frame.width, height: tabBar.frame.height/2))
        
        self.toolBar = UIToolbar(frame: frame)
        
        guard let toolBar = self.toolBar else {
            return
        }
        toolBar.items = setToolBarItems()
        toolBar.isHidden = true
        toolBar.barTintColor = .white
        self.view.addSubview(toolBar)
    }
    
    private func setCancelButton() {
        cancelButton = UIBarButtonItem(title: "취소  ", style: .plain, target: self, action: #selector(cancelButtonTouched))
    }
    
    @IBAction func filterButtonTouched(_ sender: UIButton) {
        let filterTableViewController = IssueFilterTableViewController()
        show(filterTableViewController, sender: sender)
    }
    
    @IBAction func selectButtonTouched(_ sender: UIButton) {
        
        self.toolBar?.isHidden = false
        self.tabBarController?.tabBar.isHidden = true
        self.navigationItem.leftBarButtonItem = nothingButton
        
        self.navigationItem.rightBarButtonItem = cancelButton
    }
    
    @objc private func cancelButtonTouched() {
        self.toolBar?.isHidden = true
        self.tabBarController?.tabBar.isHidden = false
        self.navigationItem.leftBarButtonItem = filterButton
        self.navigationItem.rightBarButtonItem = selectButton
    }
    
    func fetchData() {
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


