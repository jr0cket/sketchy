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

(defn e->v [e]
  [(.-x e) (.-y e)])

(defn draw-point [canvas [x y]]
  (let [context (.getContext canvas "2d")]
    (.fillRect context x y 10 10)))

(defn main []
  (let [canvas (sel1 :#canvas)
        move (map< e->v (events canvas "mousemove"))]
    ;; your part goes here!
    ))

(set! (.-onload js/window) main)
