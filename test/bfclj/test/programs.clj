(ns bfclj.test.programs
  (:require [clojure.edn :as edn]
            [clojure.java.io :refer [resource]]))

(def programs (-> "programs.edn"
                  resource
                  slurp
                  edn/read-string))

(def hello-world (:hello-world programs))
