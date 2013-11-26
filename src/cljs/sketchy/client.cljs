(ns sketchy.client
  (:require [dommy.core :as dommy]
            [chord.client :refer [ws-ch]]
            [cljs.core.async :refer [<! >! put! close! chan map<]]
            [cljs.reader :refer [read-string]])
  (:require-macros [dommy.macros :refer [node sel1]]
                   [cljs.core.async.macros :refer [go go-loop]]))

(defn events [el type]
  (let [out (chan)]
    (.addEventListener el type
                       (fn [e] (put! out e)))
    out))

(defn e->v [e]
  [(.-x e) (.-y e)])

(defn draw-point [context [x y]]
  (.fillRect context x y 10 10))

(defn main []
  (let [move (map< e->v (events js/window "mousemove"))
        down (events js/window "mousedown")
        up (events js/window "mouseup")
        canvas (sel1 :#canvas)
        context (.getContext canvas "2d")]
    (go
     (let [ws (<! (ws-ch "ws://localhost:3000/ws"))]
       (go (while true
             (draw-point context (read-string (:message (<! ws))))))
       (go-loop [draw-point? false]
                (let [[v sc] (alts! [move down up])]
                  (condp = sc
                    down (recur true)
                    up (recur false)
                    move (do (when draw-point?
                               (js/console.log (pr-str v))
                               (>! ws v))
                             (recur draw-point?)))))))))
