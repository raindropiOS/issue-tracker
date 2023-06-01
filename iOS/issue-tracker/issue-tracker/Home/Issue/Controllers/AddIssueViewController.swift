//
//  AddIssueViewController.swift
//  issue-tracker
//
//  Created by 에디 on 2023/06/01.
//

import UIKit

class AddIssueViewController: UIViewController {
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .white
        tableView.delegate = self
        tableView.dataSource = self
        
        
    }
    
    private func configureNavigationBar() {
        
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
        return UITableViewCell()
    }
}

extension AddIssueViewController: UITableViewDelegate {
    
}
