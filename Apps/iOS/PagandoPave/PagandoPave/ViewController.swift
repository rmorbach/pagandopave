//
//  ViewController.swift
//  PagandoPave
//
//  Created by Rodrigo Morbach on 26/08/17.
//  Copyright © 2017 Morbach Inc. All rights reserved.
//

import UIKit
import VisaCheckoutSDK

class ViewController: UIViewController {

    
    @IBOutlet weak var message: UILabel!
    @IBOutlet weak var event: UILabel!
    @IBOutlet weak var value: UILabel!
    
    public var notificationDict: [String: String]?{
        didSet{
            updateUI()
        }
    }
    
    var currentIdCartao: String?
    var currentValor: String?{
        didSet{
            prepareToCheckout()
        }
    }
    
    let pVM = PVManager.sharedManager
    
    @IBOutlet weak var visaCheckoutButton: VisaCheckoutButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        
        /// See the documentation/headers for `PurchaseInfo` for
        /// various ways to customize the purchase experience.
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        updateUI()
                        
    }
    
    func insertCredit()
    {
        let notifyObject = NotifyCredit()
        notifyObject.credito = self.currentValor
        notifyObject.idCartao = self.currentIdCartao
        pVM.notifySale(credit: notifyObject) { success, error in
            if(success != nil && success!)
            {
                print("Pronto, seu filho com certeza está mais feliz agora!")
            }
        }
    }
    
    func prepareToCheckout()
    {
        var vCorrigido = self.currentValor?.replacingOccurrences(of: "R$", with: "")
        vCorrigido = vCorrigido?.replacingOccurrences(of: ",", with: ".")
        let value = Double(vCorrigido!)
        
        let currencyA = CurrencyAmount(double: value!)
        
        let purchaseInfo = PurchaseInfo(total: currencyA, currency: .brl)
        purchaseInfo.reviewAction = .continue
        
        visaCheckoutButton.onCheckout(purchaseInfo: purchaseInfo) {[weak self] result in
            switch result.statusCode {
            case .success:
                print("Encrypted key: \(String(describing: result.encryptedKey))")
                print("Payment data: \(String(describing: result.encryptedPaymentData))")
                
                self?.insertCredit()
                
            case .userCancelled:
                print("Payment cancelled by the user")
            case .internalError:
                print("Error in VisaCheckoutButton")
                break
            default:
                break
            }
        }
        // Do any additional setup after loading the view, typically from a nib.

    }
    
    
    func updateUI()
    {
        if let event = notificationDict?["nomeEvento"]{
            self.event.text = event
        }
        
        if let value = notificationDict?["valor"]{
            self.value.text =  "R$".appending(value)
            self.currentValor = value
        }
        if let child = notificationDict?["filho"] {
            self.message.text = "\(child) precisa de"
        }
        
        if let idCartao = notificationDict?["numeroCartao"] {
            self.currentIdCartao = idCartao
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
        
    }

    

}

