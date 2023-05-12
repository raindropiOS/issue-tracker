//
//  CollectionReusableView.swift
//  issue-tracker
//
//  Created by SONG on 2023/05/11.
//

import UIKit

class IssueCollectionViewHeaderCell: UICollectionReusableView {
    static let identifier = "Header"

    @IBOutlet var title: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        setTitle()
    func setTitle() {
        title.font = UIFont.systemFont(ofSize: safeAreaLayoutGuide.owningView!.frame.height * 10/100, weight: .bold)
    }
    
}
