(ns bfclj.test
  (:require [bfclj.op :as op]
            [bfclj.read :as r]
            [bfclj.machine :refer [fresh]]
            [bfclj.test :as sut]
            [clojure.test :refer [deftest is run-tests testing]]))

(def hello-world "++++++++[>++++[>++>+++>+++>+<<<<-]>+>+>->>+[<]<-]>>.>---.+++++++..+++.>>.<-.<.+++.------.--------.>>+.>++.\n")

(deftest test-op
  (testing "inc-cell"
    (testing "Fresh machine"
      (is (= {:tape [1] :current 0} (op/inc-cell fresh))))
    (testing "Bigger tape"
      (is (= {:tape [1 1] :current 0} (op/inc-cell {:tape [0 1] :current 0}))))
    (testing "dec-cell"
      (testing "Fresh machine"
        (is (= {:tape [-1] :current 0} (op/dec-cell fresh))))
      (testing "Bigger tape"
        (is (= {:tape [0 0] :current 1} (op/dec-cell {:tape [0 1] :current 1}))))))
  (testing "inc-current"
    (testing "Fresh machine"
      (is (= {:tape [0 0] :current 1} {:tape [0 0] :current 1})))
    (testing "Bigger tape"
      (is (= {:tape [0 1] :current 1} (op/inc-current {:tape [0 1] :current 0})))))
  (testing "dec-current"
    (is (= {:tape [0 0] :current 0} (op/dec-current {:tape [0 0] :current 1})))))

(deftest test-read
  (testing "Read"
    (testing "Ends with a newline?"
      (testing "Has one"
        (is (r/ends-with-newline? "....\n")))
      (testing "Doesn't have one"
        (is (not (r/ends-with-newline? "..."))))
      (testing "Hello, world!"
        (is (r/ends-with-newline? hello-world))))
   ; (testing "All valid tokens?"
   ;   (testing "One of each"
   ;     (is (r/valid-tokens? "+-[],.<>\n")))
   ;   (testing "One invalid token?"
   ;     (is (not (r/valid-tokens? "+-[],;.<>\n"))))
   ;   (testing "Hello, world!"
   ;     (is (r/valid-tokens? hello-world))))
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
        (is (r/loops-valid? "...\n")))
      (testing "Hello, world!"
        (is (r/loops-valid? hello-world))))
    (testing "All together now"
      (testing "Hello, world!"
        (is (r/valid-program? hello-world))))))

