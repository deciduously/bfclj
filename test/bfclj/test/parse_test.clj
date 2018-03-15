(ns bfclj.test.parse-test
  (:require [bfclj.parse :as parse]
            [bfclj.op :as op]
            [clojure.test :refer [deftest is run-tests testing]]))

(deftest parse-bf-test
  (testing "Very simple"
    (is (= {:tape [0 0 0] :current 0 :offset nil}
           (parse/parse-bf "++.>+++>.+++->+.\n")))))

