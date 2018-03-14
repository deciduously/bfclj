(ns bfclj.core
  (:require [bfclj.op :as op]
            [bfclj.read :as read]
            [bfclj.machine :as machine]
            [clojure.string :refer [split]]
            [com.rpl.specter :as sp]))

(def simple "+++[-].")

(defn run
  "Run!"
  [program]
  (loop [ops (read/read-bf program)
         machine machine/fresh]
    (prn machine) ; for debug purposes
    (if (empty? ops)
      "Done!"
      (recur (rest ops) ((first ops) machine)))))

(defn -main
  [prog & args]
  (run prog))
