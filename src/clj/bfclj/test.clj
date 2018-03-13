(ns bfclj.test
  (:require [bfclj.op :as op]
            [bfclj.machine :refer [fresh]]
            [bfclj.test :as sut]
            [clojure.test :refer [deftest is run-tests]]))

(deftest test-inc-cell
  (is (= {:tape [1] :current 0} (op/inc-cell fresh)))
  (is (= {:tape [1 1] :current 0} (op/inc-cell {:tape [0 1] :current 0}))))

(deftest test-dec-cell
  (is (= {:tape [-1] :current 0} (op/dec-cell fresh)))
  (is (= {:tape [0 0] :current 1} (op/dec-cell {:tape [0 1] :current 1}))))

(deftest test-inc-current
  (is (= {:tape [0 0] :current 1} (op/inc-current fresh)))
  (is (= {:tape [0 1] :current 1} (op/inc-current {:tape [0 1] :current 0}))))
