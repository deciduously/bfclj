(set-env!
 :source-paths #{"src/clj" "test"}
 :dependencies '[[org.clojure/clojure "1.9.0"]
                 [adzerk/boot-test "1.2.0" :scope "test"]
                 [com.rpl/specter "1.1.0"]])

(task-options!
  aot {:namespace #{'bfclj.core}}
  pom {:project 'bfclj
       :description "Brainfuck intepreter"
       :license {"MIT" "https://github.com/deciduously/bfclj/blob/master/LICENSE"}
       :developers {"deciduously" "dev@deciduously.com"}
       :version "0.0.1"}
  jar  {:main 'bfclj.core
        :file "bfclj.jar"}
  sift {:include #{#"bfclj.jar"}})

(require '[bfclj.core :as b]
         'bfclj.test
         '[adzerk.boot-test :refer :all])

(deftask build
  "Builds a production uberjar"
  []
  (comp
   (aot)
   (pom)
   (uber)
   (jar)
   (sift)
   (target)))

;(deftask run
;  "Run interpreter"
;  []
;  (b/run))