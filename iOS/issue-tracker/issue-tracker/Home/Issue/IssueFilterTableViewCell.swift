//
//  TableViewCellTest.swift
//  issue-tracker
//
//  Created by 에디 on 2023/05/10.
//

import UIKit

class IssueFilterTableViewCell: UITableViewCell {

    @IBOutlet var filterOptionLabel: UILabel!
    @IBOutlet var checkmarkView: UIImageView!
    override func awakeFromNib() {
        
        super.awakeFromNib()
        // Initialization code
//        let config = UIImage.SymbolConfiguration(scale: .small)
        let config = UIImage.SymbolConfiguration(pointSize: 14)
        let image = UIImage(systemName: "checkmark", withConfiguration: config)?.withTintColor(.gray, renderingMode: .alwaysOriginal)
//        image?.size = CGSize(width: 3, height: 3)
        checkmarkView.image = image

        
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func configure(withOptionName optionName : String) {
        self.filterOptionLabel.text = optionName
    }
    
}
