//
//  CyberSourceManager.swift
//  PagandoPave
//
//  Created by Rodrigo Morbach on 26/08/17.
//  Copyright © 2017 Morbach Inc. All rights reserved.
//

import UIKit

class CyberSourceManager: NSObject {
    
    
  
    func createCredit(card: String, completionHandler: @escaping (Bool?, Error?) -> Swift.Void)
    {
        guard let url = URL(string: VisaConfig.CREATE_CREDIT_ENDPOINT) else{
            completionHandler(false, nil)
            return
        }
        
        Network.post(url: url, contentType: "application/json", accept: "application/json", payload: nil) { data, response, error in
            
        }
        
    }
    
}
