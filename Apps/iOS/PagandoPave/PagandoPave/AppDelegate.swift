//
//  AppDelegate.swift
//  PagandoPave
//
//  Created by Rodrigo Morbach on 26/08/17.
//  Copyright © 2017 Morbach Inc. All rights reserved.
//

import UIKit
import Firebase
import VisaCheckoutSDK


@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {

    var window: UIWindow?
    
    let DEVICE_ID = "iphone_rodrigo"


    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplicationLaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization after application launch.
        
        
        FIRApp.configure()
        
        registerForNotification(application: application)
        
        configureVisaAPI()
    
        return true
    }
    
    
    /**
     MARK - VisaCheckout
    */
    
    func configureVisaAPI()
    {
        let profile = Profile(environment: .sandbox,
                              apiKey: VisaConfig.API_KEY)
        /// An arbitrary example of some configuration details you can customize.
        /// See the documentation/headers for `Profile`.
        profile.datalevel = .full
        profile.acceptedCardBrands = [.visa, .mastercard, .discover]
        
        VisaCheckoutSDK.configure(profile: profile)
    }
    
    /**
     * MARK - Notification
     */
    
    func registerForNotification(application: UIApplication)
    {
        let settings: UIUserNotificationSettings =
            UIUserNotificationSettings(types: [.alert, .badge, .sound], categories: nil)
        application.registerUserNotificationSettings(settings)
        application.registerForRemoteNotifications()
    }
    
   
    func application(_ application: UIApplication, didReceiveRemoteNotification userInfo: [AnyHashable : Any], fetchCompletionHandler completionHandler: @escaping (UIBackgroundFetchResult) -> Void) {
        print("didReceiveRemoteNotification")
        
    
        var notificationDict = [String: String]()
        guard let valor = userInfo["valor"] as? String else{
            return
        }
        notificationDict["valor"] = valor
        
        guard let filho = userInfo["filho"] as? String else{
            return
        }
        notificationDict["filho"] = filho
        
        guard let nomeEvento = userInfo["nomeEvento"] as? String else{
            return
        }
        notificationDict["nomeEvento"] = nomeEvento
        
        if let idCartao = userInfo["numeroCartao"] as? String{
             notificationDict["numeroCartao"] = idCartao
        }
        
        FIRMessaging.messaging().appDidReceiveMessage(userInfo)
        
        
        guard let nav = window?.rootViewController as? UINavigationController else{
            return
        }
        
        guard let vc = nav.topViewController as? ViewController else{
            return
        }
        vc.notificationDict = notificationDict
    }
    
    func application(_ application: UIApplication, didReceiveRemoteNotification userInfo: [AnyHashable : Any]) {
        print(userInfo)
    }
    
    func application(_ application: UIApplication, didRegister notificationSettings: UIUserNotificationSettings) {
        
    }
    
    func application(_ application: UIApplication, didRegisterForRemoteNotificationsWithDeviceToken deviceToken: Data) {
        //let deviceTokenString = deviceToken.reduce("", {$0 + String(format: "%02X", $1)})
        guard let deviceTokenString = FIRInstanceID.instanceID().token() else {
            return
        }
        let pvM = PVManager.sharedManager
        let rPush = RegisterPush()
        rPush.deviceId = DEVICE_ID
        rPush.idPai = "1"
        rPush.token = deviceTokenString
        
        pvM.registerPushId(rPush: rPush) { success, error in
            if(success != nil && success!){
                print("Push cadastrada com sucesso")
            }
        }
        print(deviceTokenString)
    }
    
    
    func application(_ application: UIApplication, performFetchWithCompletionHandler completionHandler: @escaping (UIBackgroundFetchResult) -> Void) {
        completionHandler(.newData)
    }
}

