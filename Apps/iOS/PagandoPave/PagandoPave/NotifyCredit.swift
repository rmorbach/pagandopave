//
//  NotifyCredit.swift
//  PagandoPave
//
//  Created by Rodrigo Morbach on 26/08/17.
//  Copyright © 2017 Morbach Inc. All rights reserved.
//

import UIKit

class NotifyCredit: NSObject {

    public var idCartao: String?
    public var credito: String?
    
    public func dictionaryRepresentation()->[String: String]
    {
        var dict = [String: String]()
        
        dict["idCartao"] = self.idCartao
        dict["credito"] = self.credito
        
        return dict
    }
    
}
