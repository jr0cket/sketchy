(ns sketchy.handler
  (:require [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [compojure.handler :as handler]
            [hiccup.page :refer [html5 include-js]]
            [ring.middleware.reload :refer [wrap-reload]]
            [clojure.walk :refer [macroexpand-all]]
            [clojure.core.async :as a :refer [go go-loop >! <! put! chan]]
            [chord.http-kit :refer [with-channel]]))

(def counter (atom 0))

(defn index-view [n]
  (html5
   [:head
    (include-js "js/sketchy.js")]
   [:body
    [:canvas#canvas {:width 640 :height 480 :style "border: 1px solid black;"}]
    [:p (str "You're visitor number " n)]]))

(defroutes app-routes
  (GET "/" [] (index-view (swap! counter inc)))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app 
  (handler/site app-routes))
