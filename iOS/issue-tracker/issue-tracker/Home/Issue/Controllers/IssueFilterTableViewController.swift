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
    var filterOptionList: FilterOptionsLike = FilterOptionListMock()
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
        case 0: return 4
        case 1: return 4
        case 2: return 2
        default: return 1
        }
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
                guard let cell = tableView.dequeueReusableCell(withIdentifier: "filterOptionCell", for: indexPath) as? IssueFilterTableViewCell else { return UITableViewCell() }
        cell.configureWith(filterOption: filterOptionList.list[indexPath.section][indexPath.row], selectedImage: checkmarkImage, deselectedImage: grayCheckmarkImage)
                return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        if let cell = tableView.cellForRow(at: indexPath) as? IssueFilterTableViewCell {
            cell.toggleSelecting()
            filterOptionList.list[indexPath.section][indexPath.row].isSelected.toggle()
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
        let newUrlString = filterOptionList.collectSelectedFilterOptionUrlString()
        delegate?.setUrlString(with: newUrlString)
        delegate?.fetchData()
        dismissSelf()
    }
    
    private func dismissSelf() {
        navigationController?.popViewController(animated: true)
    }
}

struct FilterOptionListMock: FilterOptionsLike {
    var list: [[FilterOption]] = [
        [
            FilterOption(filterLabel: "열린 이슈", filterUrlStr: nil),
            FilterOption(filterLabel: "내가 작성한 이슈", filterUrlStr: nil),
            FilterOption(filterLabel: "내가 댓글을 남긴 이슈", filterUrlStr: nil),
            FilterOption(filterLabel: "닫힌 이슈", filterUrlStr: "{close=true}", isSelected: false),
        ],
        [
            FilterOption(filterLabel: "chloe", filterUrlStr: nil),
            FilterOption(filterLabel: "head", filterUrlStr: nil),
            FilterOption(filterLabel: "sam", filterUrlStr: nil),
            FilterOption(filterLabel: "zello", filterUrlStr: nil),
        ],
        [
            FilterOption(filterLabel: "레이블 없음", filterUrlStr: nil, isSelected: false),
            FilterOption(filterLabel: "그룹프로젝트:이슈트래커", filterUrlStr: nil, isSelected: false),
        ],
    ]
    
    func collectSelectedFilterOptionUrlString() -> String {
        var collectedOptionUrlString = "?"
        
        for options in list {
            for option in options {
                guard let urlStr = option.filterUrlStr else { continue }
                collectedOptionUrlString += option.isSelected ? urlStr : ""
            }
        }
        return collectedOptionUrlString.count == 1 ? "" : collectedOptionUrlString
    }
}

protocol FilterOptionsLike {
    var list: [[FilterOption]] { get set }
    func collectSelectedFilterOptionUrlString() -> String
}
