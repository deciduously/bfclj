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

;; TODO only allow a newline as the terminating character
(defn valid-tokens?
  "Check that all tokens are valid"
  [s]
  (let [valids #{\+ \- \[ \] \, \. \< \> \newline}]
    (reduce #(and % (contains? valids %2)) true s)))

(def is-bracket? (partial contains? #{\[ \]}))

(def just-brackets #(->> %
                         (partition-by is-bracket?)
                         (filter (comp is-bracket? first))
                         flatten))

(defn loops-valid?
  "Check for matching loop brackets"
  [s]
  (let [brackets (just-brackets s)]
    ;; If it's not even or zero, just stop
    (if (or (empty? brackets) (even? (count brackets)))
      (cond
        ;; We never start with an open
        (= \] (first brackets)) false
        ;; And otherwise closes will always equal opens
        (not (= (count (filter (partial = \[) brackets))
                (count (filter (partial = \]) brackets)))) false
        :otherwise true)
      false)))

(defn valid-program?
  [s]
  (let [validators [(ends-with-newline? s)
                    (valid-tokens? s)
                    (loops-valid? s)]]
    (reduce #(and % %2) true validators)))
