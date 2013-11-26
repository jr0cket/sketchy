(ns sketchy.client
  (:require [dommy.core :as d]
            [chord.client :refer [ws-ch]]
            [cljs.core.async :refer [<! >! put! close! chan map<]]
            [cljs.reader :refer [read-string]])
  (:require-macros [dommy.macros :refer [node sel1]]
                   [cljs.core.async.macros :refer [go go-loop]]))


