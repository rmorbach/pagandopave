//
//  RegisterPush.swift
//  PagandoPave
//
//  Created by Rodrigo Morbach on 26/08/17.
//  Copyright © 2017 Morbach Inc. All rights reserved.
//

import UIKit

class RegisterPush: NSObject {

    public var deviceId: String?
    
    public var idPai: String?
    
    public var token: String?
    
    func dictionaryRepresentation()->[String: String]
    {
        var dict = [String: String]()
        //dict["deviceId"] = self.deviceId
        dict["idPai"] = self.idPai
        dict["token"] = token
        return dict
    }
    
    
}
