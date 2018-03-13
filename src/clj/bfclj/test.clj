(ns bfclj.test
  (:require [bfclj.op :as op]
            [bfclj.read :as r]
            [bfclj.machine :refer [fresh]]
            [bfclj.test :as sut]
            [clojure.test :refer [deftest is run-tests testing]]))

(deftest test-inc-cell
  (testing "inc-cell"
    (testing "Fresh machine"
      (is (= {:tape [1] :current 0} (op/inc-cell fresh))))
    (testing "Bigger tape"
      (is (= {:tape [1 1] :current 0} (op/inc-cell {:tape [0 1] :current 0}))))))

(deftest test-dec-cell
  (testing "dec-cell"
    (testing "Fresh machine"
      (is (= {:tape [-1] :current 0} (op/dec-cell fresh))))
    (testing "Bigger tape"
      (is (= {:tape [0 0] :current 1} (op/dec-cell {:tape [0 1] :current 1}))))))

(deftest test-inc-current
  (testing "inc-current"
    (testing "Fresh machine"
      (is (= {:tape [0 0] :current 1} (op/inc-current fresh))))
    (testing "Bigger tape"
      (is (= {:tape [0 1] :current 1} (op/inc-current {:tape [0 1] :current 0}))))))

(deftest test-dec-current
  (is (= {:tape [0 0] :current 0} (op/dec-current {:tape [0 0] :current 1}))))

(deftest test-read
  (testing "Read"
    (testing "Ends with a newline?"
      (is (r/ends-with-newline? "....\n"))
      (is (not (r/ends-with-newline? "..."))))
    (testing "All valid tokens?"
      (testing "One of each"
        (is (r/valid-tokens "+-[],.<>\n")))
      (testing "One invalid token?"
        (is (not (r/valid-tokens "+-[],;.<>\n")))))
    (testing "Valid loops?"
      (testing "Correct loop"
        (is (r/loops-valid? "...[...]...\n")))
      (testing "Unmatched bracket"
        (is (not (r/loops-valid? "...[...\n"))))
      (testing "One correct, one unmatched"
        (is (not (r/loops-valid? "...[..].[..\n"))))
      (testing "Two correct"
        (is (r/loops-valid? "..[.]..[...].\n")))
      (testing "No loops"
        (is (r/loops-valid? "...\n"))))))
