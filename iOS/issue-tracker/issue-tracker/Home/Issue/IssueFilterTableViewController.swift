//
//  IssueFilterTableViewController.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/10.
//

import UIKit

class IssueFilterTableViewController: UITableViewController {
    private let checkmarkImage = UIImage(systemName: "checkmark")
    private let grayCheckmarkImage = UIImage(systemName: "checkmark")?.withTintColor(.gray, renderingMode: .alwaysOriginal)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let filterOptionCellNib: UINib = UINib(nibName: "IssueFilterTableViewCell", bundle: nil)
        tableView.register(filterOptionCellNib, forCellReuseIdentifier: "filterOptionCell")
        tableView.rowHeight = tableView.frame.width * 0.1173
        
        configureBackButton()
    }

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch section {
        case 0: return 2
        case 1: return 3
        case 2: return 4
        default: return 1
        }
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "filterOptionCell", for: indexPath) as? IssueFilterTableViewCell else { return UITableViewCell() }
        cell.configureWith(optionName: "Filter option", selectedImage: checkmarkImage, deselectedImage: grayCheckmarkImage)
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        if let cell = tableView.cellForRow(at: indexPath) as? IssueFilterTableViewCell {
            cell.toggleImage()
        }
    }
    
    override func tableView(_ tableView: UITableView, titleForHeaderInSection
                                section: Int) -> String? {
        switch section {
        case 0: return "상태"
        case 1: return "담당자"
        case 2: return "레이블"
        default: return "Header \(section)"
        }
    }
    
    func configureBackButton() {
        let backbutton = UIButton(type: .custom)
        backbutton.setImage(UIImage(systemName: "chevron.left"), for: .normal)
        backbutton.setTitle("Back", for: .normal)
        backbutton.setTitleColor(backbutton.tintColor, for: .normal) // You can change the TitleColor
        backbutton.addTarget(self, action: #selector(backAction), for: .touchUpInside)
        
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(customView: backbutton)
    }
    
    
    @IBAction func cancelButtonPressed(_ sender: Any) {
        self.dismiss(animated: true, completion: nil)
    }
    
    @objc func backAction() -> Void {
//        self.navigationController?.popViewController(animated: true)
        self.navigationController?.dismiss(animated: true)
    }
}

