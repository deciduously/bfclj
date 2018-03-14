(ns bfclj.test
  (:require [bfclj.core :refer [run]]
            [bfclj.test.op-test :refer :all]
            [bfclj.test.programs :refer [hello-world]]
            [bfclj.test.read-test :refer :all]
            [clojure.test :refer [deftest is run-tests testing]]))

(deftest bfclj-run-test
  (testing "Hello, world!"
    (= "Hello, world!" (run hello-world))))

;; TODO find out how to compose these
;(deftest bfclj-test
;  (testing "Testing bfclj.read"
;    (read-test))
;  (testing "Testing bfclj.op"
;    (op-test)))

;(defn test-ns-hook []
;  (bfclj-test))
