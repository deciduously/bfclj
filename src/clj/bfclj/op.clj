(ns bfclj.op
  (:require [com.rpl.specter :as sp]))

;; Change val
(def inc-cell #(sp/transform [:tape (:current %)] inc %))

(def dec-cell #(sp/transform [:tape (:current %)] dec %))

;; Change pointer
(def inc-current #(update-in % [:current] inc))

(def dec-current #(update-in % [:current] dec))

;; IO
(def out-cell #(do (prn (nth (:tape %) (:current %))) %))

;; Loop

(def jump #(-> %
               (assoc-in  [:current] (+ (:offset %) (:current %)))
               (assoc-in [:offset] nil)))

;(def set-offset-and-inc-current
;  (do
;    (assoc-in % [:offset] #(FIND OFFSET))
;    (inc-current %))

(def loop-open #(if (zero? (nth (:tape %) (:current %)))
                  jump
                  identity))
                  ;set-offset-and-inc-current))

(def loop-close #()) ;;similar here

(def terminate-program #(prn "TERMINATED"))
