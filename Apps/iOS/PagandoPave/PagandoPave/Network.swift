//
//  Network.swift
//  PagandoPave
//
//  Created by Rodrigo Morbach on 26/08/17.
//  Copyright © 2017 Morbach Inc. All rights reserved.
//

import UIKit

class Network: NSObject {

    
    private class func httpCall(request: NSMutableURLRequest, completion: @escaping (Data?, URLResponse?, Error?) -> Swift.Void)
    {
        let session = URLSession(configuration: .default, delegate: nil, delegateQueue: nil)
        
        let putTask = session.dataTask(with: request as URLRequest) {data, response, err in
            completion(data, response, err)
        }
        putTask.resume()
    }
    
    public class func post(url: URL, contentType: String?, accept: String, payload: Data?, completionHandler: @escaping (Data?, HTTPURLResponse?, Error?) -> Swift.Void)
    {
        let request = NSMutableURLRequest(url: url)
        request.httpMethod = "POST"
        request.setValue(contentType, forHTTPHeaderField: "Content-Type")
        request.setValue(accept, forHTTPHeaderField: "Accept")
        request.httpBody = payload
        httpCall(request: request) { data, response, error in
            completionHandler(data, response as? HTTPURLResponse, error)
        }
        
    }

    
}
