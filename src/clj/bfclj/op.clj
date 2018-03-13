(ns bfclj.op
  (:require [com.rpl.specter :as sp]))

(def inc-cell #(sp/transform [:tape (:current %)] inc %))

(def grow-tape-if-edge
  #(if (= (count (:tape %)) (inc (:current %)))
     (assoc-in % [:tape] (conj (:tape %) 0))))

(def inc-current #(update-in (grow-tape-if-edge %) [:current] inc))

(def out-cell #(prn (sp/select [:tape (:current %)] %)))
