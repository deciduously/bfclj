(ns bfclj.test.read-test
  (:require [bfclj.read :as r]
            [bfclj.test.programs :refer [hello-world]]
            [clojure.test :refer [deftest is run-tests testing]]))

(deftest ends-with-newline?-test
  (testing "Has one"
    (is (r/ends-with-newline? "....\n")))
  (testing "Doesn't have one"
    (is (not (r/ends-with-newline? "..."))))
  (testing "Hello, world!"
    (is (r/ends-with-newline? hello-world))))

(deftest valid-tokens?-test
  (testing "One of each"
    (is (r/valid-tokens? "+-[],.<>\n")))
  (testing "One invalid token?"
    (is (not (r/valid-tokens? "+-[],;.<>\n"))))
  (testing "Hello, world!"
    (is (r/valid-tokens? hello-world))))

(deftest valid-loops-test?
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

(deftest valid-program?-test
  (testing "Simple program"
    (is (r/valid-program? "++[.].\n")))
  (testing "No newline"
    (is (not (r/valid-program? ".."))))
  (testing "Hello, world!"
    (is (r/valid-program? hello-world))))

;; TODO find out how to compose these
;(deftest read-test
;  (ends-with-newline?-test)
;  (valid-tokens?-test)
;  (valid-loops-test?)
;  (valid-program?-test))
