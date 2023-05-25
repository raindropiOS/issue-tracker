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
    var filterOptionList: FilterOptionsLike?
    weak var delegate: IssueTabViewController?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let filterOptionCellNib: UINib = UINib(nibName: "IssueFilterTableViewCell", bundle: nil)
        tableView.register(filterOptionCellNib, forCellReuseIdentifier: "filterOptionCell")
        
        configureBackButton()
        configureSaveButton()
    }
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 3
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch section {
        case 0: return filterOptionList?.list[0].count ?? 1
        case 1: return filterOptionList?.list[1].count ?? 1
        case 2: return filterOptionList?.list[2].count ?? 1
        default: return 1
        }
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "filterOptionCell", for: indexPath) as? IssueFilterTableViewCell else { return UITableViewCell() }
        guard let filterOption = filterOptionList?.list[indexPath.section][indexPath.row] else { return UITableViewCell() }
        cell.configureWith(filterOption: filterOption, selectedImage: checkmarkImage, deselectedImage: grayCheckmarkImage)
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        if let cell = tableView.cellForRow(at: indexPath) as? IssueFilterTableViewCell {
            cell.toggleSelecting()
            filterOptionList?.list[indexPath.section][indexPath.row].isSelected.toggle()
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
    
    override func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        let num = round(tableView.frame.height * 44 / 811)
        
        return num
    }
    
    private func dismissSelf() {
        navigationController?.popViewController(animated: true)
    }
}

protocol FilterOptionsLike {
    var list: [[FilterOption]] { get set }
    func collectSelectedFilterOptionUrlString() -> String
}

protocol IssueTabViewControllerLike {
    func setUrlString(with: String)
    func fetchData()
}

// 취소, 저장 버튼
extension IssueFilterTableViewController {
    private func configureBackButton() {
        let backbutton = UIButton(type: .custom)
        backbutton.setImage(UIImage(systemName: "chevron.left"), for: .normal)
        backbutton.setTitle("Back", for: .normal)
        backbutton.setTitleColor(backbutton.tintColor, for: .normal)
        backbutton.addTarget(self, action: #selector(backAction), for: .touchUpInside)
        
        self.navigationItem.leftBarButtonItem = UIBarButtonItem(customView: backbutton)
    }
    
    private func configureSaveButton() {
        navigationItem.rightBarButtonItem = UIBarButtonItem(title: "저장  ", style: .plain, target: self, action: #selector(saveAction))
    }
    
    @objc func backAction() {
        dismissSelf()
    }
    
    @objc func saveAction() {
        let filterUrlString = delegate?.filterOptionList.collectSelectedFilterOptionUrlString() ?? ""
        let newUrlString = "http://3.38.73.117:8080/api-ios/issues" + filterUrlString
        delegate?.setUrlString(with: newUrlString)
        dismissSelf()
    }
}