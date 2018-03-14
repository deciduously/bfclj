(ns bfclj.parse
  (:require [bfclj.op :as op]
            [clojure.string :refer [split]]))

(def fresh {:tape [0]
            :current 0})

(defn parse-bf
  "Mapping from BF character instruction to operation"
  [c]
  (condp = c
    \+ op/inc-cell
    \- op/dec-cell
    \. op/out-cell
    \> op/inc-current
    \< op/dec-current
    \[ op/loop-open
    \] op/loop-close
    \newline op/terminate-program))

(defn read-bf
  "Read a BF string"
  [s]
  (map parse-bf (split s #"")))

(def grow-tape-if-edge
  #(if (= (count (:tape %)) (inc (:current %)))
     (assoc-in % [:tape] (conj (:tape %) 0))
     (identity %)))
