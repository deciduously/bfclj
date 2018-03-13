(ns bfclj.core
  (:require [bfclj.op :as op]
            [bfclj.machine :as machine]
            [clojure.string :refer [split]]
            [com.rpl.specter :as sp]))

(defn parse-bf
  "Mapping from BF character instruction to operation"
  [c]
  (condp = c
    "+" op/inc-cell
    "-" op/dec-cell
    "." op/out-cell
    ">" op/inc-current))

(defn read-bf
  "Read a BF string"
  [s]
  (map parse-bf (split s #"")))

(def simple "+++>+++-.")

(defn run
  "Run!"
  [program]
  (loop [ops (read-bf program)
         machine machine/fresh]
    (prn machine) ; for debug purposes
    (if (empty? ops)
      "Done!"
      (recur (rest ops) ((first ops) machine)))))
