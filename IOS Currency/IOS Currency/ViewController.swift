//
//  ViewController.swift
//  IOS Currency
//
//  Created by iamkurtgoz on 25.01.2019.
//  Copyright © 2019 iamkurtgoz. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITableViewDelegate, UITableViewDataSource{

    var apiURL: String = "http://data.fixer.io/api/latest?access_key=dc03684b36ec78e7419f9c974cdb761e&format=1";
    
    @IBOutlet weak var tableCurrency: UITableView!
    var currencyModels = [Currency]();
    var loadingBar: UIActivityIndicatorView? = nil;
    

    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadingBar = getProgressIndicator(uiView: self.view);
    
        tableCurrency.delegate = self;
        tableCurrency.dataSource = self;
    
        //"TableViewCell" isimli swift dosyasını tanımladık.
        let nibname = UINib(nibName: "TableViewCell", bundle: nil);
        //tanımladığımız swift dosyasındaki identifier değeri ile register ettik.
        tableCurrency.register(nibname, forCellReuseIdentifier: "tableViewCell");
    
        getValueForFixer();
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return currencyModels.count;
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 80;
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.tableCurrency.deselectRow(at: indexPath, animated: true);
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "tableViewCell", for: indexPath) as! TableViewCell;
        cell.setValue(currencyModel: currencyModels[indexPath.row]);
        return cell;
    }
    
    //GET VALUE FOR API
    
    func getValueForFixer(){
    
        setLoadingStatus(isLoading: true);
        if currencyModels.count > 0 {
            currencyModels.removeAll(keepingCapacity: false);
        }
        
        let fixerURL = URL(string: apiURL);
        let session = URLSession.shared;
        let task = session.dataTask(with: fixerURL!) { (data, response, errorData) in
            
            if errorData != nil || data == nil {
                self.customAlert(title: "Hata!", message: errorData!.localizedDescription, buttonText: "OK");
            } else {
            
                do {
                    let JSONResult = try JSONSerialization.jsonObject(with: data!, options: JSONSerialization.ReadingOptions.mutableContainers) as! Dictionary<String,AnyObject>;
                    
                    DispatchQueue.main.async {
                        let rates = JSONResult["rates"] as! [String:AnyObject];
                        print(rates);
                        
                        let CADCurrency = Currency();
                        CADCurrency.image = UIImage(named: "canada.png")!;
                        CADCurrency.name = "Kanada";
                        CADCurrency.value = String(describing: rates["CAD"]!);
                        self.currencyModels.append(CADCurrency);
                        
                        let GBPCurrency = Currency();
                        GBPCurrency.image = UIImage(named: "england.png")!;
                        GBPCurrency.name = "İngiltere";
                        GBPCurrency.value = String(describing: rates["GBP"]!);
                        self.currencyModels.append(GBPCurrency);
                        
                        let JPYCurrency = Currency();
                        JPYCurrency.image = UIImage(named: "japon.png")!;
                        JPYCurrency.name = "Japonya";
                        JPYCurrency.value = String(describing: rates["JPY"]!);
                        self.currencyModels.append(JPYCurrency);
                        
                        let USDCurrency = Currency();
                        USDCurrency.image = UIImage(named: "usd.png")!;
                        USDCurrency.name = "USD";
                        USDCurrency.value = String(describing: rates["USD"]!);
                        self.currencyModels.append(USDCurrency);
                        
                        let TRYCurrency = Currency();
                        TRYCurrency.image = UIImage(named: "Turkey.png")!;
                        TRYCurrency.name = "TÜRKİYE";
                        TRYCurrency.value = String(describing: rates["TRY"]!);
                        self.currencyModels.append(TRYCurrency);
 
                        self.setLoadingStatus(isLoading: false);
                        self.tableCurrency.reloadData();
                    }
 
                    print("TAMAM");
                } catch {
                    self.customAlert(title: "Catch!", message: "JSON parçalama hatası.", buttonText: "OK");
                }
                
            }
            
            
        };
        task.resume();
    }
    
    //Messages Function
    func customAlert(title: String, message: String, buttonText: String) {
        let alertMessage = UIAlertController(title: title, message: message, preferredStyle: UIAlertController.Style.alert);
        let okButton = UIAlertAction(title: buttonText, style: UIAlertAction.Style.cancel, handler: nil);
        alertMessage.addAction(okButton);
        self.present(alertMessage, animated: true, completion: nil);
    }

    func setLoadingStatus(isLoading: Bool){
        if isLoading {
            self.tableCurrency.isHidden = true;
            self.loadingBar?.startAnimating();
        } else {
            self.tableCurrency.isHidden = false;
            self.loadingBar?.stopAnimating();
        }
    }
}

