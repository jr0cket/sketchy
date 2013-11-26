(ns sketchy.client
  (:require [dommy.core :as d]
            [chord.client :refer [ws-ch]]
            [cljs.core.async :refer [<! >! put! close! chan map<]]
            [cljs.reader :refer [read-string]])
  (:require-macros [dommy.macros :refer [node sel1]]
                   [cljs.core.async.macros :refer [go go-loop]]))

(defn events [el type]
  (let [out (chan)]
    (d/listen! el type
      (fn [e]
        (put! out e)
        (.preventDefault e)))
    out))

(defn main []
  (let [mouse-move (events js/window "mousemove")]
    (go-loop []
      (js/console.log (<! mouse-move))
      (recur))))

(set! (.-onload js/window) main)

