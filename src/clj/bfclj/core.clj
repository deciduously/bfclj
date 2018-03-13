(ns bfclj.core
  (:require [bfclj.op :as op]
            [bfclj.machine :as machine]
            [clojure.string :refer [split]]
            [com.rpl.specter :as sp]))

(def simple "+++.")

(defn parse-bf
  "Mapping from BF character instruction to operation"
  [c]
  (condp = c
    "+" op/inc-cell
    "." op/out-cell))

(defn read-bf
  "Read a BF string"
  [s]
  (map parse-bf (split s #"")))

(def machine machine/fresh)

(defn run
  "Run!"
  [program]
  (println "p"))
