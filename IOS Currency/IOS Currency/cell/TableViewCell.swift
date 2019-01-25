//
//  TableViewCell.swift
//  IOS Currency
//
//  Created by iamkurtgoz on 25.01.2019.
//  Copyright © 2019 iamkurtgoz. All rights reserved.
//

import UIKit

class TableViewCell: UITableViewCell {

    @IBOutlet weak var imgHeader: UIImageView!
    @IBOutlet weak var lblTitle: UILabel!
    @IBOutlet weak var lblValue: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func setValue(currencyModel: Currency) {
        self.imgHeader.image = currencyModel.image;
        self.lblTitle.text = currencyModel.name;
        self.lblValue.text = currencyModel.value;
    }
}
