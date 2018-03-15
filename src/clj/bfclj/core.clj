(ns bfclj.core
  (:require [bfclj.op :as op]
            [bfclj.read :as read]
            [bfclj.parse :as parse]
            [bfclj.test.programs :refer [programs]]
            [clojure.string :refer [split]]
            [com.rpl.specter :as sp]))

(defn run
  "Run!"
  [program]
  (if (read/valid-program? program) ; switch to try/catch?
    (loop [machine (parse/parse-bf program)]
      (prn machine) ; for debug purposes
      (if (empty? ops)
        "Done!"
        (recur (rest ops) ((first ops) machine))))
    (prn (str "Not a valid program: " program))))

(defn -main
  [prog & args]
  (condp = prog
    "hello-world" (run (:hello-world programs))
    :otherwise (run prog)))
