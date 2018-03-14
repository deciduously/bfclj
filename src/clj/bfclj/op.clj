(ns bfclj.op
  (:require [com.rpl.specter :as sp]))

(def inc-cell #(sp/transform [:tape (:current %)] inc %))

(def dec-cell #(sp/transform [:tape (:current %)] dec %))

(def inc-current #(update-in % [:current] inc))

(def dec-current #(update-in % [:current] dec))

(def out-cell #(do (prn (nth (:tape %) (:current %))) %))

(def loop-open #(if (zero? (nth (:tape %) (:current %)))
                  (do
                    (assoc-in % [:current] (+ (:offset %) (:current %)))
                    (assoc-in % [:offset] nil))
                  (inc-current %)))

(def loop-close #()) ;;similar here

(def terminate-program #(prn "TERMINATED"))
