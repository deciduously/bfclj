(ns bfclj.test
  (:require [bfclj.test.op-test :refer [op-test]]
            [bfclj.test.read-test :refer [read-test]]
            [clojure.test :refer [deftest is run-tests testing]]))

(deftest bfclj-test
  (testing "Testing bfclj.read"
    (read-test))
  (testing "Testing bfclj.op"
    (op-test)))

(defn test-ns-hook []
  (bfclj-test))
