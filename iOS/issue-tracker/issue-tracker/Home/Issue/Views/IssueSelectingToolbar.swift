//
//  IssueSelectingToolbar.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/24.
//

import UIKit

class IssueSelectingToolbar: UIToolbar {
    override init(frame: CGRect) {
        super.init(frame: frame)
        setToolBarProPerties()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setToolBarProPerties()
    }
    
    private func setToolBarItems() -> [UIBarButtonItem] {
        let rightItem = UIBarButtonItem(image: UIImage(systemName: "archivebox"), style: .plain, target: nil, action: nil)
        //TODO: 이슈 선택 개수에 따라 title 변경 기능
        let titleItem = UIBarButtonItem(title: "이슈를 선택하세요.", style: .done, target: nil, action: nil)
        let leftItem = UIBarButtonItem(image: UIImage(systemName: "checkmark.circle"), style: .plain, target: nil, action: nil)
        let flexibleSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        titleItem.isEnabled = false
        
        return([leftItem, flexibleSpace, titleItem, flexibleSpace, rightItem])
    }
    
    private func setToolBarProPerties() {
        self.items = setToolBarItems()
        self.isHidden = true
        self.barTintColor = .white
    }
}
