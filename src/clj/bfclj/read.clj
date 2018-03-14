(ns bfclj.read
  (:require [bfclj.op :as op]
            [clojure.string :refer [split]]))

;; Read a string into a valid machine.

;; maybe I want to read into a machine first as I validate?
;; and then in a second pass apply the cell counts.

;; make sure the pointer does things that makes sense first.

;; These next two belong in parse
(defn parse-bf
  "Mapping from BF character instruction to operation"
  [c]
  (condp = c
    "+" op/inc-cell
    "-" op/dec-cell
    "." op/out-cell
    ">" op/inc-current
    "<" op/dec-current
    "[" op/loop-open
    "]" op/loop-close))

(defn read-bf
  "Read a BF string"
  [s]
  (map parse-bf (split s #"")))

(def grow-tape-if-edge
  #(if (= (count (:tape %)) (inc (:current %)))
     (assoc-in % [:tape] (conj (:tape %) 0))
     (identity %)))

(defn ends-with-newline?
  "Make sure it ends with a newline"
  [s]
  (= \newline (last s)))

(defn loops-valid?
  "Check for matching loop brackets"
  [s]
  (let [cs (split s #"")]
    (even? (count (filter #(or (= "[" %) (= "]" %)) cs)))))

(defn validate-program
  [s]
  (ends-with-newline? s))
