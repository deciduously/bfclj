(ns bfclj.op
  (:require [com.rpl.specter :as sp]))

(def inc-cell #(sp/transform [:tape (:current %)] inc %))

(def dec-cell #(sp/transform [:tape (:current %)] dec %))

;; this should be done during reading.
;; make sure it's a valid machine.
;; pass a pre-checked full machine.


(def inc-current #(update-in % [:current] inc))

(def dec-current #(update-in % [:current] dec))

(def out-cell #(do (prn (nth (:tape %) (:current %))) %))

(def loop-open #(if (= 0 (nth (:tape %) (:current %)))
                  (do
                    (assoc-in % [:current] (+ (:offset %) (:current %)))
                    (assoc-in % [:offset] nil))
                  (inc-current %)))

(def loop-close #()) ;;similar here
