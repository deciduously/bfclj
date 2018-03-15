(ns bfclj.parse
  (:require [bfclj.op :as op]
            [clojure.string :refer [split]]))

;; Build a machine, with full tape

;; In here, we care about how many times we go up and down
;; Filter for just inc-current and dec-current

(def fresh {:tape [0]
            :current 0})

(defn bf-ops
  "Mapping from BF character instruction to operation"
  [c]
  (condp = c
    "+" op/inc-cell
    "-" op/dec-cell
    "." op/out-cell
    ">" op/inc-current
    "<" op/dec-current
    "[" op/loop-open
    "]" op/loop-close
    "\n" op/terminate-program))

(defn get-ops
  "Get a list of ops from a list of tokens"
  [s]
  (map bf-ops (split s #"")))


;; I don't think I need this - get the tape size below
;(def grow-tape-if-edge
;  #(if (= (count (:tape %)) (inc (:current %)))
;     (assoc-in % [:tape] (conj (:tape %) 0))
;     (identity %)))

(def is-pointer-op? #(or (= % \>) (= % \<)))

(defn parse-bf
  "Return a machine from a validated BF string"
  [s]
  (let [tape-size (->> s
                       (filter is-pointer-op?)
                       (reduce #(if (= %2 \>) (inc %) (dec %)) 0))]
    {:tape (vec (repeat tape-size 0)) :current 0 :offset nil}))
