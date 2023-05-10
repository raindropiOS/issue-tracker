//
//  IssueFilterTableViewController.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/10.
//

import UIKit

class IssueFilterTableViewController: UITableViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let filterOptionCellNib: UINib = UINib(nibName: "IssueFilterTableViewCell", bundle: nil)
        tableView.register(filterOptionCellNib, forCellReuseIdentifier: "filterOptionCell")
        tableView.rowHeight = tableView.frame.width * 0.1173
    }

    // MARK: - Table view data source
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch section {
        case 0: return 1
        case 1: return 1
        case 2: return 1
        default: return 1
        }
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "filterOptionCell", for: indexPath) as? IssueFilterTableViewCell else { return UITableViewCell() }
        cell.configure(withOptionName: "filterOption")
        return cell
    }
}
