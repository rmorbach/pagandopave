//
//  Credit.swift
//  PagandoPave
//
//  Created by Rodrigo Morbach on 26/08/17.
//  Copyright © 2017 Morbach Inc. All rights reserved.
//

import UIKit


class Payment: NSObject {
    public var cardExpirationMonth: String?
    public var cardExpirationYear: String?
    public var cardNumber: String?
    public var cardType: String?
    
    
    func dictionaryRepresentation()->[String:Any]
    {
        var dict = [String: Any]()
        dict["cardExpirationMonth"] = self.cardExpirationMonth
        dict["cardExpirationYear"] = self.cardExpirationYear
        dict["cardNumber"] = self.cardNumber
        dict["cardType"] = self.cardType
        return dict
    }
    
}

class Credit: NSObject {
    
    public var amount: String?
    public var currency: String?
    public var payment: Payment?
    
    
    func dictionaryRepresentation()->[String:Any]
    {
        var dict = [String: Any]()
        dict["amount"] = self.amount
        dict["currency"] = self.currency
        dict["payment"] = self.payment?.dictionaryRepresentation()        
        return dict
    }

}
