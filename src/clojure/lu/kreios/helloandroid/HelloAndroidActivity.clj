(ns lu.kreios.helloandroid.HelloAndroidActivity
  (:gen-class :extends android.app.Activity
              :main false
              :exposes-methods {onCreate superOnCreate})
  (:import [lu.kreios.helloandroid R$id R$layout]
           [android.view View$OnClickListener]
           [android.app AlertDialog$Builder]))

(defn -onCreate
  "Called when the activity is initialised."
  [activity bundle]
  (doto activity
    (.superOnCreate bundle)
    (.setContentView R$layout/main))
  
  (let [button (.findViewById activity R$id/goButton)
        textfield (.findViewById activity R$id/nameField)]
    (.setOnClickListener button 
      (proxy [View$OnClickListener] []
        (onClick [view]
                 (-> (AlertDialog$Builder. activity)
                   (.setTitle "Welcome")
                   (.setMessage (str "Hello " 
                                     (.getText textfield) 
                                     ". Welcome to my first Cojure-Android app, ever!"))
                   (.create)
                   (.show)))))))