(ns bfclj.test.op-test
  (:require [bfclj.machine :refer [fresh]]
            [bfclj.op :as op]
            [clojure.test :refer [deftest is run-tests testing]]))

(deftest inc-cell-test
  (testing "Fresh machine"
    (is (= {:tape [1] :current 0} (op/inc-cell fresh))))
  (testing "Bigger tape"
    (is (= {:tape [1 1] :current 0} (op/inc-cell {:tape [0 1] :current 0})))))

(deftest dec-cell-test
  (testing "Fresh machine"
    (is (= {:tape [-1] :current 0} (op/dec-cell fresh))))
  (testing "Bigger tape"
    (is (= {:tape [0 0] :current 1} (op/dec-cell {:tape [0 1] :current 1})))))

(deftest inc-current-test
  (testing "Fresh machine"
    (is (= {:tape [0 0] :current 1} {:tape [0 0] :current 1})))
  (testing "Bigger tape"
    (is (= {:tape [0 1] :current 1} (op/inc-current {:tape [0 1] :current 0})))))

(deftest dec-current-test
  (is (= {:tape [0 0] :current 0} (op/dec-current {:tape [0 0] :current 1}))))

;; TODO find out how to compose these
;(deftest op-test
;  (inc-cell-test)
;  (dec-cell-test)
;  (inc-current-test)
;  (dec-current-test))
