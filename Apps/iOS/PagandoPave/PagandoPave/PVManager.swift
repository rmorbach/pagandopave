//
//  CheckoutManager.swift
//  PagandoPave
//
//  Created by Rodrigo Morbach on 26/08/17.
//  Copyright © 2017 Morbach Inc. All rights reserved.
//

import UIKit

class PVManager: NSObject {

    public static let sharedManager = PVManager()
    
    func notifySale(credit: NotifyCredit, completionHandler: @escaping (Bool?, Error?) -> Swift.Void)
    {
        
        guard let url = URL(string: PVConfig.NOTIFY_RECHARGE_SERVICE_ENDPOINT) else{return}
        
        let creditPayload = credit.dictionaryRepresentation()
        do
        {
            let notifyCreditData = try JSONSerialization.data(withJSONObject: creditPayload, options: .prettyPrinted)
            
            Network.post(url: url, contentType: "application/json", accept: "application/json", payload: notifyCreditData) { data, response, error in
                if error != nil
                {
                    completionHandler(false, error)
                    return
                }
                if response?.statusCode == 200
                {
                    do{
                        let responseDict = try JSONSerialization.jsonObject(with: data!, options: []) as? [String: Any]
                        //TODO
                        completionHandler(true, error)
                        
                    }catch{
                       completionHandler(false, error)
                    }
                }else{
                    completionHandler(false, error)
                }
            }
            
        }catch {
            
        }

    }

    func registerPushId(rPush: RegisterPush, completionHandler: @escaping (Bool?, Error?) -> Swift.Void)
    {
        
        
        guard let url = URL(string: PVConfig.REGISTER_TOKEN_SERVICE_ENDPOINT) else{return}
        
        let payloadDict = rPush.dictionaryRepresentation()
        
        do
        {
            let payloadData = try JSONSerialization.data(withJSONObject: payloadDict, options: .prettyPrinted)
            
            Network.post(url: url, contentType: "application/json", accept: "application/json", payload: payloadData) { data, response, error in
                if error != nil
                {
                    completionHandler(false, error)
                    return
                }
                if response?.statusCode == 200
                {
                    do{
                        let responseDict = try JSONSerialization.jsonObject(with: data!, options: []) as? [String: Any]
                        //TODO
                        completionHandler(true, error)
                        
                    }catch{
                        completionHandler(false, error)
                    }
                }else{
                    completionHandler(false, error)
                }
            }
            
        }catch {
            
        }
        
    }

    
    
}
