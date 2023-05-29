//
//  TableViewCellTest.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/10.
//

import UIKit

class IssueFilterTableViewCell: UITableViewCell {
    @IBOutlet var filterOptionLabel: UILabel!
    @IBOutlet var togglableImageView: UIImageView!
    private var filterOption: FilterOption?
    private let checkmarkImage = UIImage(systemName: "checkmark")
    private let grayCheckmarkImage = UIImage(systemName: "checkmark")?.withTintColor(.gray, renderingMode: .alwaysOriginal)
    private var isOptionSelected = false {
        willSet {
            togglableImageView.image = newValue ? checkmarkImage : grayCheckmarkImage
        }
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }
    
    func configureWith(filterOption: FilterOption) {
        filterOptionLabel.text = filterOption.filterLabel
        isOptionSelected = filterOption.isSelected
    }
    
    func toggleSelecting() {
        isOptionSelected.toggle()
    }
}
