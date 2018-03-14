(ns bfclj.test
  (:require [bfclj.test.op-test :refer :all]
            [bfclj.test.read-test :refer :all]
            [clojure.test :refer [deftest is run-tests testing]]))

;; TODO find out how to compose these
;(deftest bfclj-test
;  (testing "Testing bfclj.read"
;    (read-test))
;  (testing "Testing bfclj.op"
;    (op-test)))

;(defn test-ns-hook []
;  (bfclj-test))
