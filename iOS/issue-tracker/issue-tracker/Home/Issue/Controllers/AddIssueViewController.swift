//
//  AddIssueViewController.swift
//  issue-tracker
//
//  Created by 에디 on 2023/06/01.
//

import UIKit

class AddIssueViewController: UIViewController {
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var titleInputField: UITextField!
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        tableView.delegate = self
        tableView.dataSource = self
        registerCell()
        setTitleInputLabelLayout()
        
    }
    
    private func configureNavigationBar() {
        
    }
    
    private func setTitleInputLabelLayout() {
        titleInputField.translatesAutoresizingMaskIntoConstraints = false
        
        NSLayoutConstraint.activate(
            [
                titleInputField.leadingAnchor.constraint(equalTo: titleLabel.trailingAnchor, constant: 50.0)
        ])
        
        titleInputField.font = UIFont.systemFont(ofSize: 18.0)
        titleInputField.textColor = .gray
    }
    /*
     // MARK: - Navigation
     
     // In a storyboard-based application, you will often want to do a little preparation before navigation
     override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
     // Get the new view controller using segue.destination.
     // Pass the selected object to the new view controller.
     }
     */
    
    private func registerCell() {
        tableView.register(UINib(nibName: "AddIssueTableViewCell", bundle: nil), forCellReuseIdentifier: AddIssueTableViewCell.identifier)
    }
    
}

extension AddIssueViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        3
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: AddIssueTableViewCell.identifier, for: indexPath) as? AddIssueTableViewCell else {
            return UITableViewCell()
        }
        switch indexPath.row {
        case 0:
            cell.configure(type: "담당자", name: "sam")
        case 1:
            cell.configure(type: "레이블", name: "feature")
        case 2:
            cell.configure(type: "마일스톤", name: "iOS-STEP-138")
        default:
            return UITableViewCell()
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        switch section {
        case 0: return "추가 정보"
        default: return ""
        }
    }
    
    
}

extension AddIssueViewController: UITableViewDelegate {
    
}
